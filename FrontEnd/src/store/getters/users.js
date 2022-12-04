export default {
  getReviewList(state) {
    return state.userReview;
  },
  getReviewListTotalCount(state) {
    return state.userReviewTotalCount;
  },
  getmyReviewScore(state) {
    return state.userReview.length > 0 ? state.userReview[0].userScore : 0;
  },
  getUserProfile(state) {
    return state.userInfo.userProfileImageUrl;
  },
};
