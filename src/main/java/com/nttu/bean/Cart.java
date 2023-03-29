package com.nttu.bean;

import java.util.ArrayList;
import java.util.List;

public class Cart {

	private int productID;
	private String productImage;
	private String productName;
	private int cost;
	private int quantity;

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Cart(int productID, String productImage, String productName, int cost, int quantity) {
		super();
		this.productID = productID;
		this.productImage = productImage;
		this.productName = productName;
		this.cost = cost;
		this.quantity = quantity;
	}

	public Cart() {

	}

	private static List<Cart> carts;

	public static List<Cart> getCart() {
		if (carts == null) {
			carts = new ArrayList<Cart>();
		}
		return carts;
	}
}
