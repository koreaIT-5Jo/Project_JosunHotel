package com.josun.dao;

import java.util.HashMap;
import java.util.List;

import com.josun.dto.RoomDTO;

public interface RoomDAO {
	List<RoomDTO> getRoomList(String startDate, String endDate) throws Exception;
	String getDuplicateCheckRoomNumList(String startDate, String endDate)throws Exception;
	HashMap<String,String> roomDetailInfo(int num);
	RoomDTO roomOptionInfo(int num);
}
