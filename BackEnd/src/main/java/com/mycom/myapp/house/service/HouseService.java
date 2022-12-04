package com.mycom.myapp.house.service;


import com.mycom.myapp.house.dto.HouseParamDto;
import com.mycom.myapp.house.dto.HouseResultDto;


public interface HouseService {
	// 아파트 리스트 조회
	HouseResultDto getHouseList(HouseParamDto dto);
	HouseResultDto getHouseGugunList(HouseParamDto dto);
	HouseResultDto getHouseListLocationAvg(HouseParamDto dto);

	// 아파트 상세 조회
	HouseResultDto getHouseDetail(HouseParamDto dto);

	// 선택한 아파트에 대한 상세 조회 리스트
	HouseResultDto getSelectedHouseDetailList(HouseParamDto dto);

	// 특정 아파트에 대한 전체 매물 거래 정보 조회
	HouseResultDto getHouseDealListByHouseId(String houseId);

	// 행정동별 매물 개수 세기
	HouseResultDto getDongCountList(HouseParamDto dto);
}
