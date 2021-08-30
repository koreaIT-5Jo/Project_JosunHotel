package com.josun.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.josun.dto.RoomDTO;
import com.josun.util.DateUtil;

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
		DateUtil timeUtil = new DateUtil();
		//System.out.println(timeUtil.returnReserveDate(startDate, endDate));
		List<String> list = sqlSession.selectList("com.josun.mapper.roomMapper.getduplicateCheckRoomNumList",timeUtil.returnReserveDate(startDate, endDate));
		String roomNumList = "";
		for(String i : list) {
			roomNumList += (roomNumList.equals("") ? "'" : ", '") + i + "'";  
		}
		return roomNumList;
	}

	@Override
	public  HashMap<String,String> roomDetailInfo(int num) {
		HashMap<String, String> map = sqlSession.selectOne("com.josun.mapper.roomMapper.roomDetailInfo", num);
		return map;
	}

	@Override
	public RoomDTO roomOptionInfo(int num) {
		RoomDTO dto = sqlSession.selectOne("com.josun.mapper.roomMapper.roomOptionInfo",num);
		return dto;
	}

	@Override
	public RoomDTO detailReviewRoomInfo(int num) {
		RoomDTO dto = sqlSession.selectOne("com.josun.mapper.roomMapper.detailReviewRoom",num);
		return dto;
	}
}
