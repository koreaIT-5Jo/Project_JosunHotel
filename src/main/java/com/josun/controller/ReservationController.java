package com.josun.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.josun.dto.ReservationDTO;
import com.josun.dto.RoomDTO;
import com.josun.service.ReservationService;
import com.josun.service.RoomService;
import com.josun.util.DateUtil;
import com.josun.vo.StepVO;

@Controller
@RequestMapping(value = "/reservation")
public class ReservationController {
	
	@Autowired
	RoomService roomService;
	@Autowired
	ReservationService reservationService;
	
	// 예약페이지 - 방선택
	@RequestMapping(value = "/step1")
	public String reservationStep1(ReservationDTO reservationDto, Model model,DateUtil util) {
		
		String ckinDay = util.DaysCheck(reservationDto.getStartDate()); // 시작 요일
		String ckoutDay = util.DaysCheck(reservationDto.getEndDate());	// 끝 요일
		int dateDays = util.seDay(reservationDto.getStartDate(), reservationDto.getEndDate());  // 시작과 끝 요일 차이
		try {
			List<RoomDTO> list = roomService.getRoomList(reservationDto.getStartDate(), reservationDto.getEndDate());
			model.addAttribute("roomList",list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("reservationDto",reservationDto);
		model.addAttribute("ckinDay",ckinDay);
		model.addAttribute("ckoutDay",ckoutDay);
		model.addAttribute("dateDays",dateDays);
		
		return "reservation/reserveStep1";
	}
	// 예약페이지 - 옵션선택
	@RequestMapping(value = "/step2")
	public String reservationStep2(ReservationDTO reservationDto,int roomNum,HttpServletRequest request) {
		String ckinDay = request.getParameter("ckinDay");
		String ckoutDay = request.getParameter("ckoutDay");
		String dateDays = request.getParameter("dateDays");
		RoomDTO roomDto = roomService.roomOptionInfo(roomNum); 
		
		request.setAttribute("reservationDto", reservationDto);
		request.setAttribute("ckinDay",ckinDay);
		request.setAttribute("ckoutDay",ckoutDay);
		request.setAttribute("dateDays", dateDays);
		request.setAttribute("roomDto", roomDto);
		return "reservation/reserveStep2";
	}
	// 예약페이지 - 옵션선택
	@RequestMapping(value = "/step3")
	public String reservationStep3(ReservationDTO reservationDto,int roomNum,StepVO stepVo, Model model) {
		
		RoomDTO roomDto = roomService.roomOptionInfo(roomNum); 
		
		model.addAttribute("roomDto",roomDto);
		model.addAttribute("reservationDto",reservationDto);
		model.addAttribute("stepVo",stepVo);
		return "reservation/reserveStep3";
	}	
	
	@RequestMapping(value = "/final")
	public String reservationFinal(ReservationDTO reservationDto) {
		System.out.println(reservationDto);
		int result = reservationService.doReserve(reservationDto);
		System.out.println(result); 								//추후 업그레이드~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*************************************************
		return "homeGo";
	}
}
