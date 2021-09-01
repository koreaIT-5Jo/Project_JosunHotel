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

	@Override
	public int hitCount(int idx) {
		return sqlSession.update(NAMESPACE + ".hitCount", idx);
	}

	@Override
	public BoardEventNoticeDTO detailView(int idx) {
		return sqlSession.selectOne(NAMESPACE + ".detailPost", idx);
	}

	@Override
	public int getPrevIdx(int idx) {
		String dbPrevIdx = sqlSession.selectOne(NAMESPACE + ".getPrevIdx", idx);
		int prevIdx = 0; 
		if(dbPrevIdx == null) {
			prevIdx = 0;
		} else {
			prevIdx = Integer.parseInt(sqlSession.selectOne(NAMESPACE + ".getPrevIdx", idx));
		}
		return prevIdx;
	}

	@Override
	public int getNextIdx(int idx) {
		String dbNextIdx = sqlSession.selectOne(NAMESPACE + ".getNextIdx", idx);
		int nextIdx = 0;
		if(dbNextIdx == null) {
			nextIdx = 0;
		} else {
			nextIdx = Integer.parseInt(sqlSession.selectOne(NAMESPACE + ".getNextIdx", idx));
		}
		return nextIdx;
	}

	@Override
	public int deletePost(int idx) {
		return sqlSession.delete(NAMESPACE + ".deletePost", idx);
	}

	@Override
	public int postWrite(int category, String title, String content, String fileName) {
		HashMap <String, Object> map = new HashMap <String, Object>();
		map.put("category", category);
		map.put("title", title);
		map.put("content", content);
		map.put("fileName", fileName);
		
		return sqlSession.insert(NAMESPACE + ".writePost", map);
	}

	@Override
	public int postUpdate(int category, String title, String content, String fileName, int idx) {
		HashMap <String, Object> map = new HashMap <String, Object>();
		map.put("category", category);
		map.put("title", title);
		map.put("content", content);
		map.put("fileName", fileName);
		map.put("idx", idx);
		
		return sqlSession.update(NAMESPACE + ".updatePost", map);
	}

}
