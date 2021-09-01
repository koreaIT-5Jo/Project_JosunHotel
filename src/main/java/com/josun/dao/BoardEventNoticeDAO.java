package com.josun.dao;

import java.util.List;

import com.josun.dto.BoardEventNoticeDTO;

public interface BoardEventNoticeDAO {
	//글목록
	List <BoardEventNoticeDTO> enList(String content, String category, int startNum, int endNum);
	
	//총 게시글 수
	int totalCount(String content, String category);
	
	//조회수 올리기
	int hitCount(int idx);
	
	//상세보기
	BoardEventNoticeDTO detailView(int idx);
	
	//이전 글 번호
	int getPrevIdx(int idx);
	
	//다음 글 번호
	int getNextIdx(int idx);
	
	//글삭제
	int deletePost(int idx);
	
	//글쓰기
	int postWrite(int category, String title, String content, String fileName);
	
	//글수정
	int postUpdate(int category, String title, String content, String fileName, int idx);
}
