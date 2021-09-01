package com.josun.controller;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.josun.dao.RoomDAO;
import com.josun.dto.RoomDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class RoomDAOTest {

	@Autowired
	RoomDAO dao;
	
	@Test
	public void getRoomList() throws Exception {
		List<RoomDTO> list = dao.getRoomList("2021.08.23", "2021.08.26");
		System.out.println(list);
	}
	
	@Test
	public void getduplicateRoomList() throws Exception {
		String roomNum = dao.getDuplicateCheckRoomNumList("2021.08.19", "2021.08.21");
		System.out.println(roomNum);
	}
	@Test
	public void roomOptionPageInfoTest() {
		RoomDTO dto = dao.roomOptionInfo(1);
		System.out.println(dto);
	}
	@Test
	public void detailReviewRoom() {
		RoomDTO dto = dao.detailReviewRoomInfo(1);
		System.out.println(dto);
	}
	@Test
	public void writeReviewRoomInfo() {
		List<RoomDTO> list = dao.writeReviewRoomInfo("kigo0307");
		System.out.println(list);
	}
}
