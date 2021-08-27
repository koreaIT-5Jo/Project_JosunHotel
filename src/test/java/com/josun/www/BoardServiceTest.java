package com.josun.www;

import java.util.List;

import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.josun.dto.BoardQnaCommentDTO;
import com.josun.dto.BoardQnaDTO;
import com.josun.service.BoardQnaCommentService;
import com.josun.service.BoardQnaService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class BoardServiceTest {
	@Autowired
	private BoardQnaService service;
	@Autowired
	private BoardQnaCommentService cservice;
	@Autowired
	JavaMailSenderImpl mailSender;
	
	@Test
	public void insert() throws Exception{
		service.insertBoard(new BoardQnaDTO(3, "테스트2", "이름2", "010-2222-2222","test@test.com","이메일","첨부파일 없음"));
	}
	
	@Test
	public void adminList() throws Exception{
		List<BoardQnaDTO> list = service.adminBoardList(1, 10, "name", "");
		for(BoardQnaDTO dto : list) {
			System.out.println(dto.getRnum() + ", " + dto.getCategoryName() + ", " + dto.getContent() + ", " + dto.getName() + ", " + dto.getReply() + ", " + dto.getWriteDate());
		}
	}
	
	@Test
	public void getDateCount() throws Exception{
		System.out.println(service.getDataCount("name", ""));
	}
	
	@Test
	public void adminRead() throws Exception{
		BoardQnaDTO dto = service.adminBoardRead(188);
		System.out.println(dto.getIdx() + ", " + dto.getCategoryName() + ", " + dto.getName() + ", " + dto.getPhone() + ", " + dto.getEmail() + ", " + dto.getReply() + ", " + dto.getWriteDate() + ", " + dto.getContent() + ", " + dto.getFileName());
	}
	
	@Test
	public void getCommentData() throws Exception{
		List<BoardQnaCommentDTO> list = cservice.getCommentData(221);
		for(BoardQnaCommentDTO dto : list) {
			System.out.println(dto.getRnum() + ", " + dto.getContent());
		}
	}
	
	@Test
	public void mail() throws Exception{
		String subject = "test 메일 제목";
		String content = "test 내용";
		String from = "himedia.sora@gmail.com";
		String to = "sol6327@naver.com";
		
		try {
			MimeMessage mail = mailSender.createMimeMessage();
			MimeMessageHelper mailHelper = new MimeMessageHelper(mail, "utf-8");
			
			mailHelper.setFrom("관리자 <himedia.sora@gmail.com>");
			mailHelper.setTo(to);
			mailHelper.setSubject(subject);
			mailHelper.setText(content);
			
			mailSender.send(mail);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
