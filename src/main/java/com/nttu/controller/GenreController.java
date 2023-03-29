package com.nttu.controller;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.nttu.bean.Genre;
import com.nttu.model.GenreService;

@Controller
@RequestMapping(value = "/admin/genre")
public class GenreController extends Operator{
	
	@Resource(name = "genreService")
	private GenreService genreService;
	
	@RequestMapping(params = "list", method = RequestMethod.GET)
	public String list(HttpServletRequest req) {
		req.setAttribute("content", "genre");
		req.setAttribute("parentItem", "Quản lý danh mục");
		req.setAttribute("childItem", "thể loại sách");
		req.setAttribute("list", genreService.getAll());
		return "/admin/tables-data";
	}
	
	@RequestMapping(params = "create", method = RequestMethod.GET)
	public String create(HttpServletRequest req) {
		req.setAttribute("menuItem", "Quản lý danh mục");
		req.setAttribute("formName", "thể loại");
		return "/admin/form-insert-data";
	}
	
	@RequestMapping(params = "create", method = RequestMethod.POST)
	public String create(HttpServletRequest req, ModelMap modelMap) throws UnsupportedEncodingException {
		Genre genre = new Genre();
		req.setCharacterEncoding("UTF-8");
		genre.setGenreName(convertUTF8(req.getParameter("allName")));
		genreService.create(genre);
		return "redirect:/admin/genre?list";
	}
	
	@RequestMapping(params = "update", method = RequestMethod.GET)
	public String update(HttpServletRequest req) {
		req.setAttribute("menuItem", "Quản lý danh mục");
		req.setAttribute("formName", "thể loại");
		req.setAttribute("allID", req.getParameter("genreID"));
		req.setAttribute("allName", genreService.findByID(Integer.parseInt(req.getParameter("genreID"))).getGenreName());
		return "/admin/form-insert-data";
	}
	
	@RequestMapping(params = "update", method = RequestMethod.POST)
	public String update(HttpServletRequest req, ModelMap modelMap) throws UnsupportedEncodingException {
		Genre genre = new Genre();
		genre.setGenreID(Integer.parseInt(req.getParameter("allID")));
		genre.setGenreName(convertUTF8(req.getParameter("allName")));
		genreService.update(genre);
		return "redirect:/admin/genre?list";
	}
	
	@RequestMapping(params = "delete", method = RequestMethod.GET)
	public String delete(HttpServletRequest req) {
		genreService.deleteByID(Integer.parseInt(req.getParameter("genreID")));
		return "redirect:/admin/genre?list";
	}
}
