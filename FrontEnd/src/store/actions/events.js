import http from "@/common/axios.js";
import util from "@/common/util.js";
import router from "@/routers/router";

const EVENT_GROUP_CODE = "003";
function isEndedEvent(code) {
  if (code === EVENT_GROUP_CODE) {
    return true;
  }
  return false;
}
function getRandomDefaultThumbnailImage() {
  let no = util.getRandomInt(1, 4);
  return `/img/theme/thumbnail/event-${no}.png`;
}
export default {
  async eventCodeList(context) {
    try {
      let { data } = await http.get("/events/codes");
      //   console.log("공통코드: ",data);
      if (data.result == "login") {
        console.log("need to login");
      } else {
        let codeList = [];
        data.list.forEach((item) => {
          codeList.push({ name: item.codeName, code: item.code });
        });
        context.commit("SET_EVENT_CODE_LIST", codeList);
      }
    } catch (error) {
      console.error(error);
    }
  },
  async eventList(context) {
    let params = {
      limit: this.state.events.limit,
      offset: this.state.events.offset,
      searchWord: this.state.events.searchWord,
      code: this.state.events.code,
    };
    // console.log(code, searchWord)

    try {
      let { data } = await http.get("/events", { params }); // params: params shorthand property, let response 도 제거
      //    console.log(data)
      if (data.result == "login") {
        router.push("/login");
      } else {
        let list = [];
        data.list.forEach((item) => {
          list.push({
            eventId: item.eventId,
            title: item.title,
            content: item.content,
            startDate: util.makeDateStr(
              item.startDate.date.year,
              item.startDate.date.month,
              item.startDate.date.day,
              "."
            ),
            endDate: util.makeDateStr(
              item.endDate.date.year,
              item.endDate.date.month,
              item.endDate.date.day,
              "."
            ),
            registerDate: util.makeDateStr(
              item.registerDate.date.year,
              item.registerDate.date.month,
              item.registerDate.date.day,
              "."
            ),
            thumbnailFilePath:
              item.thumbnailFile == null
                ? getRandomDefaultThumbnailImage()
                : item.thumbnailFile.fileUrl,
            category: { code: item.code, name: util.getCodeName(EVENT_GROUP_CODE, item.code) },
            isEnded: isEndedEvent(item.code),
            isJoined: item.isJoined,
          });
        });
        //   console.log("afer list: ", list);
        context.commit("SET_EVENT_LIST", list);
        context.commit("SET_EVENT_TOTAL_LIST_ITEM_COUNT", data.count);
      }
    } catch (error) {
      console.error(error);
    }
  },
  async eventDetail(context, eventId) {
    try {
      let { data } = await http.get("/events/" + eventId);
      // console.log(data)
      if (data.result == "login") {
        router.push("/login");
      } else {
        let result = {
          category: {
            code: data.dto.code,
            name: util.getCodeName(EVENT_GROUP_CODE, data.dto.code),
          },
          ...data.dto,
        };
        context.commit("SET_EVENT_DETAIL", result);
      }
    } catch (error) {
      console.error(error);
    }
  },
  eventInit(context) {
    context.commit("SET_EVENT_INIT");
  },
};
