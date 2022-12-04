export default {
	/* House View */
	SET_IS_LIST_OPEN(state) {
		state.houses.isListOpen = !state.houses.isListOpen;
	},

	SET_LIST_OPEN(state) {
		state.houses.isListOpen = true;
	},

	SET_LIST_CLOSE(state) {
		state.houses.isListOpen = false;
	},

	SET_IS_DETAIL_OPEN(state) {
		state.houses.isDetailOpen = !state.houses.isDetailOpen;
	},

	SET_DETAIL_OPEN(state) {
		state.houses.isDetailOpen = true;
	},

	SET_DETAIL_CLOSE(state) {
		state.houses.isDetailOpen = false;
	},

	SET_DONG_CODE(state, payload) {
		state.houses.dongCode = payload;
	},

	// house list parameter init
	INIT_HOUSE_LIST_PARAMS(state) {
		state.houses.limit = 5;
		state.houses.offset = 0;
		state.houses.searchWord = "";
		state.houses.dongCode = "";
		state.houses.minYear = 2015;
		state.houses.maxYear = 2022;
		state.houses.minMonth = 1;
		state.houses.maxMonth = 12;
		state.houses.minDay = 1;
		state.houses.maxDay = 31;
		state.houses.minDealAmount = 100;
		state.houses.maxDealAmount = 999999;
		state.houses.minArea = 9;
		state.houses.maxArea = 430;
	},

	// house date search
	SET_HOUSE_SEARCH_DATES(state, payload) {
		state.houses.minYear = payload.minYear;
		state.houses.maxYear = payload.maxYear;
		state.houses.minMonth = payload.minMonth;
		state.houses.maxMonth = payload.maxMonth;
		state.houses.minDay = payload.minDay;
		state.houses.maxDay = payload.maxDay;
	},

	// 아파트 이름 검색
	SET_HOUSE_SEARCH_WORD(state, payload) {
		state.houses.searchWord = payload;
	},

	// 아파트 가격대 검색
	SET_HOUSE_DEAL_AMOUNT(state, payload) {
		state.houses.minDealAmount = payload.minDealAmount;
		state.houses.maxDealAmount = payload.maxDealAmount;
	},

	// 아파트 평수 검색
	SET_HOUSE_AREA(state, payload) {
		state.houses.minArea = payload.minArea;
		state.houses.maxArea = payload.maxArea;
	},

	// house list 가져오기
	SET_HOUSE_LIST(state, payload) {
		state.houses.houseList = payload;
	},

	SET_HOUSE_LIST_LOCATION_AVG(state, payload) {
		state.houses.locationAvg = payload;
	},

	// place selected object 값 초기화
	INIT_PLACE_SELECTED(state) {
		for (var key in state.houses.placeSelected) {
			state.houses.placeSelected[key] = false;
		}
	},

	// 버튼 누르면 place selected object 값 변경
	SET_PLACE_SELECTED(state, payload) {
		state.houses.placeSelected[`${payload}`] = !state.houses.placeSelected[`${payload}`];
	},

	// place list 가져오기
	SET_PLACE_LIST(state) {
		state.houses.placeList = [];
		for (var key in state.houses.placeSelected) {
			if (state.houses.placeSelected[key]) {
				state.houses.placeList.push(key);
			}
		}
	},

	// 페이지네이션
	SET_HOUSE_LIST_TOTAL_COUNT(state, count) {
		let share = parseInt(count / state.houses.pageLinkCount);
		let left = count % state.houses.pageLinkCount;
		if (left == 0) {
			state.houses.houseListTotalCount = share;
		} else {
			state.houses.houseListTotalCount = share + 1;
		}
	},
	SET_HOUSE_GUGUN_LIST_TOTAL_COUNT(state, count) {
		let share = parseInt(count / state.houses.pageLinkCount);
		let left = count % state.houses.pageLinkCount;
		if (left == 0) {
			state.houses.houseGugunListTotalCount = share;
		} else {
			state.houses.houseGugunListTotalCount = share + 1;
		}
	},
	SET_HOUSE_LIST_MOVE_PAGE(state, pageIndex) {
		state.houses.houseOffset = (pageIndex - 1) * state.houses.listRowCount;
	},

	// house detail 가져오기
	SET_HOUSE_DETAIL(state, payload) {
		state.houses.houseDetail = payload;
	},

	// house review 가져오기
	SET_HOUSE_REVIEW_LIST(state, payload) {
		state.houses.houseReviewList = payload;
	},

	// review score 등록
	SET_HOUSE_REVIEW_SCORE(state, payload) {
		state.houses.houseReviewScore = payload;
	},
	SET_DONG_REVIEW_SCORE(state, payload) {
		state.houses.dongReviewScore = payload;
	},

	// house review pagination
	SET_HOUSE_REVIEW_TOTAL_COUNT(state, count) {
		let share = parseInt(count / state.houses.pageLinkCount);
		let left = count % state.houses.pageLinkCount;
		if (left == 0) {
			state.houses.houseReviewTotalCount = share;
		} else {
			state.houses.houseReviewTotalCount = share + 1;
		}
	},
	SET_HOUSE_REVIEW_LIST_MOVE_PAGE(state, pageIndex) {
		state.houses.reviewOffset = (pageIndex - 1) * state.houses.listRowCount;
	},

	// house review isEditOpen
	SET_HOUSE_REVIEW_IS_EDIT_OPEN(state, idx) {
		state.houses.houseReviewList[idx].isEditOpen = !state.houses.houseReviewList[idx].isEditOpen;
	},

	// 아파트 거래 동향 정보 리스트
	SET_DEAL_LIST(state, payload) {
		state.houses.dealList = payload;
	},

	// 행정동 지도 path 세팅하기
	SET_GU_GEO(state, payload) {
		state.houses.guGeo = payload;
	},

	// dongCodeList 세팅하기
	SET_DONG_CODE_LIST(state, payload) {
		state.houses.dongCodeList = payload;
	},
	SET_GUGUN_CODE_LIST(state, payload) {
		state.houses.gugunCodeList = payload;
	},
	SET_DONG_COUNT_LIST(state, payload) {
		state.houses.dongCountList = payload;
	},

	// 아파트 거래 동향 정보 리스트
	SET_DEAL_LIST(state, payload) {
		state.houses.dealList = payload;
	},

	// 오늘의 뉴스 검색어 설정
	SET_SELECTED_SIDO(state, payload) {
		state.houses.selectedSido = payload;
	},
	SET_SELECTED_DONG(state, payload) {
		state.houses.selectedDong = payload;
	},

	// 지역구별 매물 리스트 가져오기
	SET_HOUSE_GUGUN_LIST(state, payload) {
		state.houses.houseGugunList = payload;
	},

	// house, houseGugun
	SET_LIST_TYPE(state, payload) {
		state.houses.listType = payload;
	},

	// kakaoMap
	SET_KAKAO_MAP(state, payload) {
		state.houses.kakaoMap = payload;
	},
	SET_CUR_LAT(state, payload) {
		state.houses.curLat = payload;
	},
	SET_CUR_LNG(state, payload) {
		state.houses.curLng = payload;
	}
};
