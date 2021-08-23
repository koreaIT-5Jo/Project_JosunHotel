package com.josun.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.josun.dao.BoardQnaDAO;
import com.josun.dto.BoardQnaDTO;

@Service
public class BoardQnaServiceImpl implements BoardQnaService {
	@Autowired
	private BoardQnaDAO dao;
	
	@Override
	public void insertBoard(BoardQnaDTO dto) {
		dao.insertBoard(dto);
	}

}
