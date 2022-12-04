package com.mycom.myapp.review.service;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycom.myapp.review.dao.ReviewDao;
import com.mycom.myapp.review.dto.DongReviewDto;
import com.mycom.myapp.review.dto.HouseReviewDto;
import com.mycom.myapp.review.dto.ReviewParamDto;
import com.mycom.myapp.review.dto.ReviewResultDto;
import com.mycom.myapp.user.dao.UserDao;
import com.mycom.myapp.user.dto.UserDto;


@Service
public class ReviewServiceImpl implements ReviewService {
	@Autowired
	ReviewDao dao;

	@Autowired
	UserDao userDao;

	private static final int SUCCESS = 1;
	private static final int FAIL = -1;


	/* 아파트 */
	@Transactional
	@Override
	public ReviewResultDto getHouseReviewList(ReviewParamDto dto, HttpSession session) {

		ReviewResultDto reviewResultDto = new ReviewResultDto();
		System.out.println("review list dto: " + dto);

		UserDto userDto = (UserDto) session.getAttribute("userDto");

		try {
			reviewResultDto.setResult(SUCCESS);
			reviewResultDto.setHouseReviewList(dao.getHouseReviewList(dto));

			if (userDto != null) {
				int userId = userDto.getUserId();

				List<HouseReviewDto> reviewList = reviewResultDto.getHouseReviewList();
				reviewList.forEach((el) -> {
				    if (el.getUserId() == userId) el.setSameUser(true);
				});

			}

			String houseId = dto.getHouseId();
			reviewResultDto.setHouseReviewTotalCount(dao.getHouseReviewTotalCount(houseId));
			reviewResultDto.setHouseReviewScore(dao.getHouseReviewScore(houseId));
		} catch (Exception e) {
			reviewResultDto.setResult(FAIL);
			e.printStackTrace();
		}

		return reviewResultDto;

	}


	@Override
	public ReviewResultDto getHouseReviewScore(ReviewParamDto dto) {

		ReviewResultDto reviewResultDto = new ReviewResultDto();
		System.out.println("review score dto: " + dto);

		try {
			reviewResultDto.setResult(SUCCESS);
			String houseId = dto.getHouseId();
			reviewResultDto.setHouseReviewScore(dao.getHouseReviewScore(houseId));
		} catch (Exception e) {
			reviewResultDto.setResult(FAIL);
			e.printStackTrace();
		}

		return reviewResultDto;

	}


	@Override
	public ReviewResultDto houseReviewInsert(HouseReviewDto dto) {

		ReviewResultDto reviewResultDto = new ReviewResultDto();
		System.out.println("review insert dto: " + dto);

		try {
			reviewResultDto.setResult(SUCCESS);
			dao.houseReviewInsert(dto);
			/* 사용자 평균 점수 갱신 */
			float avgScore = dao.getHouseReviewAverageScoreByUserId(dto.getUserId());
			userDao.userScoreUpdate(dto.getUserId(), avgScore);
		} catch (Exception e) {
			reviewResultDto.setResult(FAIL);
			e.printStackTrace();
		}

		return reviewResultDto;

	}


	@Override
	public ReviewResultDto houseReviewUpdate(HouseReviewDto dto) {

		ReviewResultDto reviewResultDto = new ReviewResultDto();
		System.out.println("review update dto: " + dto);

		try {
			reviewResultDto.setResult(SUCCESS);
			dao.houseReviewUpdate(dto);
		} catch (Exception e) {
			reviewResultDto.setResult(FAIL);
			e.printStackTrace();
		}

		return reviewResultDto;

	}


	@Override
	public ReviewResultDto houseReviewDelete(ReviewParamDto dto) {

		ReviewResultDto reviewResultDto = new ReviewResultDto();
		System.out.println("review delete dto: " + dto);

		try {
			reviewResultDto.setResult(SUCCESS);

			int reviewId = dto.getReviewId();
			dao.houseReviewDelete(reviewId);
		} catch (Exception e) {
			reviewResultDto.setResult(FAIL);
			e.printStackTrace();
		}

		return reviewResultDto;

	}


	/* 행정동 */
	@Override
	public ReviewResultDto getDongReviewScore(ReviewParamDto dto) {

		ReviewResultDto reviewResultDto = new ReviewResultDto();
		System.out.println("dong review list dto: " + dto);

		try {
			reviewResultDto.setResult(SUCCESS);

			String dongCode = dto.getDongCode();
			reviewResultDto.setDongReviewScore(dao.getDongReviewScore(dongCode));
		} catch (Exception e) {
			reviewResultDto.setResult(FAIL);
			e.printStackTrace();
		}

		return reviewResultDto;

	}


	@Override
	public ReviewResultDto dongReviewInsert(DongReviewDto dto) {

		ReviewResultDto reviewResultDto = new ReviewResultDto();
		System.out.println("dong review insert dto: " + dto);

		try {
			reviewResultDto.setResult(SUCCESS);
			dao.dongReviewInsert(dto);
		} catch (Exception e) {
			reviewResultDto.setResult(FAIL);
			e.printStackTrace();
		}

		return reviewResultDto;

	}


	@Override
	public ReviewResultDto dongReviewUpdate(DongReviewDto dto) {

		ReviewResultDto reviewResultDto = new ReviewResultDto();
		System.out.println("dong review update dto: " + dto);

		try {
			reviewResultDto.setResult(SUCCESS);
			dao.dongReviewUpdate(dto);
		} catch (Exception e) {
			reviewResultDto.setResult(FAIL);
			e.printStackTrace();
		}

		return reviewResultDto;

	}


	@Override
	public ReviewResultDto dongReviewDelete(ReviewParamDto dto) {

		ReviewResultDto reviewResultDto = new ReviewResultDto();
		System.out.println("dong review delete dto: " + dto);

		try {
			reviewResultDto.setResult(SUCCESS);

			int reviewId = dto.getReviewId();
			dao.dongReviewDelete(reviewId);
		} catch (Exception e) {
			reviewResultDto.setResult(FAIL);
			e.printStackTrace();
		}

		return reviewResultDto;

	}

}
