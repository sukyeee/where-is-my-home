<template>
  <div class="qnaInsert">
    <div class="position-relative">
      <!-- shape Hero -->
      <section class="section section-lg mb-5 bg-primary">
        <div class="container board-title">
          <div class="row row-grid justify-content-center">
            <div class="col-lg-8 text-center">
              <h1 class="display-3 text-secondary">Q&A</h1>
            </div>
          </div>
        </div>
      </section>
      <div class="row">
        <qna-insert v-bind:columns="columns"></qna-insert>
      </div>
      <!-- 1st Hero Variation -->
    </div>
  </div>
</template>

<script>
import QnaInsert from "@/views/components/QnaInsertTable.vue";

export default {
  name: "qna",
  components: { QnaInsert },
  data() {
    return {
      columns: [
        {
          no: 1,
          regDt: "2022.11.18",
          //   writer: "writer",
          category: "공지..",
          title: "제목...",
        },
      ],
    };
  },
};
</script>

<style></style>
