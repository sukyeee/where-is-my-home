export default {
	isListOpen: false,
	isDetailOpen: false,

	// pagination
	limit: 5,
	houseOffset: 0,
	reviewOffset: 0,
	listRowCount: 5,
	pageLinkCount: 5,

	// 매물 리스트 조회
	searchWord: "",
	dongCode: "",
	minYear: 2015,
	maxYear: 2022,
	minMonth: 1,
	maxMonth: 12,
	minDay: 1,
	maxDay: 31,
	minDealAmount: 400,
	maxDealAmount: 1200000,
	minArea: 9,
	maxArea: 430,

	listType: "house", // house, houseGugun

	houseList: [],
	houseListTotalCount: 0,
	houseGugunList: [],
	houseGugunListTotalCount: 0,
	houseDetail: {},
	houseReviewList: [],
	houseReviewTotalCount: 0,
	houseReviewScore: 0,
	dongReviewScore: 0,
	locationAvg: {
		latAvg: 37.58614864171379,
		lngAvg: 126.96693041470502
	},

	placeSelected: {
		AG2: false, // 중개업소
		CS2: false, // 편의점
		MT1: false, // 대형마트
		BK9: false, // 은행
		HP8: false, // 병원
		PM9: false, // 약국
		SC4: false, // 학교
		PS3: false, // 유치원
		AC5: false, // 학원
		OL7: false, // 주유소
		FD6: false, // 음식점,
		PK6: false // 주차장
	},
	placeList: [],

	// 거래 동향 그래프
	dealList: [],

	// geo json
	guGeo: [], // no
	polyPath: [], // no
	dongCodeList: [],
	gugunCodeList: [],
	dongCountList: [],

	// 오늘의 뉴스 검색어
	selectedSido: "",
	selectedDong: "",

	// kakao map
	kakaoMap: null,
	curLat: 0,
	curLng: 0,

	sido: "서울시",
	dong: "내수동",
};
