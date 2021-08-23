package com.josun.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.josun.dto.RoomDTO;

@Repository
public class RoomDAOImpl implements RoomDAO {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<RoomDTO> getRoomNumList(String startDate, String endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getReserveDateList(String startDate, String endDate) {
		// TODO Auto-generated method stub
		return null;
	}

}
