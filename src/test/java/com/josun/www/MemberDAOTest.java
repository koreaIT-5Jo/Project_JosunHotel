package com.josun.www;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.josun.dao.MemberDAO;
import com.josun.dto.MemberDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class MemberDAOTest {
	@Autowired
	private MemberDAO dao;
	
	@Test
	public void register() throws Exception{
		dao.registerMember("테스트", "test1111", "test1111", 1, "test1111", "test1111", "test1111", "test1111");
	}
	@Test
	public void idCheck() throws Exception{
		System.out.println(dao.idCheck("test1111"));
	}
	@Test
	public void login() throws Exception{
		System.out.println(dao.login("test1111", "test1111!"));
	}
	@Test
	public void findId() throws Exception{
		System.out.println(dao.findId("관리자", "010-4315-7788", "sol6327@naver.com"));
	}
	@Test
	public void findPw() throws Exception{
		System.out.println(dao.findPw("테스트", "test1111", 1, "1"));
	}
}
