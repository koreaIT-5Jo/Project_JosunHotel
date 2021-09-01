package com.josun.service;

import java.util.List;

import com.josun.dto.ReviewDTO;

public interface ReviewService {
	List<ReviewDTO> reviewMainList(int currentPage);
	int reviewPageCnt();
	ReviewDTO detailReview(int idx);
	int writeReview(ReviewDTO dto);
	int writeDelete(int idx);
	ReviewDTO ModityReviewInfo(int idx);
	int writeModify(ReviewDTO dto);
}
