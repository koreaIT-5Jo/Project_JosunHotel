package com.josun.dao;

import java.util.List;

import com.josun.dto.BoardEventNoticeDTO;

public interface BoardEventNoticeDAO {
	//글목록
	List <BoardEventNoticeDTO> enList(String searchKey, String searchValue, int startNum, int endNum);
}
