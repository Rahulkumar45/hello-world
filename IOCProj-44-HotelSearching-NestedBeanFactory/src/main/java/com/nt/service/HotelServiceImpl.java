package com.nt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;


import com.nt.bo.HotelBO;
import com.nt.dao.HotelDao;
import com.nt.dto.HotelDTO;

public class HotelServiceImpl implements HotelService {

	private HotelDao dao;
	
	public HotelServiceImpl(HotelDao dao) {
		System.out.println("HotelServiceImpl:1-param constructor");
		this.dao = dao;
	}

	public List<HotelDTO> findByCityName(String[] city) throws Exception {
		StringBuffer sb=null;
		List<HotelBO> listBO=null;
		List<HotelDTO> listDTO=null;
		HotelDTO dto;
		// converting city[] as required SQL conditions
		sb=new StringBuffer("(");
		for(int i=0;i<city.length;++i) {
			if(i==city.length-1)
				sb.append("'"+city[i]+"'");
			else
				sb.append("'"+city[i]+"',");
		}
		sb.append(")");
		
		// use DAO
		listBO=dao.fethHotelByCity(sb.toString());
		
		// converting listBO to listDTO
		listDTO=new ArrayList();
		for(HotelBO bo:listBO) {
			dto= new HotelDTO();
			BeanUtils.copyProperties(bo, dto);
			dto.setSno(listDTO.size()+1);
			listDTO.add(dto);
		}//for
		
		return listDTO;
		
	}

}
