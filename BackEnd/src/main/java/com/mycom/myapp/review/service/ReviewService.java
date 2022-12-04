package com.mycom.myapp.review.service;


import javax.servlet.http.HttpSession;

import com.mycom.myapp.review.dto.DongReviewDto;
import com.mycom.myapp.review.dto.HouseReviewDto;
import com.mycom.myapp.review.dto.ReviewParamDto;
import com.mycom.myapp.review.dto.ReviewResultDto;


public interface ReviewService {
	/* 아파트 */
	// 조회
	ReviewResultDto getHouseReviewList(ReviewParamDto dto, HttpSession session);
	ReviewResultDto getHouseReviewScore(ReviewParamDto dto);
	
	// 등록
	ReviewResultDto houseReviewInsert(HouseReviewDto dto);

	// 수정
	ReviewResultDto houseReviewUpdate(HouseReviewDto dto);

	// 삭제
	ReviewResultDto houseReviewDelete(ReviewParamDto dto);

	/* 행정동 */
	// 조회
	ReviewResultDto getDongReviewScore(ReviewParamDto dto);

	// 등록
	ReviewResultDto dongReviewInsert(DongReviewDto dto);

	// 수정
	ReviewResultDto dongReviewUpdate(DongReviewDto dto);

	// 삭제
	ReviewResultDto dongReviewDelete(ReviewParamDto dto);

}
