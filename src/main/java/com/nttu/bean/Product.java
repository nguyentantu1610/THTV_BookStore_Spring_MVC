package com.nttu.bean;

import java.util.List;

public class Product {

	private int productID;
	private int categoryID;
	private List<Integer> genreID;
	private int seriesID;
	private List<Integer> authorID;
	private int supplierID;
	private int publisherID;
	private String productName;
	private String productDescription;
	private List<String> productImage;
	private int productCost;
	private int productYear;
	private String productLevel;
	private int productStock;
	
	public Product() {
		
	}

	public Product(int productID, int categoryID, List<Integer> genreID, int seriesID, List<Integer> authorID,
			int supplierID, int publisherID, String productName, String productDescription, List<String> productImage, int productCost,
			int productYear, String productLevel, int productStock) {
		super();
		this.productID = productID;
		this.categoryID = categoryID;
		this.genreID = genreID;
		this.seriesID = seriesID;
		this.authorID = authorID;
		this.supplierID = supplierID;
		this.publisherID = publisherID;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productImage = productImage;
		this.productCost = productCost;
		this.productYear = productYear;
		this.productLevel = productLevel;
		this.productStock = productStock;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public List<Integer> getGenreID() {
		return genreID;
	}

	public void setGenreID(List<Integer> genreID) {
		this.genreID = genreID;
	}

	public int getSeriesID() {
		return seriesID;
	}

	public void setSeriesID(int seriesID) {
		this.seriesID = seriesID;
	}

	public List<Integer> getAuthorID() {
		return authorID;
	}

	public void setAuthorID(List<Integer> authorID) {
		this.authorID = authorID;
	}

	public int getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(int supplierID) {
		this.supplierID = supplierID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public List<String> getProductImage() {
		return productImage;
	}

	public void setProductImage(List<String> productImage) {
		this.productImage = productImage;
	}

	public int getProductCost() {
		return productCost;
	}

	public void setProductCost(int productCost) {
		this.productCost = productCost;
	}

	public int getProductYear() {
		return productYear;
	}

	public void setProductYear(int productYear) {
		this.productYear = productYear;
	}

	public String getProductLevel() {
		return productLevel;
	}

	public void setProductLevel(String productLevel) {
		this.productLevel = productLevel;
	}

	public int getProductStock() {
		return productStock;
	}

	public void setProductStock(int productStock) {
		this.productStock = productStock;
	}

	public int getPublisherID() {
		return publisherID;
	}

	public void setPublisherID(int publisherID) {
		this.publisherID = publisherID;
	}
}
