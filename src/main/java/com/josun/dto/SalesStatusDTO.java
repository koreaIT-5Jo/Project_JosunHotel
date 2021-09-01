package com.josun.dto;

public class SalesStatusDTO {
	private int totalpay;
	private int month;
	private String pay;
	
	public SalesStatusDTO() {}
	public SalesStatusDTO(int totalpay, int month) {
		this.totalpay = totalpay;
		this.month = month;
	}
	public int getTotalpay() {
		return totalpay;
	}
	public void setTotalpay(int totalpay) {
		this.totalpay = totalpay;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public String getPay() {
		return pay;
	}
	public void setPay(String pay) {
		this.pay = pay;
	}
	
}
