<template>
     <div class="container w-50">
          <!-- 질문게시판 상세 -->
          <card type="secondary" shadow
                            header-classes="bg-white pb-5"
                  body-classes="px-lg-4 py-lg-5"
                  class="border-1">
            
                <template>
                    <div
                        class="display-3 text-left mb-4 font-weight-800 text-default"
                        style="text-shadow: 2px 1px 2px rgba(0, 0, 0, 0.2)">
                        문의 내역
                    </div>
                    
                    <form role="form">
              
                        <div class="mb-3">
                          <label for="qnaDetailTitle" class="form-label">제목</label>
                          <input type="text" class="form-control" placeholder="Username" aria-label="Username" aria-describedby="basic-addon1" v-model=$store.state.qnas.qna.title>
                        </div>

                        <div class="mb-3">
                          <label for="qnaDetailContent" class="form-label">문의 내용</label>
                          <textarea class="form-control" id="exampleFormControlTextarea1" rows="7"  v-model=$store.state.qnas.qna.content></textarea>
                        </div>


                        <!-- <label for="exampleFormControlTextarea1" class="form-label">제목</label>
                        <base-input alternative
                                    class="mb-3"
                                    v-model=$store.state.qnas.qna.title>
                        </base-input> -->
                        <!-- 문의 내용 
                        <base-input alternative
                                    class="mb-3 h-50"
                                    v-model=$store.state.qnas.qna.content>
                        </base-input> -->


                        <div class="text-center" v-show="$store.state.qnas.qna.userId == $store.state.userInfo.userId">
                          <!-- 글 쓴 사용자와 현재 로그인한 사용자가 같으면 버튼 보이게 하기  -->
                            <base-button  type="primary" class="my-4" @click="qnaUpdate" >수정</base-button>
                            <base-button  type="primary" class="my-4" @click="qnaDelete ">삭제</base-button>
                        </div>

                    </form>
                </template>
            </card>

            <div class="mt-3">
              <comment></comment>
            </div>
    </div>
</template>

<script>
import http from "@/common/axios.js";
import Comment from "@/views/components/Comment.vue"
import alertify from 'alertifyjs';

export default ({
    components: {
      Comment
    },

    methods:{

      // Q&A 리스트 불러오기 
      qnaList() {
        this.$store.dispatch("qnaList");
      },

      // Q&A 상세 업데이트
      async qnaUpdate(){

        let formData = new FormData();
        formData.append("qnaId", this.$store.state.qnas.qna.qnaId); 
        formData.append("title", this.$store.state.qnas.qna.title); 
        formData.append("content", this.$store.state.qnas.qna.content); 
        
        try {

          let { data } = await http.put("/qnas/" + this.$store.state.qnas.qna.qnaId, formData); 
          console.log("qnaUpdate data : ");
          console.log(data);

          if (data.result == "login") {
              this.$router.push("/login");
          } else {
              alertify.success("수정이 완료되었습니다.", 1.5);
              this.qnaList();
              this.$router.push("/qna");
          }
        } catch (error) {
          alertify.error("수정에 실패했습니다.", 1.5);
          console.error(error);
        }

    },
       // Q&A 삭제 
       async qnaDelete(){

          try {

            let { data } = await http.delete("/qnas/" + this.$store.state.qnas.qna.qnaId); 
            console.log("qnaDelete data : ");
            console.log(data);

            if (data.result == "login") {
                router.push("/login");
            } else {
                alertify.success("삭제가 완료되었습니다.", 1.5);
                this.qnaList();
                this.$router.push("/qna");
            }
          } catch (error) {
            console.error(error);
          }

        },

     
    }
})
</script>

<style scoped>

.form-control-qna-detail{
  height:300px;
}

input {
  border: 0;
}
textarea {
  resize: none;
  border: 0;
}

</style>