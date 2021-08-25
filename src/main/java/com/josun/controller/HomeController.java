package com.josun.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.josun.dto.BoardQnaDTO;
import com.josun.dto.MemberDTO;
import com.josun.service.BoardQnaService;
import com.josun.service.MemberService;

@Controller
public class HomeController {
	@Autowired
	MemberService memberservice;
	@Autowired
	BoardQnaService qnaservice;
	
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
	//게시판 - Q&A게시판 글쓰기
	@RequestMapping(value = "/insertBoardQna")
	public String insertBoardQna(BoardQnaDTO qnadto, HttpServletRequest request, HttpSession session) {
		String filePath = session.getServletContext().getRealPath("/resources/upload/");
		System.out.println("filePath(절대경로) : " + filePath); //절대경로
		MultipartFile multipart = qnadto.getMultipart();
		String uuid = UUID.randomUUID().toString();
		String filename = "첨부파일 없음";
		if(!multipart.isEmpty()) {
			filename = uuid + "_" + multipart.getOriginalFilename();
			System.out.println("filename(uuid 추가) : " + filename); //파일명
			File file = new File(filePath, filename);
			if(!file.exists()) {
				file.mkdirs();
			}
			try {
				multipart.transferTo(file);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		qnadto.setFileName(filename);
		qnaservice.insertBoard(qnadto);
		request.setAttribute("msg", "문의주신 내용이 접수되었습니다. 담당자가 확인 후 연락드립니다.");
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
	//회원가입 아이디 중복확인
	@RequestMapping(value = "/idCheck")
	@ResponseBody
	public Integer idCheck(String id) {
		return memberservice.idCheck(id);
	}
	//회원가입 확인
	@RequestMapping(value = "/registerMember")
	public String registerMember(HttpServletRequest requeset, MemberDTO dto) {
		requeset.setAttribute("msg", "회원가입이 완료되었습니다.");
		memberservice.registerMember(dto);
		return "member/login";
	}
	
	//로그인
	@RequestMapping(value = "/login")
	public String login() {
		return "member/login";
	}
	//로그인 액션
	@RequestMapping(value = "/loginAction")
	public String loginAction(HttpServletRequest requeset, HttpSession session, String id, String pw) {
		List<MemberDTO> list = memberservice.login(id, pw);
		if(list.size() > 0) {
			session.setAttribute("id", id);
			for(MemberDTO dto : list) {
				session.setAttribute("name", dto.getName());
				session.setAttribute("addr", dto.getAddress());
				session.setAttribute("phone", dto.getPhone());
				session.setAttribute("email", dto.getEmail());
			}
			return "home";
		}else {
			requeset.setAttribute("msg", "입력된 정보가 없습니다. 아이디 또는 비밀번호를 확인해주세요.");
			return "member/login";
		}
	}
	//로그아웃
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "home";
	}
	
	//아이디 비번 찾기 
	@RequestMapping(value = "/find")
	public String find() {
		return "member/findIdPw";
	}
	//아이디 찾기
	@RequestMapping(value = "/findId")
	@ResponseBody
	public String findId(String name, String phone, String email) {
		String result = memberservice.findId(name, phone, email);
		return result;
	}
	//비밀번호 찾기
	@RequestMapping(value = "/findPw")
	@ResponseBody
	public String findPw(String name, String id, int pwHintQ, String pwHintA) {
		String result = memberservice.findPw(name, id, pwHintQ, pwHintA);
		return result;
	}

	//마이페이지 - 예약확인
	@RequestMapping(value = "/reserveConfirm")
	public String reserveConfirm() {
		return "member/reserveConfirm";
	}
	//마이페이지 - 회원정보 수정
	@RequestMapping(value = "/memberModify")
	public String memberModify(HttpSession session, HttpServletRequest request) {
		String name = (String)session.getAttribute("name");
		String userAddr = (String)session.getAttribute("addr");
		String userPhone = (String)session.getAttribute("phone");
		String userEmail = (String)session.getAttribute("email");
		String[] addr = userAddr.split("//");
		String[] phone = userPhone.split("-");
		String[] email = userEmail.split("@");
		
		request.setAttribute("name", name);
		request.setAttribute("addr", addr);
		request.setAttribute("phone", phone);
		request.setAttribute("email", email);
		
		return "member/memberModify";
	}
	@RequestMapping(value = "/memberModifyAction")
	public Map<String,Object> memberModifyAction(HttpServletRequest request, HttpSession session, @RequestBody Map<String,String> param) {
		String id = (String)session.getAttribute("id");
		String name = param.get("name");
		String address = param.get("address");
		String phone = param.get("phone");
		String email = param.get("email");
		
		System.out.println("파라미터 확인: " + name + " " + address + " " + phone + " " + email);
		
		int result = memberservice.updateMem(id, name, address, phone, email);
		System.out.println(" 업데이트 결과 : " + result);
		
		boolean fileSaveCheck = false;
		
		if(result == 1) {
			fileSaveCheck = true;
		} else {
			fileSaveCheck = false;
		}
		
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("fileSaveCheck", fileSaveCheck);
		
		return map1;
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
	public String adminMember(HttpServletRequest request, String page, String searchKey, String searchValue, Model model) {
		int pageNo = 1;
		if(page != null) pageNo = Integer.parseInt(page);
		
		int pageSize = 5;
		int start = pageNo * pageSize - (pageSize -1);
		int end = pageNo * pageSize;
		
		if(searchKey == null && searchValue == null) {
			searchKey = "name";
			searchValue = "";
		}
		
		List<MemberDTO> list = memberservice.adminMemberList(start, end, searchKey, searchValue);
		
		int dataCount = memberservice.getDataCount(searchKey, searchValue);
		int pageCount = dataCount / pageSize;
		if(dataCount%pageSize != 0) pageCount++;
		int totalPage = pageCount;
		
		String param = "";
		if(!searchValue.equals("")){
			param = "searchKey="+searchKey;
			param += "&searchValue="+searchValue+"&";
		}
		String url = "adminMember?"+param;
		StringBuffer pageNav = new StringBuffer();
		for(int i=1; i<=totalPage; i++){
			if(pageNo == i) pageNav.append("<a class=\"active\" href=\""+url+"page="+i+"\">"+i+"</a>&nbsp;");
			else pageNav.append("<a href=\""+url+"page="+i+"\">"+i+"</a>&nbsp;");
		}
		
		model.addAttribute("list", list);
		model.addAttribute("pageNav", pageNav.toString());
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
	
	
	
}
