package com.josun.controller;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.josun.dao.ReservationDAO;
import com.josun.dto.ReservationDTO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ReservationDAOTest {

	@Autowired
	ReservationDAO dao;
	
	@Test
	public void getRoomList() throws Exception {
		int result = dao.reservation(new ReservationDTO("kg","2021.08.28","2021.08.30",2,2,2,2,"123","010-4740-3713",
				"rladlj@naver.com","02","1111-1111-1111-1111","2030/12","19880241",20000000,1,"12","kigo0307"));
		System.out.println(result);
	}
	@Test
	public void reservedRoom() throws Exception {
		int result = dao.reservedRoom("2021.08.28","2021.08.30",1);
		System.out.println(result);
	}
	@Test
	public void okReservation() throws Exception {
		System.out.println("okReservation()");
		int count = dao.okReservation("2021.09.07","2021.09.08",1);
		System.out.println(count);
		if(count != 0) {
			System.out.println("예약 불가능");
		} else {
			System.out.println("예약 가능");
		}
	}
}
