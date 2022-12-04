<template>
  <div class="container">
    <div class="row mb-3">
      <!-- tags -->
      <div class="col-lg-6 col-sm-4 d-flex align-items-center">
        <base-button @click="getAllList" class="form-group" type="secondary" size="sm"
          ># 전체</base-button
        >
        <base-button
          @click="getSearchCodeList(category.code)"
          class="form-group"
          type="secondary"
          v-for="(category, index) in categoryList"
          :key="index"
          size="sm"
        >
          {{ "# " + category.name }}
        </base-button>
      </div>
      <!-- Search bar -->
      <div class="col-lg-6 col-sm-8 d-flex">
        <base-input
          v-model="searchWord"
          @keydown.enter="getSearchWordList"
          class="w-80 mr-2 search-bar"
          placeholder="Search"
          addon-left-icon="ni ni-zoom-split-in"
        >
        </base-input>
        <base-button @click="getSearchWordList" class="form-group w-20">검색</base-button>
        <!-- <router-link :to="{ name: 'event-input' }" class="form-group w-20"> -->
        <div v-if="getIsAdminUser" class="form-group w-20">
          <base-button @click="routeToInsertView" class="h-100">추가</base-button>
        </div>
        <!-- </router-link> -->
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      code: "",
      searchWord: "",
    };
  },
  computed: {
    categoryList() {
      return this.$store.getters.getEventCodeList;
    },
    getIsAdminUser() {
      return this.$store.getters.getIsAdminUser;
    },
  },
  methods: {
    routeToInsertView() {
      // this.$store.dispatch('eventInit');
      this.$router.push({ name: "event-input" });
    },
    getAllList() {
      this.code = "";
      this.searchWord = "";

      this.getList();
    },
    getSearchCodeList(code) {
      this.code = code;
      this.searchWord = "";
      this.getList();
    },
    getSearchWordList() {
      this.getList();
    },
    getList() {
      this.$store.commit("SET_EVENT_CODE", { code: this.code });
      this.$store.commit("SET_EVENT_SEARCH_WORD", { searchWord: this.searchWord });
      this.$store.dispatch("eventList");
    },
  },
};
</script>

<style scoped></style>
