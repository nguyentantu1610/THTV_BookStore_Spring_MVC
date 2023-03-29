package com.nttu.bean;

import java.util.Date;

public class Review {

	private int userID;
	private int reviewID;
	private int parentID;
	private String reviewContent;
	private Date reviewDate;
	private int reviewRating;
	
	public Review() {
		
	}
	
	public Review(int userID, int reviewID, int parentID, String reviewContent, Date reviewDate, int reviewRating) {
		super();
		this.userID = userID;
		this.reviewID = reviewID;
		this.parentID = parentID;
		this.reviewContent = reviewContent;
		this.reviewDate = reviewDate;
		this.reviewRating = reviewRating;
	}

	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getReviewID() {
		return reviewID;
	}
	public void setReviewID(int reviewID) {
		this.reviewID = reviewID;
	}
	public int getParentID() {
		return parentID;
	}
	public void setParentID(int parentID) {
		this.parentID = parentID;
	}
	public String getReviewContent() {
		return reviewContent;
	}
	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}
	public Date getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}
	public int getReviewRating() {
		return reviewRating;
	}
	public void setReviewRating(int reviewRating) {
		this.reviewRating = reviewRating;
	}
}
