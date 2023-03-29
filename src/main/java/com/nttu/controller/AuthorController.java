package com.nttu.controller;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.nttu.bean.Author;
import com.nttu.model.AuthorService;

@Controller
@RequestMapping(value = "/admin/author")
public class AuthorController extends Operator{

	@Resource(name = "authorService")
	private AuthorService authorService;
	
	@RequestMapping(params = "list", method = RequestMethod.GET)
	public String list(HttpServletRequest req) {
		req.setAttribute("content", "author");
		req.setAttribute("parentItem", "Quản lý đối tác");
		req.setAttribute("childItem", "Tác giả");
		req.setAttribute("list", authorService.getAll());
		return "/admin/tables-data";
	}
	
	@RequestMapping(params = "create", method = RequestMethod.GET)
	public String create(HttpServletRequest req) {
		req.setAttribute("menuItem", "Quản lý đối tác");
		req.setAttribute("formName", "tác giả");
		return "/admin/form-insert-data";
	}
	
	@RequestMapping(params = "create", method = RequestMethod.POST)
	public String create(HttpServletRequest req, ModelMap modelMap) throws UnsupportedEncodingException {
		Author author = new Author();
		author.setAuthorName(convertUTF8(req.getParameter("allName")));
		authorService.create(author);
		return "redirect:/admin/author?list";
	}
	
	@RequestMapping(params = "update", method = RequestMethod.GET)
	public String update(HttpServletRequest req) {
		req.setAttribute("menuItem", "Quản lý đối tác");
		req.setAttribute("formName", "tác giả");
		req.setAttribute("allID", req.getParameter("authorID"));
		req.setAttribute("allName", authorService.findByID(Integer.parseInt(req.getParameter("authorID"))).getAuthorName());
		return "/admin/form-insert-data";
	}
	
	@RequestMapping(params = "update", method = RequestMethod.POST)
	public String update(HttpServletRequest req, ModelMap modelMap) throws UnsupportedEncodingException {
		Author author = new Author();
		author.setAuthorID(Integer.parseInt(req.getParameter("allID")));
		author.setAuthorName(convertUTF8(req.getParameter("allName")));
		authorService.update(author);
		return "redirect:/admin/author?list";
	}
	
	@RequestMapping(params = "delete", method = RequestMethod.GET)
	public String delete(HttpServletRequest req) {
		authorService.deleteByID(Integer.parseInt(req.getParameter("authorID")));
		return "redirect:/admin/author?list";
	}
}


	

