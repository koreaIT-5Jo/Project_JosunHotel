package com.josun.service;

import java.util.List;

import com.josun.dto.ReviewDTO;
import com.josun.vo.PageVO;

public interface ReviewService {
	List<ReviewDTO> reviewMainList(PageVO vo);
}
