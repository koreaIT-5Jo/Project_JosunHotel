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
@RequestMapping(value = "/eventNotice", produces = "application/json; charset=utf-8")
public class EventNoticeController {
	@Autowired
	BoardEventNoticeService enService;
	
	//글 삭제
	 @RequestMapping("/delPostAction/json") 
	 public Map<String,String>enDetailViewAction(@RequestBody Map<String,String>param) {		 
		 Map<String, String> map = new HashMap<String, String>();
		 int idx = Integer.parseInt(param.get("idx"));
		 
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
	 
	 //글쓰기
	 @RequestMapping("/writePostAction")
	 public Map<String, String>enWritePostAction(@RequestBody Map<String, String>param) {
		 Map<String, String> map = new HashMap<String, String>();
		 int category = Integer.parseInt(param.get("cate"));
		 String title = param.get("tit");
		 String content = param.get("con");
		 String fileName = param.get("fileN");
		 boolean result = enService.writePost(category, title, content, fileName);
		 
		 String msg = "";
		 if(result) {
			 msg = "새로운 글이 작성되었습니다.";
		 } else {
			 msg = "ERR. 글 작성이 되지 않았습니다.";
		 }
		 
		 map.put("msg", msg);
		 
		 return map;
	 }
	 
	//글수정
	 @RequestMapping("/modifyPostAction")
	 public Map<String, String>enmodifyPostAction(@RequestBody Map<String, String>param) {
		 Map<String, String> map = new HashMap<String, String>();
		 int idx = Integer.parseInt(param.get("idx"));
		 int category = Integer.parseInt(param.get("cate"));
		 String title = param.get("tit");
		 String content = param.get("con");
		 String fileName = param.get("fileN");
		 
		 boolean result = enService.updatePost(category, title, content, fileName, idx);
		 
		 String msg = "글이";
		 if(result) {
			 msg = "수정되었습니다.";
		 } else {
			 msg = "수정되지 않았습니다.";
		 }
		 
		 map.put("msg", msg);
		 
		 return map;
	 }
	 
}
