package com.josun.www;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.josun.dao.BoardQnaCommentDAO;
import com.josun.dao.BoardQnaDAO;
import com.josun.dto.BoardQnaCommentDTO;
import com.josun.dto.BoardQnaDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class BoardQnaDAOTest {
	@Autowired
	private BoardQnaDAO dao;
	@Autowired
	private BoardQnaCommentDAO cdao;
	
	@Test
	public void insert() throws Exception{
		dao.insertBoard(new BoardQnaDTO(3, "테스트2222222", "이름2", "010-1111-1111","test@test.com","이메일","첨부파일 없음"));
	}
	
	@Test
	public void adminList() throws Exception{
		List<BoardQnaDTO> list = dao.adminBoardList(1, 10, "name", "%%");
		for(BoardQnaDTO dto : list) {
			System.out.println(dto.getIdx() + ", "+ dto.getRnum() + ", " + dto.getCategoryName() + ", " + dto.getContent() + ", " + dto.getName() + ", " + dto.getReply() + ", " + dto.getWriteDate());
		}
	}
	
	@Test
	public void getDataCount() throws Exception{
		System.out.println(dao.getDataCount("name", ""));
	}
	
	@Test
	public void adminRead() throws Exception{
		BoardQnaDTO dto = dao.adminBoardRead(145);
		System.out.println(dto.getIdx() + ", " + dto.getCategoryName() + ", " + dto.getName() + ", " + dto.getPhone() + ", " + dto.getEmail() + ", " + dto.getReply() + ", " + dto.getWriteDate() + ", " + dto.getContent() + ", " + dto.getFileName());
//		for(BoardQnaDTO dto : list) {
//		}
	}
	
	@Test
	public void getCommentData() throws Exception{
		List<BoardQnaCommentDTO> list = cdao.getCommentData(221);
		for(BoardQnaCommentDTO dto : list) {
			System.out.println(dto.getRnum() + ", " + dto.getContent());
		}
	}
	
	@Test
	public void insertComment() throws Exception{
		cdao.insertComment(new BoardQnaCommentDTO(221, "꾸악"));
	}
}