package com.josun.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.josun.dao.SalesStatusDAO;
import com.josun.dto.BoardEventNoticeDTO;
import com.josun.dto.BoardQnaCommentDTO;
import com.josun.dto.BoardQnaDTO;
import com.josun.dto.MemberDTO;
import com.josun.dto.ReservationDTO;
import com.josun.service.BoardEventNoticeService;
import com.josun.service.BoardQnaCommentService;
import com.josun.service.BoardQnaService;
import com.josun.service.MemberService;
import com.josun.service.ReservationService;
import com.josun.service.BoardQnaCommentService;
import com.josun.service.BoardQnaService;
import com.josun.service.MemberService;

@Controller
public class HomeController {
	@Autowired
	MemberService memberservice;
	@Autowired
	BoardQnaService qnaservice;
	@Autowired
	BoardQnaCommentService comservice;
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	BoardEventNoticeService enService;
	@Autowired
	ReservationService reserveservice;
	@Autowired
	SalesStatusDAO salesdao;
	
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
	public String enList(Model model, String page, String category, String keyword) {
		int curPage = 1;
		if(page != null) curPage = Integer.parseInt(page);
		System.out.println("현재페이지 : " + curPage);
		
		//검색을 하지 않았을 때 카테고리와 검색어 셋팅
		if((category == null && keyword == null) || (category == null && keyword.equals(""))) {
	         keyword = "%%";
	         category = "%%";
	    //검색을 했을 때
		} else if (category != null || keyword.equals("") || keyword != null) {
			if(category.equals("0")) { category = "%%"; }
			else { category = "%"+ category +"%"; }
			keyword = "%"+keyword+"%";
		}
		
		int totalPage = enService.totalCountSize(keyword, category);
		List<BoardEventNoticeDTO> list = enService.enList(keyword, category, curPage);
		for(BoardEventNoticeDTO dto : list) {
			if(dto.getCategory() == 1) { dto.setCateName("이벤트");}
			else if(dto.getCategory() == 2) { dto.setCateName("공지사항");}
		}	
		
		String param = "";
		String url = "enList";
		if(!keyword.equals("")){
			if(category.equals("0")) {
				param = "category="+"%%";
				param += "&content="+keyword;
			} else {
				param = "category="+category;
				param += "&content="+keyword;
			}
			url += "?"+param; 
		}
		
		StringBuffer pageNav = new StringBuffer();
		
		for(int i = curPage; i <= curPage; i++) { 
			int start = enService.startPage(keyword, category);
			int end = enService.endPage(keyword, category);
			
			int prev = i-1; 
			if(prev == 0) prev = start;
			
			int next = i+1; 
			if(end < next) next = end;
		  	
		  		pageNav.append("<a class=\"first\" href=\""+url+"&page="+start+"\"><span class=\"hidden\">first</span></a>&nbsp;");
		  		pageNav.append("<a class=\"prev\" href=\""+url+"&page="+prev+"\"><span class=\"hidden\">prev</span></a>&nbsp;");
		  		pageNav.append("<a class=\"current\" href=\""+url+"&page="+i+"\">"+i+"<span class=\"hidden\">현재페이지</span></a>&nbsp;");
		  		pageNav.append("<a class=\"next\" href=\""+url+"&page="+next+"\"><span class=\"hidden\">next</span></a>&nbsp;");
		  		pageNav.append("<a class=\"last\" href=\""+url+"&page="+end+"\"><span class=\"hidden\">last</span></a>&nbsp;"); 
		 }
		 
		model.addAttribute("enList", list);
		model.addAttribute("pageNav", pageNav.toString());
		return "board/board_EventNoticeList";
	}
	
	//게시판 - 이벤트, 공지사항 상세보기
	@RequestMapping(value = "/enDetailView")
	public String enDetailView(HttpServletRequest request, Model model, int idx) {
		//조회수 증가
		if(enService.hitCountUp(idx)) { System.out.println("조회수 업");}
		
		//글 상세보기
		BoardEventNoticeDTO dto = enService.detailView(idx);
		
		model.addAttribute("curIdx", dto.getIdx());
		model.addAttribute("title", dto.getTitle());
		model.addAttribute("content", dto.getContent());
		model.addAttribute("writeDate", dto.getWrite_date());
		
		//이전글, 다음글 idx
		int prevIdx = enService.getPrevIdx(idx);
		int nextIdx = enService.getNextIdx(idx);
		
		request.setAttribute("prevIdx", prevIdx);
		request.setAttribute("nextIdx", nextIdx);
		
		//이전글, 다음글 title
		String prevTitle = "";
		if(prevIdx == 0) { prevTitle = "이전 글이 없습니다."; }
		else { prevTitle = enService.detailView(prevIdx).getTitle(); }
		String nextTitle = "";
		if(nextIdx == 0) { nextTitle = "다음 글이 없습니다."; }
		else { nextTitle = enService.detailView(nextIdx).getTitle(); }
		
		model.addAttribute("prevTitle", prevTitle);
		model.addAttribute("nextTitle", nextTitle);
			
		return "board/board_EventNoticeDetailView";
	}
	
	//게시판 - 이벤트, 공지사항 글쓰기
	@RequestMapping(value = "/enWrite")
	public String enWrite() {
		return "board/board_EventNoticeWrite";
	}
		
	//게시판 - 이벤트, 공지사항 수정
	@RequestMapping(value = "/enModify")
	public String enModify(int idx, HttpServletRequest request) {
		BoardEventNoticeDTO dto = enService.detailView(idx);
		
		if(dto.getCategory() == 1) {
			request.setAttribute("cate1", dto.getCategory());
		} else if(dto.getCategory() == 2) {
			request.setAttribute("cate2", dto.getCategory());
		}
		
		request.setAttribute("idx", idx);
		request.setAttribute("tit", dto.getTitle());
		request.setAttribute("con", dto.getContent());
		request.setAttribute("fileName", dto.getFile_name());
		
		return "board/board_EventNoticeModify";
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
	public String login(ReservationDTO reservationDto,Model model,String nextURL) {
		model.addAttribute("reservationDto",reservationDto);
		model.addAttribute("nextURL",nextURL);
		
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
	
	//로그인 액션 로그인 하기전 화면으로 가기위한 액션
	@RequestMapping(value = "/loginActionUrl")
	public String loginActionUrl(HttpServletRequest request,MemberDTO memberDto,ReservationDTO reservationDto) {
		List<MemberDTO> list = memberservice.login(memberDto.getId(), memberDto.getPw());		
		HttpSession session = request.getSession();
		String url = (String)request.getParameter("nextUrl");
		if(list.size() > 0) {
			session.setAttribute("id", memberDto.getId());
			for(MemberDTO dto : list) {
				session.setAttribute("name", dto.getName());
				session.setAttribute("addr", dto.getAddress());
				session.setAttribute("phone", dto.getPhone());
				session.setAttribute("email", dto.getEmail());
			}
			if(url.equals("reservation/step1Go")) {
				request.setAttribute("reservationDto", reservationDto);
			}
			return url;
		}else {
			request.setAttribute("msg", "입력된 정보가 없습니다. 아이디 또는 비밀번호를 확인해주세요.");
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
	public String reserveConfirm(HttpServletRequest request, HttpSession session) {
		request.setAttribute("name", (String)session.getAttribute("name"));
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
		request.setAttribute("addr1", addr[0]);
		request.setAttribute("addr2", addr[1]);
		request.setAttribute("addr3", addr[2]);
		request.setAttribute("phone1", phone[0]);
		request.setAttribute("phone2", phone[1]);
		request.setAttribute("phone3", phone[2]);
		request.setAttribute("email1", email[0]);
		request.setAttribute("email2", email[1]);
		
		return "member/memberModify";
	}
	//마이페이지 - 비밀번호 변경
	@RequestMapping(value = "/memberPwChange")
	public String memberPwChange(HttpSession session, HttpServletRequest request) {
		request.setAttribute("name", (String)session.getAttribute("name"));
		return "member/memberPwChange";
	}
	//마이페이지 - 회원 탈퇴
	@RequestMapping(value = "/memberDelete")
	public String memberDelete(HttpServletRequest request, HttpSession session) {
		request.setAttribute("name", (String)session.getAttribute("name"));
		return "member/memberDelete1";
	}
	//마이페이지 - 회원 탈퇴2
	@RequestMapping(value = "/memberDeleteNext")
	public String memberDeleteNext(HttpServletRequest request, HttpSession session) {
		request.setAttribute("name", (String)session.getAttribute("name"));
		return "member/memberDelete2";
	}
	
	//관리자페이지 - 대시보드
	@RequestMapping(value = "/admin")
	public String admin() {
		return "admin/adminDashboard";
	}
	
	//관리자페이지 - 회원
	@RequestMapping(value = "/adminMember")
	public String adminMember(String page, String searchKey, String searchValue, Model model) {
		int pageNo = 1;
		if(page != null) pageNo = Integer.parseInt(page);
		
		int pageSize = 20;
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
	public String adminReservation(String page, String searchKey, String searchValue, Model model) {
		int pageNo = 1;
		if(page != null) pageNo = Integer.parseInt(page);
		int pageSize = 18;
		int start = pageNo * pageSize - (pageSize-1);
		int end = pageNo * pageSize;
		
		if(searchKey == null && searchValue == null) {
			searchKey = "name";
			searchValue = "%%";
		}else {
			searchValue = "%"+searchValue+"%";
		}
		
		List<ReservationDTO> list = reserveservice.reserveList(start, end, searchKey, searchValue);
		int dataCount = reserveservice.getDataList(searchKey, searchValue);
		int pageCount = dataCount / pageSize;
		if(dataCount%pageSize != 0) pageCount++;
		int totalPage = pageCount;
		
		String param = "";
		if(!searchValue.equals("")) {
			param = "searchKey="+searchKey;
			param += "&searchValue="+searchValue+"&";
		}
		String url = "adminReservation?"+param;
		StringBuffer pageNav = new StringBuffer();
		for(int i=1; i<=totalPage; i++) {
			if(pageNo == i) pageNav.append("<a class=\"active\" href=\""+url+"page="+i+"\">"+i+"</a>&nbsp;");
			else pageNav.append("<a href=\""+url+"page="+i+"\">"+i+"</a>&nbsp;");
		}
		model.addAttribute("list", list);
		model.addAttribute("pageNav", pageNav.toString());
		return "admin/adminReservation";
	}
	//관리자페이지 - Q&A목록
	@RequestMapping(value = "/adminQnaList")
	public String adminQnaList(String page, String searchKey, String searchValue, Model model) {
		int pageNo = 1;
		if(page != null) pageNo = Integer.parseInt(page);
		int pageSize = 20;
		int start = pageNo * pageSize - (pageSize-1);
		int end = pageNo * pageSize;
		
		if(searchKey == null && searchValue == null) {
			searchKey = "name";
			searchValue = "";
		}
		
		List<BoardQnaDTO> list = qnaservice.adminBoardList(start, end, searchKey, searchValue);
		int dataCount = qnaservice.getDataCount(searchKey, searchValue);
		int pageCount = dataCount / pageSize;
		if(dataCount%pageSize != 0) pageCount++;
		int totalPage = pageCount;
		
		String param = "";
		if(!searchValue.equals("")){
			param = "searchKey="+searchKey;
			param += "&searchValue="+searchValue+"&";
		}
		String url = "adminQnaList?"+param;
		StringBuffer pageNav = new StringBuffer();
		for(int i=1; i<=totalPage; i++){
			if(pageNo == i) pageNav.append("<a class=\"active\" href=\""+url+"page="+i+"\">"+i+"</a>&nbsp;");
			else pageNav.append("<a href=\""+url+"page="+i+"\">"+i+"</a>&nbsp;");
		}
		
		model.addAttribute("list", list);
//		model.addAttribute("searchKey", searchKey);
//		model.addAttribute("searchValue", searchValue);
//		model.addAttribute("page", page);
		model.addAttribute("pageNav", pageNav.toString());
		return "admin/adminQnaList";
	}
	//관리자페이지 - Q&A글상세보기
	@RequestMapping(value = "/adminQnaRead")
	public String adminQnaRead(int idx, Model model) {
		BoardQnaDTO dto = qnaservice.adminBoardRead(idx);
		List<BoardQnaCommentDTO> list = comservice.getCommentData(idx);
		model.addAttribute("dto", dto);
		model.addAttribute("list",list);
		return "admin/adminQnaRead";
	}
	//관리자페이지 - Q&A 댓글쓰기, 메일발송
	@RequestMapping(value = "/sendComment")
	public String sendComment(int qnaNum, String email, String content, Model model, BoardQnaCommentDTO cdto, HttpServletRequest request) {
		System.out.println(qnaNum + ", " + content + ", " + email);
		String subject = "[조선호텔제주] 문의주신 내용에 대한 답변입니다.";
		String from = "조선호텔 담당자 <himedia.sora@gmail.com>";
		String to = email;
		String msg = "";
		
		try {
			MimeMessage mail = mailSender.createMimeMessage();
			MimeMessageHelper mailHelper = new MimeMessageHelper(mail, "utf-8");
			
			mailHelper.setFrom(from);
			mailHelper.setTo(to);
			mailHelper.setSubject(subject);
			mailHelper.setText(content);
			
			mailSender.send(mail);
			comservice.insertComment(cdto);
			msg = "메일 전송이 완료되었습니다.";
		}catch(Exception e) {
			e.printStackTrace();
			msg = "메일 전송 실패";
		}
		
		BoardQnaDTO dto = qnaservice.adminBoardRead(qnaNum);
		List<BoardQnaCommentDTO> list = comservice.getCommentData(qnaNum);
		model.addAttribute("dto", dto);
		model.addAttribute("list",list);
		request.setAttribute("msg", msg);
		return "admin/adminQnaRead";
	}

}
