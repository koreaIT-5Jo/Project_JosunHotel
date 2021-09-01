package com.josun.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.josun.dto.VisitDTO;

public class VisitDAO{
	@Autowired
	private SqlSession sqlsession;
	
	public boolean isToday(String v_date) {
		boolean result = false;
		int count = sqlsession.selectOne("com.josun.mapper.visitMapper.selectToday", v_date);
		if(count == 1) result = true;
		return result;
	}
	
	public int insert(VisitDTO dto) {
		return sqlsession.insert("com.josun.mapper.visitMapper.insertCount", dto);
	}
	
	public int update(String v_date) {
		return sqlsession.update("com.josun.mapper.visitMapper.update", v_date);
	}
	
	public List<VisitDTO> selectList(String beforeDay, String today) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("beforeDay", beforeDay);
		map.put("today", today);
		return sqlsession.selectList("com.josun.mapper.visitMapper.selectList", map);
	}
}
