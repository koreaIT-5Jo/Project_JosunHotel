package com.josun.dao;

import java.util.List;

import com.josun.dto.MemberDTO;

public interface MemberDAO {
	//회원가입
	int idCheck(String id);
	void registerMember(MemberDTO dto);
	
	//로그인
	List<MemberDTO> login(String id, String pw);
	
	//아이디, 비번 찾기
	String findId(String name, String phone, String email);
	String findPw(String name, String id, int pwHintQ, String pwHintA);
	
	//관리자페이지 - 회원목록
	List<MemberDTO> adminMemberList(int start, int end, String searchKey, String searchValue);
	//관리자페이지 - 회원목록 - 전체 데이타 갯수
	int getDataCount(String searchKey, String searchValue);
	
	//회원수정 - 회원정보 불러오기
	MemberDTO findMem(String id);
	int updateMem(String id, String name, String address, String phone, String email);
}
