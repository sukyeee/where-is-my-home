<template>
  <div class="container">
    <div class="row mb-3">
      <!-- tags -->
      <div class="col-lg-6 col-sm-8 d-flex">

        <div class="dong">
          <select @change="bookmarkFilterList" v-model=$store.state.bookmarks.bookmark.dongFilter
            class="form-select  custom-select mr-3 w-80 mr-2">
            <option selected>전체</option>
            <option :value=dong v-for="(dong, index) in bookmarkDongList" :key="index">
              {{ dong }}
            </option>
          </select>
        </div>

        <!-- <base-button  class="form-group w-20">동별</base-button> -->
        <base-button class="form-group w-20 d-flex justify-content-end" @click="showHouseSpecModalShow">비교하기</base-button>
        <div class="d-flex">

        </div>

      </div>
      <!-- Search bar -->
      <div class="col-lg-6 col-sm-8 d-flex">
      </div>
    </div>

    <house-spec-compare-modal
      :modalShow.sync="houseSpec"
      @close-modal="closeHouseSpecModalShow"
    ></house-spec-compare-modal>

  </div>
</template>

<script>
import HouseSpecCompareModal from "@/views/components/HouseSpecCompareModal.vue";

export default {
  components: {  HouseSpecCompareModal },
  props: [
    'bookmarkList',
    'bookmarkDongList'
  ],
  data() {
    return {
      houseSpec: false,

    }
  },
  created() {

  },
  computed: {
    filterListGetters() {
      return this.$store.getters.getBookmarkFilterList;
    },
    listGetters() {
      return this.$store.getters.getBookmarkList;
    },
  },

  methods: {

    routeToInsertView() {
      // this.$store.dispatch('eventInit');
      this.$router.push({ name: 'event-input' });
    },
    bookmarkFilterList() {

      if (this.$store.state.bookmarks.bookmark.dongFilter == "전체") {
        this.$store.dispatch("bookmarkList");
      } else {
        this.$store.dispatch("bookmarkFilterList");
      }
    },

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

<style scoped>

</style>
