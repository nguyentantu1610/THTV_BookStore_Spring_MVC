package com.nttu.controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nttu.bean.Bill;
import com.nttu.bean.Product;
import com.nttu.model.AuthorService;
import com.nttu.model.BillService;
import com.nttu.model.CategoryService;
import com.nttu.model.GenreService;
import com.nttu.model.ProductService;
import com.nttu.model.PublisherService;
import com.nttu.model.SeriesService;
import com.nttu.model.SupplierService;
import com.nttu.model.UserService;

@Controller
@RequestMapping("/")
public class HomePageController extends Operator {

	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "categoryService")
	private CategoryService categoryService;

	@Resource(name = "productService")
	private ProductService productService;

	@Resource(name = "authorService")
	private AuthorService authorService;

	@Resource(name = "genreService")
	private GenreService genreService;

	@Resource(name = "publisherService")
	private PublisherService publisherService;

	@Resource(name = "seriesService")
	private SeriesService seriesService;

	@Resource(name = "supplierService")
	private SupplierService supplierService;

	@Resource(name = "billService")
	private BillService billService;

	@RequestMapping(value = "admin/admin", method = RequestMethod.GET)
	public String admin(HttpServletRequest req) {

		List<Bill> bills = billService.getAll();
		List<Bill> bills1 = new ArrayList<Bill>();
		int total = 0;
		int billCount = 0;
		for (Bill bill : bills) {
			total += bill.getBillTotal();
			if (bill.getBillState().equals("Chưa duyệt")) {
				bills1.add(bill);
				billCount++;
			}
		}
		req.setAttribute("billCount", billCount);
		req.setAttribute("total", total);
		req.setAttribute("bills", bills1);
		req.setAttribute("userCount", userService.getAll().size() - 1);
		return "admin/admin";
	}

	@SuppressWarnings("static-access")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(HttpServletRequest req, @RequestParam(defaultValue = "1") int pageNum,
			@RequestParam(defaultValue = "1") int pageSize,
			@RequestParam(defaultValue = "0", required = false) int categoryID) {
		req.setAttribute("categories", categoryService.getAll());
		if (categoryID != 0) {
			req.setAttribute("categoryID", categoryID);
		}
		List<Product> products = productService.getAll2(pageNum, pageSize, categoryID, null);
		int productCount = productService.getTotalRow(categoryID, null);
		int totalPages = (int) Math.ceil((double) productCount / pageSize);
		req.setAttribute("products", products);
		req.setAttribute("listCost", productService.listCost);
		req.setAttribute("currentPage", pageNum);
		req.setAttribute("totalPages", totalPages);
		return "index";
	}

	@SuppressWarnings("static-access")
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String index(HttpServletRequest req, @RequestParam(defaultValue = "1") int pageNum,
			@RequestParam(defaultValue = "1") int pageSize, @RequestParam(required = false) String search) {
		req.setAttribute("categories", categoryService.getAll());
		List<Product> products = productService.getAll2(pageNum, pageSize, 0, search);
		int productCount = productService.getTotalRow(0, search);
		int totalPages = (int) Math.ceil((double) productCount / pageSize);
		req.setAttribute("products", products);
		req.setAttribute("listCost", productService.listCost);
		req.setAttribute("currentPage", pageNum);
		req.setAttribute("totalPages", totalPages);
		req.setAttribute("search", search);
		return "index";
	}

	@SuppressWarnings("static-access")
	@RequestMapping(value = "productDetail", method = RequestMethod.GET)
	public String productDetail(@RequestParam int productID, HttpServletRequest req) {
		Product product = productService.findByID(productID);
		req.setAttribute("product", product);
		req.setAttribute("productCost", convertCost(product.getProductCost()));
		req.setAttribute("supplierName", supplierService.findByID(product.getSupplierID()).getSupplierName());
		req.setAttribute("publisherName", publisherService.findByID(product.getPublisherID()).getPublisherName());
		req.setAttribute("seriesName", seriesService.findByID(product.getSeriesID()).getSeriesName());

		List<Integer> listAuthor = product.getAuthorID();
		String authors = "";
		for (int i = 0; i < listAuthor.size(); i++) {
			authors += authorService.findByID(listAuthor.get(i)).getAuthorName() + ",";
		}
		req.setAttribute("authors", authors);

		List<Integer> listGenre = product.getAuthorID();
		String genres = "";
		for (int i = 0; i < listGenre.size(); i++) {
			genres += genreService.findByID(listGenre.get(i)).getGenreName() + ",";
		}
		req.setAttribute("genres", genres);
		req.setAttribute("products", productService.getAll2(1, 6, product.getCategoryID(), null));
		req.setAttribute("listCost", productService.listCost);
		req.setAttribute("categories", categoryService.getAll());

		return "product-details";
	}
}
