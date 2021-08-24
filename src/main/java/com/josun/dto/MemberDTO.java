package com.josun.dto;

public class MemberDTO {
	private int rnum;
	private String name;
	private String id;
	private String pw;
	private int pwHintQ;
	private String pwHintA;
	private String address;
	private String phone;
	private String email;
	
	public MemberDTO() {}
	public MemberDTO(String name, String id, String pw, int pwHintQ, String pwHintA, String address, String phone, String email) {
		this.name = name;
		this.id = id;
		this.pw = pw;
		this.pwHintQ = pwHintQ;
		this.pwHintA = pwHintA;
		this.address = address;
		this.phone = phone;
		this.email = email;
	}
	
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public int getPwHintQ() {
		return pwHintQ;
	}
	public void setPwHintQ(int pwHintQ) {
		this.pwHintQ = pwHintQ;
	}
	public String getPwHintA() {
		return pwHintA;
	}
	public void setPwHintA(String pwHintA) {
		this.pwHintA = pwHintA;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
}
