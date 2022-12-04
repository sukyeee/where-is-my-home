<template>
  <div class="banner position-relative">
    <section class="section section-lg mb-5">
      <div class="container board-title">
        <div class="row row-grid justify-content-center">
          <div class="col-lg-8 text-center">
            <h1 class="title text-secondary font-weight-800">{{ title }}</h1>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
export default {
  props: ["title"],
};
</script>

<style lang="scss" scoped>
.title {
  letter-spacing: 5px;
  text-shadow: $default 2px 2px 3px;
  // text-shadow: red 2px 0 20px;
}
.board-title {
  margin-top: 6rem !important;
}
</style>
