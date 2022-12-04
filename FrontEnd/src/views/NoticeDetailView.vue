<template>
  <div class="notice-detail">
    <CommonBackground></CommonBackground>
    <CommonBanner title="공지사항"></CommonBanner>
    <div class="view-content">
      <!-- 카테고리, 수정 삭제 버튼 -->
      <div class="container d-flex justify-content-between">
        <small class="category border rounded px-3 bg-secondary"># {{ noticeDetail.category.name }}</small>
        <div v-if="getIsAdminUser">
          <base-button @click="routeToUpdateView" class="h-100">수정</base-button>
          <base-button @click="checkDelete" class="h-100" type="danger">삭제</base-button>
        </div>
      </div>
      <div class="container mt-3 notice-detail-container d-flex flex-wrap flex-column">
        <!-- 제목 -->
        <h1 class="title mb-3 font-weight-bold">{{ noticeDetail.title }}</h1>
        <!-- 작성일, 작성자 -->
        <div class="mt-3 d-flex">
          <div class="col-6 pl-0"><span class="font-weight-bold">작성일</span><span class="px-3">|</span>{{ noticeDetail.registerDate }}</div>
          <div class="col-6 pr-0 text-right">
            <span class="h5 font-weight-bold">{{ noticeDetail.userName }}</span
            ><span class="px-3">|</span><span class="font-weight-bold">작성자</span>
          </div>
        </div>
        <hr class="w-100 mb-5 mt-1" />
        <!-- 내용 -->
        <div class="content" v-html="noticeDetail.content"></div>
        <hr class="w-100 mt-5 mb-0" />
        <!-- 첨부파일 -->
        <div class="container" v-if="noticeDetail.fileList.length > 0">
          <div class="mt-3 mb-3 font-weight-bold">첨부파일</div>
          <div class="container border table">
            <div class="row pl-3" v-for="(file, index) in noticeDetail.fileList" :key="index">
              <div>
                <span class="fileName">{{ file.fileName }}</span>
                &nbsp;&nbsp;
                <a type="button" class="btn btn-outline btn-gray btn-xs" v-bind:href="file.fileUrl" v-bind:download="file.fileName">내려받기</a>
              </div>
            </div>
          </div>
        </div>
        <hr class="w-100 mt-0 mb-0" />
      </div>
      <div class="container mt-5">
        <base-button @click="$router.push({ name: 'notice' })">&lt; 목록</base-button>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/common/axios.js";
import CommonBackground from "./components/CommonBackground.vue";
import CommonBanner from "./components/CommonBanner.vue";
import alertify from "alertifyjs";

export default {
  components: { CommonBackground, CommonBanner },
  computed: {
    noticeDetail() {
      return this.$store.getters.getNoticeDetail;
    },
    getIsAdminUser() {
      return this.$store.getters.getIsAdminUser;
    },
  },
  methods: {
    routeToUpdateView() {
      this.$router.push({ name: "notice-update" });
    },
    checkDelete() {
      var $this = this; // alertify.confirm-function()에서 this 는 alertify 객체
      alertify.confirm(
        "삭제 확인",
        "이 글을 삭제하시겠습니까?",
        function () {
          // board.boardId 사용 X
          $this.noticeDelete(); // $this 사용
        },
        function () {
          console.log("cancel");
        }
      );
    },
    async noticeDelete() {
      // detail에서 noticeId를 가져온 상태
      try {
        let { data } = await http.delete("/notices/" + this.$store.getters.getNoticeId);
        // console.log(data);

        if (data.result == "login") {
          this.doLogout();
        } else {
          alertify.success("글이 삭제되었습니다.", 1.5);
          this.$router.push({ name: "notice" });
        }
      } catch (error) {
        console.log(error);
        alertify.error("삭제를 실패하였습니다.", 1.5);
      }
    },
    doLogout() {
      this.$router.push("/login");
    },
  },
};
</script>

<style scoped>
.notice-detail >>> .category {
  width: max-content;
  height: max-content;
}

.notice-detail >>> .content {
  min-height: 300px;
}
</style>
