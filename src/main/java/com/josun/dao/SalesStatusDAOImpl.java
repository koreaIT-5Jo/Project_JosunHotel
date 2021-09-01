package com.josun.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.josun.dto.SalesStatusDTO;

public class SalesStatusDAOImpl implements SalesStatusDAO {
	@Autowired
	SqlSession sqlsession;

	@Override
	public int insert(int totalpay) {
		return sqlsession.insert("com.josun.mapper.salesStatusMapper.insert", totalpay);
	}

	@Override
	public int delete(int num) {
		return sqlsession.delete("com.josun.mapper.salesStatusMapper.delete", num);
	}

	@Override
	public int monthTotalPay(String startDate, String endDate) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		int result = 0;
		
		if(sqlsession.selectOne("com.josun.mapper.salesStatusMapper.monthTotalPay", map) != null) {
			result = sqlsession.selectOne("com.josun.mapper.salesStatusMapper.monthTotalPay", map);
		}
		return result;
	}

	@Override
	public int allMemberCount() {
		return sqlsession.selectOne("com.josun.mapper.salesStatusMapper.allMemberCount");
	}

	@Override
	public List<SalesStatusDTO> yearTotalPay() {
		return sqlsession.selectList("com.josun.mapper.salesStatusMapper.yearTotalPay");
	}

}
