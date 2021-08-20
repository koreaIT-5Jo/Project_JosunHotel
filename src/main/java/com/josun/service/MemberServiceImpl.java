package com.josun.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.josun.dao.MemberDAO;

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
	public void registerMember(String name, String id, String pw, int pwHintQ, String pwHintA, String address, String phone, String email) {
		dao.registerMember(name, id, pw, pwHintQ, pwHintA, address, phone, email);
	}
	
	//로그인
	@Override
	public int login(String id, String pw) {
		return dao.login(id, pw);
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


}
