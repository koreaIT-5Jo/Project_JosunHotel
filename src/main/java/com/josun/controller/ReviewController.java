package com.josun.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

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
			if(dto.getFileName() == null || dto.getFileName().equals("")) {
				dto.setFile_Name(dto.getRoom_Number() +".jpg");;
			}
		}
		model.addAttribute("pageVo",pageVo);
		model.addAttribute("list",list);
		
		return "review/reviewMain";
	}
	@RequestMapping(value = "detailView")
	public String detailView(int bno,Model model ) {
		ReviewDTO reviewDto = reviewService.detailReview(bno);
		RoomDTO roomDto = roomService.detailReviewRoomInfo(reviewDto.getRoom_Number());
		
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
		String uuid = UUID.randomUUID().toString();
		String fileName = "";
		if(!multipart.isEmpty()) {
			fileName = uuid + "_" + multipart.getOriginalFilename();
			File file = new File(filePath,fileName);
			if(!file.exists()) {
				file.mkdirs();
			}
			multipart.transferTo(file);
		}
		reviewDto.setFile_Name(fileName);
		reviewDto.setMember_ID((String)session.getAttribute("id"));
		
		reviewService.writeReview(reviewDto);	// 글쓰기 디비 저장
		
		return "review/mainGo";
	}
	@RequestMapping(value = "delete")
	public String delete(int idx,String fileName, HttpSession session) {
		reviewService.writeDelete(idx);	// 디비 정보 삭제
		String filePath = session.getServletContext().getRealPath("/resources/img/review");
		
		
		File file = new File(filePath,fileName);
		if(file.exists()) {
			file.delete();
		}
		
		return "review/mainGo";
	}
	@RequestMapping(value = "modify")
	public String modifyPage(int idx,HttpSession session,Model model) {
		ReviewDTO dto = reviewService.ModityReviewInfo(idx);
		String id = (String) session.getAttribute("id");
		List<RoomDTO> list = roomService.writeReviewRoomInfo(id);
		
		model.addAttribute("reviewDto",dto);	
		model.addAttribute("list", list);
		return "review/modifyReview";
	}
	@RequestMapping(value = "modifyReviewAction")
	public String modifyAction(String originFileName,ReviewDTO reviewDto, HttpSession session) throws IllegalStateException, IOException {
		String filePath = session.getServletContext().getRealPath("/resources/img/review");
		System.out.println("경로 : " + filePath);
		MultipartFile multipart = reviewDto.getUploadFile();
		String uuid = UUID.randomUUID().toString();
		String fileName = "";
		if(!multipart.isEmpty()) {
			System.out.println("noEmpty");
			fileName = uuid + "_" + multipart.getOriginalFilename();
			File file = new File(filePath,fileName);
			
			if(!file.exists()) {
				file.mkdirs();
			}
			if(!(originFileName == null || originFileName == "")) {
				//System.out.println("originFileName : "+originFileName);
				File originFile = new File(filePath,originFileName);	// 기존파일
				if(originFile.exists()) {
					originFile.delete();
				}
			}
			
			multipart.transferTo(file);
			reviewDto.setFile_Name(fileName);
		} else {				
			System.out.println("Empty");
			
			 if(!(originFileName == null || originFileName == "")) { 
				 fileName = originFileName; 
				 reviewDto.setFile_Name(originFileName); 
			 }
		}
		reviewDto.setMember_ID((String)session.getAttribute("id"));
		
		//System.out.println(reviewDto);
		reviewService.writeModify(reviewDto);
		
		return "review/mainGo";
	}
}
