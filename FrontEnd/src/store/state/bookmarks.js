export default {
  bookmark: {
    // list
    list: [],
    limit: 10,
    offset: 0,
    searchWord: "",

    // pagination
    listRowCount: 10,
    pageLinkCount: 10,
    currentPageIndex: 1,

    totalListItemCount: 0,

    bookmarkId: 0,
    houseDealId: 0,
    houseId: 0,
    dealMount: "",
    dealYear: 0,
    dealMonth: 0,
    dealDay: 0,
    area: "",
    floor: "",
    userName: "",
    build_year: "",
    dong: "",
    roadName: "",
    jibun: "",
    apartmentName: "",

    bookmarkFilterList: [],
    dongFilter: "전체",
    // 행정동 카테고리
    dongList: [],
    // 비교하기 체크된 매물 리스트
    checkedList: [],
    // 북마크에서 내집찾기로 이동 여부
    // moveSearchHouse : false,

    // 아파트 매물 비교 정보
    houseSpecList: [],
    // kakaoMap
    houseSpecKakaoMapBounds: null, // [lat, lng] 배열
  },
};
