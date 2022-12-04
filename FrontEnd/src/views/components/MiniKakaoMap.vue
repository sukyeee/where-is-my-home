<template>
  <div class="apt-map-section">
    <LoadingSpinner></LoadingSpinner>

    <div class="apt-map">
      <div id="map"></div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import LoadingSpinner from "./LoadingSpinner.vue";

export default {
  components: {
    LoadingSpinner,
  },

  props: [],

  data() {
    return {
      // map
      lat: null,
      lon: null,
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
    };
  },

  computed: {
    ...mapGetters({
      getterHouseList: "getHouseSpecList",
      getterPlaceList: "getPlaceList",
      getterMapBounds: "getHouseSpecKakaoMapBounds",
    }),
  },

  methods: {
    /* 현재 위치 받아오기 */
    async getLocs() {
      return new Promise((resolve, reject) => {
        // console.log("hello kako map");
        let nav = navigator.geolocation;

        if (nav) {
          nav.getCurrentPosition((pos) => {
            this.lat = pos.coords.latitude;
            this.lon = pos.coords.longitude;

            // console.log("현재 위치: ", this.lat, this.lon);

            if (this.lat && this.lon) resolve([this.lat, this.lon]);
            else reject("No Location");
          });
        }
      });
    },

    /* 카카오 맵 렌더링 */
    kakaoMap() {
      this.removeMarker(this.placeMarkers);
      // this.$store.commit("INIT_PLACE_SELECTED");

      /* 지도 렌더링 */
      this.container = document.getElementById("map");
      this.options = {
        center: new kakao.maps.LatLng(37.5713864751051, 126.973698680555),
        // center: new window.kakao.maps.LatLng(this.lat, this.lon),
        level: 4,
      };

      this.map = new window.kakao.maps.Map(this.container, this.options);
      // this.$store.commit("SET_KAKAO_MAP", this.map);
      // console.log("멥::::: ", this.map);

      /* 장소 검색 객체를 생성 */
      this.infowindow = new window.kakao.maps.InfoWindow({ zIndex: 1 });
      this.ps = new window.kakao.maps.services.Places(this.map);

      /* 지도 컨트롤 올리기 */
      this.zoomControl = new window.kakao.maps.ZoomControl();
      this.map.addControl(this.zoomControl, window.kakao.maps.ControlPosition.BOTTOMRIGHT);
      this.mapTypeControl = new window.kakao.maps.MapTypeControl();
      this.map.addControl(this.mapTypeControl, window.kakao.maps.ControlPosition.BOTTOMRIGHT);

      /* 현재 위치에 마커 표시하기 */
      // let marker = new window.kakao.maps.Marker({ position: this.map.getCenter() });
      // marker.setMap(this.map);
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
          imageSrc = "https://cdn-icons-png.flaticon.com/512/1673/1673313.png";
          break;
        case "CS2": // 편의점
          imageSrc = "https://cdn-icons-png.flaticon.com/512/2697/2697432.png";
          break;
        case "MT1": // 대형마트
          imageSrc = "https://cdn-icons-png.flaticon.com/512/1170/1170627.png";
          break;
        case "BK9": // 은행
          imageSrc = "https://cdn-icons-png.flaticon.com/512/66/66455.png";
          break;
        case "HP8": // 병원
          imageSrc = "https://cdn-icons-png.flaticon.com/512/799/799566.png";
          break;
        case "PM9": // 약국
          imageSrc = "https://cdn-icons-png.flaticon.com/512/840/840729.png";
          break;
        case "SC4": // 학교
          imageSrc = "https://cdn-icons-png.flaticon.com/512/999/999735.png";
          break;
        case "PS3": // 유치원
          imageSrc = "https://cdn-icons-png.flaticon.com/512/3282/3282457.png";
          break;
        case "AC5": // 학원
          imageSrc = "https://cdn-icons-png.flaticon.com/512/3359/3359814.png";
          break;
        case "FD6": // 음식점
          imageSrc = "https://cdn-icons-png.flaticon.com/512/753/753839.png";
          break;
        case "PK6": // 주차장
          imageSrc = "https://cdn-icons-png.flaticon.com/512/706/706401.png";
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

      // kakao.maps.event.addListener(marker, "mouseover", function () {
      // 	let content = `<div style="">${place.place_name}</div>`;

      // 	$this.infowindow.setContent(content);
      // 	$this.infowindow.open($this.map, marker);
      // });

      // kakao.maps.event.addListener(marker, "mouseout", function () {
      // 	$this.infowindow.close();
      // });

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

      // 지도를 재설정할 범위정보를 가지고 있을 LatLngBounds 객체를 생성합니다
      var bounds = new kakao.maps.LatLngBounds();

      this.getterHouseList.forEach((house) => {
        let lat = house.lat;
        let lng = house.lng;

        // 마커 표시 위치
        var markerPosition = new kakao.maps.LatLng(lat, lng);

        // 지도 범위 재설정할 기준 위치 추가
        bounds.extend(markerPosition);

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
							<span class="font-weight-bold">${house.apartmentName}</span>
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

        kakao.maps.event.addListener(marker, "click", function () {
          console.log("one house click");
        });

        // 지도에 마커 표시
        this.houseMarkers.push(marker);
        marker.setMap(this.map);
      });

      // 지도 범위 재설정 - setBounds 범위 설정 후, 비동기라서 어느 정도 로딩이 필요한 것 같다.
      // setBounds() -> relayout() 0ms -> setBounds() 50ms -> relayout() 100ms 하니까 제대로 됨.
      this.map.setBounds(bounds);
      var $this = this;
      var $data = bounds;
      setTimeout(function () {
        $this.map.relayout();
      }, 0);
      setTimeout(function () {
        $this.map.setBounds($data);
      }, 50);
      setTimeout(function () {
        $this.map.relayout();
      }, 100);
    },

    /* 마커 삭제하는 함수 */
    removeMarker(markers) {
      for (let i = 0; i < markers.length; i++) {
        markers[i].setMap(null);
      }
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
      this.$store.commit("INIT_PLACE_SELECTED");
      await this.getLocs().then(() => this.noSpinner());
      await this.kakaoMap();
      // console.log(">>> mapInit!!!");
      await this.displayHouseMarker();
    },
  },

  watch: {
    getterPlaceList() {
      this.checkPlaces();
    },

    getterHouseList() {
      // console.log(">>> getterHouseList!!! relayout!!!!")
      this.displayHouseMarker();
    },

    // 위치 이동 버튼을 클릭하면 그 아파트를 중심으로 이동
    getterMapBounds() {
      let lat = this.getterMapBounds.lat;
      let lng = this.getterMapBounds.lng;
      this.map.panTo(new kakao.maps.LatLng(lat, lng));
    },
  },

  mounted() {
    // console.log(">>> kakaomap mounted!!!");
    this.mapInit();
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
