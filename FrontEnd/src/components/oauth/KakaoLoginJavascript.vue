<template>
  <div class="container">
    <button @click="loginWithKakao" class="btn btn-primary">Kakao 로그인 Javascript</button>


    <a @click="kakaoAccessToken()" id="cusom-login-btn" class="cursor-pointer">
      <img src="//k.kakaocdn.net/14/dn/btqCn0WEmI3/nijroPfbpCa4at5EIsjyf0/o.jpg" width="222">
    </a>


  </div>


</template>

<script>
import http from "@/common/axios.js";

// CORS 이슈는 없다. Kakao 에서 response
// Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS
// Access-Control-Allow-Origin: *
export default {
  methods: {
    loginWithKakao() {
      var $this = this;

      // 파라미터로 object 를 전달한다.
      // success 할 때의 콜백함수
      // faile 할 때의 콜백함수 2개를 전달한다.
      console.log(Kakao)
      Kakao.Auth.login({
        success: function (authObj) {
          // access_token 등 데이터가 전달된다.
          // 백엔드에 access_token 을 전달하고 user info 를 받는 방식이라면 authObj 의 access_token 을 이용한다.
          // https://developers.kakao.com/sdk/reference/js/release/Kakao.Auth.html#AuthSuccessCallback
          // 이 코드는 javascript 로 user info 를 받는 방식으로 처리한다.
          console.log("----1");
          console.log(authObj);

          // 인증을 거치고나서 개인 프로파일 정보를 얻어온다. /v2/user/me
          Kakao.API.request({
            url: "/v2/user/me", // 내 개인 신상 정보를 가져오겠다.
            success: async function (response) {
              if (Kakao.Auth.getAccessToken()) {
                console.log("----2");
                console.log(response);
                // Kakao Login 동의항목 부분 check
                // 이메일로 회원 가입 여부 확인 후 회원이 아닌 경우는 메세지와 함께 회원 가입 페이지로 이동
                if (!response.kakao_account.has_email || !response.kakao_account.is_email_valid) {
                  alert("kakao 인증 이메일이 없거나, 유효하지 않습니다.");
                  return;
                } else {
                  // 이미 가입한 회원
                  // async, await 필수!!
                  let result = await $this.isRegisteredUser(response.kakao_account.email);

                  console.log("----3");
                  console.log(result);

                  if (result) {
                    $this.$store.commit("SET_LOGIN", {
                      isLogin: true,
                      userName: response.properties.nickname,
                      userProfileImageUrl: response.properties.thumbnail_image,
                    });
                  } else {
                    // 가입하지 않은 회원
                    alert("kakao 인증되었으나, 회원 가입이 필요합니다.");
                    let params = {
                      userName: response.properties.nickname,
                      userEmail: response.kakao_account.email,
                    };

                    $this.$router.push({ name: "RegisterPage", params }); // params : params
                  }
                }
              }
            },
            fail: function (error) {
              console.log("----4");
              console.log(error);
            },
          });
        },
        fail: function (error) {
          console.err("----------");
          console.error(error);
        },
      });

      function kakaoLogout() {
        if (Kakao.Auth.getAccessToken()) {
          //토큰이 있으면
          Kakao.API.request({
            //로그아웃하고
            url: '/v1/user/unlink',
            success: function (response) {
              //console.log(response)
            },
            fail: function (error) {
              console.log(error)
            },
          })
          //토큰도 삭제
          Kakao.Auth.setAccessToken(undefined)
          //유저정보도 삭제
          const userinfoElem = document.querySelector('#userinfo')
          if (userinfoElem) userinfoElem.value = ''
        }
      }

    },



    async isRegisteredUser(email) {
      console.log(2);
      console.log(email);

      try {
        let { data } = await http.get(`/isRegisteredUser?email=${email}`);
        console.warn(data);
        console.warn(data.result);

        if (data.result == "registered") return true;
        else return false;
      } catch (error) {
        console.log(error);
      }
    },


    kakaoAccessToken() {
      let $this = this;
      window.Kakao.Auth.login({
        success: function (authObj) {
          console.log('authObj', authObj);
          window.Kakao.Auth.setAccessToken(authObj.access_token);
          $this.kakaoLogin();
        },
        fail: function (err) {
          alert(JSON.stringify(err))
        },
      })
    },
    kakaoLogin() {
      window.Kakao.API.request({
        url: '/v2/user/me',
      })
        .then((response) => {
          let userEmail = response.kakao_account.email;
          let userName = response.properties.nickname;
          let userProfileimgUrl = response.properties.thumbnail_image;

          console.log(userEmail)
          let kakaoInfo = {
            email: userEmail,
            name: userName,
            profileImageUrl: userProfileimgUrl
          }
          this.validateServer(kakaoInfo);
        })
        .catch((error) => {
          console.log(error);
        })
    },


  },

  mounted() {
    console.log("...................???")
    // /* global Kakao */
    // if (typeof Kakao != undefined) return;

    console.log('..........................')
    // script 태그를 만들어서 강제로 집어넣는 코드
    const script = document.createElement("script");

    // 키를 전달해서 Kakao 객체를 만든다.
    script.onload = () => Kakao.init(process.env.VUE_APP_KAKAO_JAVASCRIPT_KEY);
    script.src = "https://developers.kakao.com/sdk/js/kakao.js";
    document.head.appendChild(script);
  },
  computed: {},
  watch: {},
};
</script>

<style>

</style>