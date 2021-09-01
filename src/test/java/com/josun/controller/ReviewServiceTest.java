package com.josun.controller;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.josun.dto.ReviewDTO;
import com.josun.service.ReviewService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ReviewServiceTest {

	@Autowired
	ReviewService service;
	
	@Test
	public void ReviewMainPage() {
		
		List<ReviewDTO> list = service.reviewMainList(1);
		System.out.println(list);
	}
}
