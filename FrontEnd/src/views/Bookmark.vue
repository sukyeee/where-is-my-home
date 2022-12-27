<template>
  <div class="qna">
    <CommonBackground></CommonBackground>
    <CommonBanner title="북마크"></CommonBanner>
    <div class="view-content">
      <bookmark-table></bookmark-table>

      <!-- 1st Hero Variation -->
    </div>
    <house-spec-compare-modal :modalShow.sync="houseSpec" @close-modal="closeHouseSpecModalShow">
    </house-spec-compare-modal>
  </div>
</template>

<script>
import BookmarkTable from "@/views/components/BookmarkTable.vue";
import BasePagination from "../components/BasePagination.vue";
import HouseSpecCompareModal from "@/views/components/HouseSpecCompareModal.vue";
import CommonBackground from "./components/CommonBackground.vue";
import CommonBanner from "./components/CommonBanner.vue";

export default {
  name: "bookmark",
  components: {
    BookmarkTable,
    BasePagination,
    HouseSpecCompareModal,
    CommonBackground,
    CommonBanner,
  },
  data() {
    return {
      houseSpec: false,
    };
  },
  methods: {
    showHouseSpecModalShow() {
      this.$store.dispatch("getHouseSpecList");
      this.houseSpec = true;
    },
    closeHouseSpecModalShow() {
      this.houseSpec = false;
    },
  },
};
</script>

<style scoped></style>
