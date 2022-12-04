package com.mycom.myapp.house;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mycom.myapp.house.dto.HouseParamDto;
import com.mycom.myapp.house.service.HouseService;


@SpringBootTest
class HouseTest {
	@Autowired
	HouseService service;


	@Test
	void contextLoads() {}


	@Test
	void 위치평균가져오기() {

		HouseParamDto dto = new HouseParamDto();
		dto.setDongCode("1111010100");

		assertEquals(1, service.getHouseListLocationAvg(dto).getResult());

	}

}
