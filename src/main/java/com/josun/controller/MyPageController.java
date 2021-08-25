package com.josun.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.josun.service.MemberService;

@RestController
@RequestMapping(value = "/myPage", produces = "application/json; charset=utf-8")
public class MyPageController {
	@Autowired
	MemberService service;
	
	@RequestMapping("/memberModifyAction/json")
	public String memberModifyAction(HttpSession session, @RequestBody Map<String,String> param) {
		System.out.println(param);
		String id = (String)session.getAttribute("id");
		String name = param.get("name");
		String phone = param.get("phone");
		String addre = param.get("address");
		String email = param.get("email");
		
		int result = service.updateMem(id, name, addre, phone, email);
		
		System.out.println("파라미터 확인: " + id + " " + name + " " + addre + " " + phone + " " + email + "업데이트 결과 : " + result);
		
		String msg = "회원정보가 ";
		if(result == 1) {
			session.setAttribute("name", name);
			session.setAttribute("addr", addre);
			session.setAttribute("phone", phone);
			session.setAttribute("email", email);
			msg += "수정되었습니다";
		} else {
			msg += "수정되지 않았습니다.";
		}
		
		return msg;
	}
}
