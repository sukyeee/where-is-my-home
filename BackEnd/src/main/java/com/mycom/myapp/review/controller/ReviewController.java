package com.mycom.myapp.review.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.myapp.review.dto.DongReviewDto;
import com.mycom.myapp.review.dto.HouseReviewDto;
import com.mycom.myapp.review.dto.ReviewParamDto;
import com.mycom.myapp.review.dto.ReviewResultDto;
import com.mycom.myapp.review.service.ReviewService;
import com.mycom.myapp.user.dto.UserDto;


@RestController
@CrossOrigin(
    origins = "http://localhost:5500", allowCredentials = "true",
    allowedHeaders = "*",
    methods = {
                    RequestMethod.GET, RequestMethod.POST,
                    RequestMethod.PUT, RequestMethod.DELETE,
                    RequestMethod.OPTIONS, RequestMethod.HEAD })
@RequestMapping(value = "/reviews")
public class ReviewController {

	@Autowired
	ReviewService service;

	private static final int SUCCESS = 1;


	/* 아파트 */
	/* 아파트 리뷰 리스트 가져오기 */
	@Transactional
	@GetMapping(value = "/house")
	private ResponseEntity<ReviewResultDto> reviewList(ReviewParamDto dto, HttpSession session) {

		ReviewResultDto reviewResultDto;
		reviewResultDto = service.getHouseReviewList(dto, session);

		if (reviewResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<ReviewResultDto>(reviewResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<ReviewResultDto>(
			    reviewResultDto,
			    HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}


	/* 아파트 별점 총점 평균 */
	@GetMapping(value = "/house/{houseId}")
	private ResponseEntity<ReviewResultDto> houseScore(ReviewParamDto dto) {

		ReviewResultDto reviewResultDto;
		reviewResultDto = service.getHouseReviewScore(dto);

		if (reviewResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<ReviewResultDto>(reviewResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<ReviewResultDto>(
			    reviewResultDto,
			    HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/* 아파트 리뷰 등록하기 */
	@PostMapping(value = "/house")
	private ResponseEntity<ReviewResultDto> houseInsert(@RequestBody HouseReviewDto dto,
	    HttpSession session) {
		ReviewResultDto reviewResultDto = new ReviewResultDto();;

		System.out.println("houseReviewInsert: " + dto);
		UserDto userDto = (UserDto) session.getAttribute("userDto"); // 현재 로그인되어서 상세 요청을 한 사용자 정보

		if (userDto == null) {
			return new ResponseEntity<ReviewResultDto>(reviewResultDto, HttpStatus.FORBIDDEN);
		}

		dto.setUserId(userDto.getUserId());
		System.out.println(userDto);
		System.out.println("insert Dto: " + dto);

		reviewResultDto = service.houseReviewInsert(dto);

		if (reviewResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<ReviewResultDto>(reviewResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<ReviewResultDto>(reviewResultDto,
			    HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}


	/* 아파트 리뷰 수정하기 */
	@PutMapping(value = "/house")
	private ResponseEntity<ReviewResultDto> houseUpdate(@RequestBody HouseReviewDto dto) {

		ReviewResultDto reviewResultDto = service.houseReviewUpdate(dto);

		if (reviewResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<ReviewResultDto>(reviewResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<ReviewResultDto>(reviewResultDto,
			    HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}


	/* 아파트 리뷰 삭제하기 */
	@DeleteMapping(value = "/house/{reviewId}")
	private ResponseEntity<ReviewResultDto> houseDelete(@PathVariable int reviewId) {
		System.out.println("hihihih");

		System.out.println("delete: " + reviewId);
		ReviewParamDto dto = new ReviewParamDto();
		dto.setReviewId(reviewId);
		ReviewResultDto reviewResultDto = service.houseReviewDelete(dto);

		if (reviewResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<ReviewResultDto>(reviewResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<ReviewResultDto>(reviewResultDto,
			    HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}


	/* 행정동 */
	/* 행정동 별점 총점 평균 */
	@GetMapping(value = "/dong/{dongCode}")
	private ResponseEntity<ReviewResultDto> dongScore(ReviewParamDto dto) {

		ReviewResultDto reviewResultDto;
		reviewResultDto = service.getDongReviewScore(dto);

		if (reviewResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<ReviewResultDto>(reviewResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<ReviewResultDto>(
			    reviewResultDto,
			    HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}


	/* 행정동 리뷰 등록하기 */
	@PostMapping(value = "/dong")
	private ResponseEntity<ReviewResultDto> dongInsert(DongReviewDto dto,
	    MultipartHttpServletRequest request) {

		HttpSession session = request.getSession();
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		dto.setUserId(userDto.getUserId());

		ReviewResultDto reviewResultDto = service.dongReviewInsert(dto);

		if (reviewResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<ReviewResultDto>(reviewResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<ReviewResultDto>(reviewResultDto,
			    HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}


	/* 행정동 리뷰 수정하기 */
	@PutMapping(value = "/dong")
	private ResponseEntity<ReviewResultDto> dongUpdate(DongReviewDto dto) {

		ReviewResultDto reviewResultDto = service.dongReviewUpdate(dto);

		if (reviewResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<ReviewResultDto>(reviewResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<ReviewResultDto>(reviewResultDto,
			    HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}


	/* 행정동 리뷰 수정하기 */
	@DeleteMapping(value = "/dong")
	private ResponseEntity<ReviewResultDto> dongDelete(ReviewParamDto dto) {

		ReviewResultDto reviewResultDto = service.houseReviewDelete(dto);

		if (reviewResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<ReviewResultDto>(reviewResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<ReviewResultDto>(reviewResultDto,
			    HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}