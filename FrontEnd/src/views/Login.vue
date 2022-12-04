<template>
  <section class="section section-shaped section-hero login-section section-lg my-0">
    <div class="shape shape-style-1 bg-gradient-default"></div>
    <div class="container pt-lg-md">
      <div class="row justify-content-center">
        <div class="col-lg-5">
          <card type="secondary" shadow header-classes="bg-white pb-5" body-classes="px-lg-5 py-lg-5" class="border-0">
            <template>
              <div class="display-4 text-left mb-4 font-weight-800 text-default"
                style="text-shadow: 2px 1px 2px rgba(0, 0, 0, 0.2)">
                로그인
              </div>

              <form role="form">
                <base-input alternative class="mb-3" :valid="userEmailValidState" placeholder="Email" addon-left-icon="ni ni-email-83"
                  v-model="userEmail" @change="validateUserEmail">
                </base-input>
                <base-input alternative type="password" :valid="userPasswordValidState" placeholder="Password"
                  addon-left-icon="ni ni-lock-circle-open" v-model="userPassword" @change="validatePassword">
                </base-input>

                <base-checkbox><span>아이디 저장</span></base-checkbox>

                <div class="text-center">
                  <base-button type="default" class="my-4 col-12" @click="login">로그인</base-button>
                </div>
              </form>
            </template>

          </card>
          <div class="row mt-2">
            <div class="col-6">
              <a href="#" class="text-white" @click="modals.modal3 = true">
                <span>비밀번호 찾기</span>
              </a>
            </div>
            <div class="col-6 text-right">
              <router-link to="/register" class="text-white">
                <span>회원가입</span>
              </router-link>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- modal 모달창 -->
    <modal :show.sync="modals.modal3" body-classes="p-0" modal-classes="modal-dialog-centered modal-sm">
      <card type="secondary" shadow header-classes="bg-white pb-5" body-classes="px-lg-5 py-lg-5" class="border-0">
        <template>
          <div class="display-3 text-left mb-4 font-weight-800 text-default"
            style="text-shadow: 2px 1px 2px rgba(0, 0, 0, 0.2)">
            비밀번호 찾기
          </div>

          <form role="form">
            이메일을 입력하세요
            <base-input alternativef class="mb-3 mt-1" placeholder="Email" addon-left-icon="ni ni-email-83"
              v-model="userEmail">
            </base-input>

            <div class="text-center">
              <base-button type="primary" class="my-4" @click="findPassword">비밀번호 찾기</base-button>
            </div>
          </form>
        </template>
      </card>
    </modal>
  </section>
</template>

<script>
import http from "@/common/axios.js";
import Modal from "@/components/Modal.vue";
import alertify from 'alertifyjs';

export default {
  components: {
    Modal,
  },

  data() {
    return {
      userEmail: "sk",
      userPassword: "1234",

      modals: {
        modal1: false,
        modal2: false,
        modal3: false,
      },

      userEmailValidState: false,
      userPasswordValidState: false,

      // 아이디 저장
      saveId: false,
    };
  },
  computed: {

     
  },
  methods: {
    validateUserEmail(){
      if(this.userEmail.length >= 2) this.userEmailValidState = true;
      else this.userEmailValidState = false;
    },
    validatePassword() {
       
       let userPassword = this.userPassword;
       
       var patternEngAtListOne = new RegExp(/[a-zA-Z]+/);// + for at least one
       var patternSpeAtListOne = new RegExp(/[~!@#$%^&*()_+|<>?:{}]+/);// + for at least one
       var patternNumAtListOne = new RegExp(/[0-9]+/);// + for at least one

       if( patternEngAtListOne.test( userPassword ) 
               && patternSpeAtListOne.test( userPassword )  
               && patternNumAtListOne.test( userPassword )
               && userPassword.length >= 4
       ) this.userPasswordValidState = true;
       
       else this.userPasswordValidState = false;
      },
    async login() {
    
      

      try {
        let response = await http.post("/login", {
          userEmail: this.userEmail,
          userPassword: this.userPassword,
        });
        let { data } = response;


        // interceptor session check fail -> session timeout 났을때
        if (data.result == "login") {
          this.$router.push("/login");
        } else {
          // 로그인 성공
          this.$store.commit("SET_USER_LOGIN", { isLogin: true });
          this.$store.commit("SET_USER_INFO", data.userDto);
          this.$router.push("/");
          alertify.success("로그인이 완료되었습니다.", 1.5);
        }
      } catch (error) {
        alertify.error("로그인에 실패했습니다.", 1.5);
      }
    },

    // 비밀번호 찾기
    async findPassword() {
      try {
        let { data } = await http.get("/user/" + this.userEmail);
        console.log(data);

        if (data.result == "login") {
          alertify.error("세션이 만료되었습니다.", 1.5);
          this.$router.push("/login");
        } else {
          alertify.success(`${data.UserDto.userName}고객님의 비밀번호는 ${data.UserDto.userPassword}  입니다`);
        }
      } catch (error) {
        console.log(error);
      }
    },

  },
};
</script>

<style scoped>
.login-section::before {
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
