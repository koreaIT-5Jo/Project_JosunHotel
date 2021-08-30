package com.josun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.josun.dao.BoardEventNoticeDAO;
import com.josun.dto.BoardEventNoticeDTO;

@Service
public class BoardEventNoticeServiceImpl implements BoardEventNoticeService {
	@Autowired
	BoardEventNoticeDAO dao;
	
	//페이지네이션
	private static final int PAGESIZE = 5;	//한 페이징에 표시될 페이지 수
	
	@Override
	public List<BoardEventNoticeDTO> enList(String content, String category, int page) {
		int totalSize = totalCountSize(content, category);	//총 페이징 수
		int pageNum = page;									//현재페이지
		
		int staRow = pageNum * PAGESIZE - (PAGESIZE-1);		//게시물 rowNum 시작
		int endRow = pageNum * PAGESIZE;					//게시물 rowNum 끝
		
		List<BoardEventNoticeDTO> list;
		
		if(totalSize <= pageNum) {
			pageNum = totalSize;
			list = dao.enList(content, category, staRow, endRow);
		} else {
			list = dao.enList(content, category, staRow, endRow);
		}
		
		
		return list;
	}
	
	@Override
	public int totalCountSize(String content, String category) {
		int totalPost = dao.totalCount(content, category);	//DB 총 게시물 수
		int totalSize = totalPost / PAGESIZE;			    //총 페이징 수
		
		if ((totalPost / PAGESIZE) == 0)
			totalSize = 1;
		else if((totalPost % PAGESIZE) > 0)
			totalSize++;
		
		return totalSize;
	}
	
	@Override
	public int startPage(String content, String category) {
		int totalSize = totalCountSize(content, category);
		int startPage = totalSize - (totalSize-1);
		return startPage;
	}
	
	@Override
	public int endPage(String content, String category) {
		int endPage = totalCountSize(content, category);
		return endPage;
	}

	@Override
	public boolean hitCountUp(int idx) {
		int result = dao.hitCount(idx);
		return result==1;
	}

	@Override
	public BoardEventNoticeDTO detailView(int idx) {
		return dao.detailView(idx);
	}

	@Override
	public int getPrevIdx(int idx) {
		return dao.getPrevIdx(idx);
	}

	@Override
	public int getNextIdx(int idx) {
		return dao.getNextIdx(idx);
	}

}
