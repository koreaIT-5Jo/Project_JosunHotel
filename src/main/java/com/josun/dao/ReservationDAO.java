package com.josun.dao;

import java.util.List;

import com.josun.dto.ReservationDTO;

public interface ReservationDAO {
	int reservation(ReservationDTO dto);
	int reservedRoom(String startDate, String endDate,int room_number) throws Exception;
	int okReservation(String startDate, String endDate,int room_number) throws Exception;
	
	//관리자페이지 - 예약내역 조회
	List<ReservationDTO> reserveList(int start, int end, String searchKey, String searchValue);
	//관리자페이지 - 전체 데이터 개수
	int getDataList(String searchKey, String searchValue);
}
