package com.nttu.controller;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.nttu.bean.Publisher;
import com.nttu.model.PublisherService;

@Controller
@RequestMapping(value = "/admin/publisher")
public class PublisherController extends Operator {

	@Resource(name = "publisherService")
	private PublisherService publisherService;
	
	@RequestMapping(params = "list", method = RequestMethod.GET)
	public String list(HttpServletRequest req) {
		req.setAttribute("content", "publisher");
		req.setAttribute("parentItem", "Quản lý đối tác");
		req.setAttribute("childItem", "nhà xuất bản");
		req.setAttribute("list", publisherService.getAll());
		return "/admin/tables-data";
	}
	
	@RequestMapping(params = "create", method = RequestMethod.GET)
	public String create(HttpServletRequest req) {
		req.setAttribute("menuItem", "Quản lý đối tác");
		req.setAttribute("formName", "nhà xuất bản");
		return "/admin/form-insert-data";
	}
	
	@RequestMapping(params = "create", method = RequestMethod.POST)
	public String create(HttpServletRequest req, ModelMap modelMap) throws UnsupportedEncodingException {
		Publisher publisher = new Publisher();
		publisher.setPublisherName(convertUTF8(req.getParameter("allName")));
		publisherService.create(publisher);
		return "redirect:/admin/publisher?list";
	}
	
	@RequestMapping(params = "update", method = RequestMethod.GET)
	public String update(HttpServletRequest req) {
		req.setAttribute("menuItem", "Quản lý đối tác");
		req.setAttribute("formName", "nhà xuất bản");
		req.setAttribute("allID", req.getParameter("publisherID"));
		req.setAttribute("allName", publisherService.findByID(Integer.parseInt(req.getParameter("publisherID"))).getPublisherName());
		return "/admin/form-insert-data";
	}
	
	@RequestMapping(params = "update", method = RequestMethod.POST)
	public String update(HttpServletRequest req, ModelMap modelMap) throws UnsupportedEncodingException {
		Publisher publisher = new Publisher();
		publisher.setPublisherID(Integer.parseInt(req.getParameter("allID")));
		publisher.setPublisherName(convertUTF8(req.getParameter("allName")));
		publisherService.update(publisher);
		return "redirect:/admin/publisher?list";
	}
	
	@RequestMapping(params = "delete", method = RequestMethod.GET)
	public String delete(HttpServletRequest req) {
		publisherService.deleteByID(Integer.parseInt(req.getParameter("publisherID")));
		return "redirect:/admin/publisher?list";
	}
}
