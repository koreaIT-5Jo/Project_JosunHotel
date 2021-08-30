package com.josun.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.josun.dto.ReviewDTO;
import com.josun.dto.RoomDTO;
import com.josun.service.ReviewService;
import com.josun.service.RoomService;
import com.josun.vo.PageVO;

@Controller
@RequestMapping(value = "review")
public class ReviewController {
	
	@Autowired
	ReviewService reviewService;
	@Autowired
	RoomService roomService;
	
	@RequestMapping(value = "main")
	public String main(PageVO pageVo, Model model) {
		int currentPage = pageVo.getCurrentPage();
		int pageCnt = reviewService.reviewPageCnt();
		
		pageVo = new PageVO(currentPage, pageCnt);
		List<ReviewDTO> list = reviewService.reviewMainList(currentPage);
		for(ReviewDTO dto:list) {
			if(dto.getFile_Name()=="" || dto.getFile_Name() == null) {
				dto.setFileName(dto.getRoomNumber() +".jpg");;
			}
		}
		model.addAttribute("pageVo",pageVo);
		model.addAttribute("list",list);
		
		return "review/reviewMain";
	}
	@RequestMapping(value = "detailView")
	public String detailView(int bno,Model model ) {
		ReviewDTO reviewDto = reviewService.detailReview(bno);
		RoomDTO roomDto = roomService.detailReviewRoomInfo(reviewDto.getRoomNumber());
		
		model.addAttribute("reviewDto",reviewDto);
		model.addAttribute("roomDto",roomDto);
		
		return"review/detailView";
	}
	
}
