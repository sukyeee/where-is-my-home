export default {
    getEventId: function (state) {
        return state.events.eventId;  
    },
    getIsJoined: function (state) {
        return state.events.isJoined; 
    }, 
    getEventCodeList: function (state) {
        return state.events.eventCodeList;
    },
    getEventList: function (state) {
       return state.events.list;
    },
    getEventDetail: function (state) {
        return {
            eventId : state.events.eventId,
            title : state.events.title,
            content : state.events.content,
            userName :state.events.userName,
            startDate :state.events.startDate,
            endDate :state.events.endDate,
            registerDate :state.events.registerDate,
            registerTime :state.events.registerTime,
            readCount: state.events.readCount,
            category: state.events.category,
            fileList :state.events.fileList,
        }  
    },
    // date and time
    getStartDateAndTime(state) {
        return state.events.startDate + " " + state.events.startTime;
    },
    getEndDateAndTime(state) {
        return state.events.endDate + " " + state.events.endTime;        
    },

    // pagination
    getEventPageCount: function (state) {
       return Math.ceil(state.events.totalListItemCount / state.events.listRowCount);
    },
    getEventPageLinkCount: function (state) {
        return state.events.pageLinkCount;
    },
    getEventTotalListItemCount: function (state) {
        return state.events.totalListItemCount;
    },
    getEventCurrentPageIndex: function (state) {
        return state.events.currentPageIndex;
    },
}