package com.josun.dao;

import java.util.List;

import com.josun.dto.RoomDTO;

public interface RoomDAO {
	List<RoomDTO> getRoomNumList(String startDate, String endDate);
	List<Integer> getReserveDateList(String startDate, String endDate);
}
