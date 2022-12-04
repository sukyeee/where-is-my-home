export default {
  // event category(code) list
  eventCodeList: [],

  // list
  list: [],
  limit: 12,
  offset: 0,
  searchWord: "",
  code: "", // event category

  // pagination
  listRowCount: 10,
  pageLinkCount: 10,
  currentPageIndex: 1,

  totalListItemCount: 0,

  // detail
  eventId : 0,
  title : "",
  content : "",
  userName: "",
  startDate: "",
  startTime: "",
  endDate: "",
  endTime: "",
  registerDate : "",
  registerTime : "",
  readCount: 0,
  category: {code:"", name:""},
  fileList: [],
  isEnded: false, // 추가하기
  isJoined: false, // 추가하기

  // 이벤트 참여 추가
};
