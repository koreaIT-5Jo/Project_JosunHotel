package com.josun.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.josun.dto.MemberDTO;

public class MemberDAOImpl implements MemberDAO {
	@Autowired
	private SqlSession sqlsession; 
	
	//Membermapper nameSpace
	private static final String NAMESPACE = "com.josun.mapper.MemberMapper";
	
	//회원가입
	@Override
	public int idCheck(String id) {
		int result = sqlsession.selectOne("com.josun.mapper.MemberMapper.idCheck", id);
		return result;
	}

	@Override
	public void registerMember(MemberDTO dto) {
		sqlsession.insert("com.josun.mapper.MemberMapper.registerMember", dto);
	}

	//로그인
	@Override
	public List<MemberDTO> login(String id, String pw) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pw", pw);
		List<MemberDTO> list = sqlsession.selectList("com.josun.mapper.MemberMapper.loginAction", map);
		return list;
	}
	
	//아이디, 비밀번호 찾기
	@Override
	public String findId(String name, String phone, String email) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("name", name);
		map.put("phone", phone);
		map.put("email", email);
		String result = sqlsession.selectOne("com.josun.mapper.MemberMapper.findId", map);
		return result;
	}

	@Override
	public String findPw(String name, String id, int pwHintQ, String pwHintA) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("id", id);
		map.put("pwHintQ", pwHintQ);
		map.put("pwHintA", pwHintA);
		String result = sqlsession.selectOne("com.josun.mapper.MemberMapper.findPw", map);
		return result;
	}
	
	//관리자페이지 - 회원목록
	@Override
	public List<MemberDTO> adminMemberList(int start, int end, String searchKey, String searchValue) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		map.put("searchKey", searchKey);
		map.put("searchValue", "%"+searchValue+"%");
		List<MemberDTO> list = sqlsession.selectList("com.josun.mapper.MemberMapper.adminMemberList", map);
		return list;
	}
	//관리자페이지 - 회원목록 - 전체 데이터 개수
	@Override
	public int getDataCount(String searchKey, String searchValue) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("searchKey", searchKey);
		map.put("searchValue", "%"+searchValue+"%");
		return sqlsession.selectOne("com.josun.mapper.MemberMapper.getDataCount", map);
	}
	
	//회원정보 수정
	@Override
	public int updateMem(String id, String name, String address, String phone, String email) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("name", name);
		map.put("address", address);
		map.put("phone", phone);
		map.put("email", email);
		return sqlsession.update(NAMESPACE + ".updateMem", map);
	}
	
	//비밀번호 변경
	@Override
	public int updatePw(String pw, String id) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("pw", pw);
		map.put("id", id);
		return sqlsession.update(NAMESPACE + ".updatePw", map);
	}
	
	//회원정보 삭제
	@Override
	public String checkPw(String id) {
		return sqlsession.selectOne(NAMESPACE + ".checkPw", id);
	}
	@Override
	public int deleteMember(String id, String pw) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pw", pw);
		return sqlsession.delete(NAMESPACE + ".deleteMem", map);
	}

	@Override
	public List<Map<String, Object>> reserveConfirm(String id, String searchStartDate, String searchEndDate) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("searchStartDate", searchStartDate);
		map.put("searchEndDate", searchEndDate);
		return sqlsession.selectList(NAMESPACE + ".reserveConfirm", map);
	}

	
}
