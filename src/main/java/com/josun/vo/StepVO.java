package com.josun.vo;

public class StepVO {
	private String ckinDay;
	private String ckoutDay;
	private String dateDays;
	
	public StepVO() {}
	public StepVO(String ckinDay, String ckoutDay, String dateDays) {
		this.ckinDay = ckinDay;
		this.ckoutDay = ckoutDay;
		this.dateDays = dateDays;
	}
	
	public String getCkinDay() {
		return ckinDay;
	}
	public void setCkinDay(String ckinDay) {
		this.ckinDay = ckinDay;
	}
	public String getCkoutDay() {
		return ckoutDay;
	}
	public void setCkoutDay(String ckoutDay) {
		this.ckoutDay = ckoutDay;
	}
	public String getDateDays() {
		return dateDays;
	}
	public void setDateDays(String dateDays) {
		this.dateDays = dateDays;
	}
}
