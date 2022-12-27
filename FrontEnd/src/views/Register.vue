<template>
  <section class="section section-shaped section-lg my-0 signup-section section-hero">
    <div class="shape shape-style-1 bg-gradient-default"></div>
    <div class="container pt-lg-5">
      <div class="row justify-content-center">
        <div class="col-lg-5">
          <card
            type="secondary"
            shadow
            header-classes="bg-white pb-5"
            body-classes="px-lg-5 py-lg-5"
            class="border-0"
          >
            <template>
              <div
                class="display-4 text-left mb-4 font-weight-800 text-default"
                style="text-shadow: 2px 1px 2px rgba(0, 0, 0, 0.2)"
              >
                회원가입
              </div>
              <form role="form">
                <base-input
                  alternative
                  class="mb-3"
                  placeholder="Name"
                  addon-left-icon="ni ni-hat-3"
                  v-model="userName"
                >
                </base-input>
                <base-input
                  alternative
                  class="mb-3"
                  placeholder="Nickname"
                  addon-left-icon="fa fa-address-card-o"
                  v-model="userNickname"
                >
                </base-input>
                <base-input
                  alternative
                  class="mb-3"
                  placeholder="Email"
                  addon-left-icon="ni ni-email-83"
                  v-model="userEmail"
                  :valid="userEmailValidState"
                  @change="validateUserEmail"
                >
                </base-input>
                <base-input
                  alternative
                  type="password"
                  placeholder="Password"
                  addon-left-icon="ni ni-lock-circle-open"
                  v-model="userPassword"
                  :valid="userPasswordValidState"
                  @change="validateUserPassword"
                >
                </base-input>
                <div class="mb-2"></div>
                <!-- <div class="text-muted font-italic mb-3">
                  <small>
                    password strength:
                    <span class="text-success font-weight-700">strong</span>
                  </small>
                </div> -->
                <base-checkbox>
                  <span>
                    <a href="#" class="text-muted">회원가입</a>
                    에 동의합니다.
                  </span>
                </base-checkbox>
                <div class="text-center">
                  <base-button type="default" class="my-4 col-12" @click="register"
                    >가입하기</base-button
                  >
                </div>
              </form>
            </template>
          </card>

          <div class="row mt-1">
            <div class="col-12 text-right">
              <router-link to="/login" class="text-white">
                <span>로그인</span>
              </router-link>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import http from "@/common/axios.js";
import alertify from "alertifyjs";

export default {
  data() {
    return {
      userName: "",
      userNickname: "",
      userEmail: "",
      userPassword: "",

      // 유효성 검사
      userEmailValidState: false,
      userPasswordValidState: false,
    };
  },
  methods: {
    // 유효성 검사
    validateUserEmail() {
      if (this.userEmail.length >= 2) this.userEmailValidState = true;
      else this.userEmailValidState = false;
    },
    validateUserPassword() {
      let userPassword = this.userPassword;

      var patternEngAtListOne = new RegExp(/[a-zA-Z]+/); // + for at least one
      var patternSpeAtListOne = new RegExp(/[~!@#$%^&*()_+|<>?:{}]+/); // + for at least one
      var patternNumAtListOne = new RegExp(/[0-9]+/); // + for at least one

      if (
        patternEngAtListOne.test(userPassword) &&
        patternSpeAtListOne.test(userPassword) &&
        patternNumAtListOne.test(userPassword) &&
        userPassword.length >= 4
      )
        this.userPasswordValidState = true;
      else this.userPasswordValidState = false;
    },

    async register() {
      let options = {
        headers: { "Content-Type": "application/json;charset=utf-8" },
      };

      let formData = new FormData();
      formData.append("userName", this.userName);
      formData.append("userNickname", this.userNickname);
      formData.append("userEmail", this.userEmail);
      formData.append("userPassword", this.userPassword);

      console.log("userName" + this.userName);
      console.log("userNickname" + this.userNickname);
      console.log("userEmail" + this.userEmail);
      console.log("userPassword" + this.userPassword);

      try {
        let response = await http.post("/register", formData, options);
        let { data } = response;

        console.log(data);

        // interceptor session check fail -> session timeout 났을때
        if (data.result == "login") {
          this.$router.push("/login");
        } else {
          // 등록 성공
          alertify.success("회원가입이 완료되었습니다.", 1.5);
          this.$router.push("/login");
        }
      } catch (error) {
        alertify.error("회원가입에 실패했습니다.", 1.5);
      }
    },
  },
};
</script>

<style scoped>
.signup-section::before {
  content: "";
  background-image: url("../../public/img/bg3.jpg");
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
