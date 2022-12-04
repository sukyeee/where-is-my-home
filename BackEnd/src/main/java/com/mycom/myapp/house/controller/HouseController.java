package com.mycom.myapp.house.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.myapp.house.dto.HouseParamDto;
import com.mycom.myapp.house.dto.HouseResultDto;
import com.mycom.myapp.house.dto.NewsResultDto;
import com.mycom.myapp.house.service.HouseService;
import com.mycom.myapp.house.service.SearchNewsService;


@RestController
@CrossOrigin(origins = "http://localhost:5500", allowCredentials = "true", allowedHeaders = "*", methods = {
                RequestMethod.GET, RequestMethod.POST,
                RequestMethod.PUT, RequestMethod.DELETE,
                RequestMethod.OPTIONS, RequestMethod.HEAD })
public class HouseController {

	@Autowired
	HouseService service;

	@Autowired
	SearchNewsService searchNewsService;

	private static final int SUCCESS = 1;


	/* 아파트 매물 리스트 가져오기 */
	// 행정동 입력하면 그에 맞는 아파트를 가져온다.
	// 검색을 한다면 ? => 아파트 이름 기준
	@GetMapping(value = "/houses")
	private ResponseEntity<HouseResultDto> houseList(HouseParamDto dto) {

		HouseResultDto houseResultDto;
		houseResultDto = service.getHouseList(dto);
		System.out.println("houseResultDto: " + houseResultDto);

		if (houseResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<HouseResultDto>(houseResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<HouseResultDto>(
			    houseResultDto,
			    HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}


	@GetMapping(value = "/houses/gugun")
	private ResponseEntity<HouseResultDto> houseGugunList(HouseParamDto dto) {

		HouseResultDto houseResultDto;
		houseResultDto = service.getHouseGugunList(dto);
		System.out.println("houseResultDto: " + houseResultDto);

		if (houseResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<HouseResultDto>(houseResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<HouseResultDto>(
			    houseResultDto,
			    HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}


	/* 아파트 매물 상세 조회하기 */
	@GetMapping(value = "/houses/{houseDealId}")
	private ResponseEntity<HouseResultDto> houseDetail(@PathVariable String houseDealId) {

		HouseParamDto dto = new HouseParamDto();
		dto.setHouseDealId(houseDealId);

		HouseResultDto houseResultDto = service.getHouseDetail(dto);
		System.out.println(service.getHouseDetail(dto));

		if (houseResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<HouseResultDto>(houseResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<HouseResultDto>(
			    houseResultDto,
			    HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}


	/* 특정 아파트에 대한 전체 매물 거래 정보 조회 - 상세 페이지에서 거래 추이 그래프 그릴 때 쓰임 */
	@GetMapping(value = "/houses/deals/{houseId}")
	private ResponseEntity<HouseResultDto> houseDealListByHouseId(@PathVariable String houseId) {

		HouseResultDto houseResultDto = service.getHouseDealListByHouseId(houseId);

		if (houseResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<HouseResultDto>(houseResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<HouseResultDto>(
			    houseResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}


	/* 아파트 매물 상세 조회하기 - 북마크에서 스펙 비교할 때 쓰임 */
	@GetMapping(value = "/houses/spec")
	private ResponseEntity<HouseResultDto> houseSelectedDetailList(
	    @RequestParam("houseDealId") List<String> houseDealId) {

		HouseParamDto dto = new HouseParamDto();
		dto.setHouseDealIdList(houseDealId);

		HouseResultDto houseResultDto = service.getSelectedHouseDetailList(dto);

		if (houseResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<HouseResultDto>(houseResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<HouseResultDto>(
			    houseResultDto,
			    HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}


	@GetMapping(value = "/houses/loc")
	private ResponseEntity<HouseResultDto> houseLocationAvg(HouseParamDto dto) {

		System.out.println(dto);

		HouseResultDto houseResultDto = new HouseResultDto();
		houseResultDto = service.getHouseListLocationAvg(dto);

		System.out.println("loc " + houseResultDto);

		if (houseResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<HouseResultDto>(houseResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<HouseResultDto>(
			    houseResultDto,
			    HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}


	@PostMapping(value = "/houses/count")
	private ResponseEntity<HouseResultDto> houseDongCount(@RequestBody HouseParamDto dto) {

		System.out.println("count dto: " + dto);

		HouseResultDto houseResultDto = new HouseResultDto();
		houseResultDto = service.getDongCountList(dto);

		if (houseResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<HouseResultDto>(houseResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<HouseResultDto>(
			    houseResultDto,
			    HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}


	/* 뉴스 검색하기 : 검색어 - 광역시, 행정동*/
	@GetMapping(value = "/houses/news")
	private ResponseEntity<NewsResultDto> searchNews(@RequestParam("sido") String sido,
	    @RequestParam("dong") String dong) {
		NewsResultDto resultDto = searchNewsService.crawlingNews(sido, dong);

		System.out.println("loc " + resultDto);

		if (resultDto.getResult() == SUCCESS) {
			return new ResponseEntity<>(resultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(resultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}