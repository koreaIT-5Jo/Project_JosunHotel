package com.josun.controller;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.josun.dto.ReservationDTO;
import com.josun.service.ReservationService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ReservationServiceTest {

	@Autowired
	ReservationService service;
	
	@Test
	public void reservation() {
		ReservationDTO dto = new ReservationDTO("kg","2021.09.10","2021.09.12",2,2,2,2,"123","010-4740-3713","rladlj@naver.com","02","1111-1111-1111-1111","2030/12","19880241",20000000,1,"12","kigo0307");
		System.out.println("roomnum: " + dto.getRoom_number());
		int okReserve = service.doReserve(dto);
		
		
		System.out.println("okReserve : " + okReserve);
	}
	
	@Test
	public void reserveList() throws Exception{
		List<ReservationDTO> list = service.reserveList(1, 10, "name", "%%");
		for(ReservationDTO dto : list) {
			System.out.println(dto.getRnum() + ", " + dto.getName() + ", " + dto.getRoom_number() + ", " + dto.getAdultCnt() + ", " + dto.getChildrenCnt() + ", " + dto.getAdult_breakfast() + ", " + dto.getChil_breakfast() + ", " + dto.getTxtRequest() + ", " + dto.getTotal_pay() + ", " + dto.getPhoneNum());
		}
	}
}
