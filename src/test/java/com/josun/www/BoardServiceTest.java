package com.josun.www;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.josun.dto.MemberDTO;
import com.josun.service.MemberService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class BoardServiceTest {
	@Autowired
	private MemberService service;
	
	@Test
	public void idCheck() throws Exception{
		System.out.println(service.idCheck("test1111"));
	}
	
	@Test
	public void register() throws Exception{
		service.registerMember("테스트", "test1111", "test1111", 1, "test1111", "test1111", "test1111", "test1111");
	}
	
	@Test
	public void login() throws Exception{
		List<MemberDTO> list = service.login("test1111", "test1111!");
		for(MemberDTO dto : list) {
			System.out.println(dto.getName() + ", " + dto.getAddress() + ", " + dto.getPhone() + ", " + dto.getEmail());
		}
	}
	
	@Test
	public void findId() throws Exception{
		System.out.println(service.findId("테스트", "010-1111-1111", "test1111@naver.com"));
	}
	
}
