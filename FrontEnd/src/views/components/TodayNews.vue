<template>
  <div class="p-4">
    <!-- <div class="border-top mt-1 mb-2"></div> -->
    <div class="house-review-title font-weight-bold">
      <i class="ni ni-world-2" id="news-icon"></i>
      오늘의 뉴스
    </div>
    <!-- 행정동 관련 뉴스 -->
    <div>
      <table class="d-block table table-sm table-hover py-2">
        <tbody class="d-block">
          <tr v-for="(news, index) in newsList" :key="index" class="d-block">
            <td class="d-inline-block col-9">
              <a :href="news.link" v-html="news.title" class="d-block text-truncate" />
            </td>
            <td class="d-inline-block col-3 text-right">
              <small>{{ news.pubDate | makeDateOrTimeStr }}</small>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import http from "@/common/axios";
import util from "@/common/util";

export default {
  data() {
    return {
      newsList: [],
    };
  },
  computed: {
    sido() {
      return this.$store.getters.getSelectedSido;
    },
    dong() {
      return this.$store.getters.getSelectedDong;
    },
  },
  methods: {
    async searchNews(sido, dong) {
      let query = `sido=${sido}&dong=${dong}`;
      try {
        let { data } = await http.get("/houses/news?" + query);

        if (data.result == "login") {
        } else {
          this.newsList = data.items;
          console.log(data.items);
        }
      } catch (error) {
        console.log(error);
      }
    },
  },
  watch: {
    dong() {
      console.log("dong changed!!!!");
      this.searchNews(this.sido, this.dong);
    },
  },
  mounted() {
    console.log("mounted!!!!");
    this.searchNews(this.sido, this.dong);
  },
  filters: {
    makeDateOrTimeStr(dateStrFromAPI) {
      let date = new Date(dateStrFromAPI);
      return util.makeDateStr(date.getFullYear(), date.getMonth() + 1, date.getDate(), ".");
    },
  },
};
</script>

<style lang="scss" scoped>
.house-review-title {
  color: $default;
  border-radius: 3px;
}

#news-icon {
  position: relative;
  top: 2px;
}
</style>
