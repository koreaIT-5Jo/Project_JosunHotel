package com.josun.dto;

public class BoardEventNoticeDTO {
	private int idx;
	private String cateName;
	private int category;
	private String title;
	private String content;
	private String file_name;
	private int hitCount;
	private String write_date;
	
	
	public BoardEventNoticeDTO() {}
	public BoardEventNoticeDTO(int idx, String cateName, int category, String title, String content, String fileName, int hitCount, String writeDate) {
		super();
		this.idx = idx;
		this.cateName = cateName;
		this.category = category;
		this.title = title;
		this.content = content;
		this.file_name = fileName;
		this.hitCount = hitCount;
		this.write_date = writeDate;
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
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String fileName) {
		this.file_name = fileName;
	}
	public int getHitCount() {
		return hitCount;
	}
	public void setHitCount(int hitCount) {
		this.hitCount = hitCount;
	}
	public String getWrite_date() {
		return write_date;
	}
	public void setWrite_date(String writeDate) {
		this.write_date = writeDate;
	}
 

}
