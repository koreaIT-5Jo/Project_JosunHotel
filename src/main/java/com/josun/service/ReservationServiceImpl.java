package com.josun.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.josun.dao.ReservationDAO;
import com.josun.dto.ReservationDTO;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	ReservationDAO dao;
	
	@Override
	public boolean doReserve(ReservationDTO dto) {
		int okReservation;
		int reservationResult;
		int reservedRoomResult;
		String startDate = dto.getStartDate();
		String endDAte = dto.getEndDate();
		int num = dto.getNum();
				
		try {
			okReservation = dao.okReservation( startDate,endDAte,num);
			if(okReservation == 0) {	// 0일때 예약 가능
				reservationResult = dao.reservation(dto);	// 예약
				if(reservationResult == 1) {	// 예약 입력 성공
					reservedRoomResult = dao.reservedRoom(startDate, endDAte, num);
					if(reservedRoomResult > 0) {
						return true;
					} else {
						return false;
					}
				}else { 	// 예약 입력 실패
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public int doReserve2(ReservationDTO dto) {
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
				if(reservationResult == 1) {	// 예약 입력 성공
					reservedRoomResult = dao.reservedRoom(startDate, endDAte, num);
					if(reservedRoomResult > 0) {
						return 1; // 예약성공 완료
					} else {
						return -1;	// 예약된 방 정보입력 실패
					}
				}else { 	// 예약 입력 실패
					return -2;
				}
			} else {	//  실패 : 예약하려는 방이 선택한 일정에 이미 예약 한경우 
				return -3;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		// 그냥 에러
		return -4;
	}

}
