package com.josun.service;

public interface MemberService {
	void registerMember(String name, String id, String pw, int pwHintQ, String pwHintA, String address, String phone, String email);
}
