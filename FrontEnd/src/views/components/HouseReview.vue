<template>
  <div class="house-review-section p-4">
    <div>
      <!-- <div class="border-top mt-1 mb-2"></div> -->
      <div class="house-review-title font-weight-bold">
        <i class="ni ni-active-40" id="review-icon"></i>
        사용자 리뷰
      </div>
      <!-- <div class="border-bottom mt-1"></div> -->

      <div class="form-group mt-2">
        <!-- <label for="reviewText" class="font-weight-bold m-1">리뷰 작성하기</label> -->
        <span class="d-inline display-4" v-for="(star, index) in starInsertList" :key="index">
          <i
            v-show="star"
            class="fa fa-star text-yellow"
            aria-hidden="true"
            @click="setStar(index, 'insert')"
            style="cursor: pointer"
          ></i>
          <i
            v-show="!star"
            class="fa fa-star-o text-yellow"
            aria-hidden="true"
            @click="setStar(index, 'insert')"
            style="cursor: pointer"
          ></i>
        </span>
        <textarea
          class="form-control"
          id="reviewText"
          rows="3"
          placeholder="리뷰를 작성해보세요 📑"
          v-model="reviewContent"
        ></textarea>
        <base-button size="" type="gray" class="w-100 mt-1" @click="houseReviewInsert">
          리뷰 작성하기
        </base-button>
      </div>

      <div v-for="(review, index) in houseReviewList" :key="index" class="p-3 border-bottom">
        <div class="d-flex">
          <div class="font-weight-bold">{{ review.userNickname }}</div>
          &nbsp;
          <div>
            <small>사용자 고유 별점 {{ review.userScore }}</small>
          </div>
          <div v-if="review.sameUser" class="d-flex">
            <div class="ml-2 mr-1 pointer" @click="openEditForm(index, review.content)">
              <badge type="default" class="btnEdit">수정</badge>
            </div>
            <div class="pointer" @click="houseReviewDelete(index)">
              <badge type="danger" class="btnDelete">삭제</badge>
            </div>
          </div>
        </div>
        <div>
          <small class="text-gray">{{ review.registerDate }}</small>
        </div>
        <div class="d-flex mt-1" v-if="!review.isEditOpen">
          <div class="mt-1 mb-1">
            <badge pill type="yellow">
              별점 {{ review.score }}
              <i
                class="fa fa-star text-yellow"
                aria-hidden="true"
                v-for="index in review.score"
                :key="index"
              ></i>
            </badge>
          </div>
        </div>
        <div v-if="review.isEditOpen" class="mt-2">
          <span class="d-inline display-4" v-for="(star, index) in starUpdateList" :key="index">
            <i
              v-show="star"
              class="fa fa-star text-yellow"
              aria-hidden="true"
              @click="setStar(index, 'update')"
              style="cursor: pointer"
            ></i>
            <i
              v-show="!star"
              class="fa fa-star-o text-yellow"
              aria-hidden="true"
              @click="setStar(index, 'update')"
              style="cursor: pointer"
            ></i>
          </span>
          <textarea
            class="form-control"
            id="reviewText"
            rows="3"
            placeholder="리뷰를 작성해보세요 📑"
            v-model="reviewUpdateContent"
          ></textarea>
          <div>
            <base-button
              size="sm"
              type="gray"
              outline
              class="mt-1"
              @click="houseReviewUpdate(index)"
            >
              확인
            </base-button>
            <base-button
              size="sm"
              type="gray"
              outline
              class="mt-1 mr-2"
              @click="openEditForm(index)"
            >
              취소
            </base-button>
          </div>
        </div>
        <div v-else class="mt-1">{{ review.content }}</div>
      </div>
    </div>

    <div class="apt-list-pagination mt-3">
      <base-pagination
        :page-count="houseReviewTotalCount"
        v-model="currentPageIndex"
        size="sm"
        align="center"
      ></base-pagination>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import alertify from "alertifyjs";

export default {
  data() {
    return {
      currentPageIndex: 1,
      reviewContent: "",
      reviewScore: 5,
      reviewUpdateContent: "",
      reviewUpdateScore: 4,

      // rate
      starInsertNum: 5,
      starUpdateNum: 5,
      starInsertList: [true, true, true, true, true],
      starUpdateList: [true, true, true, true, true],
    };
  },

  computed: {
    ...mapGetters({
      isOpen: "getIsListOpen",
      houseDetail: "getHouseDetail",
      houseReviewList: "getHouseReviewList",
      houseReviewTotalCount: "getHouseReviewTotalCount",
      houseReviewScore: "getHouseReviewScore",
      dongReviewScore: "getDongReviewScore",
    }),
  },

  methods: {
    /* 아파트 리뷰 리스트 가져오기  */
    getHouseReviewList() {
      this.$store.dispatch("getHouseReviewList", this.houseDetail.houseId);
    },

    /* 아파트 리뷰 등록  */
    async houseReviewInsert() {
      let houseId = this.houseDetail.houseId;

      let review = {
        houseId: houseId,
        content: this.reviewContent,
        score: this.starInsertNum,
      };

      let res = await this.$store.dispatch("houseReviewInsert", review);

      if (res == 1) {
        alertify.success("리뷰 등록에 성공하였습니다.", 1.5);
        this.getHouseReviewList();
        this.getHouseReviewScore(houseId);
      } else {
        alertify.error("리뷰 등록에 실패하였습니다.", 1.5);
      }

      this.reviewContent = "";
      this.starInsertNum = 5;
    },

    /* 수정 폼 열기 */
    openEditForm(idx, content) {
      this.reviewUpdateContent = content;
      this.$store.commit("SET_HOUSE_REVIEW_IS_EDIT_OPEN", idx);
    },

    /* 아파트 리뷰 수정 */
    async houseReviewUpdate(idx) {
      let review = {
        reviewId: this.houseReviewList[idx].reviewId,
        content: this.reviewUpdateContent,
        score: this.starUpdateNum,
      };

      let houseId = this.houseDetail.houseId;

      let res = await this.$store.dispatch("houseReviewUpdate", review);

      if (res == 1) {
        alertify.success("리뷰 수정에 성공하였습니다.", 1.5);
        this.getHouseReviewList();
        this.getHouseReviewScore(houseId);
      } else {
        alertify.error("리뷰 수정에 실패하였습니다.", 1.5);
      }

      this.reviewContent = "";
      this.starInsertNum = 5;
    },

    /* 아파트 리뷰 삭제 */
    async houseReviewDelete(idx) {
      let reviewId = this.houseReviewList[idx].reviewId;

      let res = await this.$store.dispatch("houseReviewDelete", reviewId);

      if (res == 1) {
        alertify.success("리뷰 삭제에 성공하였습니다.", 1.5);
        this.getHouseReviewList();
      } else {
        alertify.error("리뷰 삭제에 실패하였습니다.", 1.5);
      }
    },

    /* 아파트 리뷰 총점 조회 */
    async getHouseReviewScore(houseId) {
      await this.$store.dispatch("getHouseReviewScore", houseId);
    },

    /* 별점 주기 */
    setStar(idx, type) {
      idx = idx + 1;

      if (type == "insert") {
        this.starInsertList = [];
        this.starInsertNum = idx;
        for (let i = 0; i < idx; i++) this.starInsertList.push(true);
        for (let i = 0; i < 5 - idx; i++) this.starInsertList.push(false);
      } else if (type == "update") {
        this.starUpdateList = [];
        this.starUpdateNum = idx;
        for (let i = 0; i < idx; i++) this.starUpdateList.push(true);
        for (let i = 0; i < 5 - idx; i++) this.starUpdateList.push(false);
      }
    },
  },

  watch: {
    async currentPageIndex() {
      this.$store.commit("SET_HOUSE_REVIEW_LIST_MOVE_PAGE", this.currentPageIndex);
      this.getHouseReviewList();
    },
  },

  created() {
    let houseId = this.houseDetail.houseId;
    this.getHouseReviewScore(houseId);
  },
};
</script>

<style lang="scss" scoped>
.house-review-section {
  // background-color: #fff;
  padding: 10px 0;
}
.house-review-title {
  // background: $default;
  color: $default;
  border-radius: 3px;
}

#review-icon {
  position: relative;
  top: 3px;
}

.badge {
  position: relative;
  top: -3px;
}

.btnEdit {
  // color: $default;
  // transition: 0.2s all ease;

  // &:hover {
  background-color: $default;
  color: #fff;
  // }
}
.btnDelete {
  background-color: $danger;
  color: #fff;
}
.pointer {
  cursor: pointer;
}
</style>
