<template>
  <div class="notice">
    <CommonBackground></CommonBackground>
    <CommonBanner title="공지사항"></CommonBanner>

    <div class="view-content">
      <notice-category-bar></notice-category-bar>
      <notice-board-table @movePage="movePage"></notice-board-table>
    </div>
  </div>
</template>

<script>
import NoticeBoardTable from "@/views/components/NoticeBoardTable.vue";
import NoticeCategoryBar from "./components/NoticeCategoryBar.vue";
import CommonBackground from "./components/CommonBackground.vue";
import CommonBanner from "./components/CommonBanner.vue";

export default {
  components: { NoticeBoardTable, NoticeCategoryBar, CommonBackground, CommonBanner },
  computed: {
    noticeList() {
      return this.$store.getters.getNoticeList;
    },
  },
  methods: {
    // pagination
    movePage(pageIndex) {
      this.$store.commit("SET_NOTICE_MOVE_PAGE", pageIndex);
      this.$store.dispatch("noticeList");
    },
  },
  created() {
    this.$store.dispatch("noticeCodeList");
    this.$store.dispatch("noticeList");
  },
};
</script>

<style scoped></style>
