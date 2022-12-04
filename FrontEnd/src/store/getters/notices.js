export default {
    getNoticeId: function (state) {
        return state.notices.noticeId;  
    },
    getNoticeCodeList: function (state) {
        return state.notices.noticeCodeList;
    },
    getNoticeList: function (state) {
       return state.notices.list;
    },
    getNoticeDetail: function (state) {
        return {
            noticeId : state.notices.noticeId,
            title : state.notices.title,
            content : state.notices.content,
            userName :state.notices.userName,
            registerDate :state.notices.registerDate,
            registerTime :state.notices.registerTime,
            readCount: state.notices.readCount,
            category: state.notices.category,
            fileList :state.notices.fileList,
        }  
    },

    // pagination
    getNoticePageCount: function (state) {
       return Math.ceil(state.notices.totalListItemCount / state.notices.listRowCount);
    },
    getNoticePageLinkCount: function (state) {
        return state.notices.pageLinkCount;
    },
    getNoticeTotalListItemCount: function (state) {
        return state.notices.totalListItemCount;
    },
    getNoticeCurrentPageIndex: function (state) {
        return state.notices.currentPageIndex;
    },
    // getNoticeStartPageIndex: function (state) {
    //    if (state.notices.currentPageIndex % state.notices.pageLinkCount == 0) {
    //       //10, 20...맨마지막
    //       return (state.notices.currentPageIndex / state.notices.pageLinkCount - 1) * state.notices.pageLinkCount + 1;
    //    } else {
    //       return Math.floor(state.notices.currentPageIndex / state.notices.pageLinkCount) * state.notices.pageLinkCount + 1;
    //    }
    // },
    // getNoticeEndPageIndex: function (state, getters) {
    //    let ret = 0;
    //    if (state.notices.currentPageIndex % state.notices.pageLinkCount == 0) {
    //       //10, 20...맨마지막
    //       ret = (state.notices.currentPageIndex / state.notices.pageLinkCount - 1) * state.notices.pageLinkCount + state.notices.pageLinkCount;
    //    } else {
    //       ret = Math.floor(state.notices.currentPageIndex / state.notices.pageLinkCount) * state.notices.pageLinkCount + state.notices.pageLinkCount;
    //    }
    //    // 위 오류나는 코드를 아래와 같이 비교해서 처리
    //    return ret > getters.getPageCount ? getters.getPageCount : ret;
    // },
    // getNoticePrev: function (state) {
    //    if (state.notices.currentPageIndex <= state.notices.pageLinkCount) {
    //       return false;
    //    } else {
    //       return true;
    //    }
    // },
    // getNoticeNext: function (state, getters) {
    //    if (Math.floor(getters.getPageCount / state.notices.pageLinkCount) * state.notices.pageLinkCount < state.notices.currentPageIndex) {
    //       return false;
    //    } else {
    //       return true;
    //    }
    // },
}