<template>
	<div class="house-detail-section">
		<div class="p-2">
			<!-- <div class="house-review-title font-weight-bold mb-2"><small>매물 정보</small></div> -->
			<!-- <div><small>매물정보</small></div> -->

			<card header-classes="bg-white px-3 pb-0 pt-2 " body-classes="px-3 py-3">
				<template v-slot:header>
					<div class="btnBookmark text-right">
						<i class="ni ni-favourite-28 text-danger display-3 pt-1" style="cursor:pointer"></i>
					</div>

					<div class="display-3 text-default font-weight-800">
						{{ houseDetail.apartmentName }}
					</div>

					<div class="display-4 p-1">
						{{ houseReviewScoreToFixed }}점
						<i
							class="fa fa-star text-yellow"
							aria-hidden="true"
							v-for="index in houseReviewScoreFloor"
							:key="index"
						></i>
						<i
							class="fa fa-star-o text-yellow"
							aria-hidden="true"
							v-for="index in 5 - houseReviewScoreFloor"
							:key="`o-${index}`"
						></i>
					</div>
				</template>

				<table class="table table-hover house-detail-content mt-2">
					<tbody>
						<tr>
							<th scope="row " class="p-1 w-25 text-center">
								<small class="font-weight-bold">주소</small>
							</th>
							<td class="px-3 p-1 w-75">{{ houseDetail.dong }} {{ houseDetail.roadName }}</td>
						</tr>
						<tr>
							<th scope="row " class="p-1 w-25 text-center">
								<small class="font-weight-bold">면적</small>
							</th>
							<td class="px-3 p-1 w-75">{{ houseDetail.area }} m<sup>2</sup></td>
						</tr>
						<tr>
							<th scope="row " class="p-1 w-25 text-center">
								<small class="font-weight-bold">층수</small>
							</th>
							<td class="px-3 p-1 w-75">{{ houseDetail.floor }} 층</td>
						</tr>
						<tr>
							<th scope="row " class="p-1 w-25 text-center">
								<small class="font-weight-bold">준공연도</small>
							</th>
							<td class="px-3 p-1 w-75">{{ houseDetail.buildYear }} 년</td>
						</tr>
						<tr class="price border-bottom">
							<th scope="row " class="p-1 w-25 text-center">
								<small class="font-weight-bold">거래가격</small>
							</th>
							<td class="px-3 p-1 w-75">
								{{ Number(houseDetail.dealAmount).toLocaleString() }} 만원
							</td>
						</tr>
					</tbody>
				</table>
			</card>
		</div>
	</div>
</template>

<script>
	import { mapGetters } from "vuex";

	export default {
		data() {
			return {
				starNum: 5,
				starList: [false, false, false, false, false]
			};
		},

		methods: {
			setStar(idx) {
				idx = idx + 1;

				this.starList = [];
				this.starNum = idx;

				for (let i = 0; i < idx; i++) this.starList.push(true);
				for (let i = 0; i < 5 - idx; i++) this.starList.push(false);
			}
		},

		computed: {
			...mapGetters({
				isOpen: "getIsListOpen",
				houseDetail: "getHouseDetail",
				houseReviewScore: "getHouseReviewScore"
			}),

			houseReviewScoreFloor() {
				return Math.floor(this.houseReviewScore);
			},

			houseReviewScoreToFixed() {
				return this.houseReviewScore.toFixed(1);
			}
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
	};
</script>

<style lang="scss" scoped>
	.house-review-title {
		background: $default;
		// color: #fff;
		border-radius: 3px;
	}

	.btnBookmark {
		position: fixed;
		top: 10px;
		left: 370px;
		z-index: 10;
	}
</style>
