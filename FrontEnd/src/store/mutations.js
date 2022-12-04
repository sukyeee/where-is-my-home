import notices from "./mutations/notices";
import events from "./mutations/events";
import qnas from "./mutations/qnas";
import bookmarks from "./mutations/bookmarks";
import houses from "./mutations/houses";
import users from "./mutations/users";

export default {
  /* 헤더 다크모드 - 글씨, 버튼 색 반전 */
  SET_HEADER_LIGHT_MODE(state) {
    state.headerDarkMode = false;
  },
  SET_HEADER_DARK_MODE(state) {
    state.headerDarkMode = true;
  },

  /* 사용자 로그인 */
  SET_USER_LOGIN(state) {
    state.isLogin = true;
  },

  /* 로그인한 사용자 정보 받기 */
  SET_USER_INFO(state, payload) {
    state.userInfo = payload;
  },

  ...users,
  ...houses,
  ...notices,
  ...events,
  ...qnas,
  ...bookmarks,
};
