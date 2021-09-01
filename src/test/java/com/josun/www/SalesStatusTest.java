package com.josun.www;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.josun.dao.SalesStatusDAO;
import com.josun.dto.SalesStatusDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class SalesStatusTest {
	@Autowired
	private SalesStatusDAO dao;
	
	@Test
	public void test() throws Exception{
		SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
		
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		System.out.println(year);
		
		String startDate = year+"-01-01";
		String endDate = year+"-12-31 23:59:59";
		
		System.out.println(dao.monthTotalPay(startDate, endDate));
	}
	
	@Test
	public void testList() throws Exception{
		DecimalFormat df = new DecimalFormat("###,###,###");
		List<SalesStatusDTO> list = dao.yearTotalPay();
		for(SalesStatusDTO dto : list) {
			String pay = df.format(dto.getTotalpay());
			dto.setPay(pay);
			System.out.println(dto.getMonth() + ", " + dto.getTotalpay() + ", " + dto.getPay());
		}
	}
	
}
