package com.josun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.josun.dao.ReviewDAO;
import com.josun.dto.ReviewDTO;
import com.josun.vo.PageVO;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	ReviewDAO dao;
	
	@Override
	public List<ReviewDTO> reviewMainList(int currentPage) {
		List<ReviewDTO> list = dao.reivewMainList(currentPage); 
		return list;
	}

	@Override
	public int reviewPageCnt() {
		int writingCnt = dao.reviewWritingCnt();	// 총 글의 갯수
		final int PAGESIZE = PageVO.getPagesize();	// 페이지당 글의 갯수
		int pageCnt = writingCnt/PAGESIZE; 	// 총 페이지 수
		if(writingCnt> PAGESIZE*pageCnt) {pageCnt++;}
		if(pageCnt == 0) {pageCnt = 1;}		//	글의 갯수가 0개일때 방지 
		return pageCnt;
	}

	@Override
	public ReviewDTO detailReview(int idx) {
		dao.plusHitCount(idx);
		ReviewDTO dto = dao.detailReview(idx);
		return dto;
	}

	@Override
	public int writeReview(ReviewDTO dto) {
		int result = dao.writeReview(dto);
		return result;
	}

	@Override
	public int writeDelete(int idx) {
		int result = dao.writeDelete(idx);
		return result;
	}

	@Override
	public ReviewDTO ModityReviewInfo(int idx) {
		ReviewDTO dto = dao.detailReview(idx);
		return dto;
	}

	@Override
	public int writeModify(ReviewDTO dto) {
		int result = dao.writeModify(dto);
		return result;
	}


}
