package com.josun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.josun.dao.BoardQnaCommentDAO;
import com.josun.dto.BoardQnaCommentDTO;

@Service
public class BoardQnaCommentServiceImpl implements BoardQnaCommentService {
	@Autowired
	private BoardQnaCommentDAO dao;
	
	//댓글 조회
	@Override
	public List<BoardQnaCommentDTO> getCommentData(int qnaNum) {
		List<BoardQnaCommentDTO> list = dao.getCommentData(qnaNum);
		return list;
	}
	
	//댓글 쓰기
	@Override
	public void insertComment(BoardQnaCommentDTO dto) {
		dao.insertComment(dto);
	}

}
