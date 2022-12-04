<template>
	<div class="apt-setting-container d-flex w-100 px-2" v-if="toggleSettings">
		<div class="city">
			<small class="sub_title text-default font-weight-700">도시 검색</small>
			<div class="d-flex mt-1">
				<div class="sido">
					<select class="form-select custom-select mr-1" @change="getGugun">
						<option selected value="">시도/광역시</option>
						<option :value="index" v-for="(sido, index) in sidoList" :key="index">
							{{ sido.name }}
						</option>
					</select>
				</div>
				<div class="gugun">
					<select class="form-select custom-select mr-1" @change="getDong">
						<option selected value="">군/구</option>
						<option :value="index" v-for="(gugun, index) in gugunList" :key="index">
							{{ gugun.name }}
						</option>
					</select>
				</div>
				<div class="dong">
					<select class="form-select custom-select mr-1" @change="setDongCode">
						<option selected value="">읍/면/동</option>
						<option :value="index" v-for="(dong, index) in dongList" :key="index">
							{{ dong.name }}
						</option>
					</select>
				</div>
			</div>
		</div>

		<div class="date">
			<small class="sub_title text-default font-weight-700">날짜 검색</small>
			<div class="d-flex mt-1" style="width: 260px;">
				<div>
					<div class="d-flex">
						<div class="d-flex">
							<select
								class="form-select custom-select custom-select-date mr-1 year"
								v-model="dates.minYear"
							>
								<option selected value="">연도</option>
								<option v-for="index in yearList" :key="index" :value="index">
									{{ index }}년
								</option>
							</select>
							<select
								class="form-select custom-select custom-select-date mr-1 month"
								v-model="dates.minMonth"
							>
								<option selected value="">월</option>
								<option v-for="index in 12" :key="index" :value="index"> {{ index }}월 </option>
							</select>
						</div>
						<span>~&nbsp;</span>
						<div class="d-flex">
							<select
								class="form-select custom-select custom-select-date mr-1 year"
								v-model="dates.maxYear"
							>
								<option selected value="">연도</option>
								<option v-for="index in yearList" :key="index" :value="index">
									{{ index }}년
								</option>
							</select>
							<select
								class="form-select custom-select custom-select-date mr-1 month"
								v-model="dates.maxMonth"
							>
								<option selected value="">월</option>
								<option v-for="index in 12" :key="index" :value="index"> {{ index }}월 </option>
							</select>
						</div>
					</div>
				</div>
				<base-button class="btnSearch mb-3" size="sm" type="white" @click="searchDate">
					검색
				</base-button>
			</div>
		</div>

		<div class="search">
			<small class="sub_title text-default font-weight-700">아파트 검색</small>
			<div class="d-flex mt-1">
				<base-input
					type="search"
					addon-left-icon="ni ni-zoom-split-in"
					class="w-100 mr-1 h-25"
					id="searchWord"
					placeholder="Search"
					v-model="houseName"
					:small="true"
				/>
				<base-button class="btnSearch mb-3" size="sm" type="white" @click="searchHouseName">
					검색
				</base-button>
			</div>
		</div>

		<div class="price ml-3 col-1">
			<div class="d-flex flex-column">
				<small class="sub_title text-default font-weight-700 mt-1">가격대 검색</small>
				<div class="d-flex">
					<div>
						<base-button
							class="mt-1"
							size="sm"
							type="white"
							@click="isDealAmountOpen = !isDealAmountOpen"
						>
							<i class="ni ni-bold-down pt-1"></i>
							가격대 설정하기
						</base-button>

						<div
							v-if="isDealAmountOpen"
							class="dealAmount-popover shadow-sm d-flex flex-column align-items-center"
						>
							<CustomSlider></CustomSlider>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="area">
			<div class="d-flex flex-column">
				<small class="sub_title text-default font-weight-700 mt-1">평수 검색</small>
				<div class="d-flex">
					<base-button class="mt-1" size="sm" type="white" @click="searchHouseArea(1)">
						10평
					</base-button>
					<base-button class="mt-1" size="sm" type="white" @click="searchHouseArea(2)">
						20평
					</base-button>
					<base-button class="mt-1" size="sm" type="white" @click="searchHouseArea(3)">
						30평
					</base-button>

					<div>
						<base-button class="mt-1" size="sm" type="white" @click="isAreaOpen = !isAreaOpen">
							<i class="ni ni-bold-down pt-1"></i>
						</base-button>

						<div v-if="isAreaOpen" class="area-popover shadow-sm">
							<base-button size="sm" type="gray" class="mb-1" @click="searchHouseArea(0)">
								&lt; 10평
							</base-button>
							<base-button
								v-for="index in 10"
								:key="index"
								class="mb-1"
								size="sm"
								type="white"
								@click="searchHouseArea(index)"
							>
								{{ index * 10 }}평
							</base-button>
							<base-button size="sm" type="gray" @click="searchHouseArea(11)">
								100평+
							</base-button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import axios from "axios";
	import FlatPicker from "vue-flatpickr-component";
	import "flatpickr/dist/flatpickr.css";
	import { VBTooltip } from "bootstrap-vue/esm/directives/tooltip/tooltip";
	import { VBPopover } from "bootstrap-vue/esm/directives/popover/popover";
	import { mapGetters } from "vuex";

	import BaseCheckbox from "../../components/BaseCheckbox.vue";
	import BaseSlider from "../../components/BaseSlider.vue";
	import CustomSlider from "@/components/CustomSlider.vue";
	import alertify from "alertifyjs";

	export default {
		props: ["toggleSettings"],

		directives: {
			BTooltip: VBTooltip,
			BPopover: VBPopover
		},

		components: {
			BaseCheckbox,
			FlatPicker,
			BaseSlider,
			CustomSlider
		},

		data() {
			return {
				// 도시 검색
				sidoList: [],
				gugunList: [],
				dongList: [],
				selected: {
					sido: null,
					gugun: null,
					dong: null
				},

				// 날짜 검색
				dates: { minYear: 2015, maxYear: 2022, minMonth: 1, maxMonth: 12 },
				yearList: [2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022],

				// 아파트 검색
				houseName: "",

				// 주변 플레이스 검색
				placeCheckedList: [],

				// 금액대별 검색
				isDealAmountOpen: false,
				slider: [150, 400],

				// 평수 검색
				isAreaOpen: false,
				minArea: 9,
				maxArea: 430,
				areaList: [
					[0, 9],
					[10, 19],
					[20, 29],
					[30, 39],
					[40, 49],
					[50, 59],
					[60, 69],
					[70, 79],
					[80, 89],
					[90, 99],
					[100, 199],
					[200, 430]
				]
			};
		},

		computed: {
			...mapGetters({
				dongCode: "getDongCode",
				gugunCodeList: "getGugunCodeList",
				houseListTotalCount: "getHouseListTotalCount"
			})
		},

		methods: {
			async init() {
				// 대한민국 행정명 API 이용하여 시도/구군 데이터 얻어오기
				await this.getSido();
				this.selected.sido = this.sidoList[0].code;

				let sido = this.selected.sido.substr(0, 2) + "*00000";
				let regcodes = await this.sendRequest("gugun", sido);

				// geo map에게 전달할 데이터
				let $this = this;
				this.$store.commit("SET_GUGUN_CODE_LIST", []);

				regcodes.forEach((el) => {
					$this.gugunCodeList.push(el.code);
				});

				this.$store.commit("SET_GUGUN_CODE_LIST", this.gugunCodeList);

				let payload = {
					gugunCodeList: this.gugunCodeList
				};

				$this.$store.dispatch("getGugunCodeList", payload);
			},

			/* 옵션 초기화하기 (비우기) */
			initOption(selid) {
				let options = document.querySelector(`#${selid}`);
				options.length = 0;
			},

			/* 대한민국 법정동 API 사용 */
			async sendRequest(selid, regcode) {
				const url = "https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes";
				let params = "regcode_pattern=" + regcode + "&is_ignore_zero=true";

				let { data } = await axios.get(`${url}?${params}`);
				let regcodes = await data.regcodes;

				return regcodes;
			},

			/* 시도/광역시 데이터 얻기 */
			async getSido() {
				this.sidoList = await this.sendRequest("sido", "*00000000");
			},

			/* 군/구 데이터 얻기 */
			async getGugun(e) {
				this.gugunList = [];
				let index = e.target.value;
				this.selected.sido = this.sidoList[index].code; //e.target.value;

				// 오늘의 뉴스에게 전달할 데이터
				let sidoName = this.sidoList[index].name;
				this.$store.commit("SET_SELECTED_SIDO", sidoName);

				let sido = this.selected.sido.substr(0, 2) + "*00000";
				let regcodes = await this.sendRequest("gugun", sido);
				let gugunList = [];

				// geo map에게 전달할 데이터
				let $this = this;

				this.$store.commit("SET_GUGUN_CODE_LIST", []);

				regcodes.forEach((el) => {
					gugunList.push({
						code: el.code,
						name: el.name.split(" ")[1]
					});
					$this.gugunCodeList.push(el.code);
				});

				this.$store.commit("SET_GUGUN_CODE_LIST", this.gugunCodeList);

				this.gugunList = gugunList;

				let payload = {
					gugunCodeList: this.gugunCodeList
				};

				this.$store.dispatch("getGugunCodeList", payload);
			},

			/* 읍/면/동 데이터 얻기 */
			async getDong(e) {
				this.dongList = [];
				let index = e.target.value;
				this.selected.gugun = this.gugunList[index].code; //e.target.value;

				let gugun = this.selected.gugun.substr(0, 5) + "*";
				let regcodes = await this.sendRequest("dong", gugun);
				let dongList = [];

				regcodes.forEach((el) =>
					dongList.push({
						code: el.code,
						name: el.name.split(" ")[2]
					})
				);

				this.dongList = dongList;
			},

			async setDongCode(e) {
				let index = e.target.value;
				let dongCode = this.dongList[index].code;

				// 오늘의 뉴스에게 전달할 데이터
				let dongName = this.dongList[index].name;
				this.$store.commit("SET_SELECTED_DONG", dongName);

				this.$store.commit("SET_LIST_TYPE", "house");
				this.$store.commit("SET_DONG_CODE", dongCode);
				let res = await this.$store.dispatch("getHouseListLocationAvg");

				await this.getHouseList();

				if (this.houseListTotalCount == 0) {
					alertify.error("매물 리스트가 없습니다.", 1.5);
				}
				this.$store.commit("SET_LIST_OPEN");
			},

			/* 아파트 매물 리스트 가져오기 */
			async getHouseList() {
				await this.$store.dispatch("getHouseList");
			},

			/* 아파트 매물 날짜 검색 */
			searchDate() {
				if (this.dongCode === "") {
					alert("도시를 먼저 설정해주세요");
				} else {
					let datePayload = {
						minYear: this.dates.minYear,
						minMonth: this.dates.minMonth,
						maxYear: this.dates.maxYear,
						maxMonth: this.dates.maxMonth
					};
					this.$store.commit("SET_HOUSE_SEARCH_DATES", datePayload);
					this.$store.dispatch("getHouseList");
				}
			},

			/* 아파트 이름 검색 */
			searchHouseName() {
				if (this.dongCode === "") {
					alert("도시를 먼저 설정해주세요");
				} else {
					this.$store.commit("SET_HOUSE_SEARCH_WORD", this.houseName);
					this.$store.dispatch("getHouseList");
				}
			},

			/* 아파트 평수 검색 */
			searchHouseArea(idx) {
				if (this.dongCode === "") {
					alert("도시를 먼저 설정해주세요");
				} else {
					this.minArea = this.areaList[idx][0];
					this.maxArea = this.areaList[idx][1];

					let areaPayload = {
						minArea: this.minArea,
						maxArea: this.maxArea
					};

					this.$store.commit("SET_HOUSE_AREA", areaPayload);
					this.$store.dispatch("getHouseList");
				}
			}
		},

		created() {
			this.init();
		}
	};
</script>

<style lang="scss">
	.apt-setting-container {
		position: absolute;
		width: 100vw;
		height: 67px;
		z-index: 10;
		background-color: rgba(255, 255, 255, 0.7);
		backdrop-filter: blur(1px);
		justify-content: space-evenly;
	}

	.custom-select {
		width: 110px !important;
		height: 30px !important;
		font-size: small;
	}

	.custom-select-date {
		&.year {
			width: 90px !important;
		}

		&.month {
			width: 65px !important;
		}

		height: 30px !important;
		font-size: small;
	}

	.city {
		width: 330px;
		margin-right: 30px;
	}
	.date {
		width: 370px;
		margin-right: 30px;
	}
	.search {
		width: 250px;
	}
	.price {
		width: 100px;
		margin-right: 30px;
	}

	.dealAmount-popover {
		position: absolute;
		width: 500px;
		height: 110px;
		top: 70px;
		right: 0px;
		background-color: rgba(255, 255, 255, 0.9);
		// backdrop-filter: blur(100px);
		z-index: 10;
		border-radius: 3px;
		padding: 15px 0;
	}

	.area-popover {
		position: absolute;
		width: 300px;
		height: 120px;
		top: 70px;
		right: 55px;
		background-color: rgba(255, 255, 255, 0.9);
		z-index: 10;
		border-radius: 3px;
		padding: 15px;
	}
</style>
