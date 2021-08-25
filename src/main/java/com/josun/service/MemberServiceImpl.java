package com.josun.service;

import java.util.List;

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

	@Override
	public MemberDTO findMem(String id) {
		return dao.findMem(id);
	}

	@Override
	public int updateMem(String id, String name, String address, String phone, String email) {
		return dao.updateMem(id, name, address, phone, email);
	}


}