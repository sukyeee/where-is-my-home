<template>
  <div class="common-bg position-relative">
    <div class="background">
      <section class="section-hero section-shaped my-0">
        <div class="shape shape-style-1 shape-dark"></div>
      </section>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.common-bg >>> .section-hero::before {
  content: "";
  background-image: url("../../../public/img/bg3.jpg");
  background-repeat: no-repeat;
  background-size: cover;
  background-position-x: center;
  background-position-y: top;
  opacity: 0.7;
  position: absolute;
  top: 0px;
  left: 0px;
  right: 0px;
  bottom: 0px;
}
.common-bg >>> .background {
  position: absolute;
  top: 0px;
  left: 0px;
  right: 0px;
  bottom: 0px;
  z-index: -1;
}
</style>
