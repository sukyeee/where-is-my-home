<template>
  <div class="event-detail">
    <CommonBackground></CommonBackground>
    <CommonBanner title="이벤트"></CommonBanner>
    <div class="view-content">
      <!-- 카테고리, 수정 삭제 버튼 -->
      <div class="container d-flex justify-content-between">
        <small class="category border rounded px-3 bg-secondary"># {{ eventDetail.category.name }}</small>
        <div v-if="getIsLogin && getIsAdminUser">
          <base-button @click="routeToUpdateView" class="h-100">수정</base-button>
          <base-button @click="checkDelete" class="h-100" type="danger">삭제</base-button>
        </div>
      </div>
      <div class="container mt-3 event-detail-container d-flex flex-wrap flex-column">
        <!-- 제목 -->
        <h1 class="title mb-3 font-weight-bold">{{ eventDetail.title }}</h1>
        <!-- 이벤트 기간 -->
        <div class="mt-3 d-flex">
          <div class="col-8 pl-0">
            <span class="font-weight-bold">이벤트 기간</span><span class="px-3">|</span>{{ eventDetail.startDate + " ~ " + eventDetail.endDate }}
          </div>
          <div class="col-4 pr-0 text-right">
            <span class="font-weight-bold">작성일</span><span class="px-3">|</span>{{ eventDetail.registerDate }}
          </div>
        </div>
        <hr class="w-100 mb-5 mt-1" />
        <!-- 내용 -->
        <div class="content" v-html="eventDetail.content"></div>
        <hr class="w-100 mt-5 mb-0" />
        <!-- 첨부파일 -->
        <div class="container" v-if="eventDetail.fileList.length > 0">
          <div class="mt-3 mb-3 font-weight-bold">첨부파일</div>
          <div class="container border table">
            <div class="row pl-3" v-for="(file, index) in eventDetail.fileList" :key="index">
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
      <div class="container mt-5 d-flex justify-content-between">
        <base-button @click="$router.push({ name: 'event' })">&lt; 목록</base-button>
        <base-button v-if="getIsLogin && isJoined" @click="checkJoinCancel" type="danger" class=""> 신청 취소 </base-button>
        <base-button v-if="getIsLogin && !isJoined" @click="checkJoin" type="default" class=""> 참여 신청 </base-button>
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
    eventDetail() {
      return this.$store.getters.getEventDetail;
    },
    isJoined() {
      return this.$store.getters.getIsJoined;
    },
    getIsLogin() {
      return this.$store.getters.getIsLogin;
    },
    getIsAdminUser() {
      return this.$store.getters.getIsAdminUser;
    },
  },
  methods: {
    routeToUpdateView() {
      this.$router.push({ name: "event-update" });
    },
    checkDelete() {
      var $this = this; // alertify.confirm-function()에서 this 는 alertify 객체
      alertify.confirm(
        "삭제 확인",
        "이 글을 삭제하시겠습니까?",
        function () {
          // board.boardId 사용 X
          $this.eventDelete(); // $this 사용
        },
        function () {
          console.log("cancel");
        }
      );
    },
    async eventDelete() {
      // detail에서 eventId를 가져온 상태
      try {
        let { data } = await http.delete("/events/" + this.$store.getters.getEventId);
        // console.log(data);

        if (data.result == "login") {
          this.doLogout();
        } else {
          alertify.success("글이 삭제되었습니다.");
          this.$router.push({ name: "event" });
        }
      } catch (error) {
        console.log(error);
        alertify.error("삭제를 실패하였습니다.");
      }
    },
    checkJoin() {
      var $this = this; // alertify.confirm-function()에서 this 는 alertify 객체
      alertify.confirm(
        "신청 확인",
        "이벤트에 참여하시겠습니까?",
        function () {
          // board.boardId 사용 X
          $this.eventJoin(); // $this 사용
        },
        function () {
          console.log("cancel");
        }
      );
    },
    async eventJoin() {
      let formData = new FormData();
      formData.append("eventId", this.$store.getters.getEventId);

      try {
        let { data } = await http.post("/events/join/" + this.$store.getters.getEventId, formData);
        // console.log(data);

        if (data.result == "login") {
          this.doLogout();
        } else {
          alertify.success("이벤트 참여 신청되었습니다.");
          this.$router.push({ name: "event" });
        }
      } catch (error) {
        console.log(error);
        alertify.error("신청을 실패하였습니다.");
      }
    },
    checkJoinCancel() {
      var $this = this; // alertify.confirm-function()에서 this 는 alertify 객체
      alertify.confirm(
        "취소 확인",
        "이벤트 참여 신청을 취소하시겠습니까?",
        function () {
          // board.boardId 사용 X
          $this.eventJoinCancel(); // $this 사용
        },
        function () {
          console.log("cancel");
        }
      );
    },
    async eventJoinCancel() {
      try {
        let { data } = await http.delete("/events/join/" + this.$store.getters.getEventId);
        // console.log(data);

        if (data.result == "login") {
          this.doLogout();
        } else {
          alertify.success("이벤트 참여 신청을 취소하였습니다.");
          this.$router.push({ name: "event" });
        }
      } catch (error) {
        console.log(error);
        alertify.error("취소를 실패하였습니다.");
      }
    },
    doLogout() {
      this.$router.push("/login");
    },
  },
};
</script>

<style scoped>
.event-detail >>> .category {
  width: max-content;
  height: max-content;
}

.event-detail >>> .content {
  min-height: 300px;
}
</style>
