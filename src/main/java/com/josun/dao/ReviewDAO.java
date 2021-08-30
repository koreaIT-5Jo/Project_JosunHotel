package com.josun.dao;

import java.util.List;

import com.josun.dto.ReviewDTO;

public interface ReviewDAO {
	List<ReviewDTO> reivewMainList(int page);
	int reviewWritingCnt();
}
