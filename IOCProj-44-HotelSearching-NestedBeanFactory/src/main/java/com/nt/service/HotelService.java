package com.nt.service;

import java.util.List;

import com.nt.dto.HotelDTO;

public interface HotelService {
public List<HotelDTO> findByCityName(String[]city) throws Exception;
}
