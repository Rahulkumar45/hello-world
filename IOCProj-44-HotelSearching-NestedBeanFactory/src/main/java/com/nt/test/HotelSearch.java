package com.nt.test;

import java.util.List;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import com.nt.controller.HotelController;
import com.nt.dto.HotelDTO;

public class HotelSearch {

	public static void main(String[] args) {
		DefaultListableBeanFactory pFactory = null;
		XmlBeanDefinitionReader pReader = null;
		HotelController controller;

		// create IOC contaner for target class
		pFactory = new DefaultListableBeanFactory();
		pReader = new XmlBeanDefinitionReader(pFactory);
		pReader.loadBeanDefinitions("com/nt/cfgs/business-tire.xml");

		// create child contaner
		DefaultListableBeanFactory cFactory = null;
		XmlBeanDefinitionReader cReader = null;
		cFactory = new DefaultListableBeanFactory(pFactory);
		cReader = new XmlBeanDefinitionReader(cFactory);
		cReader.loadBeanDefinitions("com/nt/cfgs/presentation-tire.xml");

		// invoke getBean()
		controller = cFactory.getBean("hCtr", HotelController.class);

		List<HotelDTO> listDTO;
		try {
			// invoke controler class method
			listDTO = controller.displayHotelByCity(new String[] { "delhi" });
			listDTO.forEach(dto -> {
				System.out.println(dto);
			});
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
