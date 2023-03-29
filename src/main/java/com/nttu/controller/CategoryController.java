package com.nttu.controller;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.nttu.bean.Category;
import com.nttu.model.CategoryService;

@Controller
@RequestMapping(value = "/admin/category")
public class CategoryController extends Operator{
	
	@Resource(name = "categoryService")
	private CategoryService categoryService;
	
	@RequestMapping(params = "list", method = RequestMethod.GET)
	public String list(HttpServletRequest req) {
		req.setAttribute("content", "category");
		req.setAttribute("parentItem", "Quản lý danh mục");
		req.setAttribute("childItem", "danh mục sách");
		req.setAttribute("list", categoryService.getAll());
		return "/admin/tables-data";
	}
	
	@RequestMapping(params = "create", method = RequestMethod.GET)
	public String create(HttpServletRequest req) {
		req.setAttribute("menuItem", "Quản lý danh mục");
		req.setAttribute("formName", "danh mục");
		return "/admin/form-insert-data";
	}
	
	@RequestMapping(params = "create", method = RequestMethod.POST)
	public String create(HttpServletRequest req, ModelMap modelMap) throws UnsupportedEncodingException {
		Category category = new Category();
		category.setCategoryName(convertUTF8(req.getParameter("allName")));
		categoryService.create(category);
		return "redirect:/admin/category?list";
	}
	
	@RequestMapping(params = "update", method = RequestMethod.GET)
	public String update(HttpServletRequest req) {
		req.setAttribute("menuItem", "Quản lý danh mục");
		req.setAttribute("formName", "danh mục");
		req.setAttribute("allID", req.getParameter("categoryID"));
		req.setAttribute("allName", categoryService.findByID(Integer.parseInt(req.getParameter("categoryID"))).getCategoryName());
		return "/admin/form-insert-data";
	}
	
	@RequestMapping(params = "update", method = RequestMethod.POST)
	public String update(HttpServletRequest req, ModelMap modelMap) throws UnsupportedEncodingException {
		Category category = new Category();
		category.setCategoryID(Integer.parseInt(req.getParameter("allID")));
		category.setCategoryName(convertUTF8(req.getParameter("allName")));
		categoryService.update(category);
		return "redirect:/admin/category?list";
	}
	
	@RequestMapping(params = "delete", method = RequestMethod.GET)
	public String delete(HttpServletRequest req) {
		categoryService.deleteByID(Integer.parseInt(req.getParameter("categoryID")));
		return "redirect:/admin/category?list";
	}
}
