export default {
	qna: {
        // list
        list: [],
        limit: 10,
        offset: 0,
        searchWord: "",

        // pagination
        listRowCount: 10,
        pageLinkCount: 10,
        currentPageIndex: 1,

        totalListItemCount: 0,

        // detail, update, delete
        qnaId: 0,
        title: "",
        content: "",
        userName: "",
        regDate: "",
        regTime: "",
        readCount: 0,
        sameUser: false,

        // 해당 게시글 안의 댓글 리스트
        commentList: []
    },

    comment: {
        list: [
            
        ],
        
        limit: 10,
        offset: 0,

        // pagination
        listRowCount: 10,
        pageLinkCount: 10,
        currentPageIndex: 1,

        totalListItemCount: 0,

        // detail, update, delete
        commentId: 0,
        parentCommentId: -1,
        userId: "",
        qnaId: "",
        regDate: "",
        regTime: "",
        content: "",
    },
    
};
