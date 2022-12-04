<template>
    <div class="container">
       <div class="container-fluid text-sm-center p-5 bg-light">
          <!-- bg-light is background color & p-5 is padding -->
          <h1 class="display-2">삐따기 세상2</h1>
          <p class="lead">가입하세요.</p>
       </div>
       <h2 class="mb-3 mt-3">Register</h2>
       <div class="mb-3">
          <input type="name" class="form-control" placeholder="Enter User Name" v-model="userName" />
       </div>
       <div class="mb-3">
          <input type="email" class="form-control" placeholder="Enter Email" v-model="userEmail" />
       </div>
       <div class="mb-3">
          <input type="password" class="form-control" placeholder="Enter Password" v-model="userPassword" />
       </div>
       <div class="mb-3">
          <input type="password" class="form-control" placeholder="Conform Password" v-model="userPassword2" />
       </div>
       <div>
          <button class="btn btn-primary">가입하기</button>
       </div>
    </div>
 </template>
 
 
 <script>
 import Vue from "vue";
 import VueAlertify from "vue-alertify";
 Vue.use(VueAlertify);
 
 export default {
    // data 사용 X
    data() {
       return {
          // v-model., from router
          userName: this.$route.params.userName,
          userEmail: this.$route.params.userEmail,
          userPassword: "",
          userPassword2: "",
       };
    },
    methods: {},
    async created() {},
 };
 </script>