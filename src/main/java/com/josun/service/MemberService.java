package com.josun.service;

import java.util.List;
import java.util.Map;

import com.josun.dto.MemberDTO;

public interface MemberService {
	//회원가입
	int idCheck(String id);
	void registerMember(MemberDTO dto);
	
	//로그인
	List<MemberDTO> login(String id, String pw);
	
	//아이디, 비밀번호 찾기
	String findId(String name, String phone, String email);
	String findPw(String name, String id, int pwHintQ, String pwHintA);
	
	//관리자페이지 - 회원목록
	List<MemberDTO> adminMemberList(int start, int end, String searchKey, String searchValue);
	//관리자페이지 - 회원목록 - 전체 데이터 개수
	int getDataCount(String searchKey, String searchValue);
	
	//회원정보 수정
	int updateMem(String id, String name, String address, String phone, String email);
	
	//비밀번호 변경
	int updatePw(String id, String pw);
	
	//회원정보 삭제
	String checkPw(String id);
	int deleteMem(String id, String pw);
	
	//예약확인
	List<Map<String, Object>> reserveConfirm(String id, String searchStartDate, String searchEndDate);
	
	//예약취소
	int reserveCancel(int num, int roomNumber, String startDate, String endDate) throws Exception;
}
