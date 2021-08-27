package com.josun.dto;

import java.text.SimpleDateFormat;

public class ReviewDTO {
	private int idx;
	private String title;
	private String content;
	private String write_Date;
	private int hitCount;
	private String member_ID;
	private int room_Number;
	private String file_Name;
	
	public ReviewDTO( String title, String content,String memberID,int roomNumber, String fileName) {
		SimpleDateFormat format = new SimpleDateFormat ( "yyyy.MM.dd HH:mm:ss");
		String crrentTime = format.format (System.currentTimeMillis());
		
		this.title = title;
		this.content = content;
		this.write_Date = crrentTime;
		this.hitCount = 0;
		this.member_ID = memberID;
		this.room_Number = roomNumber;
		this.file_Name = fileName;
	}
	
	public ReviewDTO(int idx, String title, String content, String writeDate, int hitCount, String memberID,
			int roomNumber, String fileName) {
		this.idx = idx;
		this.title = title;
		this.content = content;
		this.write_Date = writeDate;
		this.hitCount = hitCount;
		this.member_ID = memberID;
		this.room_Number = roomNumber;
		this.file_Name = fileName;
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
	public String getWriteDate() {
		return write_Date;
	}
	public void setWrite_Date(String writeDate) {
		this.write_Date = writeDate;
	}
	public int getHitCount() {
		return hitCount;
	}
	public void setHitCount(int hitCount) {
		this.hitCount = hitCount;
	}
	public String getMemberID() {
		return member_ID;
	}
	public void setMember_ID(String memberID) {
		this.member_ID = memberID;
	}
	public int getRoomNumber() {
		return room_Number;
	}
	public void setRoom_Number(int roomNumber) {
		this.room_Number = roomNumber;
	}
	public String getFile_Name() {
		return file_Name;
	}
	public void setFileName(String fileName) {
		this.file_Name = fileName;
	}

	@Override
	public String toString() {
		return "ReviewDTO [idx=" + idx + ", title=" + title + ", content=" + content + ", writeDate=" + write_Date
				+ ", hitCount=" + hitCount + ", memberID=" + member_ID + ", roomNumber=" + room_Number + ", fileName="
				+ file_Name + "]";
	}
}
