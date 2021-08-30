package com.josun.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.josun.dto.BoardEventNoticeDTO;

public class BoardEventNoticeDAOImpl implements BoardEventNoticeDAO {
	@Autowired
	private SqlSession sqlSession; 
	
	//EventNoticemapper nameSpace
	private static final String NAMESPACE = "com.josun.mapper.BoardEventNoticeMapper";
		
	@Override
	public List<BoardEventNoticeDTO> enList(String content, String category, int startNum, int endNum) {
		HashMap <String, Object> map = new HashMap <String, Object>();
		map.put("content", content);
		map.put("category", category);
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		return sqlSession.selectList(NAMESPACE + ".enbList", map);
	}

	@Override
	public int totalCount(String content, String category) {
		HashMap <String, String> map = new HashMap <String, String>();
		map.put("content", content);
		map.put("category", category);
		return sqlSession.selectOne(NAMESPACE + ".totalCount", map);
	}

}
