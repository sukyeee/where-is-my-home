<template>
  <header class="header-global">
    <base-nav class="navbar-main mt-lg-0 mt-sm-3" transparent type="" effect="dark" expand>
      <router-link slot="brand" class="navbar-brand mr-lg-3" to="/">
        <img :src="logoImage" alt="logo" />
      </router-link>

      <div class="row" slot="content-header" slot-scope="{ closeMenu }">
        <div class="col-6 collapse-brand">
          <a href="https://demos.creative-tim.com/vue-argon-design-system/documentation/">
            <img src="img/brand/logo1.png" />
          </a>
        </div>
        <div class="col-6 collapse-close">
          <close-button @click="closeMenu"></close-button>
        </div>
      </div>

      <!-- 왼쪽 -->
      <ul class="navbar-nav navbar-nav-hover align-items-lg-center">
        <li class="nav-item" menu-classes="dropdown-menu-xl">
          <a slot="title" class="nav-link" data-toggle="dropdown" role="button">
            <i class="ni ni-ui-01 d-lg-none"></i>
            <router-link
              to="/"
              :class="'nav-link-inner--text ml-0 font-weight-600 text-' + textColor"
            >
              Home
            </router-link>
          </a>
        </li>

        <li class="nav-item" menu-classes="dropdown-menu-xl">
          <a slot="title" class="nav-link" data-toggle="dropdown" role="button">
            <i class="ni ni-ui-01 d-lg-none"></i>
            <router-link
              to="/notice"
              :class="'nav-link-inner--text ml-0 font-weight-600 text-' + textColor"
            >
              공지사항
            </router-link>
          </a>
        </li>

        <li class="nav-item" menu-classes="dropdown-menu-xl">
          <a slot="title" class="nav-link" data-toggle="dropdown" role="button">
            <i class="ni ni-ui-01 d-lg-none"></i>
            <router-link
              to="/house"
              :class="'nav-link-inner--text ml-0 font-weight-600 text-' + textColor"
            >
              내 집 찾기
            </router-link>
          </a>
        </li>

        <li class="nav-item" menu-classes="dropdown-menu-xl ">
          <a slot="title" class="nav-link" data-toggle="dropdown" role="button">
            <i class="ni ni-ui-01 d-lg-none"></i>
            <router-link
              to="/event"
              :class="'nav-link-inner--text ml-0 font-weight-600 text-' + textColor"
            >
              이벤트
            </router-link>
          </a>
        </li>
        <li class="nav-item" menu-classes="dropdown-menu-xl ">
          <a slot="title" class="nav-link" data-toggle="dropdown" role="button">
            <i class="ni ni-ui-01 d-lg-none"></i>
            <router-link
              to="/qna"
              :class="'nav-link-inner--text ml-0 font-weight-600 text-' + textColor"
            >
              Q&A
            </router-link>
          </a>
        </li>
      </ul>

      <!-- 오른쪽 -->
      <ul v-if="isLogin" class="navbar-nav align-items-lg-center ml-lg-auto">
        <a slot="title" class="nav-link" data-toggle="dropdown" role="button">
          <i class="ni ni-book-bookmark mr-0 d-lg-inline d-sm-none text-default"></i>
          <router-link
            to="/bookmark"
            :class="'nav-link-inner--text font-weight-600 text-' + textColor"
          >
            북마크
          </router-link>
        </a>

        <base-dropdown tag="li" class="nav-item" position="right">
          <a slot="title" class="nav-link" data-toggle="dropdown" role="button">
            <div class="card-profile-image d-lg-block d-none">
              <a href="#">
                <img
                  v-lazy="fileRealPath"
                  class="rounded-circle"
                  style="height: 40px"
                />
              </a>
            </div>

            <div class="nav-link-inner--text d-lg-none font-weight-600">MyProfile</div>
          </a>
          <router-link to="/profile" class="dropdown-item">마이페이지</router-link>
          <router-link to="/admin" class="dropdown-item">이벤트 관리</router-link>
          <router-link to="/settings" class="dropdown-item">Settings</router-link>
          <button class="dropdown-item" @click="logout">Logout</button>
        </base-dropdown>
      </ul>
      <ul v-else class="navbar-nav align-items-lg-center ml-lg-auto">
        <div class="text-center nav-item">
          <router-link to="/login">
            <base-button :type="buttonColor" :class="'my-3 col-12 text-' + buttonTextColor">
              로그인
            </base-button>
          </router-link>
        </div>
        <div class="text-center nav-item">
          <router-link to="/register">
            <base-button :type="buttonColor" :class="'my-3 col-12 test-' + buttonTextColor">
              회원가입
            </base-button>
          </router-link>
        </div>
      </ul>
    </base-nav>
  </header>
</template>

<script>
import http from "@/common/axios.js";
import BaseNav from "@/components/BaseNav";
import BaseDropdown from "@/components/BaseDropdown";
import CloseButton from "@/components/CloseButton";

export default {
  components: {
    BaseNav,
    CloseButton,
    BaseDropdown,
  },

  // props: ["textColor"],

  data() {
    return {
      					// 파일 실제 경로
					fileRealPath: require("../../../BackEnd/src/main/resources/static/img/noProfile.png"),
    };
  },

  computed: {
    /* 다크모드 색 반전 */
    textColor() {
      return this.$store.state.headerDarkMode ? "yellow" : "default";
    },
    buttonColor() {
      return this.$store.state.headerDarkMode ? "yellow" : "default";
    },
    buttonTextColor() {
      return this.$store.state.headerDarkMode ? "default" : "secondary";
    },
    logoImage() {
      return this.$store.state.headerDarkMode ? "img/brand/logo2.png" : "img/brand/logo1.png";
    },

    isLogin() {
      console.log(this.$store.state);
      return this.$store.state.isLogin;
    },
  },
  methods: {
    async logout() {
      try {
        let { data } = await http.get("/logout");
        console.log(data);

        if (data.result == "login") {
          this.$router.push("/login");
        } else {
          this.doLogout();
        }
      } catch (error) {
        console.log("BoardMainVue: error : ");
        console.log(error);
      }
    },
    doLogout() {
      this.$store.state.isLogin = false;
      this.$store.commit("SET_USER_INFO", {});
      this.$router.push("/login");
    },
  },
};
</script>

<style scoped></style>
