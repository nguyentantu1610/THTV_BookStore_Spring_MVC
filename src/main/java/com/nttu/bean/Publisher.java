package com.nttu.bean;

public class Publisher {

	private int publisherID;
	private String publisherName;
	
	public Publisher() {
		
	}
	
	public Publisher(int publisherID, String publisherName) {
		super();
		this.publisherID = publisherID;
		this.publisherName = publisherName;
	}

	public int getPublisherID() {
		return publisherID;
	}
	public void setPublisherID(int publisherID) {
		this.publisherID = publisherID;
	}
	public String getPublisherName() {
		return publisherName;
	}
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
}
