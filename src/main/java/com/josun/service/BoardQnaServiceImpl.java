package com.josun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.josun.dao.BoardQnaDAO;
import com.josun.dto.BoardQnaDTO;

@Service
public class BoardQnaServiceImpl implements BoardQnaService {
	@Autowired
	private BoardQnaDAO dao;
	
	//글쓰기
	@Override
	public void insertBoard(BoardQnaDTO dto) {
		dao.insertBoard(dto);
	}

	//글목록
	@Override
	public List<BoardQnaDTO> adminBoardList(int start, int end, String searchKey, String searchValue) {
		List<BoardQnaDTO> list = dao.adminBoardList(start, end, searchKey, searchValue);
		return list;
	}
	//글목록 - 전체 데이터 개수
	@Override
	public int getDataCount(String searchKey, String searchValue) {
		return dao.getDataCount(searchKey, searchValue);
	}
	
	//글 상세보기
	@Override
	public BoardQnaDTO adminBoardRead(int idx) {
		return dao.adminBoardRead(idx);
	}

}
