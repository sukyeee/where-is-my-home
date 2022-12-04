<template>
  <div class="apt-map-section">
    <LoadingSpinner></LoadingSpinner>

    <div class="apt-map">
      <div id="map" style="width: 100%; height: calc(100vh - 100px)"></div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import LoadingSpinner from "./LoadingSpinner.vue";

import seoul from "../../../public/img/json/seoul.json";

export default {
  components: {
    LoadingSpinner,
  },

  props: [],

  data() {
    return {
      // map
      lat: null,
      lng: null,
      container: null,
      options: null,
      map: null,
      mapTypeControl: null,
      zoomControl: null,

      // 장소 키워드 검색
      infowindow: null,
      ps: null,
      placeMarkers: [],

      // 매물 리스트
      houseMarkers: [],

      // geo json
      guGeo: [],
      polygonList: [],
      avgList: [],
      latAvg: 0,
      lngAvg: 0,
      customOverlayList: [],
    };
  },

  computed: {
    ...mapGetters({
      getterHouseList: "getHouseList",
      getterPlaceList: "getPlaceList",
      locationAvg: "getHouseListLocationAvg",
      dongCodeList: "getDongCodeList",
      gugunCodeList: "getGugunCodeList",
      dongCountList: "getDongCountList",
    }),
  },

  methods: {
    /* 현재 위치 받아오기 */
    async getLocs() {
      return new Promise((resolve, reject) => {
        let nav = navigator.geolocation;

        if (nav) {
          nav.getCurrentPosition((pos) => {
            this.lat = pos.coords.latitude;
            this.lng = pos.coords.longitude;

            console.log("현재 위치: ", this.lat, this.lng);

            if (this.lat && this.lng) {
              this.$store.commit("SET_CUR_LAT", this.lat);
              this.$store.commit("SET_CUR_LNG", this.lng);
              resolve([this.lat, this.lng]);
            } else reject("No Location");
          });
        }
      });
    },

    /* 카카오 맵 렌더링 */
    initKakaoMap() {
      this.removeMarker(this.placeMarkers);
      // this.$store.commit("INIT_PLACE_SELECTED");

      /* 지도 렌더링 */
      this.container = document.getElementById("map");
      this.options = {
        // center: new kakao.maps.LatLng(37.5713864751051, 126.973698680555),
        // center: new window.kakao.maps.LatLng(this.lat, this.lng),
        center: new kakao.maps.LatLng(this.locationAvg.latAvg, this.locationAvg.lngAvg),
        level: 8,
      };

      this.map = new kakao.maps.Map(this.container, this.options);
      this.$store.commit("SET_KAKAO_MAP", this.map);

      /* 장소 검색 객체를 생성 */
      this.infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
      this.ps = new kakao.maps.services.Places(this.map);

      /* 지도 컨트롤 올리기 */
      this.zoomControl = new kakao.maps.ZoomControl();
      this.map.addControl(this.zoomControl, kakao.maps.ControlPosition.BOTTOMRIGHT);
      this.mapTypeControl = new kakao.maps.MapTypeControl();
      this.map.addControl(this.mapTypeControl, kakao.maps.ControlPosition.BOTTOMRIGHT);

      /* 현재 위치에 마커 표시하기 */
      let marker = new kakao.maps.Marker({
        position: new kakao.maps.LatLng(this.lat, this.lng),
      });
      marker.setMap(this.map);

      /* 지도 표시하기 */
      this.removeGeoJson();
      for (var i = 0, len = this.guGeo.length; i < len; i++) {
        this.displayGeoJson(this.guGeo[i]);
      }

      /* 줌인 줌아웃 이벤트 */
      let $this = this;
      kakao.maps.event.addListener(this.map, "zoom_changed", async () => {
        var level = $this.map.getLevel();

        if (level >= 7) {
          $this.removeGeoJson();

          if ($this.gugunCodeList.length > 0) {
            for (var i = 0, len = $this.guGeo.length; i < len; i++) {
              $this.displayGeoJson($this.guGeo[i]);
            }
          } else {
            alert("도시가 설정되어있지않습니다.");
          }
        } else {
          $this.removeGeoJson();
        }
      });
    },

    /* 주변 플레이스 체크하면 마커 표시하기 */
    checkPlaces() {
      this.removeMarker(this.placeMarkers);
      this.getterPlaceList.forEach((checkbox) => {
        // 플레이스 키워드로 위치 검색하기
        this.ps.categorySearch(checkbox, this.placesSearchCB, { useMapBounds: true });
      });
    },

    /* 플레이스 키워드 검색 완료 시 호출되는 콜백함수 */
    placesSearchCB(data, status, pagination) {
      if (status === window.kakao.maps.services.Status.OK) {
        for (let i = 0; i < data.length; i++) {
          this.displayPlaceMarker(data[i]);
        }
      }
      // } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
      // 	alert("검색 결과가 존재하지 않습니다.");
      // 	return;
      // } else if (status === kakao.maps.services.Status.ERROR) {
      // 	alert("검색 결과 중 오류가 발생했습니다.");
      // 	return;
      // }
    },

    /* 지도에 플레이스 마커를 표시하는 함수 */
    displayPlaceMarker(place) {
      // 마커 이미지
      var imageSrc;
      switch (place.category_group_code) {
        case "AG2": // 중개업소
          imageSrc = "https://cdn-icons-png.flaticon.com/512/942/942089.png";
          break;
        case "CS2": // 편의점
          imageSrc = "https://cdn-icons-png.flaticon.com/512/869/869636.png";
          break;
        case "MT1": // 대형마트
          imageSrc = "https://cdn-icons-png.flaticon.com/512/1261/1261163.png";
          break;
        case "BK9": // 은행
          imageSrc = "https://cdn-icons-png.flaticon.com/512/901/901388.png";
          break;
        case "HP8": // 병원
          imageSrc = "https://cdn-icons-png.flaticon.com/512/3063/3063176.png";
          break;
        case "PM9": // 약국
          imageSrc = "https://cdn-icons-png.flaticon.com/512/684/684284.png";
          break;
        case "SC4": // 학교
          imageSrc = "https://cdn-icons-png.flaticon.com/512/2602/2602414.png";
          break;
        case "PS3": // 유치원
          imageSrc = "https://cdn-icons-png.flaticon.com/512/2383/2383077.png";
          break;
        case "AC5": // 학원
          imageSrc = "https://cdn-icons-png.flaticon.com/512/709/709418.png";
          break;
        case "FD6": // 음식점
          imageSrc = "https://cdn-icons-png.flaticon.com/512/1046/1046755.png";
          break;
        case "PK6": // 주차장
          imageSrc = "https://cdn-icons-png.flaticon.com/512/3005/3005337.png";
          break;
        default:
          break;
      }

      var imageSize = new kakao.maps.Size(28, 28); // 이미지 크기
      var imageOption = { offset: new kakao.maps.Point(10, 20) }; // 이미지 offset 설정 (좌우/상하)

      // 마커의 이미지정보를 가지고 있는 마커이미지를 생성
      var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);

      // 마커를 생성하고 지도에 표시
      let marker = new kakao.maps.Marker({
        map: this.map,
        position: new kakao.maps.LatLng(place.y, place.x),
        image: markerImage,
      });

      // 마커에 moseover 이벤트를 등록
      var $this = this; // window 말고 뷰 자기자신 가르키기
      var customOverlay;

      kakao.maps.event.addListener(marker, "mouseover", function () {
        let content = `
						<div class="place-infowindow">
							<div><span class="font-weight-bold">${place.place_name}</span></div>
							<div><small>주소: ${place.road_address_name}</small></div>
							<div><small>Tel: ${place.phone}</small></div>
						</div>
						`;

        let position = marker.getPosition();

        customOverlay = new kakao.maps.CustomOverlay({
          position: position,
          content: content,
          yAnchor: 1.3,
          xAnchor: 0.1,
        });

        customOverlay.setMap($this.map);
      });

      // kakao.maps.event.addListener(marker, "click", function () {
      //   console.log("click");
      // });

      kakao.maps.event.addListener(marker, "mouseout", function () {
        customOverlay.setMap(null);
      });

      this.placeMarkers.push(marker);
    },

    /* 아파트 매물 리스트 지도에 표시하기 */
    displayHouseMarker() {
      this.removeMarker(this.houseMarkers);

      // 마커 이미지
      var imageSrc = "https://cdn-icons-png.flaticon.com/512/2544/2544087.png"; // 마커이미지의 주소입니다
      var imageSize = new kakao.maps.Size(32, 32); // 이미지 크기
      var imageOption = { offset: new kakao.maps.Point(10, 20) }; // 이미지 offset 설정 (좌우/상하)

      // 마커의 이미지정보를 가지고 있는 마커이미지를 생성
      var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);

      this.getterHouseList.forEach((house) => {
        let lat = house.lat;
        let lng = house.lng;

        // 마커 표시 위치
        var markerPosition = new kakao.maps.LatLng(lat, lng);

        // 마커 생성
        var marker = new kakao.maps.Marker({
          position: markerPosition,
          image: markerImage, // 마커이미지 설정
        });

        // 마커에 moseover 이벤트를 등록
        var $this = this; // window 말고 뷰 자기자신 가르키기
        var customOverlay;

        kakao.maps.event.addListener(marker, "mouseover", function () {
          let content = `
						<div class="house-infowindow">
							<span class="houseDealId font-weight-bold ${house.houseId}" id="${house.houseDealId}">${house.apartmentName}</span>
						</div>
						`;

          let position = marker.getPosition();

          customOverlay = new kakao.maps.CustomOverlay({
            position: position,
            content: content,
            yAnchor: 1.7,
            xAnchor: 0.1,
          });

          customOverlay.setMap($this.map);
        });

        kakao.maps.event.addListener(marker, "mouseout", function () {
          customOverlay.setMap(null);
        });

        kakao.maps.event.addListener(marker, "click", function (e) {
          $this.map.setCenter(marker.getPosition());

          let houseDealId = customOverlay.a.childNodes[1].querySelectorAll(".houseDealId")[0].id;
          let houseId =
            customOverlay.a.childNodes[1].querySelectorAll(".houseDealId")[0].classList[2];

          $this.$store.commit("SET_LIST_OPEN");
          $this.$store.commit("SET_DETAIL_OPEN");
          $this.$store.dispatch("getHouseDetail", houseDealId);
          $this.$store.dispatch("getHouseDealList", houseId);
          $this.$store.dispatch("getHouseReviewList", houseId);
        });

        // 지도에 마커 표시
        this.houseMarkers.push(marker);
        marker.setMap(this.map);
        this.map.setCenter(marker.getPosition());
        this.map.setLevel(3);
      });
    },

    /* 마커 삭제하는 함수 */
    removeMarker(markers) {
      for (let i = 0; i < markers.length; i++) {
        markers[i].setMap(null);
      }
    },

    /* geo json 정보 가져오기 */
    getGeoPath(city) {
      let data = city.features;

      data.forEach((el) => {
        let path = [];

        let latSum = 0;
        let lngSum = 0;

        el.geometry.coordinates[0].forEach((gu) => {
          path.push(new kakao.maps.LatLng(gu[1], gu[0]));

          latSum += gu[1];
          lngSum += gu[0];
        });

        let len = path.length;
        let gu = {
          name: el.properties.name,
          path: path,
          locAvg: new kakao.maps.LatLng(latSum / len, lngSum / len),
        };

        this.guGeo.push(gu);
      });
    },

    /* geo json 데이터 띄우기 */
    displayGeoJson(area) {
      let customOverlay = new kakao.maps.CustomOverlay({});
      let infowindow = new kakao.maps.InfoWindow({ removable: true });

      // 다각형을 생성합니다
      let polygon = new kakao.maps.Polygon({
        map: this.map, // 다각형을 표시할 지도 객체
        path: area.path,
        strokeWeight: 2,
        strokeColor: "#172b4d",
        strokeOpacity: 1,
        fillColor: "#fff",
        fillOpacity: 0.6,
      });

      let houseCount = 0;
      let dongCode = "";
      for (let i = 0; i < this.dongCountList.length; i++) {
        if (this.dongCountList[i].gugunName == area.name) {
          houseCount = this.dongCountList[i].houseCount;
          dongCode = this.dongCountList[i].dongCode;
        }
      }

      let $this = this;

      customOverlay.setContent(
        `<div class="area">
	           <div class="display-4 font-weight-800 text-default">${area.name}</div>
	             <h4 class="">
	             <div class="font-weight-800 badge badge-primary">${houseCount}건</div>
	             </h4>
	           </div>
	         `
      );
      customOverlay.setPosition(area.locAvg);
      customOverlay.setMap(this.map);

      kakao.maps.event.addListener(polygon, "mouseover", function () {
        polygon.setOptions({ fillColor: "#f5365c" });
      });

      kakao.maps.event.addListener(polygon, "mouseout", function () {
        polygon.setOptions({ fillColor: "#fff" });
      });

      kakao.maps.event.addListener(polygon, "click", function () {
        console.log("click polygon", dongCode);
        $this.$store.commit("INIT_HOUSE_LIST_PARAMS");
        $this.$store.commit("SET_DONG_CODE", dongCode);
        $this.$store.commit("SET_LIST_OPEN");
        $this.$store.commit("SET_LIST_TYPE", "houseGugun");
        $this.$store.dispatch("getHouseGugunList");
      });

      this.polygonList.push(polygon);
      this.customOverlayList.push(customOverlay);
    },

    /* polygon 삭제하기 */
    removeGeoJson() {
      this.polygonList.forEach((el) => {
        el.setMap(null);
      });

      this.customOverlayList.forEach((el) => {
        el.setMap(null);
      });
    },

    /* 로딩 스피너 없애기 */
    noSpinner() {
      let spinner = document.querySelectorAll(".spinner-child");
      spinner.forEach((s) => {
        s.style.display = "none";
      });
    },

    /* await async */
    async mapInit() {
      // await this.getGeoPath(seoul);
      this.$store.commit("INIT_PLACE_SELECTED");
      await this.getLocs().then(() => this.noSpinner());
      await this.initKakaoMap();
      await this.displayHouseMarker();
    },
  },

  watch: {
    getterPlaceList() {
      this.checkPlaces();
    },

    getterHouseList() {
      this.removeGeoJson();

      if (this.map != null) {
        this.map.setLevel(6);
      }

      this.displayHouseMarker();
    },

    locationAvg() {
      this.map.setCenter(new kakao.maps.LatLng(this.locationAvg.latAvg, this.locationAvg.lngAvg));
    },
  },

  created() {
    this.$store.commit("INIT_HOUSE_LIST_PARAMS");
    this.$store.commit("SET_DETAIL_CLOSE");
    this.removeMarker(this.houseMarkers);
    this.getGeoPath(seoul);
  },

  async mounted() {
    await this.mapInit();
  },

  destroyed() {
    this.$store.commit("INIT_HOUSE_LIST_PARAMS");
    this.$store.commit("SET_LIST_CLOSE");
    this.$store.commit("SET_DETAIL_CLOSE");
    this.removeMarker(this.houseMarkers);
  },
};
</script>

<style lang="scss">
.house-infowindow {
  display: block;
  background: $default;
  color: #fff;
  text-align: center;
  // height: 24px;
  // line-height: 22px;
  border-radius: 3px;
  padding: 5px 10px;
  word-break: break-word;

  &:after {
    border-top: 10px solid $default;
    border-left: 10px solid transparent;
    border-right: 10px solid transparent;
    border-bottom: 0px solid transparent;
    content: "";
    position: absolute;
    top: 30px;
    left: 0px;
  }
}

.place-infowindow {
  display: block;
  background-color: $default;
  color: white;
  padding: 5px 10px;

  &:after {
    border-top: 10px solid $default;
    border-left: 10px solid transparent;
    border-right: 10px solid transparent;
    border-bottom: 0px solid transparent;
    content: "";
    position: absolute;
    top: 81px;
    left: 0px;
  }
}
</style>
