package com.josun.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.josun.dto.BoardEventNoticeDTO;
import com.josun.service.BoardEventNoticeService;

@RestController
@RequestMapping(value = "/envetNotice", produces = "application/json; charset=utf-8")
public class EventNoticeController {
	@Autowired
	BoardEventNoticeService enService;
	
	//글 삭제
	 @RequestMapping("/delPostAction/json") 
	 public Map<String,String>enDetailViewAction(HttpSession session, @RequestBody Map<String,String>param) {
		 int idx = Integer.parseInt(param.get("idx"));
		 System.out.println(idx);
		 //글삭제
		 Map<String, String> map = new HashMap<String, String>();
		 
		 String msg = "글이 삭제";
		 boolean delResult = enService.delPost(idx);
		
		 if(delResult) {
			 msg +="되었습니다.";
			 System.out.println("삭제 완료");
		 } else {
			 msg +="되지 않았습니다.";
		 }
		 
		 map.put("msg", msg);
		
		 return map;
	 }
}
