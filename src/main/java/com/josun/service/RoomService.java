package com.josun.service;

import java.util.HashMap;
import java.util.List;

import com.josun.dto.RoomDTO;

public interface RoomService {
	List<RoomDTO> getRoomList(String startDate, String endDate) throws Exception;
	HashMap<String,Object> roomDetailInfo(int num);
}
