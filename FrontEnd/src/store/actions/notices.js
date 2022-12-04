import http from "@/common/axios.js";
import util from "@/common/util.js";
import router from "@/routers/router";

const NOTICE_GROUP_CODE = "002";
export default {
  async noticeCodeList(context) {
    try {
      let { data } = await http.get("/notices/codes");
      //   console.log("공통코드: ",data);
      if (data.result == "login") {
        console.log("need to login");
      } else {
        let codeList = [];
        data.list.forEach((item) => {
          codeList.push({ name: item.codeName, code: item.code });
        });
        context.commit("SET_NOTICE_CODE_LIST", codeList);
      }
    } catch (error) {
      console.error(error);
    }
  },
  async noticeList(context) {
    let params = {
      limit: this.state.notices.limit,
      offset: this.state.notices.offset,
      searchWord: this.state.notices.searchWord,
      code: this.state.notices.code,
    };
    // console.log(code, searchWord)

    try {
      let { data } = await http.get("/notices", { params }); // params: params shorthand property, let response 도 제거
      if (data.result == "login") {
        router.push("/login");
      } else {
        let list = [];
        data.list.forEach((item) => {
          list.push({
            category: util.getCodeName(NOTICE_GROUP_CODE, item.code),
            noticeId: item.noticeId,
            title: item.title,
            content: item.content,
            registerDate: util.makeDateStr(
              item.registerDate.date.year,
              item.registerDate.date.month,
              item.registerDate.date.day,
              "."
            ),
          });
        });
        context.commit("SET_NOTICE_LIST", list);
        context.commit("SET_NOTICE_TOTAL_LIST_ITEM_COUNT", data.count);
      }
    } catch (error) {
      console.error(error);
    }
  },
  async noticeDetail(context, noticeId) {
    try {
      let { data } = await http.get("/notices/" + noticeId);
      if (data.result == "login") {
        router.push("/login");
      } else {
        let result = {
          category: {
            code: data.dto.code,
            name: util.getCodeName(NOTICE_GROUP_CODE, data.dto.code),
          },
          ...data.dto,
        };
        context.commit("SET_NOTICE_DETAIL", result);
      }
    } catch (error) {
      console.error(error);
    }
  },
  noticeInit(context) {
    context.commit("SET_NOTICE_INIT");
  },
};
