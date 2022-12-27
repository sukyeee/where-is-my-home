<template>
  <div>
    <HouseList v-show="isOpen"></HouseList>

    <div :class="{ isOpen: isOpen }" class="apt-list-toggle" @click="toggleAptList">
      <base-button
        size="sm"
        type="white"
        v-b-popover.hover.top
        title="매물 리스트 닫기"
        v-if="isOpen"
      >
        <i class="ni ni-bold-left pt-1"></i>
      </base-button>

      <base-button size="sm" type="default" v-b-popover.hover.top title="매물 리스트 열기" v-else>
        <i class="ni ni-bold-right pt-1"></i>
      </base-button>
    </div>

    <div :class="{ isOpen: isOpen }" class="place-menu">
      <div class="place-menu-list d-flex">
        <base-button
          class="d-flex align-items-center button-default"
          :type="[getterPlaceSeleted['AG2'] ? 'danger' : 'white']"
          size="sm"
          @click="selectPlace('AG2')"
        >
          <i class="ni ni-building mr-1"></i>
          중개업소
        </base-button>
        <base-button
          class="d-flex align-items-center"
          :type="[getterPlaceSeleted['CS2'] ? 'danger' : 'white']"
          size="sm"
          @click="selectPlace('CS2')"
        >
          <i class="ni ni-shop mr-1"></i>
          편의점
        </base-button>
        <base-button
          class="d-flex align-items-center"
          :type="[getterPlaceSeleted['MT1'] ? 'danger' : 'white']"
          size="sm"
          @click="selectPlace('MT1')"
        >
          <i class="fa fa-shopping-cart mr-1"></i>
          대형마트
        </base-button>
        <base-button
          class="d-flex align-items-center"
          :type="[getterPlaceSeleted['BK9'] ? 'danger' : 'white']"
          size="sm"
          @click="selectPlace('BK9')"
        >
          <i class="fa fa-university mr-1"></i>
          은행
        </base-button>
        <base-button
          class="d-flex align-items-center"
          :type="[getterPlaceSeleted['HP8'] ? 'danger' : 'white']"
          size="sm"
          @click="selectPlace('HP8')"
        >
          <i class="fa fa-hospital-o mr-1"></i>
          병원
        </base-button>
        <base-button
          class="d-flex align-items-center"
          :type="[getterPlaceSeleted['PM9'] ? 'danger' : 'white']"
          size="sm"
          @click="selectPlace('PM9')"
        >
          <i class="fa fa-medkit mr-1"></i>
          약국
        </base-button>
        <base-button
          class="d-flex align-items-center"
          :type="[getterPlaceSeleted['SC4'] ? 'danger' : 'white']"
          size="sm"
          @click="selectPlace('SC4')"
        >
          <i class="ni ni-hat-3 mr-1"></i>
          학교
        </base-button>
        <base-button
          class="d-flex align-items-center"
          :type="[getterPlaceSeleted['PS3'] ? 'danger' : 'white']"
          size="sm"
          @click="selectPlace('PS3')"
        >
          <i class="fa fa-bell mr-1"></i>
          유치원
        </base-button>
        <base-button
          class="d-flex align-items-center"
          :type="[getterPlaceSeleted['AC5'] ? 'danger' : 'white']"
          size="sm"
          @click="selectPlace('AC5')"
        >
          <i class="ni ni-palette mr-1"></i>
          학원
        </base-button>
        <base-button
          class="d-flex align-items-center"
          :type="[getterPlaceSeleted['FD6'] ? 'danger' : 'white']"
          size="sm"
          @click="selectPlace('FD6')"
        >
          <i class="fa fa-cutlery mr-1"></i>
          음식점
        </base-button>
        <base-button
          class="d-flex align-items-center"
          :type="[getterPlaceSeleted['PK6'] ? 'danger' : 'white']"
          size="sm"
          @click="selectPlace('PK6')"
        >
          <i class="fa fa-product-hunt mr-1"></i>
          주차장
        </base-button>
      </div>
    </div>

    <div :class="{ isOpen: isOpen }" class="btnCurrentLoc" @click="moveToCurrentLoc">
      <base-button type="white">현재 위치로 이동하기</base-button>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import BaseButton from "@/components/BaseButton.vue";
import BasePagination from "@/components/BasePagination.vue";
import { VBTooltip } from "bootstrap-vue/esm/directives/tooltip/tooltip";
import { VBPopover } from "bootstrap-vue/esm/directives/popover/popover";

import HouseDetail from "@/views/components/HouseDetail.vue";
import HouseList from "@/views/components/HouseList.vue";

export default {
  components: {
    BasePagination,
    BaseButton,
    HouseDetail,
    HouseList,
  },

  directives: {
    BTooltip: VBTooltip,
    BPopover: VBPopover,
  },

  computed: {
    ...mapGetters({
      isOpen: "getIsListOpen",
      getterPlaceSeleted: "getPlaceSelected",
      kakaoMap: "getKakaoMap",
      lat: "getCurLat",
      lng: "getCurLng",
    }),
  },

  methods: {
    /* 아파트 리스트 옆으로 토글하기 */
    toggleAptList() {
      this.$store.commit("SET_IS_LIST_OPEN");
    },

    /* 주변 플레이스 마커 표시하기 */
    selectPlace(type) {
      this.$store.commit("SET_PLACE_SELECTED", type);
      this.$store.commit("SET_PLACE_LIST");
    },

    moveToCurrentLoc() {
      var moveLatLon = new kakao.maps.LatLng(this.lat, this.lng);
      this.kakaoMap.panTo(moveLatLon);
      this.kakaoMap.setLevel(2);
    },
  },
};
</script>

<style scoped>
.apt-list-toggle {
  position: absolute;
  width: 100px;

  top: 50%;
  left: 10px;
  /* // left: 440px; */
  z-index: 10;
  transition: all 0.3s ease;
}

.place-menu {
  position: absolute;
  top: 75px;
  left: 10px;
  z-index: 9;
  transition: all 0.3s ease;
}

.btnCurrentLoc {
  position: absolute;
  width: 200px;

  bottom: 7px;
  left: 10px;
  z-index: 9;
  transition: all 0.3s ease;
}

.isOpen {
  left: 450px;
  /* // top: 10%; */
}
</style>
