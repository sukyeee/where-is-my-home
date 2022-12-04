export default {
  // notice category(code) list
  noticeCodeList: [],

  // list
  list: [],
  limit: 10,
  offset: 0,
  searchWord: "",
  code: "", // notice category

  // pagination
  listRowCount: 10,
  pageLinkCount: 10,
  currentPageIndex: 1,

  totalListItemCount: 0,

  // detail
  noticeId : 0,
  title : "",
  content : "",
  userName : "",
  registerDate : "",
  registerTime : "",
  readCount: 0,
  category: {code:"", name:""},
  fileList : [],

};
