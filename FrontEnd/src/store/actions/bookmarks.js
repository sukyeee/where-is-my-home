import http from "@/common/axios.js";
import router from "@/routers/router";

export default {
  /* Bookmark View */
  async bookmarkList(context) {
    let params = {
      limit: this.state.bookmarks.bookmark.limit,
      offset: this.state.bookmarks.bookmark.offset,
    };

    try {
      let { data } = await http.get("/bookmarks", { params }); // params: params shorthand property, let response 도 제거
      console.log("bookmarkList data : ");
      console.log(data);
      if (data.result == "login") {
        router.push("/login");
      } else {
        context.commit("SET_BOOKMARK_LIST", data.list);
        context.commit("SET_BOOKMARK_TOTAL_LIST_ITEM_COUNT", data.count);
        context.commit("SET_BOOKMARK_DONG_LIST", data.dongList);
      }
    } catch (error) {
      console.error(error);
    }
  },
  async bookmarkFilterList(context) {
    let params = {
      limit: this.state.bookmarks.bookmark.limit,
      offset: this.state.bookmarks.bookmark.offset,
      dong: this.state.bookmarks.bookmark.dongFilter,
    };

    console.log("여까지오냐~?");
    console.log(params);

    try {
      let { data } = await http.get("/bookmarks", { params }); // params: params shorthand property, let response 도 제거
      console.log("bookmarkFilterList data : ");
      console.log(data);
      if (data.result == "login") {
        router.push("/login");
      } else {
        context.commit("SET_BOOKMARK_LIST", data.list);
        context.commit("SET_BOOKMARK_TOTAL_LIST_ITEM_COUNT", data.count);
      }
    } catch (error) {
      console.error(error);
    }
  },

  /* Bookmark spec compare list */
  async getHouseSpecList(context) {
    let houseSpecList = ["111101501000001", "111101501000006", "111101501000010", "111101501000015", "111101501000020"];

    //  let houseSpecList = this.state.bookmarks.bookmark.houseSpecList;
    let params = houseSpecList.map((el) => `houseDealId=${el}`).join("&");
    console.log(params);
    try {
      let { data } = await http.get("/houses/spec?" + params);
      // console.log("house detail list: ", data.houseList);
      if (data.result == "login") {
        router.push("/login");
      } else {
        context.commit("SET_HOUSE_SPEC_LIST", data.houseList);
      }
    } catch (error) {
      console.error(error);
    }
  },
};
