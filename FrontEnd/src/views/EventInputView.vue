<template>
  <div class="event">
    <CommonBackground></CommonBackground>
    <CommonBanner title="이벤트"></CommonBanner>
    <div class="view-content">
      <!-- 제목, 내용 입력 -->
      <div class="container d-flex flex-wrap flex-column">
        <base-input placeholder="제목" required v-model="title"></base-input>
        <!-- 이벤트 기간 입력 -->
        <div class="d-flex">
          <div class="form-group align-self-center ml-2 mr-4">
            <small style="width: max-content" class="d-block text-uppercase font-weight-bold">이벤트 기간</small>
          </div>
          <div style="width: 100%">
            <event-date-picker-range class="w-100" @select-start-date="changeStartDate" @select-end-date="changeEndDate"></event-date-picker-range>
          </div>
        </div>
        <div id="divEditorInput"></div>
        <!-- 첨부파일  -->
        <div class="container border table">
          <div class="mt-3 mb-3 font-weight-bold">첨부파일</div>
          <div class="mb-3" id="imgFileUploadInsertWrapper">
            <input @change="changeFile" type="file" id="inputFileUploadInsert" multiple />
            <div id="imgFileUploadInsertThumbnail" class="thumbnail-wrapper">
              <!-- vue way img 를 만들어서 append 하지 않고, v-for 로 처리 -->
              <img v-for="(file, index) in fileList" v-bind:src="file" v-bind:key="index" />
            </div>
          </div>
        </div>
        <base-button @click="eventInsert" class="mt-3 w-25 form-group mx-auto">등록</base-button>
      </div>
    </div>
  </div>
</template>

<script>
import Vue from "vue";
import CKEditor from "@ckeditor/ckeditor5-vue2";
import ClassicEditor from "@ckeditor/ckeditor5-build-classic";
import BaseInput from "@/components/BaseInput.vue";
import BaseButton from "@/components/BaseButton.vue";
import EventDatePickerRange from "@/views/components/EventDatePickerRange.vue";
import CommonBackground from "./components/CommonBackground.vue";
import CommonBanner from "./components/CommonBanner.vue";

import http from "@/common/axios.js";
import alertify from "alertifyjs";

Vue.use(CKEditor);
export default {
  components: { BaseInput, BaseButton, EventDatePickerRange, CommonBackground, CommonBanner },
  data() {
    return {
      title: "",
      // CKEditor
      CKEditor: null,

      // 이벤트 기간
      startDate: null,
      endDate: null,

      // 첨부파일
      // attachFile: false,
      fileList: [],
    };
  },
  methods: {
    initUI() {
      this.title = "";
      this.CKEditor.setData("");

      this.attachFile = false;
      this.fileList = [];
    },
    changeFile(fileEvent) {
      this.fileList = [];

      const fileArray = Array.from(fileEvent.target.files);
      fileArray.forEach((file) => {
        this.fileList.push(URL.createObjectURL(file));
      });
    },
    changeStartDate(startDate) {
      this.startDate = startDate;
    },
    changeEndDate(endDate) {
      this.endDate = endDate;
    },
    // 필요한 내용이 다 들어있는지 검사
    validateInput() {
      if (this.title.length == 0) {
        alertify.error("제목을 입력해주세요.", 1.5);
        return false;
      }
      if (this.CKEditor.getData().length == 0) {
        alertify.error("내용을 입력해주세요.", 1.5);
        return false;
      }
      if (this.startDate == null) {
        alertify.error("이벤트 시작일을 선택해주세요.", 1.5);
        return false;
      }
      if (this.endDate == null) {
        alertify.error("이벤트 종료일을 선택해주세요.", 1.5);
        return false;
      }
      return true;
    },
    getCategoryCode() {
      let today = new Date();
      let startDate = new Date(this.startDate);
      let endDate = new Date(this.endDate);
      if (today.getDate() < startDate.getDate()) {
        // 시작 전
        return "001";
      }
      if (today.getDate() > endDate.getDate()) {
        // 마감
        return "003";
      }
      return "002";
    },
    async eventInsert() {
      if (!this.validateInput()) {
        return;
      }

      let formData = new FormData();
      formData.append("title", this.title);
      formData.append("content", this.CKEditor.getData());
      formData.append("startDate", this.startDate);
      formData.append("endDate", this.endDate);
      formData.append("code", this.getCategoryCode());

      // file upload
      let attachFiles = document.querySelector("#inputFileUploadInsert").files;

      if (attachFiles.length > 0) {
        const fileArray = Array.from(attachFiles);
        fileArray.forEach((file) => formData.append("file", file));
      }

      let options = {
        headers: { "Content-Type": "multipart/form-data" },
      };

      try {
        let { data } = await http.post("/events", formData, options);

        console.log("InsertModalVue: data : ");
        console.log(data);
        if (data.result == "login") {
          this.$router.push("/login");
        } else {
          alertify.success("글이 등록되었습니다.", 1.5);
          this.$router.push({ name: "event" });
        }
      } catch (error) {
        // console.log("InsertModalVue: error ");
        console.log(error);
        alertify.error("등록을 실패하였습니다.", 1.5);
      }
    },
  },
  async mounted() {
    try {
      this.CKEditor = await ClassicEditor.create(document.querySelector("#divEditorInput"));
      this.initUI();
    } catch (error) {
      console.error(error);
    }
  },
};
</script>

<style scoped>
.event >>> .board-title {
  /* background-color: theme-color('secondary'); */
  margin-top: 6rem !important;
}

.event >>> .ck-editor__editable {
  height: 500px;
}

.event >>> .thumbnail-wrapper img {
  width: 100px !important;
  margin-right: 5px;
  max-width: 100%;
}

.event >>> .dropdown-toggle::after {
  /* dropdown 화살표 오른쪽 정렬 */
  position: absolute;
  left: 90%;
  top: 45%;
}
</style>
