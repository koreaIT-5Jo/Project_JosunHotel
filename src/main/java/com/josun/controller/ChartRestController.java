package com.josun.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.josun.dao.SalesStatusDAO;
import com.josun.dao.VisitDAO;
import com.josun.dto.SalesStatusDTO;
import com.josun.dto.VisitDTO;

@RestController
@RequestMapping(value = "/dashboard", produces = "application/json; charset=utf-8")
public class ChartRestController {
	@Autowired
	VisitDAO visit;
	@Autowired
	SalesStatusDAO sales;
	
	//방문자 수 통계
	@RequestMapping("/getVisitData")
	public List<VisitDTO> getVisitData(){
		SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
		Date time = new Date();
		String today = format.format(time);
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		cal.add(Calendar.DATE, -4);
		String beforeDay = format.format(cal.getTime()); 
		List<VisitDTO> list = visit.selectList(beforeDay, today);
		return list;
	}
	
	//매출현황 숫자만
	@RequestMapping("/getSalesData")
	public Map<String, Object> getSalesMonthData(){
		Map<String, Object> map = new HashMap<String, Object>();
		SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
		DecimalFormat df = new DecimalFormat("###,###,###");
		Calendar cal = Calendar.getInstance();
		//현재 날짜로 설정
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
		
		//현재 달의 시작일, 종료일
		int start = cal.getActualMinimum(Calendar.DATE);
		int end = cal.getActualMaximum(Calendar.DATE);
		
		cal.set(year, month, start);
		String startDate = format.format(cal.getTime());
		cal.set(year, month, end);
		String endDate = format.format(cal.getTime());
		endDate += " 23:59:59";
		
		int totalpay = sales.monthTotalPay(startDate, endDate);
		String totalpayMonth = df.format(totalpay);
		map.put("totalpayMonth", totalpay);
		
		//이번년도 매출현황
		startDate = year+"-01-01";
		endDate = year+"-12-31 23:59:59";
		int totalpay2 = sales.monthTotalPay(startDate, endDate);
		String totalpayYear = df.format(totalpay2);
		map.put("totalpayYear", totalpay2);
		
		return map;
	}
	
	//전체 회원수
	@RequestMapping("/getMemberData")
	public Map<String, Object> getMemberData(){
		Map<String, Object> map = new HashMap<String, Object>();
		int result = sales.allMemberCount();
		map.put("result", result);
		return map;
	}
	
	//매출현황 - 월별
	@RequestMapping("/getSalesChartData")
	public List<SalesStatusDTO> getSalesChartData(){
		DecimalFormat df = new DecimalFormat("###,###,###");
		List<SalesStatusDTO> list = sales.yearTotalPay();
		for(SalesStatusDTO dto : list) {
			String pay = df.format(dto.getTotalpay());
			dto.setPay(pay);
			System.out.println(dto.getMonth() + ", " + dto.getTotalpay() + ", " + dto.getPay());
		}
		return list;
	}
}
