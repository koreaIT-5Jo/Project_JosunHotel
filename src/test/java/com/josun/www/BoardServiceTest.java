package com.josun.www;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.josun.dto.BoardQnaDTO;
import com.josun.dto.MemberDTO;
import com.josun.service.BoardQnaService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class BoardServiceTest {
	@Autowired
	private BoardQnaService service;
	
	@Test
	public void insert() throws Exception{
		service.insertBoard(new BoardQnaDTO(3, "테스트2", "이름2", "010-2222-2222","test@test.com","이메일","첨부파일 없음"));
	}
	
}
