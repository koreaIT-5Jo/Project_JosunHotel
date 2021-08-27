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
		dao.registerMember(new MemberDTO("테스트", "test1111", "test1111", 1, "test1111", "test1111", "test1111", "test1111"));
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
	@Test
	public void adminMemberList() throws Exception{
		List<MemberDTO> list = dao.adminMemberList(1, 10, "name", "테스트");
		for(MemberDTO dto : list) {
			System.out.println(dto.getName() + ", " + dto.getId() + ", " + dto.getPhone() + ", " + dto.getEmail() + ", " + dto.getAddress());
		}
	}
	@Test
	public void getDataCount() throws Exception{
		System.out.println(dao.getDataCount("name", "테스트"));
	}
	
	@Test
	public void testUpdateMem() throws Exception {
		System.out.println(dao.updateMem("admin", "관리자", "00000//서울특별시", "010-0000-0000", "admin@naer.com")); //1
	}
	
	@Test
	public void testCheckPw() throws Exception {
		System.out.println(dao.checkPw("minmi1234"));
	}
	
	@Test
	public void testUpdatePw() throws Exception {//pw, id순
		System.out.println(dao.updatePw("admin", "admin1234!")); //0
		System.out.println(dao.updatePw("admin1234!!", "admin"));//1
	}
	
	@Test
	public void testDeleteMem() throws Exception {
		System.out.println(dao.deleteMember("test0000", "test0000")); //0
	}
	
	
}
