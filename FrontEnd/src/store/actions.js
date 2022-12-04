import notices from "./actions/notices";
import events from "./actions/events";
import bookmarks from "./actions/bookmarks";
import qnas from "./actions/qnas";
import houses from "./actions/houses";
import users from "./actions/users";

export default {
  ...users,
  ...houses,
  ...notices,
  ...events,
  ...qnas,
  ...bookmarks,
};
