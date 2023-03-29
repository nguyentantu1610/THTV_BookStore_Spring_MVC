package com.nttu.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.nttu.model.BillService;
import com.nttu.model.CategoryService;
import com.nttu.model.UserService;

@Controller
@RequestMapping("/")
public class AccountController extends Operator {

	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "categoryService")
	private CategoryService categoryService;

	@Resource(name = "billService")
	private BillService billService;

	private static int code;

	@RequestMapping(value = "signin", method = RequestMethod.GET)
	public String signIn() {
		return "signin";
	}

	@RequestMapping(value = "signin", method = RequestMethod.POST)
	public String signIn(HttpServletRequest req) {
		com.nttu.bean.User user = userService.findByEmail(req.getParameter("userEmail").toString());
		if (user.getUserID() != 0) {
			if (user.getUserEmail().equals(req.getParameter("userEmail"))
					&& BCrypt.checkpw(req.getParameter("userPassword"), user.getUserPassword())) {
				List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
				authorities.add(new SimpleGrantedAuthority(user.getUserRole()));
				UserDetails user1 = new User(user.getUserEmail(), user.getUserPassword(), authorities);
				Authentication authentication = new UsernamePasswordAuthenticationToken(user1, null, authorities);
				SecurityContextHolder.getContext().setAuthentication(authentication);
				if (user.getUserRole().equals("ROLE_ADMIN")) {
					return "redirect:/admin/admin";
				} else {
					return "redirect:/";
				}
			} else {
				return "redirect:/signin?error";
			}
		} else {
			return "redirect:/signin?error";
		}
	}

	@RequestMapping(value = "signup", method = RequestMethod.GET)
	public String signUp() {
		return "signup";
	}

	@RequestMapping(value = "signup", method = RequestMethod.POST)
	public String signUp(HttpServletRequest req) throws MessagingException {
		com.nttu.bean.User user = userService.findByEmail(req.getParameter("userEmail").toString());
		if (user.getUserID() == 0) {
			if (req.getParameter("userPassword").equals(req.getParameter("rePassword"))) {
				user.setUserEmail(req.getParameter("userEmail").toString());
				user.setUserPassword(req.getParameter("userPassword").toString());
				user.setUserName(req.getParameter("userName").toString());
				boolean check = userService.create(user);
				if (check == true) {

					String htmlMsg = "<div style=\"box-shadow: 0 10px 16px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19) !important;\">\r\n"
							+ "<p style=\"margin: 20px 0 20px 0;\">Kính gửi " + req.getParameter("userName").toString()
							+ ",</p>\r\n"
							+ "<p style=\"margin: 0 0 20px 0;\">Bạn đã thay đổi mật khẩu thành công,</p>\r\n"
							+ "<p style=\"margin: 0 0 30px 0;\">Truy cập http://localhost:6429/webcomic/signin để đăng nhập.</p>\r\n"
							+ "<p style=\"margin: 0 0 30px 0;\">Trân trọng,</p>\r\n"
							+ "<p style=\"margin: 0 0 30px 0;\">THTV - BookStore</p>\r\n" + "</div>";

					sendMailCustom(req.getParameter("userEmail").toString(), htmlMsg, "Đăng ký tài khoản thành công");

				}
				return "signin";
			} else {
				return "redirect:/signup?error";
			}
		} else {
			return "redirect:/signup?error";
		}
	}

	@RequestMapping(value = "forgotPassword", method = RequestMethod.GET)
	public String forgotPassword(HttpServletRequest req) {
		req.setAttribute("form1", "show");
		req.setAttribute("form2", "hide");
		req.setAttribute("form3", "hide");
		return "forgot-password";
	}

	@RequestMapping(value = "checkMail", method = RequestMethod.POST)
	public String checkMail(HttpServletRequest req) throws MessagingException {
		com.nttu.bean.User user = userService.findByEmail(req.getParameter("userEmail"));
		if (user.getUserID() != 0) {
			req.setAttribute("form1", "show");
			req.setAttribute("form2", "show");
			req.setAttribute("form3", "hide");
			req.setAttribute("userEmail", user.getUserEmail());
			Random random = new Random();
			code = random.nextInt(999999 - 100000) + 100000;
			String htmlMsg = "<div style=\"box-shadow: 0 10px 16px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19) !important;\">\r\n"
					+ "<p style=\"margin: 20px 0 20px 0;\">Kính gửi " + user.getUserName() + ",</p>\r\n"
					+ "<p style=\"margin: 0 0 20px 0;\">Mã xác thực: " + code + "</p>\r\n"
					+ "<p style=\"margin: 0 0 20px 0;\">Trân trọng,</p>\r\n"
					+ "<p style=\"margin: 0 0 20px 0;\">THTV - BookStore</p>\r\n" + "</div>";
			sendMailCustom(req.getParameter("userEmail").toString(), htmlMsg, "Mã xác minh");
			return "forgot-password";
		}
		return "redirect:/forgotPassword?error";
	}

	@RequestMapping(value = "checkCode", method = RequestMethod.POST)
	public String checkCode(HttpServletRequest req) throws MessagingException {
		if (Integer.parseInt(req.getParameter("code").toString()) == code) {
			req.setAttribute("form1", "hide");
			req.setAttribute("form2", "hide");
			req.setAttribute("form3", "show");
			req.setAttribute("userEmail", req.getParameter("userEmail"));
			return "forgot-password";
		}
		return "redirect:/forgotPassword?error";
	}

	@RequestMapping(value = "changePassword", method = RequestMethod.POST)
	public String changePassword(HttpServletRequest req) throws MessagingException {
		com.nttu.bean.User user = userService.findByEmail(req.getParameter("userEmail"));
		if (user.getUserID() != 0 && req.getParameter("userPassword").equals(req.getParameter("rePassword"))) {
			user.setUserPassword(req.getParameter("userPassword"));
			boolean check = userService.update(user, 0);
			if (check == true) {

				String htmlMsg = "<div style=\"box-shadow: 0 10px 16px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19) !important;\">\r\n"
						+ "<p style=\"margin: 20px 0 20px 0;\">Kính gửi" + user.getUserName() + ",</p>\r\n"
						+ "<p style=\"margin: 0 0 20px 0;\">Bạn đã thay đổi mật khẩu thành công,</p>\r\n"
						+ "<p style=\"margin: 0 0 30px 0;\">Truy cập http://localhost:6429/webcomic/signin để đăng nhập</p>\r\n"
						+ "<p style=\"margin: 0 0 30px 0;\">Trân trọng,</p>\r\n"
						+ "<p style=\"margin: 0 0 30px 0;\">THTV - BookStore</p>\r\n" + "</div>";

				sendMailCustom(req.getParameter("userEmail").toString(), htmlMsg, "Thay đổi mật khẩu thành công");
			}
			return "redirect:/signin";
		}
		return "redirect:/forgotPassword?error";
	}

	@RequestMapping(value = "account", method = RequestMethod.GET)
	public String account(HttpServletRequest req) {
		req.setAttribute("categories", categoryService.getAll());
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUserName = null;
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			currentUserName = authentication.getName();
		}
		if (currentUserName != null) {
			com.nttu.bean.User user = userService.findByEmail(currentUserName);
			if (user.getUserID() != 0) {
				req.setAttribute("user", user);
			}
		}
		return "account";
	}

	@RequestMapping(value = "account", method = RequestMethod.POST)
	public String account(HttpServletRequest req, ModelMap map) {

		com.nttu.bean.User user = userService.findByID(Integer.parseInt(req.getParameter("userID")));
		if (user.getUserID() != 0) {
			user.setUserAddress(req.getParameter("userAddress"));
			user.setUserPhoneNumber(Integer.parseInt(req.getParameter("userPhoneNumber")));
			userService.update(user, 1);

			req.setAttribute("user", user);
		}
		return "account";
	}

	@RequestMapping(value = "admin/user", params = "list", method = RequestMethod.GET)
	public String list(HttpServletRequest req) {
		req.setAttribute("content", "user");
		req.setAttribute("parentItem", "Quản lý người dùng");
		req.setAttribute("childItem", "người dùng");
		req.setAttribute("list", userService.getAll());
		return "/admin/tables-data";
	}

	@RequestMapping(value = "admin/user", params = "update", method = RequestMethod.GET)
	public String update(HttpServletRequest req) {
		req.setAttribute("menuItem", "Quản lý người dùng");
		req.setAttribute("formName", "người dùng");
		req.setAttribute("user", userService.findByID(Integer.parseInt(req.getParameter("userID"))));
		return "/admin/form-insert-data";
	}

	@RequestMapping(value = "admin/user", params = "update", method = RequestMethod.POST)
	public String update(HttpServletRequest req, ModelMap modelMap) throws UnsupportedEncodingException {
		com.nttu.bean.User user = userService.findByID(Integer.parseInt(req.getParameter("allID")));
		user.setUserState(Boolean.parseBoolean(req.getParameter("userState")));
		userService.update(user, 1);
		return "redirect:/admin/user?list";
	}
}
