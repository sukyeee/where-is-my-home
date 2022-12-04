export default {
	/* House View */
	getIsListOpen(state) {
		return state.houses.isListOpen;
	},
	getIsDetailOpen(state) {
		return state.houses.isDetailOpen;
	},
	getHouseList(state) {
		return state.houses.houseList;
	},
	getPlaceSelected(state) {
		return state.houses.placeSelected;
	},
	getPlaceList(state) {
		return state.houses.placeList;
	},
	getHouseListTotalCount(state) {
		return state.houses.houseListTotalCount;
	},
	getHouseListCurrentPageIndex(state) {
		return state.houses.currentPageIndex;
	},
	getHouseDetail(state) {
		return state.houses.houseDetail;
	},
	getHouseReviewList(state) {
		return state.houses.houseReviewList;
	},
	getHouseReviewScore(state) {
		return state.houses.houseReviewScore;
	},
	getDongReviewScore(state) {
		return state.houses.dongReviewScore;
	},
	getHouseReviewTotalCount(state) {
		return state.houses.houseReviewTotalCount;
	},
	getDongCode(state) {
		return state.houses.dongCode;
	},
	getHouseListLocationAvg(state) {
		return state.houses.locationAvg;
	},
	getDealList(state) {
		return state.houses.dealList;
	},
	getGuGeo(state) {
		return state.houses.guGeo;
	},
	getDongCodeList(state) {
		return state.houses.dongCodeList;
	},
	getGugunCodeList(state) {
		return state.houses.gugunCodeList;
	},
	getDongCountList(state) {
		return state.houses.dongCountList;
	},
	getSelectedSido(state) {
		return state.houses.selectedSido;
	},
	getSelectedDong(state) {
		return state.houses.selectedDong;
	},
	getHouseGugunList(state) {
		return state.houses.houseGugunList;
	},
	getHouseGugunListTotalCount(state) {
		return state.houses.houseGugunListTotalCount;
	},
	getListType(state) {
		return state.houses.listType;
	},
	getKakaoMap(state) {
		return state.houses.kakaoMap;
	},
	getCurLat(state) {
		return state.houses.curLat;
	},
	getCurLng(state) {
		return state.houses.curLng;
	}
};
