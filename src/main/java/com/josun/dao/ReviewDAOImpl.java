package com.josun.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.josun.dto.ReviewDTO;
import com.josun.vo.PageVO;

@Repository
public class ReviewDAOImpl implements ReviewDAO {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<ReviewDTO> reivewMainList(int page) {
		int size = PageVO.getPagesize(); 
		int rNumStart = (page-1)*size +1;
		int rNumEnd = page*size;
		
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		map.put("rNumStart", rNumStart);
		map.put("rNumEnd", rNumEnd);
		
		List<ReviewDTO> list = sqlSession.selectList("com.josun.mapper.reviewMapper.reviewMainList",map);   
		return list;
	}

	@Override
	public int reviewWritingCnt() {
		int writingCnt = sqlSession.selectOne("com.josun.mapper.reviewMapper.reviewWritingCnt");
		return writingCnt;
	}

}
