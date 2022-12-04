package com.mycom.myapp.house.dto;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HouseResultDto {
	private int result = -1;

	// 리스트 조회
	private List<HouseDto> houseList;
	private HouseDto houseDetail;
	private int totalCount;

	private LocationDto locationAvg;

	private List<DongCountDto> dongCountList;

}
