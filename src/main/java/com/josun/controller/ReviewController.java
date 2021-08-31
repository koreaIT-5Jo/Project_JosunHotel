package com.josun.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

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
	@RequestMapping(value = "writeReview")
	public String writeReview(HttpSession session,Model model) {
		String id = (String) session.getAttribute("id");
		List<RoomDTO> list = roomService.writeReviewRoomInfo(id);
		model.addAttribute("list", list);
		
		return "review/writeReview";
	}
	@RequestMapping(value = "reviewWriteAction")
	public String ReviewWriteAction(ReviewDTO reviewDto,Model model, HttpSession session) throws IllegalStateException, IOException{
		String filePath = session.getServletContext().getRealPath("/resources/img/review");
		System.out.println("경로 : " + filePath);
		MultipartFile multipart = reviewDto.getUploadFile();
		String fileName = "";
		if(!multipart.isEmpty()) {
			fileName = multipart.getOriginalFilename();
			File file = new File(filePath,fileName);
			if(!file.exists()) {
				file.mkdirs();
			}
			multipart.transferTo(file);
		}
		reviewDto.setFileName(fileName);
		reviewDto.setMember_ID((String)session.getAttribute("id"));
		System.out.println(reviewDto);
		return "mainGo";
	}
	
}
