package com.josun.dto;

public class RoomDTO {
	private int hotelNumber;
	private int num;
	private String name;
	private String price;
	private String img;
	private String location;
	private String beds;
	private String r_Size;
	private String features;
	private String viewPoint;
	private String detail_View;
	
	public int getHotelNumber() {
		return hotelNumber;
	}
	public void setHotelNumber(int hotelNumber) {
		this.hotelNumber = hotelNumber;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getBeds() {
		return beds;
	}
	public void setBeds(String beds) {
		this.beds = beds;
	}
	
	public String getr_Size() {
		return r_Size;
	}
	public void setr_Size(String rSize) {
		this.r_Size = rSize;
	}
	public String getFeatures() {
		return features;
	}
	public void setFeatures(String features) {
		this.features = features;
	}
	public String getViewPoint() {
		return viewPoint;
	}
	public void setViewPoint(String viewPoint) {
		this.viewPoint = viewPoint;
	}
	public String getDetailView() {
		return detail_View;
	}
	public void setDetailView(String detailView) {
		this.detail_View = detailView;
	}
	@Override
	public String toString() {
		return "RoomDTO [hotelNumber=" + hotelNumber + ", num=" + num + ", name=" + name + ", price=" + price + ", img="
				+ img + ", location=" + location + ", beds=" + beds + ", r_Size=" + r_Size + ", features=" + features
				+ ", viewPoint=" + viewPoint + ", detailView=" + detail_View + "]\n";
	}
	
}
