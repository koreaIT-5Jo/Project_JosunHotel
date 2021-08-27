package com.josun.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	
	//회원정보수정Action
	@RequestMapping("/memberModifyAction/json")
	public Map<String,String> memberModifyAction(HttpSession session, @RequestBody Map<String,String> param) {
		
		String id = (String)session.getAttribute("id");
		String name = param.get("name");
		String phone = param.get("phone");
		String addre = param.get("address");
		String email = param.get("email");
		
		int result = service.updateMem(id, name, addre, phone, email);
		
		System.out.println("파라미터 확인: " + id + " " + name + " " + addre + " " + phone + " " + email + "업데이트 결과 : " + result);
		
		Map<String, String> map = new HashMap<String, String>();
		String msg = "회원정보가 ";
		if(result == 1) {
			session.setAttribute("name", name);
			session.setAttribute("addr", addre);
			session.setAttribute("phone", phone);
			session.setAttribute("email", email);
			msg += "수정되었습니다.";
		} else {
			msg += "수정되지 않았습니다.";
		}
		
		map.put("msg", msg);
		
		return map;
		
	}
	//비밀번호 변경 Action
	@RequestMapping("memberUpdatePwAction/json")
	public Map<String, Object> memberUpdatePwAction(HttpSession session, @RequestBody Map<String, String> param) {
		String loginId = (String)session.getAttribute("id");
		String loginPw = service.checkPw(loginId);
		String curPw = param.get("curPw");
		String newPw = param.get("newPw");
		
		System.out.println("contorller로 전송 된 값 확인 " + loginPw + curPw + newPw);
		
		int check = 0;
		Map<String, Object>map = new HashMap<String, Object>();
		
		if(loginPw.equals(curPw)) {
			check = service.updatePw(loginId, newPw); //제대로 업데이트 되면 1
			System.out.println("check 값 : " + check);
			session.invalidate();
		} else {
			check = 0;
		}
		
		map.put("result", check);
		
		return map;
				
	}
	
	//회원탈퇴1 Action
	@RequestMapping("/memberDelete1Action/json")
	public Map<String,Object> memberDelete1Action(HttpSession session, @RequestBody Map<String,String> param) {
		String loginId = (String)session.getAttribute("id");
		String loginPw = service.checkPw(loginId);
		String inputPw = param.get("password");
		
		System.out.println("값 확인 : " + loginId +" "+ loginPw +" "+ inputPw);
		
		Map<String, Object> map = new HashMap<String, Object>();
		boolean check = false;
		if(inputPw.equals(loginPw)) {
			check = true;
			
		} else {
			check = false;
		}
		//String result = String.valueOf(check);
		map.put("result", check);
		
		return map;
		
	}
	
	//회원탈퇴2 Action
	@RequestMapping("/memberDelete2Action/json")
	public Map<String,Object> memberDelete2Action(HttpSession session, @RequestBody Map<String,String> param) {
		String id = (String)session.getAttribute("id");
		String pw = service.checkPw(id);
		String ok = param.get("notichk");
		boolean result = false;
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(ok.equals("true")) {
			result = true;
			service.deleteMem(id, pw);
			session.invalidate();
		} else {
			result = false;
		}
		
		map.put("result", result);
		
		return map;
	}

}
