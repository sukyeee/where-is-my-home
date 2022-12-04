<template>
  <div class="container d-flex flex-column align-items-center">
    <table class="table table-hover bg-white p-3">
      <!-- table header-->
      <thead>
        <th scope="col" class="text-center">카테고리</th>
        <th scope="col" class="text-center">제목</th>
        <th scope="col" class="text-center">작성일</th>
      </thead>
      <!-- table body -->
      <tbody>
        <tr @click="routeToDetailView(board.noticeId)" v-for="(board, index) in noticeList" :key="index">

          <td class="col-2 text-center">{{ board.category }}</td>
          <td class="col-8">{{ board.title }}</td>
          <td class="col-2 text-center">{{ board.registerDate }}</td>
        </tr>
      </tbody>
    </table>
    <!-- pagination -->
    <base-pagination class="mt-5" 
    :perPage="this.$store.getters.getNoticePageLinkCount"
    :total="this.$store.getters.getNoticeTotalListItemCount"
    :value="this.$store.getters.getNoticeCurrentPageIndex"
    @input="movePage"
    ></base-pagination>
  </div>
</template>

<script>
  export default {
    computed : {
      noticeList(){
        return this.$store.getters.getNoticeList;
      },
    },
    methods:{
      // 상세 조회
      routeToDetailView(noticeId){
        this.$store.dispatch("noticeDetail", noticeId);
        this.$router.push({name: "notice-detail"});
      },
      // pagination
      movePage(pageIndex){
        this.$emit('movePage', pageIndex);
      }
    },
  };
</script>

<style>
  .table {
    width: 80%;
    background: white;
  }
</style>
