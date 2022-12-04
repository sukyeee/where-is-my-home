<template>
    <div class="container">
       <button @click="loginWithKakao" class="btn btn-primary">Kakao 로그인 Javascript BackEnd</button>
    </div>
 </template>
 
 
 <script>
 import http from "@/common/axios.js";
 
 export default {
    methods: {
       loginWithKakao() {
          var $this = this;
          // /* global Kakao / 는 한번만 선언해 줘야 한다. 아래 코드는 already defined 오류 발생
          Kakao.Auth.login({
             success: function (authObj) {
                console.log("----1");
                console.log(authObj);
 
                // 인증이 성공하면 개인 프로파일 정보를 얻어온다. 단, 백엔드에서 /v2/user/me 로 요청
                // 백엔드에 access_token 을 전달하고 user info 를 받는다.
                $this.getUserProfile(authObj.access_token);
             },
             fail: function (error) {
                console.error(error);
             },
          });
       },
       async getUserProfile(accessToken) {
          console.log(accessToken);
 
          // 주의! BackEnd 가 아래와 같은 경우, urlencoded 방식으로 처리해야 함. axios 기본은 json
          // @PostMapping(value="/kakaologin")
          // public HashMap<String, String> kakaoLogin(String accessToken) {
 
          // axios 기본 : application/json
          let options = {
             headers: { "Content-Type": "application/x-www-form-urlencoded" },
          };
 
          // urlencoded 방식으로 param 보낼 때는 아래와 같이
          const params = new URLSearchParams();
          params.append("accessToken", accessToken);
 
          try {
             // 전체를 보려면
             // let {response} = await http.post("/kakaologin", params, options);
             // console.log(response);
 
             let { data } = await http.post("/kakaoLogin", params, options);
             console.log(data);
 
             if (data.result == "registered") {
                this.$store.commit("SET_LOGIN", {
                   isLogin: true,
                   userName: data.nickname,
                   userProfileImageUrl: data.thumbnail_image,
                });
             } else {
                // 가입하지 않은 회원
                alert("kakao 인증되었으나, 회원 가입이 필요합니다.");
                let params = {
                   userName: data.nickname,
                   userEmail: data.email,
                };
                this.$router.push({ name: "RegisterPage", params }); // params : params
             }
          } catch (error) {
             console.log(error);
          }
       },
    },
    mounted() {
       // 주의 if( Kakao == undefined ) 는 오류 발생 typeof 필요
       // typeof Kakao 는 string "undefined" 를 return
       // if (typeof Kakao !== undefined) return; 은 문법 오류는 아니나, Kakao 가 is not defuned 발생
       /* global Kakao */
       if (typeof Kakao !== "undefined") return;
 
       const script = document.createElement("script");
 
       script.onload = () => Kakao.init(process.env.VUE_APP_KAKAO_JAVASCRIPT_KEY);
       script.src = "https://developers.kakao.com/sdk/js/kakao.js";
       document.head.appendChild(script);
    },
    computed: {},
    watch: {},
 };
 </script>
 
 
 <style></style>