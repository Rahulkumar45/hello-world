package com.nt.controller;

import java.util.List;

import com.nt.dto.HotelDTO;
import com.nt.service.HotelService;

public class HotelController {
	private HotelService service;

	public HotelController(HotelService service) {
		System.out.println("HotelController:1-param");
		this.service = service;
	}
	public  List<HotelDTO> displayHotelByCity(String[] city) throws Exception{
		List<HotelDTO> listDTO;
		// use  service
		listDTO=service.findByCityName(city);
		return listDTO;
		
	}

}
