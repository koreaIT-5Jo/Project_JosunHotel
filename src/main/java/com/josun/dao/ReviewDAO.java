package com.josun.dao;

import java.util.List;

import com.josun.dto.ReviewDTO;

public interface ReviewDAO {
	List<ReviewDTO> reivewMainList(int page);
	int reviewWritingCnt();
	ReviewDTO detailReview(int idx);
	int writeReview(ReviewDTO dto);
	int writeDelete(int idx);
	int plusHitCount(int idx);
	int writeModify(ReviewDTO dto);
}
