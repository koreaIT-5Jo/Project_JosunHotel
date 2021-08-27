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
}
