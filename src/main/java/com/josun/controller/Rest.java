package com.josun.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.josun.dto.RoomDTO;
import com.josun.service.RoomService;

@RestController
@RequestMapping(value = "/rest", produces = "application/json; charset=utf-8")
public class Rest {
	
	@Autowired
	RoomService roomService;
	
	@RequestMapping("/vo")
	public RoomDTO rest() {
		
		return new RoomDTO();
	}
	
	@RequestMapping("/vo/json")
	public HashMap<String,String> roomInfo(@RequestBody Map<String,String> param) {
		int num = Integer.parseInt(param.get("num"));
		HashMap<String,String> map = roomService.roomDetailInfo(num);
		System.out.println(map);
		return map;
	}
}
