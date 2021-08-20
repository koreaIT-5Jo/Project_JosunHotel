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


}
