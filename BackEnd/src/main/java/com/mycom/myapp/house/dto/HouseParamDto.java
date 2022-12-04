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
public class HouseParamDto {
	private int limit = 5;
	private int offset;

	// 리스트 조회
	private String dongCode; // 행정동
	private String searchWord = ""; // 검색어 (아파트 이름)
	private String minYear = "2015"; // 날짜
	private String maxYear = "2022";
	private String minMonth = "1";
	private String maxMonth = "12";
	private String minDealAmount = "1000"; // 아파트 가격
	private String maxDealAmount = "99999";
	private String minArea = "9"; // 평수
	private String maxArea = "430";

	// 상세 조회
	private String houseDealId;

	// 스펙 비교 조회 (비교할 아파트의 id 리스트 필요)
	private List<String> houseDealIdList;

	// 행정동별 매물 개수
	private List<String> gugunCodeList;
}
