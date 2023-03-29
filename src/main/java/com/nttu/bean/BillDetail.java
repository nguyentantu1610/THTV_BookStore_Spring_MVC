package com.nttu.bean;

public class BillDetail {

	private int billID;
	private int productID;
	private int productQuantity;
	private float productCost;

	public int getBillID() {
		return billID;
	}

	public void setBillID(int billID) {
		this.billID = billID;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public float getProductCost() {
		return productCost;
	}

	public void setProductCost(float productCost) {
		this.productCost = productCost;
	}

	public BillDetail(int billID, int productID, int productQuantity, float productCost) {
		super();
		this.billID = billID;
		this.productID = productID;
		this.productQuantity = productQuantity;
		this.productCost = productCost;
	}

	public BillDetail() {
		super();
	}
}
