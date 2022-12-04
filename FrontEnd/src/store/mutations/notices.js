import util from "@/common/util.js";

export default {    
    SET_NOTICE_CODE_LIST(state, list){
        state.notices.noticeCodeList = list;
    },
    SET_NOTICE_LIST(state, list) {
        state.notices.list = list;
     },
     // pagination
     SET_NOTICE_TOTAL_LIST_ITEM_COUNT(state, count) {
        state.notices.totalListItemCount = count;
     },
     SET_NOTICE_MOVE_PAGE(state, pageIndex) {
        state.notices.offset = (pageIndex - 1) * state.notices.listRowCount;
        state.notices.currentPageIndex = pageIndex;
     },

     // 상세 조회
     SET_NOTICE_DETAIL(state, payload) {
        state.notices.noticeId = payload.noticeId;
        state.notices.title = payload.title;
        state.notices.content = payload.content;
        state.notices.userName = payload.userName;
        state.notices.registerDate = util.makeDateStr(payload.registerDate.date.year, payload.registerDate.date.month, payload.registerDate.date.day, ".");
        state.notices.registerTime = util.makeTimeStr(payload.registerDate.time.hour, payload.registerDate.time.minute, payload.registerDate.time.second, ":");
         state.notices.readCount = payload.readCount;
         state.notices.category = payload.category;
        state.notices.fileList = payload.fileList;
        // state.notices.sameUser = payload.sameUser;
    },
     // 추가 버튼 클릭시, 내용을 초기화
    SET_NOTICE_INIT(state) {
       state.notices.noticeId = "";
       state.notices.title = "";
       state.notices.content = "";
       state.notices.userName = "";
       state.notices.registerDate = "";
       state.notices.registerTime = "";
        state.notices.readCount = 0;
        state.notices.category = {code:"", name:""};
       state.notices.fileList = [];
       // state.notices.sameUser = payload.sameUser;
   },
    SET_NOTICE_CODE(state, payload) {
        state.notices.code = payload.code; 
    },
    SET_NOTICE_SEARCH_WORD(state, payload) {
        state.notices.searchWord = payload.searchWord;
    }
}