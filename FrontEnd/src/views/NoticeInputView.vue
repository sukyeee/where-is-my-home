<template>
  <div class="notice">
    <CommonBackground></CommonBackground>
    <CommonBanner title="공지사항"></CommonBanner>

    <div class="view-content">
      <div class="container">
        <!-- 카테고리 선택 -->
        <div class="row">
          <div class="col-lg-3 col-sm-6 form-group">
            <base-dropdown class="h-100 w-100 border rounded d-flex flex-row align-items-center" tag="div" :title="category.name" position="right" menuClasses="w-100 select">
              <div v-for="(item, index) in categoryList" :key="index" class="dropdown-item" @click="selectCategory(item.name, item.code)">
                {{ item.name }}
              </div>
            </base-dropdown>
          </div>
        </div>
      </div>
      <!-- 제목, 내용 입력 -->
      <div class="container d-flex flex-wrap flex-column">
        <base-input placeholder="제목" required v-model="title"></base-input>
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
        <base-button @click="noticeInsert" class="mt-3 w-25 form-group mx-auto">등록</base-button>
      </div>
    </div>
  </div>
</template>

<script>
import Vue from "vue";
import alertify from "alertifyjs";
import CKEditor from "@ckeditor/ckeditor5-vue2";
import ClassicEditor from "@ckeditor/ckeditor5-build-classic";
import BaseInput from "@/components/BaseInput.vue";
import BaseDropdown from "@/components/BaseDropdown.vue";
import CommonBackground from "./components/CommonBackground.vue";
import CommonBanner from "./components/CommonBanner.vue";

import http from "@/common/axios.js";

Vue.use(CKEditor);

export default {
  components: { BaseInput, BaseDropdown, CommonBackground, CommonBanner },
  data() {
    return {
      // 공지사항 카테고리
      // [{ name: "공지사항", code: "001" }, { name: "업데이트", code: "002" },],
      category: { name: "카테고리", code: null },

      title: "",
      // CKEditor
      CKEditor: null,

      // 첨부파일
      attachFile: false,
      fileList: [],
    };
  },
  computed: {
    categoryList() {
      return this.$store.getters.getNoticeCodeList;
    },
  },
  methods: {
    initUI() {
      this.title = "";
      this.CKEditor.setData("");

      this.attachFile = false;
      this.fileList = [];
    },
    selectCategory(name, code) {
      // console.log("Selected!");
      this.category = { name: name, code: code };
    },
    changeFile(fileEvent) {
      this.fileList = [];

      const fileArray = Array.from(fileEvent.target.files);
      fileArray.forEach((file) => {
        this.fileList.push(URL.createObjectURL(file));
      });
    },
    validateInput() {
      if (this.category.code == null) {
        alertify.error("카테고리를 선택해주세요.", 1.5);
        return false;
      }
      if (this.title.length == 0) {
        alertify.error("제목을 입력해주세요.", 1.5);
        return false;
      }
      if (this.CKEditor.getData().length == 0) {
        alertify.error("내용을 입력해주세요.", 1.5);
        return false;
      }
      return true;
    },
    async noticeInsert() {
      if (!this.validateInput()) {
        return;
      }

      let formData = new FormData();
      formData.append("code", this.category.code);
      formData.append("title", this.title);
      formData.append("content", this.CKEditor.getData());

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
        let { data } = await http.post("/notices", formData, options);

        if (data.result == "login") {
          this.$router.push("/login");
        } else {
          alertify.success("글이 등록되었습니다.", 1.5);
          this.$router.push({ name: "notice" });
        }
      } catch (error) {
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
.notice >>> .board-title {
  /* background-color: theme-color('secondary'); */
  margin-top: 6rem !important;
}

.notice >>> .ck-editor__editable {
  height: 500px;
}

.notice >>> .thumbnail-wrapper {
  margin-top: 5px;
}

.notice >>> .thumbnail-wrapper img {
  width: 100px !important;
  margin-right: 5px;
  max-width: 100%;
}

.notice >>> .dropdown-toggle::after {
  /* dropdown 화살표 오른쪽 정렬 */
  position: absolute;
  left: 90%;
  top: 45%;
}
</style>
