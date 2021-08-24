package com.josun.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.josun.dto.RoomDTO;
import com.josun.util.TimeUtil;

@Repository
public class RoomDAOImpl implements RoomDAO {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<RoomDTO> getRoomList(String startDate, String endDate) throws Exception {
//		System.out.println(getDuplicateCheckRoomNumList(startDate, endDate));
		List<RoomDTO> list = sqlSession.selectList("com.josun.mapper.roomMapper.getRoomNumList",getDuplicateCheckRoomNumList(startDate, endDate));
		return list;
	}

	@Override
	public String getDuplicateCheckRoomNumList(String startDate, String endDate) throws Exception {
		TimeUtil timeUtil = new TimeUtil();
//		System.out.println(timeUtil.returnReserveDate(startDate, endDate));
		List<String> list = sqlSession.selectList("com.josun.mapper.roomMapper.getduplicateCheckRoomNumList",timeUtil.returnReserveDate(startDate, endDate));
		String roomNumList = "";
		for(String i : list) {
			roomNumList += (roomNumList.equals("") ? "'" : ", '") + i + "'";  
		}
		return roomNumList;
	}
}
