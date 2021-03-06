package com.josun.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.josun.dao.MemberDAO;
import com.josun.dto.MemberDTO;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDAO dao;
	
	//회원가입
	@Override
	public int idCheck(String id) {
		return dao.idCheck(id);
	}

	@Override
	public void registerMember(MemberDTO dto) {
		dao.registerMember(dto);
	}
	
	//로그인
	@Override
	public List<MemberDTO> login(String id, String pw) {
		List<MemberDTO> list = dao.login(id, pw); 
		return list;
	}

	//아이디, 비밀번호 찾기
	@Override
	public String findId(String name, String phone, String email) {
		return dao.findId(name, phone, email);
	}
	
	@Override
	public String findPw(String name, String id, int pwHintQ, String pwHintA) {
		return dao.findPw(name, id, pwHintQ, pwHintA);
	}
	
	//관리자페이지 - 회원목록
	@Override
	public List<MemberDTO> adminMemberList(int start, int end, String searchKey, String searchValue) {
		List<MemberDTO> list = dao.adminMemberList(start, end, searchKey, searchValue);
		return list;
	}
	//관리자페이지 - 회원목록 - 전체 데이터 개수
	@Override
	public int getDataCount(String searchKey, String searchValue) {
		return dao.getDataCount(searchKey, searchValue);
	}
	
	//회원정보 수정
	@Override
	public int updateMem(String id, String name, String address, String phone, String email) {
		return dao.updateMem(id, name, address, phone, email);
	}
	
	//비밀번호 변경
	@Override
	public int updatePw(String id, String pw) {
		return dao.updatePw(pw, id);
	}
	
	//회원정보 삭제
	@Override
	public String checkPw(String id) {
		String pw = dao.checkPw(id);
		String result = "";
		
		if(pw != null) {
			result = "아이디 찾기 완료";
			System.out.println(result);
		} else {
			result = "가입된 아이디를 찾을 수 없음";
			System.out.println(result);
		}
		return pw;
	}
	@Override
	public int deleteMem(String id, String pw) {
		return dao.deleteMember(id, pw);
	}
	
	//예약확인
	@Override
	public List<Map<String, Object>> reserveConfirm(String id, String searchStartDate, String searchEndDate) {
		List<Map<String, Object>> list = dao.reserveConfirm(id, searchStartDate, searchEndDate);
		return list;
	}
	
	//예약취소
	@Override
	public int reserveCancel(int num, int roomNumber, String startDate, String endDate) throws Exception {
		int result = dao.reserveCancel(num, roomNumber, startDate, endDate);
		return result;
	}

}
