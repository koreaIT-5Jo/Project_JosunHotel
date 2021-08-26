package com.josun.dao;

import com.josun.dto.ReservationDTO;

public interface ReservationDAO {
	int reservation(ReservationDTO dto);
	int reservedRoom(String startDate, String endDate,int room_number) throws Exception;
	int okReservation(String startDate, String endDate,int room_number) throws Exception;
}
