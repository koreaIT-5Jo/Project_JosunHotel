package com.josun.www;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.josun.dao.BoardEventNoticeDAO;
import com.josun.dto.BoardEventNoticeDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class BoardEventNoticeDAOTest {
	@Autowired
	private BoardEventNoticeDAO dao;
	
	@Test
	public void testEnList() throws Exception{
		List <BoardEventNoticeDTO> enList = dao.enList("%%", "%%", 1, 5);
		for(BoardEventNoticeDTO dto : enList) {
			System.out.println(dto.getIdx() + " " + dto.getCategory() + " " + dto.getTitle() + " " + dto.getContent() + " " + dto.getWriteDate());
		}
	}
	
	@Test
	public void testTotalCount() throws Exception{
		System.out.println(dao.totalCount("%%", "%%"));		//11
		System.out.println(dao.totalCount("%%", "%1%"));	//6
		System.out.println(dao.totalCount("%%", "%2%"));	//5
	}
}
