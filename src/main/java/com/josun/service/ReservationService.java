package com.josun.service;

import java.util.List;

import com.josun.dto.ReservationDTO;

public interface ReservationService {
	int doReserve(ReservationDTO dto);
	
	//관리자페이지 - 예약내역 조회
	List<ReservationDTO> reserveList(int start, int end, String searchKey, String searchValue);
	//관리자페이지 - 전체 데이터 개수
	int getDataList(String searchKey, String searchValue);
}
