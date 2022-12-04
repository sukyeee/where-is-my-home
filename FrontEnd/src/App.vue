<template>
  <div id="app">
    <router-view name="header" :userInfo="userInfo"></router-view>
    <main>
      <fade-transition origin="center" mode="out-in" :duration="250">
        <router-view @login-success="loginSuccess" />
      </fade-transition>
    </main>
    <router-view name="footer"></router-view>
  </div>
</template>

<script>
import { FadeTransition } from "vue2-transitions";

export default {
  components: {
    FadeTransition,
  },

  data() {
    return {
      isLogin: false,
      userInfo: {},
    };
  },

  methods: {
    loginSuccess(userInfo) {
      console.log("userInfo:", userInfo);
      this.isLogin = true;
      this.userInfo = userInfo;
    },
  },
};
</script>

<style>
#app {
  cursor: default;
}

.ajs-message .ajs-custom {
  color: #31708f;
  background-color: #d9edf7;
  border-color: #31708f;
}
</style>
