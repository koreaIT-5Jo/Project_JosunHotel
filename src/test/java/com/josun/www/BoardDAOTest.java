package com.josun.www;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.josun.dao.BoardQnaDAO;
import com.josun.dto.BoardQnaDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class BoardDAOTest {
	@Autowired
	private BoardQnaDAO dao;
	
	@Test
	public void insert() throws Exception{
		dao.insertBoard(new BoardQnaDTO(3, "테스트1", "이름1", "010-1111-1111","test@test.com","이메일","첨부파일 없음"));
	}
}