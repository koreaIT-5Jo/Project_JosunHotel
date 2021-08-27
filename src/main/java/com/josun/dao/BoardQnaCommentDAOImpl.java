package com.josun.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.josun.dto.BoardQnaCommentDTO;

public class BoardQnaCommentDAOImpl implements BoardQnaCommentDAO {
	@Autowired
	private SqlSession sqlsession;

	@Override
	public List<BoardQnaCommentDTO> getCommentData(int qnaNum) {
		return sqlsession.selectList("com.josun.mapper.BoardQnaCommentMapper.getCommentData", qnaNum);
	}

}
