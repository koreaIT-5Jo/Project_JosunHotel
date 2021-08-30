package com.josun.dao;

import java.util.List;

import com.josun.dto.BoardEventNoticeDTO;

public interface BoardEventNoticeDAO {
	//글목록
	List <BoardEventNoticeDTO> enList(String content, String category, int startNum, int endNum);
	
	//총 게시글 수
	int totalCount(String content, String category);
}
