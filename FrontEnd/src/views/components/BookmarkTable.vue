<template>

  <div class="bookmark">
    <div class="position-relative">

      <bookmark-category-bar :bookmarkList="listGetters" :bookmarkDongList="dongListGetters"></bookmark-category-bar>
      <base-card-table v-bind:bookmark-body-list="listGetters"></base-card-table>

    </div>
  </div>

</template>

<script>
import http from "@/common/axios.js";

import BaseCardTable from "@/views/components/BookmarkCardTable.vue";
import BookmarkCategoryBar from "@/views/components/BookmarkCategoryBar.vue";
import alertify from 'alertifyjs';

export default {
  components: { BaseCardTable, BookmarkCategoryBar },

  data() {
    return {
    }
  },
  created() {

    // 페이지 재방문시 초기화 
    // this.init();
    this.bookmarkList();
    // this.$store.commit("SET_COMMENT_MOVE_PAGE", this.currentPageIndex);
  },
  computed: {
    listGetters() {
      return this.$store.getters.getBookmarkList;
    },
    dongListGetters() {
      return this.$store.getters.getBookmarkDongList;
    },

  },
  methods: {
    async bookmarkList() {
      this.$store.dispatch("bookmarkList");
    },

    // 북마크 상세조회
    async bookmarkDetail(bookmarkId) {
      try {
        console.log('bookmarkId????' + bookmarkId)
        let { data } = await http.get("/bookmarks/" + bookmarkId);
        console.log("bookmarkDetail data : ");
        console.log(data);
        if (data.result == "login") {
          this.$router.push("/login");
        } else {
          let { dto } = data;
          this.$store.commit("SET_BOOKMARK_DETAIL", dto);
        }
      } catch (error) {
        console.error(error);
      }

    },

 
  },

  data() {
    return {
      boardBodyList: [
        {
          category: "동별",
          thumbnailImage: "/img/theme/team-1-800x800.jpg",
          title: "우리 집 찾아조! 1번 별똥별",
          minDate: "2022.11.15",
          maxDate: "2022.11.15",
          joinTag: false, // 이미 참여 신청한 이벤트인지 표시
          isEnded: true,
        },

      ],
    };
  },
};
</script>

<style>

</style>