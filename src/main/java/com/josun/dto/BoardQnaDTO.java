package com.josun.dto;

import org.springframework.web.multipart.MultipartFile;

public class BoardQnaDTO {
	private int category;
	private String content;
	private String name;
	private String phone;
	private String email;
	private String reply;
	private MultipartFile multipart;
	private String fileName;
	
	public BoardQnaDTO() {}
	public BoardQnaDTO(int category, String content, String name, String phone, String email, String reply, String fileName) {
		this.category = category;
		this.content = content;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.reply = reply;
		this.fileName = fileName;
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
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
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
