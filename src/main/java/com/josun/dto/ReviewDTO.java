package com.josun.dto;

import org.springframework.web.multipart.MultipartFile;

public class ReviewDTO {
	private int rnum;
	private int idx;
	private String title;
	private String content;
	private String write_Date;
	private int hitCount;
	private String member_ID;
	private int room_Number;
	private String fileName;
	private MultipartFile uploadFile;
	
	
	public ReviewDTO() {}
	public ReviewDTO(String title, String content, String member_ID, int room_Number, String fileName) {
		this.title = title;
		this.content = content;
		this.member_ID = member_ID;
		this.room_Number = room_Number;
		this.fileName = fileName;
	}
	
	
	public ReviewDTO(int idx, String title, String content, String member_ID, int room_Number, String fileName) {
		this.idx = idx;
		this.title = title;
		this.content = content;
		this.member_ID = member_ID;
		this.room_Number = room_Number;
		this.fileName = fileName;
	}
	public ReviewDTO(int rnum, int idx, String title, String content, String write_Date, int hitCount, String member_ID,
			int room_Number, String fileName, MultipartFile uploadFile) {
		this.rnum = rnum;
		this.idx = idx;
		this.title = title;
		this.content = content;
		this.write_Date = write_Date;
		this.hitCount = hitCount;
		this.member_ID = member_ID;
		this.room_Number = room_Number;
		this.fileName = fileName;
		this.uploadFile = uploadFile;
	}
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
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
	public String getWrite_Date() {
		return write_Date;
	}
	public void setWrite_Date(String write_Date) {
		this.write_Date = write_Date;
	}
	public int getHitCount() {
		return hitCount;
	}
	public void setHitCount(int hitCount) {
		this.hitCount = hitCount;
	}
	public String getMember_ID() {
		return member_ID;
	}
	public void setMember_ID(String member_ID) {
		this.member_ID = member_ID;
	}
	public int getRoom_Number() {
		return room_Number;
	}
	public void setRoom_Number(int room_Number) {
		this.room_Number = room_Number;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFile_Name(String fileName) {
		this.fileName = fileName;
	}
	public MultipartFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	@Override
	public String toString() {
		return "ReviewDTO [rnum=" + rnum + ", idx=" + idx + ", title=" + title + ", content=" + content
				+ ", write_Date=" + write_Date + ", hitCount=" + hitCount + ", member_ID=" + member_ID
				+ ", room_Number=" + room_Number + ", fileName=" + fileName + ", uploadFile=" + uploadFile + "]\n";
	}
	
}