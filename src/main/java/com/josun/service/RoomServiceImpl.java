package com.josun.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.josun.dao.RoomDAO;
import com.josun.dto.RoomDTO;

@Service
public class RoomServiceImpl implements RoomService {
	
	@Autowired
	RoomDAO dao;
	
	@Override
	public List<RoomDTO> getRoomList(String startDate, String endDate) throws Exception {
		List<RoomDTO> list = dao.getRoomList(startDate, endDate);
		return list;
	}

	@Override
	public HashMap<String, Object> roomDetailInfo(int num) {
		HashMap<String,Object> map = dao.roomDetailInfo(num);
		return map;
	}

}
