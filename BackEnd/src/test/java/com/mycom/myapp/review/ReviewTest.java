package com.mycom.myapp.review;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mycom.myapp.review.dto.DongReviewDto;
import com.mycom.myapp.review.dto.HouseReviewDto;
import com.mycom.myapp.review.dto.ReviewParamDto;
import com.mycom.myapp.review.service.ReviewService;


@SpringBootTest
class ReviewTest {
	@Autowired
	ReviewService service;


	@Test
	void contextLoads() {}


	/* 아파트 리뷰 Test */
	@Test
	void 리뷰등록() {

		HouseReviewDto houseReview = new HouseReviewDto();
		houseReview.setHouseId("11110000000003");
		houseReview.setUserId(5);
		houseReview.setContent("리뷰 테스트1");
		houseReview.setScore(5);

		assertEquals(1, service.houseReviewInsert(houseReview).getResult());

	}


	@Test
	void 리뷰수정() {

		HouseReviewDto houseReview = new HouseReviewDto();
		houseReview.setHouseId("11110000000003");
		houseReview.setReviewId(10);
		houseReview.setUserId(5);
		houseReview.setContent("리뷰 수정 테스트222");
		houseReview.setScore(1);

		assertEquals(1, service.houseReviewUpdate(houseReview).getResult());

	}


	@Test
	void 리뷰삭제() {

		ReviewParamDto dto = new ReviewParamDto();
		int reviewId = 10;
		dto.setReviewId(reviewId);

		assertEquals(1, service.houseReviewDelete(dto).getResult());

	}


	/* 행정동 리뷰 Test */
	@Test
	void 동리뷰등록() {

		DongReviewDto dongReview = new DongReviewDto();
		dongReview.setDongCode("1111010100");
		dongReview.setUserId(1);
		dongReview.setScore(5);

		assertEquals(1, service.dongReviewInsert(dongReview).getResult());

	}


	@Test
	void 동리뷰수정() {

		DongReviewDto dongReview = new DongReviewDto();
		dongReview.setReviewId(9);
		dongReview.setScore(3);

		assertEquals(1, service.dongReviewUpdate(dongReview).getResult());

	}


	@Test
	void 동리뷰삭제() {

		ReviewParamDto dto = new ReviewParamDto();
		int reviewId = 6;
		dto.setReviewId(reviewId);

		assertEquals(1, service.dongReviewDelete(dto).getResult());

	}

}
