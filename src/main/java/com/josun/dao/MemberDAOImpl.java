package com.josun.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.josun.dto.MemberDTO;

public class MemberDAOImpl implements MemberDAO {
	@Autowired
	private SqlSession sqlsession; 

	@Override
	public void registerMember(String name, String id, String pw, int pwHintQ, String pwHintA, String address, String phone, String email) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("id", id);
		map.put("pw", pw);
		map.put("pwHintQ", pwHintQ);
		map.put("pwHintA", pwHintA);
		map.put("address", address);
		map.put("phone", phone);
		map.put("email", email);
		sqlsession.insert("com.josun.mapper.MemberMapper.registerMember", map);
	}

}
