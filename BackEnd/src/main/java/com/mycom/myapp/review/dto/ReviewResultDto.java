package com.mycom.myapp.review.dto;


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
public class ReviewResultDto {
	private int result = -1;

	// 아파트
	private List<HouseReviewDto> houseReviewList;
	private double houseReviewScore = 0;
	private int houseReviewTotalCount = 0;
	
	// 유저 매물 평균 점수
	private double houseReviewAvgScore = 0;

	// 행정동
	private double dongReviewScore = 0;

}
