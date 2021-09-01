package com.josun.dto;

public class VisitDTO {
	private String v_date;
	private int v_count;
	
	public VisitDTO() {}
	public VisitDTO(String v_date, int v_count) {
		this.v_date = v_date;
		this.v_count = v_count;
	}
	public String getV_date() {
		return v_date;
	}
	public void setV_date(String v_date) {
		this.v_date = v_date;
	}
	public int getV_count() {
		return v_count;
	}
	public void setV_count(int v_count) {
		this.v_count = v_count;
	}
}
