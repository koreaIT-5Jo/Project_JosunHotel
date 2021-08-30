package com.josun.service;

import java.util.List;

import com.josun.dto.ReviewDTO;

public interface ReviewService {
	List<ReviewDTO> reviewMainList(int currentPage);
	int reviewPageCnt();
}
