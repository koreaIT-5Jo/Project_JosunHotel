package com.josun.dto;

public class BoardEventNoticeDTO {
	private int idx;
	private String cateName;
	private int category;
	private String title;
	private String content;
	private String fileName;
	private String writeDate;
	
	
	public BoardEventNoticeDTO() {}
	public BoardEventNoticeDTO(int idx, String cateName, int category, String title, String content, String fileName, String writeDate) {
		super();
		this.idx = idx;
		this.cateName = cateName;
		this.category = category;
		this.title = title;
		this.content = content;
		this.fileName = fileName;
		this.writeDate = writeDate;
	}


	public int getIdx() {
		return idx;
	}
	
	public void setIdx(int idx) {
		this.idx = idx;
	}
	
	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	
	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
}
