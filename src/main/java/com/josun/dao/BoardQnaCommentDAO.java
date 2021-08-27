package com.josun.dao;

import java.util.List;

import com.josun.dto.BoardQnaCommentDTO;

public interface BoardQnaCommentDAO {
	//댓글 조회
	List<BoardQnaCommentDTO> getCommentData(int qnaNum);
}
