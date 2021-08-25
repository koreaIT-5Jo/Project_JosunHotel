package com.josun.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.josun.dto.RoomDTO;

@RestController
@RequestMapping(value = "/rest", produces = "application/json; charset=utf-8")
public class Rest {
	
//	@Autowired
//	RoomService roomService;
	
	@RequestMapping("/vo")
	public RoomDTO rest() {
		
		return new RoomDTO();
	}
	
	@RequestMapping("/vo/json")
	public HashMap<String,Object> roomInfo() {
//		int num = Integer.parseInt(param.get("num"));
//		System.out.println(num);
		HashMap<String,Object> map = new HashMap<String, Object>(); 
		
				//roomService.roomDetailInfo(num);
		return map;
	}
}
