package com.nttu.bean;

public class Bill {

	private int billID;
	private int userID;
	private String billDate;
	private String billPaymentMethod;
	private String billDeliveryAddress;
	private int billPhoneNumber;
	private int billTotal;
	private String billNote;
	private String billState;

	public Bill() {

	}

	public Bill(int billID, int userID, String billDate, String billPaymentMethod, String billDeliveryAddress,
			int billPhoneNumber, int billTotal, String billNote, String billState) {
		super();
		this.billID = billID;
		this.userID = userID;
		this.billDate = billDate;
		this.billPaymentMethod = billPaymentMethod;
		this.billDeliveryAddress = billDeliveryAddress;
		this.billPhoneNumber = billPhoneNumber;
		this.billTotal = billTotal;
		this.billNote = billNote;
		this.billState = billState;
	}

	public int getBillID() {
		return billID;
	}

	public void setBillID(int billID) {
		this.billID = billID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	public String getBillPaymentMethod() {
		return billPaymentMethod;
	}

	public void setBillPaymentMethod(String billPaymentMethod) {
		this.billPaymentMethod = billPaymentMethod;
	}

	public String getBillDeliveryAddress() {
		return billDeliveryAddress;
	}

	public void setBillDeliveryAddress(String billDeliveryAddress) {
		this.billDeliveryAddress = billDeliveryAddress;
	}

	public int getBillPhoneNumber() {
		return billPhoneNumber;
	}

	public void setBillPhoneNumber(int billPhoneNumber) {
		this.billPhoneNumber = billPhoneNumber;
	}

	public int getBillTotal() {
		return billTotal;
	}

	public void setBillTotal(int billTotal) {
		this.billTotal = billTotal;
	}

	public String getBillNote() {
		return billNote;
	}

	public void setBillNote(String billNote) {
		this.billNote = billNote;
	}

	public String getBillState() {
		return billState;
	}

	public void setBillState(String billState) {
		this.billState = billState;
	}

}
