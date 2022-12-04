<template>
  <div class="container d-flex flex-column align-items-center">
    <div class="container">
      <div class="row">
        <div class="col-lg-12">
          <div class="row row-grid">
            <!-- 카드 테이블 내용 -->
            <div class="col-sm-6 col-lg-3 mb-3" v-for="(event, index) in eventList" :key="index">
              <card class="w-100 border-0" hover shadow>
                <div
                  class="event-thumbnail"
                  :data-join="event.isJoined"
                  :data-ended="event.isEnded"
                >
                  <div class="event-thumbnail-image"><img :src="event.thumbnailFilePath" /></div>
                  <div v-show="event.isJoined" class="event-thumbnail-text">신청 완료</div>
                  <div v-show="event.isEnded" class="event-thumbnail-text">마감된 이벤트</div>
                </div>
                <div>
                  <p class="description mt-2">{{ event.startDate }} ~ {{ event.endDate }}</p>
                  <h6 class="text-primary text-uppercase text-truncate">{{ event.title }}</h6>
                </div>
                <base-button
                  @click="routeToDetailView(event.eventId)"
                  tag="a"
                  href="#"
                  type="primary"
                  class="mt-2 w-100"
                >
                  자세히 보기
                </base-button>
              </card>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- pagination -->
    <base-pagination
      class="mt-5"
      :perPage="this.$store.getters.getEventPageLinkCount"
      :total="this.$store.getters.getEventTotalListItemCount"
      :value="this.$store.getters.getEventCurrentPageIndex"
      @input="movePage"
    ></base-pagination>
  </div>
</template>

<script>
export default {
  computed: {
    eventList() {
      return this.$store.getters.getEventList;
    },
  },
  methods: {
    // 상세 조회
    routeToDetailView(eventId) {
      this.$store.dispatch("eventDetail", eventId);
      this.$router.push({ name: "event-detail" });
    },
    // pagination
    movePage(pageIndex) {
      this.$emit("movePage", pageIndex);
    },
  },
};
</script>

<style scoped lang="scss">
.table {
  width: 80%;
  background: white;
}

.event-thumbnail-image {
  width: 100%;
  // height: 100%;
  height: 150px;
  overflow: hidden;
}

.event-thumbnail-image img {
  width: 100%;
  // height: 100%;
  height: auto;
}

// .join-tag-container{
//   height: 25px;
//   // text-align: center;
// }

/* 참여중인 이벤트 표시 */
.event-thumbnail[data-ended] img {
  filter: grayscale(1);
}
.event-thumbnail {
  position: relative;
}
.event-thumbnail-text {
  /* padding: 5px 10px; */
  font-size: 80%;
  width: 100%;
  height: 23px;
  line-height: 23px;

  text-align: center;
  position: absolute;
  bottom: 0%;
  left: 50%;
  transform: translate(-50%, 0%);
}
.event-thumbnail[data-join] .event-thumbnail-text {
  color: $black;
  background-color: $success;
}
.event-thumbnail[data-ended] .event-thumbnail-text {
  color: $yellow;
  background-color: $default;
}
</style>
