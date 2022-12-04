import util from "@/common/util.js";

export default {    
    SET_EVENT_CODE_LIST(state, list){
        state.events.eventCodeList = list;
    },
    SET_EVENT_LIST(state, list) {
        state.events.list = list;
     },
     // pagination
     SET_EVENT_TOTAL_LIST_ITEM_COUNT(state, count) {
        state.events.totalListItemCount = count;
     },
     SET_EVENT_MOVE_PAGE(state, pageIndex) {
        state.events.offset = (pageIndex - 1) * state.events.listRowCount;
        state.events.currentPageIndex = pageIndex;
     },

     // 상세 조회
     SET_EVENT_DETAIL(state, payload) {
        state.events.eventId = payload.eventId;
        state.events.title = payload.title;
        state.events.content = payload.content;
        state.events.userName = payload.userName;
        state.events.startDate = util.makeDateStr(payload.startDate.date.year, payload.startDate.date.month, payload.startDate.date.day, ".");
        state.events.startTime = util.makeTimeStr(payload.startDate.time.hour, payload.startDate.time.minute, payload.startDate.time.second, ":");
        state.events.endDate = util.makeDateStr(payload.endDate.date.year, payload.endDate.date.month, payload.endDate.date.day, ".");
        state.events.endTime = util.makeTimeStr(payload.endDate.time.hour, payload.endDate.time.minute, payload.endDate.time.second, ":");
        state.events.registerDate = util.makeDateStr(payload.registerDate.date.year, payload.registerDate.date.month, payload.registerDate.date.day, ".");
        state.events.registerTime = util.makeTimeStr(payload.registerDate.time.hour, payload.registerDate.time.minute, payload.registerDate.time.second, ":");
         state.events.readCount = payload.readCount;
         state.events.category = payload.category;
        state.events.fileList = payload.fileList;
         state.events.isEnded = payload.code === "003" ? true : false;
         state.events.isJoined = payload.isJoined;
        // state.events.sameUser = payload.sameUser;
    },
     // 추가 버튼 클릭시, 내용을 초기화
    SET_EVENT_INIT(state) {
       state.events.eventId = "";
       state.events.title = "";
       state.events.content = "";
        state.events.userName = "";
        state.events.startDate = "";
        state.events.endDate = "";
       state.events.registerDate = "";
       state.events.registerTime = "";
        state.events.readCount = 0;
        state.events.category = {code:"", name:""};
       state.events.fileList = [];
       // state.events.sameUser = payload.sameUser;
   },
    SET_EVENT_CODE(state, payload) {
        state.events.code = payload.code; 
    },
    SET_EVENT_SEARCH_WORD(state, payload) {
        state.events.searchWord = payload.searchWord;
    }
}