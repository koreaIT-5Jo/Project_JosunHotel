package com.josun.service;

import com.josun.dto.ReservationDTO;

public interface ReservationService {
	boolean doReserve(ReservationDTO dto);
	int doReserve2(ReservationDTO dto);
}
