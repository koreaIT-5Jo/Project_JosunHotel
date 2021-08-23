package com.josun.dao;

import java.util.List;

import com.josun.dto.RoomDTO;

public interface RoomDAO {
	List<RoomDTO> getRoomList(String startDate, String endDate) throws Exception;
	String getDuplicateCheckRoomNumList(String startDate, String endDate)throws Exception;
	List<String> getDuplicateCheckRoomNumList2(String startDate, String endDate)throws Exception;
}
