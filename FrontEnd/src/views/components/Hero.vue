<template>
  <section class="section-hero section-shaped my-0">
    <div class="shape shape-style-1 shape-dark">
      <!-- <span class="span-150"></span>
      <span class="span-50"></span>
      <span class="span-50"></span>
      <span class="span-75"></span>
      <span class="span-100"></span>
      <span class="span-75"></span>
      <span class="span-50"></span>
      <span class="span-100"></span>
      <span class="span-50"></span>
      <span class="span-100"></span> -->
    </div>
    <div class="container shape-container d-flex align-items-center">
      <div class="col px-0">
        <div class="row justify-content-center align-items-center">
          <div class="col-lg-12 text-left pt-lg w-100">
            <p
              class="display-4 text-white mt-4 font-italic font-weight-700"
              style="text-shadow: 2px 1px 2px rgba(0, 0, 0, 0.2)"
            >
              나의 집은 도대체 어디에? 당신의 집을 찾아보세요!
            </p>
            <div
              class="display-1 text-white mb-5 font-italic font-weight-800"
              style="text-shadow: 2px 1px 2px rgba(0, 0, 0, 0.2)"
            >
              SSAFY - Where Is My House
            </div>

            <div class="btn-wrapper">
              <base-button
                tag="a"
                href="/apt"
                class="mb-3 mb-sm-0"
                type="default"
                icon="ni ni-pin-3"
              >
                <router-link to="/house" class="text-white">내 집 찾으러 가기</router-link>
              </base-button>

              <base-button
                tag="a"
                href="/register"
                class="mb-3 mb-sm-0"
                type="white"
                icon="ni ni-user-run"
              >
                <router-link to="/register" class="text-default">회원가입하기</router-link>
              </base-button>
            </div>
          </div>
        </div>

        <div class="row align-items-center justify-content-around stars-and-coded">
          <!-- <div class="col-sm-4">
            <span class="text-white alpha-7 ml-3">Star us on</span>
            <a
              href="https://github.com/creativetimofficial/argon-design-system"
              target="_blank"
              title="Support us on Github">
              <img src="img/brand/github-white-slim.png" style="height: 22px; margin-top: -3px" />
            </a>
          </div>
          <div class="col-sm-4 mt-4 mt-sm-0 text-right">
            <span class="text-white alpha-7">Coded by</span>
            <a
              href="https://www.creative-tim.com"
              target="_blank"
              title="Creative Tim - Premium Bootstrap Themes and Templates">
              <img src="img/brand/creativetim-white-slim.png" class="ml-3" style="height: 30px" />
            </a>
          </div> -->
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import BaseButton from "../../components/BaseButton.vue";
export default {
  components: {
    BaseButton,
  },
};
</script>

<style scoped>
.section-hero::before {
  content: "";
  background-image: url("../../../public/img/bg3.jpg");
  background-repeat: no-repeat;
  background-size: cover;
  background-position: center;
  opacity: 0.7;
  position: absolute;
  top: 0px;
  left: 0px;
  right: 0px;
  bottom: 0px;
}
</style>
