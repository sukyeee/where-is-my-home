package com.mycom.myapp.house.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycom.myapp.house.dao.HouseDao;
import com.mycom.myapp.house.dto.DongCountDto;
import com.mycom.myapp.house.dto.HouseDto;
import com.mycom.myapp.house.dto.HouseParamDto;
import com.mycom.myapp.house.dto.HouseResultDto;
import com.mycom.myapp.review.dao.ReviewDao;


@Service
public class HouseServiceImpl implements HouseService {
	@Autowired
	HouseDao dao;

	@Autowired
	ReviewDao reviewDao;

	private static final int SUCCESS = 1;
	private static final int FAIL = -1;


	@Transactional
	@Override
	public HouseResultDto getHouseList(HouseParamDto dto) {

		HouseResultDto houseResultDto = new HouseResultDto();
		System.out.println("dto: " + dto);

		try {
			houseResultDto.setResult(SUCCESS);
			houseResultDto.setHouseList(dao.getHouseList(dto));
			houseResultDto.setTotalCount(dao.getHouseListTotalCount(dto));
		} catch (Exception e) {
			houseResultDto.setResult(FAIL);
			e.printStackTrace();
		}

		return houseResultDto;

	}


	@Override
	public HouseResultDto getHouseDetail(HouseParamDto dto) {

		HouseResultDto houseResultDto = new HouseResultDto();

		try {
			houseResultDto.setResult(SUCCESS);
			String houseDealId = dto.getHouseDealId();
			houseResultDto.setHouseDetail(dao.getHouseDetail(dto.getHouseDealId()));
		} catch (Exception e) {
			houseResultDto.setResult(FAIL);
			e.printStackTrace();
		}

		return houseResultDto;

	}


	@Override
	public HouseResultDto getSelectedHouseDetailList(HouseParamDto dto) {

		HouseResultDto houseResultDto = new HouseResultDto();

		try {
			List<String> houseDealIdList = dto.getHouseDealIdList();

			List<HouseDto> list = new ArrayList<>();
			for (String houseDealId : houseDealIdList) {
				list.add(dao.getHouseDetail(houseDealId));
			}

			// 각 아파트의 평균 점수
			for (HouseDto house : list) {
				double score = reviewDao.getHouseReviewScore(house.getHouseId());
				System.out.println(score);
				house.setScore((float) score);
			}

			houseResultDto.setHouseList(list);
			houseResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			houseResultDto.setResult(FAIL);
			e.printStackTrace();
		}

		return houseResultDto;

	}


	@Override
	public HouseResultDto getHouseListLocationAvg(HouseParamDto dto) {

		HouseResultDto houseResultDto = new HouseResultDto();
		System.out.println("dto: " + dto);

		try {
			houseResultDto.setResult(SUCCESS);
			houseResultDto.setLocationAvg(dao.getHouseListLocationAvg(dto));

			System.out.println(dao.getHouseListLocationAvg(dto));
		} catch (Exception e) {
			houseResultDto.setResult(FAIL);
			e.printStackTrace();
		}

		return houseResultDto;

	}


	@Override
	public HouseResultDto getHouseDealListByHouseId(String houseId) {
		HouseResultDto houseResultDto = new HouseResultDto();

		try {
			List<HouseDto> list = dao.getHouseDealListByHouseId(houseId);
			houseResultDto.setHouseList(list);
			houseResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			houseResultDto.setResult(FAIL);
			e.printStackTrace();
		}

		return houseResultDto;
	}


	@Override
	public HouseResultDto getDongCountList(HouseParamDto dto) {

		HouseResultDto houseResultDto = new HouseResultDto();

		try {
			List<DongCountDto> list = new ArrayList<>();
			List<String> gugunCodeList = dto.getGugunCodeList();

			for (String gugunCode : gugunCodeList) list.add(dao.getDongCount(gugunCode));

			System.out.println("count " + list);

			houseResultDto.setDongCountList(list);
			houseResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			houseResultDto.setResult(FAIL);
			e.printStackTrace();
		}

		return houseResultDto;

	}


	@Override
	public HouseResultDto getHouseGugunList(HouseParamDto dto) {

		HouseResultDto houseResultDto = new HouseResultDto();
		System.out.println("dto: " + dto);

		try {
			houseResultDto.setResult(SUCCESS);
			houseResultDto.setHouseList(dao.getHouseGugunList(dto));
			houseResultDto.setTotalCount(dao.getHouseGugunListTotalCount(dto));
		} catch (Exception e) {
			houseResultDto.setResult(FAIL);
			e.printStackTrace();
		}

		return houseResultDto;

	}

}
