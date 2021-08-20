package com.josun.dao;

import com.josun.dto.MemberDTO;

public interface MemberDAO {
	int idCheck(String id);
	void registerMember(String name, String id, String pw, int pwHintQ, String pwHintA, String address, String phone, String email);
}
