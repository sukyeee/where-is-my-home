// import util from "@/common/util.js";

export default {
  /* Bookmark 리스트 받기  */
  SET_BOOKMARK_LIST(state, list) {
    state.bookmarks.bookmark.list = list;
  },
  /* Bookmark 리스트 개수 받기 */
  SET_BOOKMARK_TOTAL_LIST_ITEM_COUNT(state, count) {
    state.bookmarks.bookmark.totalListItemCount = count;
  },

  /* Bookmark 행정동 리스트 받기 */
  SET_BOOKMARK_DONG_LIST(state, dongList) {
    state.bookmarks.bookmark.dongList = dongList;
  },

  /* Bookmark 상세 정보  */
  SET_BOOKMARK_DETAIL(state, payload) {
    state.bookmarks.bookmark.bookmarkId = payload.bookmarkId;
    state.bookmarks.bookmark.userId = payload.userId;
    state.bookmarks.bookmark.houseDealId = payload.houseDealId;
    state.bookmarks.bookmark.dealAmount = payload.dealAmount;
    state.bookmarks.bookmark.dealMonth = payload.dealMonth;
    state.bookmarks.bookmark.dealDay = payload.dealDay;
    state.bookmarks.bookmark.area = payload.area;
    state.bookmarks.bookmark.floor = payload.floor;
    state.bookmarks.bookmark.userName = payload.userName;
    state.bookmarks.bookmark.build_year = payload.build_year;
    state.bookmarks.bookmark.dong = payload.dong;
    state.bookmarks.bookmark.roadName = payload.roadName;
    state.bookmarks.bookmark.jibun = payload.jibun;
    state.bookmarks.bookmark.apartmentName = payload.apartmentName;
  },
  /* 체크된 매물들 리스트 */
  SET_CHECKED_LIST(state, payload) {
    state.bookmarks.bookmark.houseSpecList = payload;
  },

  /* 내집찾기로 넘겨줄 데이터 저장  */
  SET_SEARCH_LIST(state, payload) {
    state.bookmarks.bookmark.houseDealId = payload.houseDealId;
    state.bookmarks.bookmark.houseId = payload.houseId;
  },


  /* 매물 스펙 비교 리스트 */
  SET_HOUSE_SPEC_LIST(state, payload) {
    state.bookmarks.bookmark.houseSpecList = payload;
  },
  SET_HOUSE_SPEC_KAKAO_MAP_BOUNDS(state, payload) {
    state.bookmarks.bookmark.houseSpecKakaoMapBounds = payload;
  },
};
