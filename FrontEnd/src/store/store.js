import Vue from "vue";
import Vuex from "vuex";
Vue.use(Vuex);

import getters from "./getters";
import mutations from "./mutations";
import actions from "./actions";
import houses from "./state/houses";
import notices from "./state/notices";
import events from "./state/events";
import qnas from "./state/qnas";
import bookmarks from "./state/bookmarks";

export default new Vuex.Store({
  state: {
    headerDarkMode: false,

    isLogin: false,
    userInfo: {},
    userReview: [],
    userReviewTotalCount: 0,

    houses: houses,
    notices: notices,
    events: events,

    qnas: qnas,
    bookmarks: bookmarks,
  },

  getters,
  mutations,
  actions,
});
