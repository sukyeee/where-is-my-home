<template> 
    <div class="qnaDetail">
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
          <qna-detail></qna-detail>
        </div>
      </div>
    </div>
  </template>


<script>

import QnaDetail from "@/views/components/QnaDetailTable.vue";

export default {
  components: {
    QnaDetail
  }
};
</script>

<style>

</style>
