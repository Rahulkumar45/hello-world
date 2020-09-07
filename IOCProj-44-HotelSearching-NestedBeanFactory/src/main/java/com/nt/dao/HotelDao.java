package com.nt.dao;

import java.util.List;

import com.nt.bo.HotelBO;

public interface HotelDao {
public List<HotelBO> fethHotelByCity(String cond) throws Exception;
}
