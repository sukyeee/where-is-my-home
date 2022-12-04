<template>
    <div>
        <!-- 답글 리스트 토글 -->
     
        <small v-if="childCommentNumber > 0" class="reply-count" style="cursor:pointer" @click="openReplyCommentsClick" >▼ 답글 {{childCommentNumber}} 개</small>
   
        <div v-if="openReplyComments">
            <div v-for="(comment, index) in commentLists" :key="index" >
                <div v-if="comment.parentCommentId == parentCommentId" class="ml-4"> 
                    <single-comments :comment="comment" :qnaId="comment.qnaId" ></single-comments> 
                    <reply-comments :commentLists="commentLists" :parentCommentId="comment.commentId" :qnaId="comment.qnaId" :childCommentNumber="childCommentNumber"></reply-comments>
                </div>

            </div>
        </div>
   
        
    </div>
</template>


<script>

import SingleComments from "@/views/components/SingleComments.vue"

export default {
    name: 'reply-comments',
    components: {
        SingleComments
    },

    props: [
        'commentLists',
        'parentCommentId',
        
    ],
    data(){
        return{
            childCommentNumber: 0,
            openReplyComments: false,
        }
    },

    created() {

        console.log(this.commentLists)

        let commentNumber = 0;
        this.commentLists.map((comment) => {
            if (comment.parentCommentId == this.parentCommentId) {
                commentNumber++
            }
        })
        this.childCommentNumber = commentNumber;
    },
    methods: {
        // 답글 리스트 버튼 클릭 시
        openReplyCommentsClick(){
            this.openReplyComments = !this.openReplyComments;
        },
    },



}
</script>

<style scoped>
</style>