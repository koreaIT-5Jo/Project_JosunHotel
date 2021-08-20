package com.josun.www;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.josun.service.MemberService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class MemberServiceTest {
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
}
