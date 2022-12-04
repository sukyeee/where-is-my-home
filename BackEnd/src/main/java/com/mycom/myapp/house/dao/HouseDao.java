package com.mycom.myapp.house.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.myapp.house.dto.DongCountDto;
import com.mycom.myapp.house.dto.HouseDto;
import com.mycom.myapp.house.dto.HouseParamDto;
import com.mycom.myapp.house.dto.LocationDto;


@Mapper
public interface HouseDao {
	// 아파트 리스트 조회
	List<HouseDto> getHouseList(HouseParamDto dto);
	List<HouseDto> getHouseGugunList(HouseParamDto dto);
	int getHouseListTotalCount(HouseParamDto dto);
	int getHouseGugunListTotalCount(HouseParamDto dto);
	LocationDto getHouseListLocationAvg(HouseParamDto dto);

	// 아파트 상세 조회
	HouseDto getHouseDetail(String houseDealId);

	// 특정 아파트에 대한 전체 매물 거래 정보 조회
	List<HouseDto> getHouseDealListByHouseId(String houseId);

	// 행정동별 매물 카운트
	DongCountDto getDongCount(String dongCode);

}
