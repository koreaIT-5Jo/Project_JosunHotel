package com.josun.service;

import java.util.List;

import com.josun.dto.BoardEventNoticeDTO;

public interface BoardEventNoticeService {
	//글목록
	List<BoardEventNoticeDTO> enList(String content, String category, int page);
	//DB 총 페이징 수
	int totalCountSize(String content, String category);
	
	//시작 페이지네이션
	int startPage(String content, String category);
	
	//끝 페이지네이션
	int endPage(String content, String category);
	
	//조회수 올리기
	boolean hitCountUp(int idx);
	
	//글 상세보기
	BoardEventNoticeDTO detailView(int idx);
	
	//이전 글 번호
	int getPrevIdx(int idx);
	
	//다음 글 번호
	int getNextIdx(int idx);
	
	//글 삭제
	boolean delPost(int idx);
}
