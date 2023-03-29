package com.nttu.controller;

import java.io.UnsupportedEncodingException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.nttu.bean.Series;
import com.nttu.model.SeriesService;

@Controller
@RequestMapping(value = "/admin/series")
public class SeriesController extends Operator{

	@Resource(name = "seriesService")
	private SeriesService seriesService;
	
	@RequestMapping(params = "list", method = RequestMethod.GET)
	public String list(HttpServletRequest req) {
		req.setAttribute("content", "series");
		req.setAttribute("parentItem", "Quản lý sản phẩm");
		req.setAttribute("childItem", "bộ sách");
		req.setAttribute("list", seriesService.getAll());
		return "/admin/tables-data";
	}
	
	@RequestMapping(params = "create", method = RequestMethod.GET)
	public String create(HttpServletRequest req) {
		req.setAttribute("menuItem", "Quản lý sản phẩm");
		req.setAttribute("formName", "bộ sách");
		return "/admin/form-insert-data";
	}
	
	@RequestMapping(params = "create", method = RequestMethod.POST)
	public String create(HttpServletRequest req, ModelMap modelMap) throws UnsupportedEncodingException {
		Series series = new Series();
		series.setSeriesName(convertUTF8(req.getParameter("allName")));
		seriesService.create(series);
		return "redirect:/admin/series?list";
	}
	
	@RequestMapping(params = "update", method = RequestMethod.GET)
	public String update(HttpServletRequest req) {
		req.setAttribute("menuItem", "Quản lý sản phẩm");
		req.setAttribute("formName", "bộ sách");
		req.setAttribute("allID", req.getParameter("seriesID"));
		req.setAttribute("allName", seriesService.findByID(Integer.parseInt(req.getParameter("seriesID"))).getSeriesName());
		return "/admin/form-insert-data";
	}
	
	@RequestMapping(params = "update", method = RequestMethod.POST)
	public String update(HttpServletRequest req, ModelMap modelMap) throws UnsupportedEncodingException {
		Series series = new Series();
		series.setSeriesID(Integer.parseInt(req.getParameter("allID")));
		series.setSeriesName(convertUTF8(req.getParameter("allName")));
		seriesService.update(series);
		return "redirect:/admin/series?list";
	}
	
	@RequestMapping(params = "delete", method = RequestMethod.GET)
	public String delete(HttpServletRequest req) {
		seriesService.deleteByID(Integer.parseInt(req.getParameter("seriesID")));
		return "redirect:/admin/series?list";
	}
}
