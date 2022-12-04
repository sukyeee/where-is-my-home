import http from "@/common/axios.js";

export default {
    /* Q&A View - 질문 리스트 받아오기 */
	async qnaList(context){

        let params = {
            limit: this.state.qnas.qna.limit,
            offset: this.state.qnas.qna.offset,
            searchWord: this.state.qnas.qna.searchWord,
         };

         try {
            let { data } = await http.get("/qnas", { params }); // params: params shorthand property, let response 도 제거
            console.log("qnaList data : ");
            console.log(data);
            if (data.result == "login") {
               router.push("/login");
            } else {
               context.commit("SET_QNA_LIST", data.list);
               context.commit("SET_QNA_TOTAL_LIST_ITEM_COUNT", data.count);
            }
         } catch (error) {
            console.error(error);
         }

    },
	/* Q&A View - 댓글 리스트 받아오기 */
    async commentList(context){

        let params = {
            limit: this.state.qnas.qna.limit,
            offset: this.state.qnas.qna.offset,
            qnaId: this.state.qnas.qna.qnaId
         };

         try {
            let { data } = await http.get("/comments", { params }); // params: params shorthand property, let response 도 제거
            // console.log("commentList data : ");
            // console.log(data);
            if (data.result == "login") {
               router.push("/login");
            } else {
               context.commit("SET_COMMENT_LIST", data.commentList);
               context.commit("SET_COMMENT_TOTAL_LIST_ITEM_COUNT", data.count);
            }
         } catch (error) {
            console.error(error);
         }

    },
	/* Q&A View - 답글 리스트 받아오기 */
    async replyList(context, {commentId, index}){

      let params = {
          limit: this.state.qnas.qna.limit,
          offset: this.state.qnas.qna.offset,
          qnaId: this.state.qnas.qna.qnaId,
          parentCommentId: commentId
       };

       console.log("현재 reply의 commentId"+ commentId)

       try {
              // commentId => 답글 버튼을 누른 댓글의 id
          let { data } = await http.get("/comments/" + commentId, { params }); // params: params shorthand property, let response 도 제거
          console.log("replyList data : ");
          console.log(data);
          if (data.result == "login") {
             router.push("/login");
          } else {
             context.commit("SET_REPLY_LIST", {list:data.replyList, index});
          }
       } catch (error) {
          console.error(error);
       }

      },

}