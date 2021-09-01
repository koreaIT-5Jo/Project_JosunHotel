package com.josun.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.josun.dao.VisitDAO;
import com.josun.dto.VisitDTO;

public class VisitSessionListener implements HttpSessionListener{
	private VisitDAO dao;
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		//세션이 실행될때
		HttpSession session = se.getSession(); 
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext()); 
		dao = (VisitDAO) context.getBean("VisitDAO");


		SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
		Date time = new Date();
		String v_date = format.format(time);
		System.out.println("새로운 세션 발생! 오늘날짜 : " + v_date);
		
		if(dao.isToday(v_date)) {
			dao.update(v_date);
		}else {
			VisitDTO dto = new VisitDTO();
			dto.setV_date(v_date);
			dto.setV_count(1);
			dao.insert(dto);
		}
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		//세션이 실행안될때
	}

}
