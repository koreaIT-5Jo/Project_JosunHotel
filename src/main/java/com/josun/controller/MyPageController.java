package com.josun.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.josun.dao.SalesStatusDAO;
import com.josun.service.MemberService;

@RestController
@RequestMapping(value = "/myPage", produces = "application/json; charset=utf-8")
public class MyPageController {
	@Autowired
	MemberService service;
	@Autowired
	SalesStatusDAO dao;
	
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
		
		Map<String, Object>map = new HashMap<String, Object>();
		boolean check = false;
		String msg = "";
		
		if(loginPw.equals(curPw)) {
			int updatePw = service.updatePw(loginId, newPw); //제대로 업데이트 되면 1
			if(updatePw == 1) { check= true; }
			session.invalidate();
		} else {
			check = false;
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
	
	
	//마이페이지 - 예약확인
	@RequestMapping("/reserveConfirm/json")
	public List<Map<String, Object>> reserveConfirm(HttpSession session, @RequestBody Map<String, String> param){
		String id = (String)session.getAttribute("id");
		String searchStartDate = param.get("searchStartDate");
		String searchEndDate = param.get("searchEndDate");
		
		System.out.println("파라미터 확인 : " + id + ", " + searchStartDate + ", " + searchEndDate);
		
		List<Map<String, Object>> data = service.reserveConfirm(id, searchStartDate, searchEndDate);
		for(Object datamap : data) {
			Map map = (Map) datamap;
			String formatStartDate = map.get("STARTDATE").toString();
			String formatEndDate = map.get("ENDDATE").toString();
			String formatPay = map.get("TOTAL_PAY").toString();
			formatStartDate = formatStartDate.substring(0,10).replaceAll("-", ".");
			formatEndDate = formatEndDate.substring(0, 10).replaceAll("-", ".");
			map.put("STARTDATE", formatStartDate);
			map.put("ENDDATE", formatEndDate);
			map.put("TOTAL_PAY", formatPay);
		}
		
		System.out.println(data);
		return data;
	}
	
	//마이페이지 - 예약취소
	@RequestMapping("/reserveCancel/json")
	public Map<String, Integer> reserveCancel(@RequestBody Map<String, String> param) {
		int num = Integer.parseInt(param.get("num"));
		int roomNumber = Integer.parseInt(param.get("roomNumber"));
		String startDate = param.get("startDate");
		String endDate = param.get("endDate");
		
		System.out.println("파라미터 확인 : " + num + ", " + roomNumber + ", " + startDate + ", " + endDate);
		Map<String, Integer> map = new HashMap<String, Integer>();
		int result = 0;
		try {
			service.reserveCancel(num, roomNumber, startDate, endDate);
			dao.delete(num); // 매출현황 테이블 delete
			result = 2;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		map.put("result", result);
		System.out.println(map);
		return map;
	}
}
