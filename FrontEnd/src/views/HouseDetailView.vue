<template>
  <div class="house-detail-view">
    <HouseDetail></HouseDetail>
    <HouseGraph></HouseGraph>
    <HouseReview></HouseReview>
    <TodayNews></TodayNews>
  </div>
</template>

<script>
import HouseDetail from "./components/HouseDetail.vue";
import HouseReview from "./components/HouseReview.vue";
import HouseGraph from "./components/HouseGraph.vue";
import TodayNews from "./components/TodayNews.vue";

export default {
  components: {
    HouseDetail,
    HouseReview,
    HouseGraph,
    TodayNews,
  },
};
</script>

<style lang="scss" scoped>
.house-detail-view {
  // background-color: #fff;
}
</style>
