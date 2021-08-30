package com.josun.vo;

public class PageVO {
	private final static int pageSize = 5;
	private int pageCnt;
	private int currentPage;
	private int startPage;
	private int endPage;
	
	public PageVO() {this.currentPage = 1;}
	public PageVO(int currentPage, int pageCnt) {
		this.currentPage = currentPage;
		this.pageCnt = pageCnt;
		this.startPage = currentPage%5 == 0 ? currentPage - 4 : currentPage-currentPage%5 + 1;
		if(startPage<0) {startPage = 0;}	//최솟값 설정
		this.endPage = startPage+4;	
		if(endPage>pageCnt) {endPage = pageCnt;}	//최대값 성정
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageCnt() {
		return pageCnt;
	}
	public void setPageCnt(int pageCnt) {
		this.pageCnt = pageCnt;
	}
	public static int getPagesize() {
		return pageSize;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	
	
}
