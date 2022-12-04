import notices from "./getters/notices";
import events from "./getters/events";
import qnas from "./getters/qnas";
import bookmarks from "./getters/bookmarks";
import houses from "./getters/houses";
import users from "./getters/users";

export default {
  getHeaderDarkMode(state) {
    return state.headerDarkNode;
  },
  getUserInfo(state) {
    return state.userInfo;
  },
  getIsLogin(state) {
    return state.isLogin;
  },
  getIsAdminUser(state) {
    return state.userInfo.code === "001" ? true : false;
  },

  ...users,
  ...houses,
  ...notices,
  ...events,
  ...qnas,
  ...bookmarks,
};
