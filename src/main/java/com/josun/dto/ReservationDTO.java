package com.josun.dto;

public class ReservationDTO {
	private int num;
	private String name;
	private String startDate;
	private String endDate;
	private int adultCnt;
	private int childrenCnt;
	private int adult_breakfast; 
	private int chil_breakfast;
	private String request;
	private String phone_Number;
	private String email;
	private String card_type;
	private String card_Number;
	private String card_Expiration_Date;
	private String birthDate;
	private int total_pay;
	private int room_number;
	private String card_Password;
	private String memeber_Id;
	
	public ReservationDTO(){ }
	public ReservationDTO(String name, String startDate, String endDate, int adultCnt, int childrenCnt,
			int adult_breakfast, int chil_breakfast, String txtRequest, String phoneNum, String email, String card_type,
			String card_num, String card_expiration, String birth, int total_pay, int room_number, String card_pw,
			String memeberId) {
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.adultCnt = adultCnt;
		this.childrenCnt = childrenCnt;
		this.adult_breakfast = adult_breakfast;
		this.chil_breakfast = chil_breakfast;
		this.request = txtRequest;
		this.phone_Number = phoneNum;
		this.email = email;
		this.card_type = card_type;
		this.card_Number = card_num;
		this.card_Expiration_Date = card_expiration;
		this.birthDate = birth;
		this.total_pay = total_pay;
		this.room_number = room_number;
		this.card_Password = card_pw;
		this.memeber_Id = memeberId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getAdultCnt() {
		return adultCnt;
	}
	public void setAdultCnt(int adultCnt) {
		this.adultCnt = adultCnt;
	}
	public int getChildrenCnt() {
		return childrenCnt;
	}
	public void setChildrenCnt(int childrenCnt) {
		this.childrenCnt = childrenCnt;
	}
	public int getAdult_breakfast() {
		return adult_breakfast;
	}
	public void setAdult_breakfast(int adult_breakfast) {
		this.adult_breakfast = adult_breakfast;
	}
	public int getChil_breakfast() {
		return chil_breakfast;
	}
	public void setChil_breakfast(int chil_breakfast) {
		this.chil_breakfast = chil_breakfast;
	}
	public String getTxtRequest() {
		return request;
	}
	public void setTxtRequest(String txtRequest) {
		this.request = txtRequest;
	}
	public String getPhoneNum() {
		return phone_Number;
	}
	public void setPhoneNum(String phoneNum) {
		this.phone_Number = phoneNum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCard_type() {
		return card_type;
	}
	public void setCard_type(String card_type) {
		this.card_type = card_type;
	}
	public String getCard_num() {
		return card_Number;
	}
	public void setCard_num(String card_num) {
		this.card_Number = card_num;
	}
	public String getCard_expiration() {
		return card_Expiration_Date;
	}
	public void setCard_expiration(String card_expiration) {
		this.card_Expiration_Date = card_expiration;
	}
	public String getBirth() {
		return birthDate;
	}
	public void setBirth(String birth) {
		this.birthDate = birth;
	}
	public int getTotal_pay() {
		return total_pay;
	}
	public void setTotal_pay(int total_pay) {
		this.total_pay = total_pay;
	}
	public int getRoom_number() {
		return room_number;
	}
	public void setRoom_number(int room_number) {
		this.room_number = room_number;
	}
	public String getCard_pw() {
		return card_Password;
	}
	public void setCard_pw(String card_pw) {
		this.card_Password = card_pw;
	}
	public String getMemeberId() {
		return memeber_Id;
	}
	public void setMemeberId(String memeberId) {
		this.memeber_Id = memeberId;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int rnum) {
		this.num = num;
	}
	@Override
	public String toString() {
		return "ReservationDTO [num=" + num + ", name=" + name + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", adultCnt=" + adultCnt + ", childrenCnt=" + childrenCnt + ", adult_breakfast=" + adult_breakfast
				+ ", chil_breakfast=" + chil_breakfast + ", request=" + request + ", phoneNum=" + phone_Number + ", email="
				+ email + ", card_type=" + card_type + ", card_num=" + card_Number + ", card_expiration=" + card_Expiration_Date
				+ ", birth=" + birthDate + ", total_pay=" + total_pay + ", room_number=" + room_number
				+ ", card_pw=" + card_Password + ", memeberId=" + memeber_Id + "]";
	}
	
	
	
}
