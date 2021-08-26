package com.josun.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.josun.dto.ReservationDTO;

@Repository
public class ReservationDAOImpl implements ReservationDAO {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int reservation(ReservationDTO dto) {
		int result = sqlSession.insert("com.josun.mapper.reservationMapper.reservation",dto);
		
		return result;
	}

}
