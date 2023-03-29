package com.nttu.bean;

public class Supplier {
	
	private int supplierID;
	private String supplierName;
	private String supplierAddress;
	private int supplierPhoneNumber;
	
	public Supplier() {
	
	}
	
	public Supplier(int supplierID, String supplierName, String supplierAddress, int supplierPhoneNumber) {
		super();
		this.supplierID = supplierID;
		this.supplierName = supplierName;
		this.supplierAddress = supplierAddress;
		this.supplierPhoneNumber = supplierPhoneNumber;
	}

	public int getSupplierID() {
		return supplierID;
	}
	public void setSupplierID(int supplierID) {
		this.supplierID = supplierID;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierAddress() {
		return supplierAddress;
	}

	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}

	public int getSupplierPhoneNumber() {
		return supplierPhoneNumber;
	}

	public void setSupplierPhoneNumber(int supplierPhoneNumber) {
		this.supplierPhoneNumber = supplierPhoneNumber;
	}
}
