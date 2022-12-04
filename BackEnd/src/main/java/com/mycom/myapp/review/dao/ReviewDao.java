package com.mycom.myapp.review.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.myapp.review.dto.DongReviewDto;
import com.mycom.myapp.review.dto.HouseReviewDto;
import com.mycom.myapp.review.dto.ReviewParamDto;


@Mapper
public interface ReviewDao {
	/* 아파트 */
	// 리뷰 조회
	List<HouseReviewDto> getHouseReviewList(ReviewParamDto dto);
	double getHouseReviewScore(String houseId);
	int getHouseReviewTotalCount(String houseId);

	// 특정 유저가 낸 매물 리뷰 평균
	float getHouseReviewAverageScoreByUserId(int userId);

	// 리뷰 등록
	int houseReviewInsert(HouseReviewDto dto);

	// 리뷰 수정
	int houseReviewUpdate(HouseReviewDto dto);

	// 리뷰 삭제
	int houseReviewDelete(int reviewId);

	/* 행정동 */
	// 리뷰 조회
	double getDongReviewScore(String dongCode);

	// 리뷰 등록
	int dongReviewInsert(DongReviewDto dto);

	// 리뷰 수정
	int dongReviewUpdate(DongReviewDto dto);

	// 리뷰 삭제
	int dongReviewDelete(int reviewId);

}
