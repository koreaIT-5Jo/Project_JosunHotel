package com.josun.dao;

import com.josun.dto.MemberDTO;

public interface MemberDAO {
	//회원가입
	int idCheck(String id);
	void registerMember(String name, String id, String pw, int pwHintQ, String pwHintA, String address, String phone, String email);
	
	//로그인
	int login(String id, String pw);
}
