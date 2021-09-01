package com.josun.dto;

public class BoardQnaCommentDTO {
	private int rnum;
	private int qnaNum;
	private String content;
	
	public BoardQnaCommentDTO() {}
	public BoardQnaCommentDTO(int qnaNum, String content) {
		this.qnaNum = qnaNum;
		this.content = content;
	}
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public int getQnaNum() {
		return qnaNum;
	}
	public void setQnaNum(int qnaNum) {
		this.qnaNum = qnaNum;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
