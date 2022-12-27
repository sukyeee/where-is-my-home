<template>
  <div class="container w-50">
    <!-- Q&A 문의 작성 테이블  -->
    <card
      type="secondary"
      shadow
      header-classes="bg-white pb-5"
      body-classes="px-lg-5 py-lg-5"
      class="border-0"
    >
      <template>
        <div
          class="display-3 text-left mb-4 font-weight-800 text-default"
          style="text-shadow: 2px 1px 2px rgba(0, 0, 0, 0.2)"
        >
          문의 작성
        </div>

        <form role="form">
          <div class="mb-3">
            <label for="qnaDetailTitle" class="form-label">제목</label>
            <input
              type="text"
              class="form-control"
              placeholder="제목을 입력해주세요."
              aria-label="Username"
              aria-describedby="basic-addon1"
              v-model="title"
            />
          </div>

          <div class="mb-3">
            <label for="qnaDetailContent" class="form-label">문의 내용</label>
            <textarea
              class="form-control"
              rows="7"
              placeholder="내용을 입력해주세요."
              id="exampleFormControlTextarea1"
              v-model="content"
            ></textarea>
          </div>

          <div class="text-center">
            <base-button type="primary" class="my-4" @click="qnaInsert">작성하기</base-button>
          </div>
        </form>
      </template>
    </card>
  </div>
</template>

<script>
import http from "@/common/axios.js";
import alertify from "alertifyjs";

export default {
  data() {
    return {
      title: "",
      content: "",
    };
  },

  computed: {
    textColor() {
      return this.$store.state.headerDarkMode ? "yellow" : "default";
    },
    buttonColor() {
      return this.$store.state.headerDarkMode ? "yellow" : "default";
    },
    buttonTextColor() {
      return this.$store.state.headerDarkMode ? "default" : "secondary";
    },
    logoImage() {
      return this.$store.state.headerDarkMode ? "img/brand/logo2.png" : "img/brand/logo1.png";
    },
  },

  methods: {
    async qnaInsert() {
      let formData = new FormData();
      formData.append("title", this.title);
      formData.append("content", this.content);

      console.log(this.title);
      console.log(this.content);

      try {
        let { data } = await http.post("/qnas", formData);
        console.log(data);

        if (data.result == "login") {
          this.$router.push("/login");
        } else {
          alertify.success("글이 등록되었습니다.", 1.5);
          this.$router.push("/qna");
        }
      } catch (error) {
        console.log(error);
      }
    },
  },
};
</script>

<style scoped>
.board-title {
  /* background-color: theme-color('secondary'); */
  margin-top: 6rem !important;
}

input {
  border: 0;
}
textarea {
  resize: none;
  border: 0;
}
</style>
