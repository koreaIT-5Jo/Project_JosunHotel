package com.josun.service;

import java.util.List;

import com.josun.dto.BoardQnaCommentDTO;

public interface BoardQnaCommentService {
	//댓글 조회
	List<BoardQnaCommentDTO> getCommentData(int qnaNum);
}
