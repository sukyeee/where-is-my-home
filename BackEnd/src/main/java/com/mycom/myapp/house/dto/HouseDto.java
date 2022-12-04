package com.mycom.myapp.house.dto;


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
public class HouseDto {
	private String houseDealId; // 아파트 매물 코드
	private String houseId; // aptCode
	private String dongCode;

	private String dealAmount; // 가격
	private int dealYear; // 거래 연도
	private int dealMonth; // 거래 월
	private int dealDay; // 거래 일
	private String area; // 평수
	private String floor; // 층수

	private int buildYear; // 건설 연도
	private String roadName; // 도로명
	private String dong; // 법정동
	private String apartmentName; // 아파트 이름

	private String lng; // 경도
	private String lat; // 위도
	
	private float score; // 아파트의 리뷰 평균 점수 (매물x 아파트o)
}
