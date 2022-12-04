export default {
  /* BOOKMARK View */
  getBookmarkList(state) {
    return state.bookmarks.bookmark.list;
  },

  getBookmarkListTotalCount(state) {
    return state.bookmarks.bookmark.totalListItemCount;
  },

	getBookmarkDongList(state) {
		return state.bookmarks.bookmark.dongList;
	},

  getBookmarkHouseId(state) {
		return state.bookmarks.bookmark.houseId;
	},
  getBookmarkHouseDealId(state) {
		return state.bookmarks.bookmark.houseDealId;
	},
 

  /* 아파트 스펙 비교 창 */
  getHouseSpecList(state) {
    return state.bookmarks.bookmark.houseSpecList;
  },

  getHouseSpecKakaoMapBounds(state) {
    return state.bookmarks.bookmark.houseSpecKakaoMapBounds;
  },
};
