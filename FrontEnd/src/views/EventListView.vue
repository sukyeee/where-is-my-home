<template>
  <div class="event">
    <CommonBackground></CommonBackground>
    <CommonBanner title="이벤트"></CommonBanner>
    <div class="view-content">
      <event-category-bar></event-category-bar>
      <event-board-card-table @movePage="movePage"></event-board-card-table>
    </div>
  </div>
</template>

<script>
import EventBoardCardTable from "@/views/components/EventBoardCardTable.vue";
import EventCategoryBar from "@/views/components/EventCategoryBar.vue";
import CommonBackground from "./components/CommonBackground.vue"
import CommonBanner from "./components/CommonBanner.vue"

export default {
  components: { EventBoardCardTable, EventCategoryBar, CommonBackground, CommonBanner },
  computed : {
    eventList(){
      return this.$store.getters.getEventList;
    },
  },
  methods:{
    // pagination
    movePage(pageIndex){
        this.$store.commit("SET_EVENT_MOVE_PAGE", pageIndex);
      this.$store.dispatch("eventList");
    }
  },
  created(){
    this.$store.dispatch("eventCodeList");
    this.$store.dispatch("eventList");
  },
};
</script>

<style scoped>
  .board-title {
    /* background-color: theme-color('secondary'); */
    margin-top: 6rem !important;
  }
</style>
