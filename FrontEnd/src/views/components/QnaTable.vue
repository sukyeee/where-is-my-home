<template>
  <div class="container">

      <div class="row mb-3">
        <!-- tags -->
        <div class="col-lg-6 col-sm-4 d-flex align-items-center">
         
        </div>
        <!-- Search bar -->
        <div class="col-lg-6 col-sm-8 d-flex">
          <base-input class="w-80 mr-2 search-bar" placeholder="Search" addon-left-icon="ni ni-zoom-split-in" v-model=$store.state.qnas.qna.searchWord>
          </base-input>
          <base-button class="form-group w-20" @click="selectListSearchWord">검색</base-button>
          <!-- 추가 버튼 클릭 시 qnaInsert페이지로 이동 -->
          <base-button :type="buttonColor" class="form-group w-20">
                <router-link to="/qnaInsert"   :class='"text-"+buttonTextColor'>추가</router-link>
          </base-button>
            </div>
          </div>


      <table class="table table-hover bg-white p-3">
        <!-- table header-->
        <thead>
          <th scope="col" class="text-center">제목</th>
          <th scope="col" class="text-center">문의 내용</th>
          <th scope="col" class="text-center">작성일</th>
        </thead>
        <!-- table body -->
        <tbody>
          <tr v-for="(qna, index) in listGetters" :key="index"  @click="qnaDetail(qna.qnaId)">
            <td class="col-2 text-center">{{ qna.title }}</td>
            <td class="col-8">{{ qna.content }}</td>
            <!-- 날짜 나중에 수정 -->
            <td class="col-2 text-center">{{ qna.registerDate.date.year }}</td>
          </tr>
        </tbody>
      </table>
    

      <div class="apt-list-pagination mt-3">
				<base-pagination
					:page-count="getterQnaListTotalCount"
					v-model="currentPageIndex"
					size="sm"
					align="center"
				>
				</base-pagination>
			</div>
  </div>
</template>

<script>
import http from "@/common/axios.js";

export default {
 
  data() {
    return {

      currentPageIndex: 1,

    }
  },
  props: {
    tagList: {
      type: Array,
      description: "게시글의 카테고리 유형 리스트",
      default: function () {
        return [
          { tagName: "공지", tagValue: "공지" },
          { tagName: "업데이트", tagValue: "업데이트" },
        ];
      },
    },
    boardBodyList: {
      type: Array,
      description: "게시글 목록 리스트",
      default: function () {
        return [
          {
            category: "공지",
            title: "우리집 찾아조! 로그인이 안됩니다",
            registerDate: "2022.11.15",
          },
     
        ];
      },
    },
  },
  created() {
    // 페이지 재방문시 searchWord 초기화 
    this.init();
    this.qnaList();
    this.$store.commit("SET_QNA_MOVE_PAGE", this.currentPageIndex);
    this.qnaList();
  },
	watch: {
			currentPageIndex() {
				this.$store.commit("SET_QNA_MOVE_PAGE", this.currentPageIndex);
        this.qnaList();
			}
		},
  computed: {
    textColor(){ return this.$store.state.headerDarkMode ? 'yellow' : 'default'},
    buttonColor(){ return this.$store.state.headerDarkMode ? 'yellow' : 'default'},
    buttonTextColor(){ return  this.$store.state.headerDarkMode ? 'default' : 'secondary'},
    logoImage(){ return  this.$store.state.headerDarkMode ? 'img/brand/logo2.png' : 'img/brand/logo1.png'},

    listGetters() {
      return this.$store.getters.getQnaList;
    },
    getterQnaListTotalCount(){
      return this.$store.getters.getQnaListTotalCount;
    }

  },
  methods: {
    init(){
      // searchWord 초기화 
      this.$store.state.qnas.qna.searchWord='';
    },
    qnaList() {
      this.$store.dispatch("qnaList");
    },

    selectListSearchWord(){
      this.$store.dispatch("qnaList");
    },

    // Q&A 상세조회
    async qnaDetail(qnaId){
        try {
        console.log('qnaId는????'+ qnaId)
        let { data } = await http.get("/qnas/" + qnaId); // params: params shorthand property, let response 도 제거
        console.log("qnaDetail data : ");
        console.log(data);
        if (data.result == "login") {
            this.$router.push("/login");
        } else {
            let { dto } = data;
            this.$store.commit("SET_QNA_DETAIL", dto);
            this.$router.push("/qnaDetail");
        }
      } catch (error) {
        console.error(error);
      }

    },
    
    

  }
};
</script>

<style>
.table {
  width: 80%;
  background: white;
}
</style>