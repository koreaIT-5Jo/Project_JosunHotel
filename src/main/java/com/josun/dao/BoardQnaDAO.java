package com.josun.dao;

import java.util.List;

import com.josun.dto.BoardQnaDTO;

public interface BoardQnaDAO {
	//글쓰기
	void insertBoard(BoardQnaDTO dto);
	
	//글목록
	List<BoardQnaDTO> adminBoardList(int start, int end, String searchKey, String searchValue);
	//글목록 - 전체 데이터 개수
	int getDataCount(String searchKey, String searchValue);
	
	//글 상세보기
	BoardQnaDTO adminBoardRead(int idx);
}
