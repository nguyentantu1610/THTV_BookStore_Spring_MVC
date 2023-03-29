package com.nttu.bean;

public class User {

	private int userID;
	private String userEmail;
	private String userPassword;
	private String userName;
	private String userRole;
	private String userAddress;
	private int userPhoneNumber;
	private boolean userState;
	
	public User() {
		
	}

	public User(int userID, String userEmail, String userPassword, String userName, String userRole,
			String userAddress, int userPhoneNumber, boolean userState) {
		super();
		this.userID = userID;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userName = userName;
		this.userRole = userRole;
		this.userAddress = userAddress;
		this.userPhoneNumber = userPhoneNumber;
		this.userState = userState;
	}

	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public int getUserPhoneNumber() {
		return userPhoneNumber;
	}
	public void setUserPhoneNumber(int userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}
	public boolean isUserState() {
		return userState;
	}
	public void setUserState(boolean userState) {
		this.userState = userState;
	}
}
