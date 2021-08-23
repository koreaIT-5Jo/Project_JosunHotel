package com.josun.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.josun.dto.BoardQnaDTO;

public class BoardQnaDAOImpl implements BoardQnaDAO {
	@Autowired
	private SqlSession sqlsession;

	@Override
	public void insertBoard(BoardQnaDTO dto) {
		sqlsession.insert("com.josun.mapper.BoardQnaMapper.insertBoard", dto);
	}

}
