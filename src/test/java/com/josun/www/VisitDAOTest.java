package com.josun.www;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.josun.dao.VisitDAO;
import com.josun.dto.VisitDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class VisitDAOTest {
	@Autowired
	private VisitDAO dao;
	
	@Test
	public void test() throws Exception{
		System.out.println(dao.isToday("2021-08-31"));
	}
	
	@Test
	public void insert() throws Exception{
		System.out.println(dao.insert(new VisitDTO("2021-08-28",1)));
	}
	
}
