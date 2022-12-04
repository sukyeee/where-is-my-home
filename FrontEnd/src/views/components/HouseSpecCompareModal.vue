<template>
  <div id="houseSpec" class="mb-3" tabindex="-1">
    <!-- <base-button block type="primary" class="mb-3" @click="modals.modal1 = true">
      Default
    </base-button> -->
    <modal id="houseSpecModal" :show="modalShow" @close="closeModal">
      <h6 slot="header" class="modal-title" id="modal-title-default"></h6>
      <div class="table-content">
        <table class="table table-hover">
          <thead class="thead-light">
            <tr>
              <th class="col-1">#</th>
              <th class="col-2" v-for="(house, index) in houseSpecList" :key="'th' + index">
                {{ house.apartmentName }}
              </th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <th>별점</th>
              <th v-for="(house, index) in houseSpecList" :key="'d' + index">
                {{ Math.floor(house.score * 10) / 10 }}점
                <i class="fa fa-star text-yellow" aria-hidden="true" v-for="index in Math.ceil(house.score)" :key="index"></i>
                <i class="fa fa-star-o text-yellow" aria-hidden="true" v-for="index in 5 - Math.ceil(house.score)" :key="`o-${index}`"></i>
              </th>
            </tr>
            <tr>
              <th>가격</th>
              <th v-for="(house, index) in houseSpecList" :key="'d' + index">
                {{ Number(house.dealAmount).toLocaleString() + "만원" }}
              </th>
            </tr>
            <tr>
              <th>주소</th>
              <th v-for="(house, index) in houseSpecList" :key="'a' + index">
                {{ house.dong + " " + house.roadName }}
              </th>
            </tr>
            <tr>
              <th>층 수</th>
              <th v-for="(house, index) in houseSpecList" :key="'f' + index">
                {{ house.floor + "층" }}
              </th>
            </tr>
            <tr>
              <th>면적</th>
              <th v-for="(house, index) in houseSpecList" :key="'d' + index">{{ house.area }}m<sup>2</sup></th>
            </tr>
            <tr>
              <th>건축 연도</th>
              <th v-for="(house, index) in houseSpecList" :key="'y' + index">
                {{ house.buildYear + "년" }}
              </th>
            </tr>
            <tr>
              <th></th>
              <th v-for="(house, index) in houseSpecList" :key="'y' + index">
                <base-button @click="clickMapMove(index)" type="secondary">지도 위치 이동하기</base-button>
              </th>
            </tr>
          </tbody>
        </table>
      </div>
      <div class="kakao-map-wrapper">
        <MiniKakaoMap class="modal-spec-kakao-map"></MiniKakaoMap>
      </div>
      <!-- <template slot="footer" class="p-0">
        <base-button type="link" class="ml-auto" @click="closeModal">Close </base-button>
      </template> -->
    </modal>
  </div>
</template>

<script>
  import Modal from "@/components/Modal.vue";
  import MiniKakaoMap from "@/views/components/MiniKakaoMap.vue";

  export default {
    components: { Modal, MiniKakaoMap },
    props: {
      modalShow: {
        type: Boolean,
      },
    },
    computed: {
      houseSpecList() {
        return this.$store.getters.getHouseSpecList;
      },
    },
    methods: {
      closeModal() {
        this.$emit("close-modal");
      },
      clickMapMove(index) {
        let lat = this.houseSpecList[index].lat;
        let lng = this.houseSpecList[index].lng;
        // console.log([lat, lng])
        this.$store.commit("SET_HOUSE_SPEC_KAKAO_MAP_BOUNDS", { lat, lng });
      },
    },
  };
</script>

<style scoped>
  #houseSpec >>> .modal-dialog {
    min-width: 800px;
    max-width: 90% !important;
  }

  #houseSpec >>> .modal-header {
    padding-top: 0.5rem;
    padding-bottom: 0.5rem;
    /* width: 100%; */

    background-color: #e9ecef;
  }
  #houseSpec >>> .modal-footer {
    padding-top: 0.5rem;
    padding-bottom: 0.5rem;
    /* width: 100%; */
  }

  #houseSpec >>> .modal-body {
    width: 100%;
    max-height: 90vh;

    padding: 0;
    overflow: auto;
  }

  /* #houseSpec >>> .kakao-map-wrapper {
  height: 400px !important;
} */
  #houseSpec >>> .modal-spec-kakao-map #map {
    width: 100% !important;
    height: 400px !important;
  }
</style>
