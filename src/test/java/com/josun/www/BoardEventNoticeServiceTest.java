package com.josun.www;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.josun.dao.BoardEventNoticeDAO;
import com.josun.dto.BoardEventNoticeDTO;
import com.josun.service.BoardEventNoticeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class BoardEventNoticeServiceTest {
	@Autowired
	private BoardEventNoticeService service;
	
	@Test
	public void testEnList() throws Exception{
		List <BoardEventNoticeDTO> list = service.enList("%%", "%1%", 1); //현재페이지가 페이징 수보다 크면 아무것도 출력안됌.
		for(BoardEventNoticeDTO dto : list) {
			System.out.println(dto.getIdx() + " " + dto.getCategory() + " " + dto.getTitle() + " " + dto.getContent() + " " + dto.getFileName() );
		}
	}
	
	@Test
	public void testTotalCountSize() throws Exception{
		System.out.println("총 게시물 수 " + service.totalCountSize("%%", "%%"));
	}
	
	@Test
	public void testStartPage() throws Exception{
		System.out.println(service.startPage("%%", "%%"));
	}
	
	@Test
	public void testEndPage() throws Exception{
		System.out.println(service.endPage("%%", "%%"));
	}
}
