package com.josun.controller;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.josun.dao.ReviewDAO;
import com.josun.dto.ReviewDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ReivewDAOTest {
	
	@Autowired
	ReviewDAO dao;
	
	@Test
	public void reviewMainList( ) {
		List<ReviewDTO> list = dao.reivewMainList(1); 
		System.out.println(list);
	}
	@Test
	public void reviewWritingCnt() {
		int writingCnt = dao.reviewWritingCnt();
		System.out.println(writingCnt);
	}
	@Test
	public void detailReview() {
		ReviewDTO dto = dao.detailReview(96);
		System.out.println(dto);
	}
	@Test
	public void writeReview() {
		int result = dao.writeReview(new ReviewDTO("lsjdf","dlajdsf","kigo0307",1,"캡처.jpg"));
		if(result == 1 ) {System.out.println("성공");}
	}
	@Test
	public void writeDelete() {
		int result = dao.writeDelete(115);
		if(result == 1 ) {System.out.println("성공");}
	}
	@Test
	public void writeModify() {
		int result = dao.writeModify(new ReviewDTO(121,"ladsjf","dklajf","kigo0307",2,"캡처.jpg"));
		if(result == 1 ) {System.out.println("성공");}
	}
}
