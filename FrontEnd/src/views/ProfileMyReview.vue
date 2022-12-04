<template>
  <div class="profile-my-review">
    <div v-for="(review, index) in getterReviewList" :key="index" class="pb-2 border-bottom">
      <div class="d-flex w-100">
        <div class="font-weight-bold">{{ review.userNickname }}</div>
        &nbsp;

        <span>
          <small>{{ review.apartmentName }} </small>
          <badge pill type="yellow">
            별점 {{ review.score }}
            <i class="fa fa-star text-yellow" aria-hidden="true" v-for="index in review.score" :key="index"></i>
          </badge>
        </span>


        <div class="d-flex justify-content-end w-100">
          <div class="ml-2 mr-1 pointer justify-content-end" @click="openEditForm(index, review.content)">
            <badge type="white" class="btnEdit">수정</badge>
          </div>
          <div class="pointer justify-content-end" @click="myReviewDelete(index)">
            <badge type="white" class="btnDelete">삭제</badge>
          </div>
        </div>
      </div>

      <div>
        <small class="text-gray">{{ review.registerDate }}</small>
      </div>


      <div v-if="review.isEditOpen" class="mt-2">

        <span class="d-inline display-4" v-for="(star, index) in starUpdateList" :key="index">
          <i v-show="star" class="fa fa-star text-yellow" aria-hidden="true" @click="setStar(index, 'update')"
            style="cursor: pointer"></i>
          <i v-show="!star" class="fa fa-star-o text-yellow" aria-hidden="true" @click="setStar(index, 'update')"
            style="cursor: pointer"></i>
        </span>
        <textarea class="form-control" id="reviewText" rows="3" placeholder="리뷰를 작성해보세요 📑"
          v-model="reviewUpdateContent"></textarea>
        <div>
          <base-button size="sm" type="gray" outline class="mt-1" @click="myReviewUpdate(index)">
            확인
          </base-button>
          <base-button size="sm" type="gray" outline class="mt-1 mr-2" @click="openEditForm(index)">
            취소
          </base-button>
        </div>
      </div>

      <div v-else class="mt-1 ml-1">{{ review.content }}</div>
    </div>
  </div>





</template>
<script>
import { mapGetters } from "vuex";
import alertify from "alertifyjs";

export default {
  components: {

  },
  data() {
    return {
      starUpdateList: [false, false, false, false, false],
      reviewUpdateContent: "",
    }
  },
  computed: {
    ...mapGetters({
      getterReviewList: "getReviewList",
      getUserInfo: "getUserInfo",
    }),
  },
  created() {
    this.getReviewList();
  },

  methods: {

    // 내가 쓴 리뷰 리스트 가져오기
    async getReviewList() {
      console.log("리뷰리스트 가져오기")
      await this.$store.dispatch("getReviewList");

      console.log(this.getUserInfo)

      console.log("리뷰리스트~~~", this.getterReviewList)
    },

    /* 수정 폼 열기 */
    openEditForm(idx, content) {
      this.reviewUpdateContent = content;
      this.$store.commit("SET_USER_REVIEW_IS_EDIT_OPEN", idx);
      // 별점 init
    },

    /* 리뷰 수정 */
    async myReviewUpdate(idx) {
      let review = {
        reviewId: this.getterReviewList[idx].reviewId,
        content: this.reviewUpdateContent,
        score: this.starUpdateNum,
      };

      let res = await this.$store.dispatch("houseReviewUpdate", review);

      if (res == 1) {
        alertify.success("리뷰 수정에 성공하였습니다.", 1.5);
        this.getReviewList();
      } else {
        alertify.error("리뷰 수정에 실패하였습니다.", 1.5);
      }
    },

    /* 리뷰 삭제 */
    async myReviewDelete(idx) {
      let reviewId = this.getterReviewList[idx].reviewId;

      let res = await this.$store.dispatch("houseReviewDelete", reviewId);

      if (res == 1) {
        alertify.success("리뷰 삭제에 성공하였습니다.", 1.5);
        this.getReviewList();
      } else {
        alertify.error("리뷰 삭제에 실패하였습니다.", 1.5);
      }
    },

    /* 별점 주기 */
    setStar(idx, type) {
      idx = idx + 1;

      if (type == "update") {
        this.starUpdateList = [];
        this.starUpdateNum = idx;
        for (let i = 0; i < idx; i++) this.starUpdateList.push(true);
        for (let i = 0; i < 5 - idx; i++) this.starUpdateList.push(false);
      }
    },



  }


};

</script>
<style lang="scss" scoped>
.house-review-section {
  background-color: #fff;
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
  color: $default;
  transition: 0.2s all ease;

  &:hover {
    background-color: $default;
    color: #fff;
  }
}

.btnDelete {
  color: $danger;
  transition: 0.2s all ease;

  &:hover {
    background-color: $danger;
    color: #fff;
  }
}

.pointer {
  cursor: pointer;
}
</style>