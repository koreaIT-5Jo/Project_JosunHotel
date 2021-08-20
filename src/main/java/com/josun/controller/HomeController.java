package com.josun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "home";
	}
	
	//브랜드스토리
	@RequestMapping(value = "/brand")
	public String brand() {
		return "brand/brandStory";
	}
	
	//게시판 - Q&A게시판
	@RequestMapping(value = "/qna")
	public String qna() {
		return "board/board_qna";
	}
	//게시판 - 이벤트, 공지사항 목록
	@RequestMapping(value = "/enList")
	public String enList() {
		return "board/board_event_noticeList";
	}
	
	//회원가입
	@RequestMapping(value = "/register")
	public String register() {
		return "member/register";
	}
	//로그인
	@RequestMapping(value = "/login")
	public String login() {
		return "member/login";
	}
	//아이디 비번 찾기
	@RequestMapping(value = "/find")
	public String find() {
		return "member/findIdPw";
	}

	//마이페이지 - 예약확인
	@RequestMapping(value = "/reserveConfirm")
	public String reserveConfirm() {
		return "member/reserveConfirm";
	}
	//마이페이지 - 회원정보 수정
	@RequestMapping(value = "/memberModify")
	public String memberModify() {
		return "member/memberModify";
	}
	//마이페이지 - 비밀번호 변경
	@RequestMapping(value = "/memberPwChange")
	public String memberPwChange() {
		return "member/memberPwChange";
	}
	//마이페이지 - 회원 탈퇴
	@RequestMapping(value = "/memberDelete")
	public String memberDelete() {
		return "member/memberDelete1";
	}
	//마이페이지 - 회원 탈퇴2
	@RequestMapping(value = "/memberDelete/next")
	public String memberDeleteNext() {
		return "member/memberDelete2";
	}
	
	//관리자페이지 - 회원
	@RequestMapping(value = "/adminMember")
	public String adminMember() {
		return "admin/adminMember";
	}
	//관리자페이지 - 예약
	@RequestMapping(value = "/adminReservation")
	public String adminReservation() {
		return "admin/adminReservation";
	}
	//관리자페이지 - Q&A목록
	@RequestMapping(value = "/adminQnaList")
	public String adminQnaList() {
		return "admin/adminQnaList";
	}
	// 예약페이지 - 방선택
	@RequestMapping(value = "/reservation/step1")
	public String reservationStep1() {
		return "reservation/step1";
	}
	// 예약페이지 - 옵션선택
	@RequestMapping(value = "/reservation/step2")
	public String reservationStep2() {
		return "reservation/step2";
	}
	// 예약페이지 - 옵션선택
	@RequestMapping(value = "/reservation/step3")
	public String reservationStep3() {
		return "reservation/step3";
	}	
}
