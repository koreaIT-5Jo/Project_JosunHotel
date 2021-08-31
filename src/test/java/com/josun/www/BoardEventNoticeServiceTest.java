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
	public void testEnList() throws Exception {
		List <BoardEventNoticeDTO> list = service.enList("%%", "%%", 3); //현재페이지가 페이징 수보다 크면 아무것도 출력안됌.
		for(BoardEventNoticeDTO dto : list) {
			System.out.println(dto.getIdx() + " / " + dto.getCategory() + " / " + dto.getTitle() + " / " + dto.getContent() + " / " + dto.getFile_name() + " / " + dto.getHitCount() + " / " + dto.getWrite_date() );
		}
	}
	
	@Test
	public void testTotalCountSize() throws Exception {
		System.out.println("총 게시물 수 " + service.totalCountSize("%%", "%%"));
	}
	
	@Test
	public void testStartPage() throws Exception {
		System.out.println(service.startPage("%%", "%%"));
	}
	
	@Test
	public void testEndPage() throws Exception {
		System.out.println(service.endPage("%%", "%%"));
	}
	
	@Test
	public void testHitCountUp() throws Exception {
		System.out.println(service.hitCountUp(24)); //true
		System.out.println(service.hitCountUp(1));  //false
	}
	
	@Test
	public void testDetailView() throws Exception {
		BoardEventNoticeDTO dto = service.detailView(21);
		System.out.println(dto.getIdx() + " / " + dto.getTitle() + " / " + dto.getContent() + " / " + dto.getFile_name() + " / " + dto.getWrite_date());
	}
	
	@Test
	public void testPrevIdx() throws Exception {
		System.out.println(service.getPrevIdx(3));
		System.out.println(service.getPrevIdx(24));
	}
	
	@Test
	public void testNextIdx() throws Exception {
		System.out.println(service.getNextIdx(3));
		System.out.println(service.getNextIdx(24));
	}
	
	@Test
	public void testDelPost() throws Exception {
		System.out.println(service.delPost(24)); //false
		System.out.println(service.delPost(23)); //true
	}
}
