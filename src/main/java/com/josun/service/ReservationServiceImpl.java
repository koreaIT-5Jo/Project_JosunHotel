package com.josun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.josun.dao.ReservationDAO;
import com.josun.dao.SalesStatusDAO;
import com.josun.dto.ReservationDTO;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	ReservationDAO dao;
	@Autowired
	SalesStatusDAO sdao;
	
	@Override
	public int doReserve(ReservationDTO dto) {
		int okReservation;
		int reservationResult;
		int reservedRoomResult;
		String startDate = dto.getStartDate();
		String endDAte = dto.getEndDate();
		int num = dto.getRoom_number();
		try {
			okReservation = dao.okReservation( startDate,endDAte,num);
			if(okReservation == 0) {	// 0일때 예약 가능
				reservationResult = dao.reservation(dto);	// 예약
				System.out.println(dto.getTotal_pay());
				sdao.insert(dto.getTotal_pay()); // 매출현황 테이블 insert
				if(reservationResult == 1) {	// 예약 입력 성공
					reservedRoomResult = dao.reservedRoom(startDate, endDAte, num);
					if(reservedRoomResult > 0) {
						return 1; // 예약 정보 입력 성공
					} else {
						return -1;	// reserved_room table 정보입력 실패
					}
				}else { 	
					return -2;	// 예약 정보입력 입력 실패
				}
			} else {	 
				return -3;	// 실패 : 예약하려는 방이 선택한 일정에 이미 예약 한경우
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return -4;	// 그냥 에러
	}
	
	//관리자페이지 - 예약내역 조회
	@Override
	public List<ReservationDTO> reserveList(int start, int end, String searchKey, String searchValue) {
		List<ReservationDTO> list = dao.reserveList(start, end, searchKey, searchValue);
		return list;
	}
	//관리자페이지 - 전체 데이터 개수
	@Override
	public int getDataList(String searchKey, String searchValue) {
		return dao.getDataList(searchKey, searchValue);
	}

}
