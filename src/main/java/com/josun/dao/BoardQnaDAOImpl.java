package com.josun.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.josun.dto.BoardQnaDTO;

public class BoardQnaDAOImpl implements BoardQnaDAO {
	@Autowired
	private SqlSession sqlsession;
	
	//글쓰기
	@Override
	public void insertBoard(BoardQnaDTO dto) {
		sqlsession.insert("com.josun.mapper.BoardQnaMapper.insertBoard", dto);
	}
	
	//글목록
	@Override
	public List<BoardQnaDTO> adminBoardList(int start, int end, String searchKey, String searchValue) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		map.put("searchKey", searchKey);
		map.put("searchValue", "%"+searchValue+"%");
		List<BoardQnaDTO> list = sqlsession.selectList("com.josun.mapper.BoardQnaMapper.adminBoardList", map);
		return list;
	}
	//글목록 - 전체 데이터 개수
	@Override
	public int getDataCount(String searchKey, String searchValue) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("searchKey", searchKey);
		map.put("searchValue", "%"+searchValue+"%");
		return sqlsession.selectOne("com.josun.mapper.BoardQnaMapper.getDataCount", map);
	}

	//글 상세보기
	@Override
	public BoardQnaDTO adminBoardRead(int idx) {
		return sqlsession.selectOne("com.josun.mapper.BoardQnaMapper.adminBoardRead", idx);
	}

}
