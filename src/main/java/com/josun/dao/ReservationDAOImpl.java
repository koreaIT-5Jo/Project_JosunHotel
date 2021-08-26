package com.josun.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.josun.dto.ReservationDTO;
import com.josun.util.DateUtil;

@Repository
public class ReservationDAOImpl implements ReservationDAO {

	@Autowired
	SqlSession sqlSession;
	
	// 예약 정보 입력
	@Override
	public int reservation(ReservationDTO dto) {
		int result = sqlSession.insert("com.josun.mapper.reservationMapper.reservation",dto);
		
		return result;
	}
	
	//	예약 날짜와 봥번호 정보 입력
	@Override
	public int reservedRoom(String startDate, String endDate,int room_number) throws Exception {
		DateUtil timeUtil = new DateUtil();
		int result=0;
	//	System.out.println("reservedRoom  num : " + room_number);
		for(int i=0; i<timeUtil.seDay(startDate,endDate); i++ ) {
			HashMap<String,Object> map = new HashMap<String, Object>();
			map.put("room_number", room_number);
			map.put("date", timeUtil.addDay(startDate, i));
			
			result += sqlSession.insert("com.josun.mapper.reservationMapper.reservedRoom",map);
		}
		
		return result;
	}
	
	// 예약 날짜에 방이 없는지 최종 조회 
	@Override
	public int okReservation(String startDate, String endDate,int room_number) throws Exception {
		DateUtil timeUtil = new DateUtil();
		String reserveDateList = timeUtil.returnReserveDate(startDate,endDate);
//		System.out.println("reserveDateList : " + reserveDateList);
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("reserveDateList", reserveDateList);
		map.put("room_number", room_number);
		
		int count = sqlSession.selectOne("com.josun.mapper.reservationMapper.okReservation",map);
		return count;
	}

}
