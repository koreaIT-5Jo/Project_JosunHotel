package com.josun.dao;

import java.util.List;

import com.josun.dto.SalesStatusDTO;

public interface SalesStatusDAO {
	int insert(int totalpay);
	int delete(int num);
	int monthTotalPay(String startDate, String endDate);
	int allMemberCount();
	List<SalesStatusDTO> yearTotalPay();
}
