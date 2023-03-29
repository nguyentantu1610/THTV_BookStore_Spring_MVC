package com.nttu.bean;

public class Author {

	private int authorID;
	private String authorName;
	
	public Author() {

	}
	
	public Author(int authorID, String authorName) {
		super();
		this.authorID = authorID;
		this.authorName = authorName;
	}

	public int getAuthorID() {
		return authorID;
	}
	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
}
