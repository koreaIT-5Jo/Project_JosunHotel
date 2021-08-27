package com.josun.controller;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.josun.dto.RoomDTO;
import com.josun.service.RoomService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class RoomServiceTest {

	@Autowired
	RoomService service;
	
	@Test
	public void getRoomList() throws Exception {
		List<RoomDTO> list = service.getRoomList("2021.08.23", "2021.08.26");
		System.out.println(list);
	}
}
