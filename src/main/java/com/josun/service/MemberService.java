package com.josun.service;

public interface MemberService {
	//회원가입
	int idCheck(String id);
	void registerMember(String name, String id, String pw, int pwHintQ, String pwHintA, String address, String phone, String email);
	
	//로그인
	int login(String id, String pw);
	
	//아이디, 비밀번호 찾기
	String findId(String name, String phone, String email);
	String findPw(String name, String id, int pwHintQ, String pwHintA);
}
