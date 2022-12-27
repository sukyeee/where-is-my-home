<template>
  <div class="qna">
    <CommonBackground></CommonBackground>
    <CommonBanner title="Q&A"></CommonBanner>
    <div class="view-content">
      <div class="row">
        <qna-table></qna-table>
      </div>
      <!-- 1st Hero Variation -->
    </div>
  </div>
</template>

<script>
import QnaTable from "@/views/components/QnaTable.vue";
import BasePagination from "../components/BasePagination.vue";
import CommonBackground from "./components/CommonBackground.vue";
import CommonBanner from "./components/CommonBanner.vue";

export default {
  name: "qna",
  components: { QnaTable, BasePagination, CommonBackground, CommonBanner },
  data() {
    return {};
  },
};
</script>

<style scoped>
.board-title {
  /* background-color: theme-color('secondary'); */
  margin-top: 6rem !important;
}
</style>
