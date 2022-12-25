<template>
	<div>
		<div class="house-list-section">
			<div class="house-list-content">
				<HouseDetailView v-if="isDetailOpen"></HouseDetailView>
				<div v-else class="h-75">
					<div
						v-if="getterHouseList.length == 0"
						class="d-flex justify-content-center align-items-center noHouseList h-100"
					>
						<div class="text-center display-4 text-default">매물 리스트가 없습니다.</div>
					</div>
					<div v-else v-for="(house, index) in getterHouseList" :key="index" class="mb-2 mr-2">
						<card header-classes="bg-white px-3 pb-0 pt-2" body-classes="px-3 py-3">
							<template v-slot:header>
								<div class="text-default font-weight-800">{{ house.apartmentName }}</div>
							</template>

							<div class="d-flex flex-row">
								<div class="card-profile-image">
									<img
										v-lazy="'img/theme/house.jpg'"
										class="rounded"
										style="width: 135px; height: 135px; object-fit: cover;"
									/>
								</div>

								<div class="w-100 ml-3">
									<div>
										<div>
											<small class="font-weight-bold">가격 </small>
											<span class="font-weight-bold text-danger pt-2">
												{{ Number(house.dealAmount).toLocaleString() }} 만원
											</span>
										</div>
										<div>
											<small class="font-weight-bold">면적 </small>
											<small>{{ house.area }}m<sup>2</sup></small>
										</div>
										<div>
											<small class="font-weight-bold">층수 </small>
											<small>{{ house.floor }}층</small>
										</div>
										<div>
											<small class="font-weight-bold">준공연도 </small>
											<small>{{ house.buildYear }}년</small>
										</div>
									</div>
									<div class="d-flex mt-1 align-items-center">
										<base-button
											type="gray"
											class="btnHouseDetail h-25 text-default"
											size="sm"
											@click="getHouseDetail(house.houseDealId, house.houseId)"
										>
											<i class="ni ni-curved-next mr-1"></i>
											매물 상세 정보
										</base-button>
										<div class="ml-1 btnBookmark"   v-for="(bookmark, index) in getterBookmarkList" :key="index" >
											<!-- <i  class="ni ni-favourite-28 text-danger display-4 pt-1" @click="bookmarkInsert(house.houseDealId)"></i> -->
											<i  v-show="bookmark.houseDealId == house.houseDealId" class="ni ni-favourite-28 text-danger display-4 pt-1 favorite" @click="bookmarkInsert(house.houseDealId)"></i>
											<!-- <i  class="ni ni-favourite-28 text-default display-4 pt-1" @click="bookmarkInsert(house.houseDealId)"></i> -->
										</div>
									</div>
								</div>
							</div>
						</card>
					</div>
				</div>
			</div>

			<base-button
				v-show="isDetailOpen"
				type="white"
				@click="$store.commit('SET_IS_DETAIL_OPEN')"
				class="btnBack"
			>
				<i class="ni ni-bold-left" id="back-icon"></i>
				뒤로 가기
			</base-button>
			<div v-show="!isDetailOpen" class="apt-list-pagination mt-3">
				<base-pagination
					:page-count="getterHouseListTotalCount"
					v-model="currentPageIndex"
					size="sm"
					align="center"
				></base-pagination>
			</div>
		</div>
	</div>
</template>

<script>
	import { mapGetters } from "vuex";
	import BaseButton from "../../components/BaseButton.vue";

	import HouseDetailView from "@/views/HouseDetailView.vue";
	import alertify from 'alertifyjs';
	import http from "@/common/axios.js";

	export default {
		components: {
			BaseButton,
			HouseDetailView
		},

		computed: {
			...mapGetters({
				isOpen: "getIsListOpen",
				isDetailOpen: "getIsDetailOpen",
				getterHouseList: "getHouseList",
				getterHouseListTotalCount: "getHouseListTotalCount",
				listType: "getListType",
				getterBookmarkList: "getBookmarkList"
			})
		},

		data() {
			return {
				currentPageIndex: 1
			};
		},

		methods: {
			/* 아파트 매물 리스트 가져오기 */
			async getHouseList() {
				await this.$store.dispatch("getHouseList");
			},

			/* 아파트 상세 정보 가져오기 */
			async getHouseDetail(houseDealId, houseId) {
				this.$store.commit("SET_IS_DETAIL_OPEN");
				await this.$store.dispatch("getHouseDetail", houseDealId);
				await this.$store.dispatch("getHouseReviewList", houseId);
				// 아파트 거래 동향 그래프 정보 불러오기
				await this.$store.dispatch("getHouseDealList", houseId);
				await this.$store.dispatch("getHouseReviewScore", houseId);

				this.currentPageIndex = 1;
			},

			setDongCode(e) {
				let dongCode = e.target.value;
				this.$store.commit("SET_DONG_CODE", dongCode);
				this.getHouseList();
			},

			async getBookmarkList() {
				await this.$store.dispatch("bookmarkList");
			},


			   // 북마크 추가 ( 내집찾기 화면 )
				 async bookmarkInsert(houseDealId) {

					let formData = new FormData();
					formData.append("houseDealId", houseDealId);

					console.log(houseDealId);

					try {

						let { data } = await http.post('/bookmarks', formData);
						console.log(data);

						if (data.result == 'login') {
							alertify.error('세션이 만료되었습니다.', 1.5);
							this.$router.push("/login");
						} else {
							alertify.success('북마크가 추가되었습니다.', 1.5);
						}

					} catch (error) {
						console.log(error);
					}

					},
		},

		watch: {
			currentPageIndex() {
				this.$store.commit("SET_HOUSE_LIST_MOVE_PAGE", this.currentPageIndex);

				if (this.listType == "houseGugun") {
					this.$store.dispatch("getHouseGugunList");
				} else {
					this.getHouseList();
				}
			}
		},

		created() {
			// dongCode 설정하고 처음에 아파트 리스트 가져오기
			this.$store.commit("SET_HOUSE_LIST_MOVE_PAGE", this.currentPageIndex);
			this.getHouseList();

			// 북마크 리스트 가져오기 
			this.getBookmarkList()
		}
	};
</script>

<style lang="scss" scoped>
	.house-list-section {
		position: absolute;
		top: 67px;
		width: 440px;
		height: calc(100% - 67px);
		/* height: 100px; */
		z-index: 10;
		background-color: rgba(255, 255, 255, 1);
		backdrop-filter: blur(4px);
		transition: all 0.4s ease;
	}

	.house-list-content {
		height: calc(100% - 60px);
		overflow-y: scroll;
	}

	.noHouseList {
		// height: 550px;
	}

	.btnBookmark {
		cursor: pointer;
		opacity: 0.4;

		&:hover {
			opacity: 1;
		}
		.favorite {
			opacity : 1;
		}
	}

	.btnHouseDetail {
		width: 190px;
	}

	.btnBack {
		position: relative;
		top: 10px;
		left: 310px;

		#back-icon {
			position: relative;
			top: 2px;
		}
	}

	.isOpen {
		left: 450px;
		// top: 10%;
	}
</style>
