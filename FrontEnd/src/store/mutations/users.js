export default {
  SET_USER_REVIEW(state, payload) {
    state.userReview = payload;
  },

  SET_USER_REVIEW_TOTAL_COUNT(state, payload) {
    state.userReviewTotalCount = payload;
  },

  // review isEditOpen
  SET_USER_REVIEW_IS_EDIT_OPEN(state, idx) {
    state.userReview[idx].isEditOpen = !state.userReview[idx].isEditOpen;
  },

  SET_USER_PROFILE(state, payload) {
    state.userInfo.userProfileImageUrl = payload;
  },
};
