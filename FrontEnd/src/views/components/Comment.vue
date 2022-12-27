<template>
  <div>
    <!-- 댓글 추가 input -->
    <!-- 로그인 된 사용자만 보이게 -->
    <div v-if="$store.state.isLogin" class="d-flex align-items-center py-3">
      <img
        class="rounded-circle mr-3"
        src="https://picsum.photos/250/100?1"
        alt=""
        style="width: 40px; height: 40px"
      />
      <div class="w-100 ms-3">
        <div class="d-flex w-100 justify-content-between">
          <h6 class="mb-0">{{ $store.state.userInfo.userName }}</h6>
          <small></small>
        </div>
        <base-input
          alternative
          class="mb-2 mt-1"
          placeholder="댓글 추가..."
          v-model="commentContent"
        >
        </base-input>
      </div>
      <base-button type="primary" class="ml-2 mt-3" @click="commentInsert">댓글</base-button>
    </div>
    <div v-for="(comment, index) in listGetters" :key="index">
      <div class="py-1 border-bottom mt-3" v-if="comment.parentCommentId == 0">
        <single-comments
          :comment="comment"
          :qnaId="comment.qnaId"
          :commentId="comment.commentId"
        ></single-comments>
        <reply-comments
          :qnaId="comment.qnaId"
          :parentCommentId="comment.commentId"
          :commentLists="listGetters"
        ></reply-comments>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/common/axios.js";

import ReplyComments from "@/views/components/ReplyComments.vue";
import SingleComments from "@/views/components/SingleComments.vue";
import alertify from "alertifyjs";

export default {
  data() {
    return {
      commentContent: this.$store.state.qnas.comment.content,
    };
  },
  components: {
    ReplyComments,
    SingleComments,
  },
  computed: {
    listGetters() {
      return this.$store.getters.getCommentList;
    },
  },
  created() {
    this.commentList();
  },
  methods: {
    // comment 목록 불러오기
    commentList() {
      this.$store.dispatch("commentList");
    },
    init() {
      // 댓글 내용 초기화
      this.commentContent = "";
    },

    // comment 추가
    async commentInsert() {
      console.log(this.commentContent);
      console.log(this.$store.state.qnas.qna.qnaId);

      let formData = new FormData();
      formData.append("content", this.commentContent);
      formData.append("qnaId", this.$store.state.qnas.qna.qnaId);

      try {
        let { data } = await http.post("/comments", formData);
        console.log(data);

        if (data.result == "login") {
          // this.doLogout();
          this.$router.push("/login");
        } else {
          alertify.success("댓글이 등록되었습니다.", 1.5);
          this.commentList();
          this.init();
        }
      } catch (error) {
        console.log(error);
      }
    },
  },
};
</script>

<style scoped></style>
