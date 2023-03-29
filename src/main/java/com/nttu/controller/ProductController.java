package com.nttu.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import com.nttu.bean.Product;
import com.nttu.model.AuthorService;
import com.nttu.model.CategoryService;
import com.nttu.model.GenreService;
import com.nttu.model.ProductService;
import com.nttu.model.PublisherService;
import com.nttu.model.SeriesService;
import com.nttu.model.SupplierService;

@Controller
@RequestMapping(value = "/admin/product")
public class ProductController extends Operator {

	@Resource(name = "productService")
	private ProductService productService;

	@Resource(name = "authorService")
	private AuthorService authorService;

	@Resource(name = "categoryService")
	private CategoryService categoryService;

	@Resource(name = "genreService")
	private GenreService genreService;

	@Resource(name = "publisherService")
	private PublisherService publisherService;

	@Resource(name = "seriesService")
	private SeriesService seriesService;

	@Resource(name = "supplierService")
	private SupplierService supplierService;

	@SuppressWarnings("static-access")
	@RequestMapping(params = "list", method = RequestMethod.GET)
	public String list(HttpServletRequest req) {
		req.setAttribute("content", "product");
		req.setAttribute("parentItem", "Quản lý sản phẩm");
		req.setAttribute("childItem", "sách");
		req.setAttribute("list", productService.getAll());
		req.setAttribute("listCost", productService.listCost);
		return "/admin/tables-data";
	}

	@RequestMapping(params = "create", method = RequestMethod.GET)
	public String create(HttpServletRequest req) {
		req.setAttribute("menuItem", "Quản lý sản phẩm");
		req.setAttribute("formName", "sách");
		req.setAttribute("listAuthor", authorService.getAll());
		req.setAttribute("listCategory", categoryService.getAll());
		req.setAttribute("listGenre", genreService.getAll());
		req.setAttribute("listPublisher", publisherService.getAll());
		req.setAttribute("listSeries", seriesService.getAll());
		req.setAttribute("listSupplier", supplierService.getAll());
		return "/admin/form-insert-data";
	}

	@RequestMapping(params = "create", method = RequestMethod.POST)
	public String create(HttpServletRequest req, ModelMap modelMap) throws UnsupportedEncodingException {

		String[] listGenre = req.getParameterValues("genreID");
		List<Integer> genreIDs = new ArrayList<Integer>();
		int i1 = 0;
		while (i1 < listGenre.length) {
			genreIDs.add(Integer.parseInt(listGenre[i1]));
			i1++;
		}

		String[] listAuthor = req.getParameterValues("authorID");
		List<Integer> authorIDs = new ArrayList<Integer>();
		int i2 = 0;
		while (i2 < listAuthor.length) {
			authorIDs.add(Integer.parseInt(listAuthor[i2]));
			i2++;
		}

		Product product = new Product();
		product.setCategoryID(Integer.parseInt(req.getParameter("categoryID")));
		product.setSeriesID(Integer.parseInt(req.getParameter("seriesID")));
		product.setSupplierID(Integer.parseInt(req.getParameter("supplierID")));
		product.setPublisherID(Integer.parseInt(req.getParameter("publisherID")));
		product.setGenreID(genreIDs);
		product.setAuthorID(authorIDs);
		product.setProductName(convertUTF8(req.getParameter("allName")));
		product.setProductLevel(convertUTF8(req.getParameter("productLevel")));
		product.setProductDescription(convertUTF8(req.getParameter("productDescription")));
		product.setProductYear(Integer.parseInt(req.getParameter("productYear")));
		product.setProductStock(Integer.parseInt(req.getParameter("productStock")));
		product.setProductCost(Integer.parseInt(req.getParameter("productCost")));

		productService.create(product);
		return "redirect:/admin/product?list";
	}

	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@RequestMapping(params = "excel", method = RequestMethod.GET)
	public void excel() throws IOException {

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Books");
		CellStyle cellStyle = sheet.getWorkbook().createCellStyle();

		Font font = sheet.getWorkbook().createFont();
		font.setBold(true);
		font.setFontHeightInPoints((short) 16);
		font.setColor(IndexedColors.RED.index);
		cellStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cellStyle.setFont(font);

		List<Product> products = productService.getAll();
		String[] cellTitle = { "productID", "productName", "categoryID", "genreID", "seriesID", "authorID",
				"supplierID", "publisherID", "productDescription", "productCost", "productYear", "productLevel",
				"productStock" };
		int i = -1, j = 0;

		while (i < products.size()) {

			i++;
			Row row = sheet.createRow(i);
			while (j < 13) {

				Cell cell = row.createCell(j);
				if (i == 0) {
					cell.setCellValue(cellTitle[j]);
					cell.setCellStyle(cellStyle);
				} else {
					int r = i - 1;
					System.out.print(r);
					switch (j) {
					case 0: {
						cell.setCellValue(products.get(r).getProductID());
						break;
					}
					case 1: {
						cell.setCellValue(products.get(r).getProductName());
						break;
					}
					case 2: {
						cell.setCellValue(products.get(r).getCategoryID());
						break;
					}
					case 3: {
						List<Integer> integers = products.get(r).getAuthorID();
						int t = 0;
						String genres = "";
						while (i < integers.size()) {
							genres = genres + integers.get(t) + ",";
							t++;
						}
						cell.setCellValue(genres);
						break;
					}
					case 4: {
						cell.setCellValue(products.get(r).getSeriesID());
						break;
					}
					case 5: {
						List<Integer> integers = products.get(r).getAuthorID();
						String authors = "";
						int t = 0;
						while (i < integers.size()) {
							authors = authors + integers.get(t) + ",";
							t++;
						}
						cell.setCellValue(authors);
						break;
					}
					case 6: {
						cell.setCellValue(products.get(r).getSupplierID());
						break;
					}
					case 7: {
						cell.setCellValue(products.get(r).getPublisherID());
						break;
					}
					case 8: {
						cell.setCellValue(products.get(r).getProductDescription());
						break;
					}
					case 9: {
						cell.setCellValue(products.get(r).getProductCost());
						break;
					}
					case 10: {
						cell.setCellValue(products.get(r).getProductYear());
						break;
					}
					case 11: {
						cell.setCellValue(products.get(r).getProductLevel());
						break;
					}
					case 12: {
						cell.setCellValue(products.get(r).getProductStock());
						break;
					}
					default:
						break;
					}
				}
				j++;
			}
			j = 0;
		}

		try (FileOutputStream outputStream = new FileOutputStream("C:/Users/ad/Downloads/Books.xlsx")) {
			workbook.write(outputStream);
			outputStream.close();
			workbook.close();
		}
	}

	@RequestMapping(params = "excel", method = RequestMethod.POST)
	public String excel(HttpServletRequest req, @RequestParam("file") MultipartFile file) throws IOException {

		InputStream inputStream = file.getInputStream();

		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

		XSSFSheet sheet = workbook.getSheetAt(0);

		Product product = new Product();

		int i = 1, j = 0;

		while (i <= sheet.getLastRowNum()) {
			Row row = sheet.getRow(i);

			System.out.println(row.getLastCellNum());

			System.out.println(sheet.getRow(0).getCell(0).toString());

			while (j < row.getLastCellNum()) {
				Cell cell = row.getCell(j);

				switch (sheet.getRow(0).getCell(j).toString()) {
				case "productName": {
					product.setProductName(cell.getStringCellValue());
					break;
				}
				case "categoryID": {
					product.setCategoryID((int) cell.getNumericCellValue());
					break;
				}
				case "genreID": {
					List<Integer> genreIDs = new ArrayList<Integer>();
					genreIDs.add((int) cell.getNumericCellValue());
					product.setGenreID(genreIDs);
					break;
				}
				case "seriesID": {
					product.setSeriesID((int) cell.getNumericCellValue());
					break;
				}
				case "authorID": {
					List<Integer> authorIDs = new ArrayList<Integer>();
					authorIDs.add((int) cell.getNumericCellValue());
					product.setAuthorID(authorIDs);
					break;
				}
				case "supplierID": {
					product.setSupplierID((int) cell.getNumericCellValue());
					break;
				}
				case "publisherID": {
					product.setPublisherID((int) cell.getNumericCellValue());
					break;
				}
				case "productDescription": {
					product.setProductDescription(cell.getStringCellValue());
					break;
				}
				case "productCost": {
					product.setProductCost((int) cell.getNumericCellValue());
					break;
				}
				case "productYear": {
					product.setProductYear((int) cell.getNumericCellValue());
					break;
				}
				case "productLevel": {
					product.setProductLevel(cell.toString());
					break;
				}
				case "productStock": {
					product.setProductStock((int) cell.getNumericCellValue());
					break;
				}
				default:
					break;
				}
				j++;
			}
			productService.create(product);
			j = 0;
			i++;
		}

		workbook.close();
		inputStream.close();

		return "redirect:/admin/product?list";
	}

	@RequestMapping(params = "update", method = RequestMethod.GET)
	public String update(HttpServletRequest req) {
		req.setAttribute("menuItem", "Quản lý sản phẩm");
		req.setAttribute("formName", "sách");
		req.setAttribute("allID", req.getParameter("productID"));
		Product product = productService.findByID(Integer.parseInt(req.getParameter("productID")));
		req.setAttribute("allName", product.getProductName());
		req.setAttribute("product", product);

		req.setAttribute("listAuthor", authorService.getAll());
		req.setAttribute("listCategory", categoryService.getAll());
		req.setAttribute("listGenre", genreService.getAll());
		req.setAttribute("listPublisher", publisherService.getAll());
		req.setAttribute("listSeries", seriesService.getAll());
		req.setAttribute("listSupplier", supplierService.getAll());

		req.setAttribute("categoryID", categoryService.findByID(product.getCategoryID()).getCategoryName());
		req.setAttribute("seriesID", seriesService.findByID(product.getSeriesID()).getSeriesName());
		req.setAttribute("publisherID", publisherService.findByID(product.getPublisherID()).getPublisherName());
		req.setAttribute("supplierID", supplierService.findByID(product.getSupplierID()).getSupplierName());

		return "/admin/form-insert-data";
	}

	@RequestMapping(params = "update", method = RequestMethod.POST)
	public String update(HttpServletRequest req, ModelMap modelMap) throws UnsupportedEncodingException {

		String[] listGenre = req.getParameterValues("genreID");
		List<Integer> genreIDs = new ArrayList<Integer>();
		int i1 = 0;
		while (i1 < listGenre.length) {
			genreIDs.add(Integer.parseInt(listGenre[i1]));
			i1++;
		}

		String[] listAuthor = req.getParameterValues("authorID");
		List<Integer> authorIDs = new ArrayList<Integer>();
		int i2 = 0;
		while (i2 < listAuthor.length) {
			authorIDs.add(Integer.parseInt(listAuthor[i2]));
			i2++;
		}

		Product product = productService.findByID(Integer.parseInt(req.getParameter("allID")));
		product.setCategoryID(Integer.parseInt(req.getParameter("categoryID")));
		product.setSeriesID(Integer.parseInt(req.getParameter("seriesID")));
		product.setSupplierID(Integer.parseInt(req.getParameter("supplierID")));
		product.setPublisherID(Integer.parseInt(req.getParameter("publisherID")));
		product.setGenreID(genreIDs);
		product.setAuthorID(authorIDs);
		product.setProductName(convertUTF8(req.getParameter("allName")));
		product.setProductLevel(convertUTF8(req.getParameter("productLevel")));
		product.setProductDescription(convertUTF8(req.getParameter("productDescription")));
		product.setProductYear(Integer.parseInt(req.getParameter("productYear")));
		product.setProductStock(Integer.parseInt(req.getParameter("productStock")));
		product.setProductCost(Integer.parseInt(req.getParameter("productCost")));

		productService.update(product);
		return "redirect:/admin/product?list";
	}

	@RequestMapping(params = "delete", method = RequestMethod.GET)
	public String delete(HttpServletRequest req) {
		productService.deleteByID(Integer.parseInt(req.getParameter("productID")));
		return "redirect:/admin/product?list";
	}

	@RequestMapping(params = "image", method = RequestMethod.GET)
	public String image(HttpServletRequest req) {
		req.setAttribute("menuItem", "Quản lý sản phẩm");
		req.setAttribute("formName", "sách");
		req.setAttribute("product", productService.findByID(Integer.parseInt(req.getParameter("productID"))));
		return "/admin/form-image";
	}

	@RequestMapping(params = "image", method = RequestMethod.POST)
	public String image(HttpServletRequest req, ModelMap map) {

		Product product = productService.findByID(Integer.parseInt(req.getParameter("productID")));
		String removeImages = req.getParameter("removeImages").toString();
		String newImages = req.getParameter("newImages").toString();

		if (removeImages.equals("") == false) {
			String[] split1 = removeImages.split("@");
			for (String item : split1) {
				if (product.getProductImage().contains(item)) {
					product.getProductImage().remove(item);
				}
			}
		}

		if (newImages.equals("") == false) {
			String[] split2 = newImages.split("@");
			for (int i = 0; i < split2.length; i++) {
				product.getProductImage().add(split2[i]);
			}
		}

		productService.update(product);

		return "redirect:/admin/product?list";
	}
}
