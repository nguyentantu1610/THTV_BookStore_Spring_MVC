package com.nttu.controller;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.nttu.bean.Bill;
import com.nttu.bean.Cart;
import com.nttu.bean.Product;
import com.nttu.bean.User;
import com.nttu.model.BillService;
import com.nttu.model.CategoryService;
import com.nttu.model.ProductService;
import com.nttu.model.UserService;

@Controller
@RequestMapping("/")
public class BillController {

	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "categoryService")
	private CategoryService categoryService;

	@Resource(name = "productService")
	private ProductService productService;

	@Resource(name = "billService")
	private BillService billService;

	@RequestMapping(value = "cart", params = "list", method = RequestMethod.GET)
	public String cart(HttpServletRequest req) {
		req.setAttribute("listCategory", categoryService.getAll());
		return "cart";
	}

	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@RequestMapping(value = "cart", params = "add", method = RequestMethod.GET)
	public void cart(HttpSession session, @RequestParam int productID, @RequestParam(defaultValue = "1") int quantity) {
		Product product = productService.findByID(productID);
		if (product.getProductID() != 0) {
			List<Cart> carts = Cart.getCart();
			if (carts.size() == 0) {
				Cart cart = new Cart(product.getProductID(), product.getProductImage().get(0), product.getProductName(),
						product.getProductCost(), quantity);
				carts.add(cart);
			} else {
				for (int i = 0; i < carts.size(); i++) {
					if (carts.get(i).getProductID() == product.getProductID()) {
						carts.get(i).setQuantity(carts.get(i).getQuantity() + quantity);
						break;
					}
					if (i == carts.size() - 1) {
						Cart cart = new Cart(product.getProductID(), product.getProductImage().get(0),
								product.getProductName(), product.getProductCost(), quantity);
						carts.add(cart);
						break;
					}
				}
			}
			session.setAttribute("carts", carts);
		}
	}

	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@RequestMapping(value = "cart", params = "add", method = RequestMethod.POST)
	public void cart(HttpSession session, HttpServletRequest req) {
		Product product = productService.findByID(Integer.parseInt(req.getParameter("productID")));
		if (product.getProductID() != 0) {
			int quantity = Integer.parseInt(req.getParameter("quantity"));
			List<Cart> carts = Cart.getCart();
			if (carts.size() == 0) {
				Cart cart = new Cart(product.getProductID(), product.getProductImage().get(0), product.getProductName(),
						product.getProductCost(), quantity);
				carts.add(cart);
			} else {
				for (int i = 0; i < carts.size(); i++) {
					if (carts.get(i).getProductID() == product.getProductID()) {
						if ((carts.get(i).getQuantity() + quantity) > product.getProductStock()) {
							carts.get(i).setQuantity(product.getProductStock());
							break;
						} else {
							carts.get(i).setQuantity(carts.get(i).getQuantity() + quantity);
							break;
						}
					}
					if (i == carts.size() - 1) {
						Cart cart = new Cart(product.getProductID(), product.getProductImage().get(0),
								product.getProductName(), product.getProductCost(), quantity);
						carts.add(cart);
						break;
					}
				}
			}
			session.setAttribute("carts", carts);
		}
	}

	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@RequestMapping(value = "cart", params = "change", method = RequestMethod.POST)
	public void change(HttpSession session, HttpServletRequest req) {
		Product product = productService.findByID(Integer.parseInt(req.getParameter("productID")));
		if (product.getProductID() != 0) {
			int quantity = Integer.parseInt(req.getParameter("quantity"));
			List<Cart> carts = Cart.getCart();
			for (int i = 0; i < carts.size(); i++) {
				if (carts.get(i).getProductID() == product.getProductID()) {
					if (quantity > product.getProductStock()) {
						carts.get(i).setQuantity(product.getProductStock());
					} else {
						carts.get(i).setQuantity(quantity);
					}
					break;
				}
			}
			session.setAttribute("carts", carts);
		}
	}

	@RequestMapping(value = "cart", params = "delete", method = RequestMethod.GET)
	public String delete(HttpSession session, HttpServletRequest req) {
		Cart.getCart().clear();
		req.setAttribute("listCategory", categoryService.getAll());
		return "cart";
	}

	@RequestMapping(value = "cart", params = "checkout", method = RequestMethod.GET)
	public String checkout(HttpSession session, HttpServletRequest req) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUserName = null;
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			currentUserName = authentication.getName();
		}
		if (currentUserName != null) {
			com.nttu.bean.User user = userService.findByEmail(currentUserName);
			req.setAttribute("listCategory", categoryService.getAll());
			req.setAttribute("user", user);
			session.setAttribute("carts", Cart.getCart());
			return "checkout";
		} else {
			return "redirect:/signin";
		}
	}

	@RequestMapping(value = "cart", params = "checkout", method = RequestMethod.POST)
	public String checkout(HttpSession session, HttpServletRequest req, ModelMap map) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUserName = null;
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			currentUserName = authentication.getName();
		}
		if (currentUserName != null) {
			com.nttu.bean.User user = userService.findByEmail(currentUserName);
			com.nttu.bean.User user1 = new User();
			user1.setUserID(user.getUserID());
			user1.setUserAddress(req.getParameter("userAddress"));
			user1.setUserPhoneNumber(Integer.parseInt(req.getParameter("userPhoneNumber")));
			billService.create(user1);
			return "redirect:/";
		} else {
			return "redirect:/signin";
		}
	}

	@RequestMapping(value = "bill", method = RequestMethod.GET)
	public String bill(HttpServletRequest req, ModelMap map) {
		req.setAttribute("categories", categoryService.getAll());
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUserName = null;
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			currentUserName = authentication.getName();
		}
		if (currentUserName != null) {
			com.nttu.bean.User user = userService.findByEmail(currentUserName);
			req.setAttribute("bills", billService.getAll2(user.getUserID()));
		}
		return "bill";
	}

	@SuppressWarnings("static-access")
	@RequestMapping(value = "billDetail", method = RequestMethod.GET)
	public String billDetail(HttpServletRequest req, ModelMap map) {
		req.setAttribute("categories", categoryService.getAll());
		req.setAttribute("bill", billService.findByID(Integer.parseInt(req.getParameter("billID"))));
		req.setAttribute("billDetails", billService.billDetails);
		return "bill-details";
	}

	@RequestMapping(value = "admin/bill", params = "list", method = RequestMethod.GET)
	public String bill(HttpServletRequest req) {
		req.setAttribute("content", "bill");
		req.setAttribute("parentItem", "Quản lý đơn hàng");
		req.setAttribute("childItem", "Đơn hàng");
		req.setAttribute("list", billService.getAll());
		return "/admin/tables-data";
	}

	@SuppressWarnings("static-access")
	@RequestMapping(value = "admin/bill", params = "update", method = RequestMethod.GET)
	public String updateBill(HttpServletRequest req) {
		req.setAttribute("menuItem", "Quản lý đơn hàng");
		req.setAttribute("formName", "đơn hàng");
		Bill bill = billService.findByID(Integer.parseInt(req.getParameter("billID")));
		req.setAttribute("bill", bill);
		req.setAttribute("billDetails", billService.billDetails);
		return "/admin/form-insert-data";
	}

	@SuppressWarnings("static-access")
	@RequestMapping(value = "admin/bill", params = "update", method = RequestMethod.POST)
	public String updateBill(HttpServletRequest req, ModelMap map) {
		Bill bill = billService.findByID(Integer.parseInt(req.getParameter("allID")));
		bill.setBillState(req.getParameter("billState"));
		bill.setBillNote(req.getParameter("billNote"));
		billService.update(bill);
		return "redirect:/admin/bill?list";
	}
}
