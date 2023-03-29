package com.nttu.controller;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.nttu.bean.Supplier;
import com.nttu.model.SupplierService;

@Controller
@RequestMapping(value = "/admin/supplier")
public class SupplierController extends Operator{

	@Resource(name = "supplierService")
	private SupplierService supplierService;
	
	@RequestMapping(params = "list", method = RequestMethod.GET)
	public String list(HttpServletRequest req) {
		req.setAttribute("content", "supplier");
		req.setAttribute("parentItem", "Quản lý đối tác");
		req.setAttribute("childItem", "nhà cung cấp");
		req.setAttribute("list", supplierService.getAll());
		return "/admin/tables-data";
	}
	
	@RequestMapping(params = "create", method = RequestMethod.GET)
	public String create(HttpServletRequest req) {
		req.setAttribute("menuItem", "Quản lý đối tác");
		req.setAttribute("formName", "nhà cung cấp");
		return "/admin/form-insert-data";
	}
	
	@RequestMapping(params = "create", method = RequestMethod.POST)
	public String create(HttpServletRequest req, ModelMap modelMap) throws UnsupportedEncodingException {
		Supplier supplier = new Supplier();
		supplier.setSupplierName(convertUTF8(req.getParameter("allName")));
		supplier.setSupplierPhoneNumber(Integer.parseInt(req.getParameter("supplierPhoneNumber")));
		supplier.setSupplierAddress(convertUTF8(req.getParameter("supplierAddress")));
		supplierService.create(supplier);
		return "redirect:/admin/supplier?list";
	}
	
	@RequestMapping(params = "update", method = RequestMethod.GET)
	public String update(HttpServletRequest req) {
		req.setAttribute("menuItem", "Quản lý đối tác");
		req.setAttribute("formName", "nhà cung cấp");
		req.setAttribute("allID", req.getParameter("supplierID"));
		Supplier supplier = supplierService.findByID(Integer.parseInt(req.getParameter("supplierID")));
		req.setAttribute("allName", supplier.getSupplierName());
		req.setAttribute("supplierPhoneNumber", supplier.getSupplierPhoneNumber());
		req.setAttribute("supplierAddress", supplier.getSupplierAddress());
		return "/admin/form-insert-data";
	}
	
	@RequestMapping(params = "update", method = RequestMethod.POST)
	public String update(HttpServletRequest req, ModelMap modelMap) throws UnsupportedEncodingException {
		Supplier supplier = new Supplier();
		supplier.setSupplierID(Integer.parseInt(req.getParameter("allID")));
		supplier.setSupplierName(convertUTF8(req.getParameter("allName")));
		supplier.setSupplierPhoneNumber(Integer.parseInt(req.getParameter("supplierPhoneNumber")));
		supplier.setSupplierAddress(convertUTF8(req.getParameter("supplierAddress")));
		supplierService.update(supplier);
		return "redirect:/admin/supplier?list";
	}
	
	@RequestMapping(params = "delete", method = RequestMethod.GET)
	public String delete(HttpServletRequest req) {
		supplierService.deleteByID(Integer.parseInt(req.getParameter("supplierID")));
		return "redirect:/admin/supplier?list";
	}
}
