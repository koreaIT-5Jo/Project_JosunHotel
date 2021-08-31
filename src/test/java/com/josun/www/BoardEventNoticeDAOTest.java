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
	public void testEnList() throws Exception {
		List <BoardEventNoticeDTO> enList = dao.enList("%%", "%%", 1, 5);
		for(BoardEventNoticeDTO dto : enList) {
			System.out.println(dto.getIdx() + " " + dto.getCategory() + " " + dto.getTitle() + " " + dto.getContent() + " " + dto.getFile_name() + " " + dto.getWrite_date());
		}
	}
	
	@Test
	public void testTotalCount() throws Exception {
		System.out.println(dao.totalCount("%%", "%%"));		//11
		System.out.println(dao.totalCount("%%", "%1%"));	//6
		System.out.println(dao.totalCount("%%", "%2%"));	//5
	}
	
	@Test
	public void testHitCount() throws Exception {
		System.out.println(dao.hitCount(21));//1
		System.out.println(dao.hitCount(1)); //0
	}
	
	@Test
	public void testDetailView() throws Exception {
		BoardEventNoticeDTO dto = dao.detailView(24);
		System.out.println(dto.getIdx() + " " + dto.getCategory() + " " + dto.getTitle() + " " + dto.getContent() + " " + dto.getFile_name() + " " + dto.getWrite_date());
	}
	
	@Test
	public void testPrevIndx() throws Exception {
		System.out.println(dao.getPrevIdx(3));	//0
		System.out.println(dao.getPrevIdx(21)); //9
	}
	
	@Test
	public void testNextIndx() throws Exception {
		System.out.println(dao.getNextIdx(24)); //0
		System.out.println(dao.getNextIdx(23)); //24
	}
	
	@Test
	public void testDelPost() throws Exception {
		System.out.println(dao.deletePost(24)); //1
	}
}
