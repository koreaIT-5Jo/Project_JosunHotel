package com.josun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.josun.dto.ReservationDTO;
import com.josun.util.TimeUtil;

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
	public String reservationStep1(ReservationDTO reservationDto, Model model,TimeUtil util) {
		
		String ckinDay = util.DaysCheck(reservationDto.getStartDate());
		String ckoutDay = util.DaysCheck(reservationDto.getEndDate());
		int dateDays = util.seDay(reservationDto.getStartDate(), reservationDto.getEndDate());
		model.addAttribute("reservationDto",reservationDto);
		model.addAttribute("ckinDay",ckinDay);
		model.addAttribute("ckoutDay",ckoutDay);
		model.addAttribute("dateDays",dateDays);
		
		return "reservation/reserveStep1";
	}
	// 예약페이지 - 옵션선택
	@RequestMapping(value = "/reservation/step2")
	public String reservationStep2() {
		return "reservation/reserveStep2";
	}
	// 예약페이지 - 옵션선택
	@RequestMapping(value = "/reservation/step3")
	public String reservationStep3() {
		return "reservation/reserveStep3";
	}	
}
