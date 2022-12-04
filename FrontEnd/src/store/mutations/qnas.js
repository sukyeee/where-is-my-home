import util from "@/common/util.js";

export default {
    /* QnA 리스트 받기 */
	SET_QNA_LIST(state, list) {
		state.qnas.qna.list = list;
	},

	/* QnA 리스트 개수 받기 */
	SET_QNA_TOTAL_LIST_ITEM_COUNT(state, count) {
		state.qnas.qna.totalListItemCount = count;
	},

	SET_QNA_MOVE_PAGE(state, pageIndex) {
		state.qnas.qna.offset = (pageIndex - 1) * state.qnas.qna.listRowCount;
		state.qnas.qna.currentPageIndex = pageIndex;
	 },

	/* QNA 상세 정보  */
	SET_QNA_DETAIL(state, payload){
		state.qnas.qna.qnaId = payload.qnaId;
		state.qnas.qna.userId = payload.userId;
		state.qnas.qna.title = payload.title;
		state.qnas.qna.content = payload.content;
		state.qnas.qna.checkYn = payload.checkYn;
		state.qnas.qna.regDate = util.makeDateStr(payload.registerDate.date.year, payload.registerDate.date.month, payload.registerDate.date.day, ".");
		state.qnas.qna.regTime = util.makeTimeStr(payload.registerDate.time.hour, payload.registerDate.time.minute, payload.registerDate.time.second, ":"); 
	},

    /* Comment 리스트 받기 */
	SET_COMMENT_LIST(state, list) {
		state.qnas.comment.list = list;
	},
	/* Comment 리스트 개수 받기 */
	SET_COMMENT_TOTAL_LIST_ITEM_COUNT(state, count) {
		state.qnas.comment.totalListItemCount = count;
	},
    
	

}