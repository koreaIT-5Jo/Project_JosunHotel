package com.josun.dto;

import org.springframework.web.multipart.MultipartFile;

public class BoardQnaDTO {
	private int rnum;
	private int idx;
	private String categoryName;
	private int category;
	private String content;
	private String name;
	private String phone;
	private String email;
	private String reply_by;
	private MultipartFile multipart;
	private String fileName;
	private String write_date;
	
	public BoardQnaDTO() {}
	public BoardQnaDTO(int category, String content, String name, String phone, String email, String reply, String fileName) {
		this.category = category;
		this.content = content;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.reply_by = reply;
		this.fileName = fileName;
	}
	
	
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getWriteDate() {
		return write_date;
	}
	public void setWriteDate(String writeDate) {
		this.write_date = writeDate;
	}
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getReply() {
		return reply_by;
	}
	public void setReply(String reply) {
		this.reply_by = reply;
	}
	public MultipartFile getMultipart() {
		return multipart;
	}
	public void setMultipart(MultipartFile multipart) {
		this.multipart = multipart;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
