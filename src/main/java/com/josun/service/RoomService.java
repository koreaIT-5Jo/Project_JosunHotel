package com.josun.service;

import java.util.List;

import com.josun.dto.RoomDTO;

public interface RoomService {
	List<RoomDTO> getRoomList(String startDate, String endDate) throws Exception;
}
