export default {
    /* Q&A View */
	getQnaList(state) {
		return state.qnas.qna.list;
	},
	getQnaListTotalCount(state) {
		return state.qnas.qna.totalListItemCount;
	},
	getCommentList(state){
		return state.qnas.comment.list;
	},

}