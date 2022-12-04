import http from "@/common/axios.js";

export default {
  // 내가 쓴 리뷰 조회
  async getReviewList(context) {
    try {
      console.log("리뷰리스트2..");
      let { data } = await http.get("/user/reviews"); // params: params shorthand property, let response 도 제거
      console.log("review data : ");
      console.log(data);
      if (data.result == "login") {
        router.push("/login");
      } else {
        context.commit("SET_USER_REVIEW", data.review);
        context.commit("SET_USER_REVIEW_TOTAL_COUNT", data.reviewTotalCount);
      }
    } catch (error) {
      console.error(error);
    }
  },

  async myReviewDelete(context, reviewId) {
    console.log("async myReviewDelete");
    console.log(reviewId);

    try {
      let { data } = await http.delete(`/reviews/house/${reviewId}`);
      console.log("houseReviewDelete Response:", data);

      if (data.result == 1) {
        // if (data.houseReviewTotalCount > 0) {
        // 	context.commit("SET_HOUSE_REVIEW_SCORE", data.houseReviewScore);
        // }
        // context.commit("SET_DONG_REVIEW_SCORE", data.dongReviewScore);
      } else {
      }

      return data.result;
    } catch (err) {
      if (err.response.status == 403) {
        alert("로그인 하세요");
      }
      console.error(err);
      return -1;
    }
  },

  /* 내가 쓴 리뷰 수정 */
  async myReviewUpdate(context, review) {
    try {
      let { data } = await http.put(`/reviews/house`, review);
      console.log("houseReviewUpdate Response:", data);

      if (data.result == 1) {
        // if (data.houseReviewTotalCount > 0) {
        // 	context.commit("SET_HOUSE_REVIEW_SCORE", data.houseReviewScore);
        // }
        // context.commit("SET_DONG_REVIEW_SCORE", data.dongReviewScore);
      } else {
      }

      return data.result;
    } catch (err) {
      if (err.response.status == 403) {
        alert("로그인 하세요");
      }
      console.error(err);
      return -1;
    }
  },
};
