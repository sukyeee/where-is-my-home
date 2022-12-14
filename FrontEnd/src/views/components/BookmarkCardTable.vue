<template>
	<div class="container d-flex flex-column align-items-center">
		<section class="section section-lg pt-lg-0">
			<div class="container">
				<div class="row">
					<div class="col-lg-12">
						<div class="row row-grid">

							<!-- 북마크 카드 테이블 내용 -->
							<div class="col-md-6 col-lg-4 mb-3" v-for="(bookmark, index) in bookmarkBodyList" :key="index">
								<div>
									<card class="border-0" hover shadow>
										<i class="fa fa-times display-4 pt-1 d-flex justify-content-end" style="cursor: pointer;"
											@click="bookmarkDelete(bookmark.bookmarkId)"></i>

										<div @click="bookmarkDetail(bookmark)" style="cursor: pointer;">
											<div class="event-thumbnail" :data-join="bookmark.joinTag" :data-ended="bookmark.isEnded">
												<div class="event-thumbnail-image">
													<!-- <img :src="board.thumbnailImage" /> -->
													<img src="../../../public/img/theme/house.jpg" alt="매물 이미지.." class="mt-4 mb-2" />
												</div>
												<div v-show="bookmark.joinTag" class="event-thumbnail-text">찜한 매물</div>
												<!-- <div v-show="board.isEnded" class="event-thumbnail-text">마감된 이벤트</div> -->
											</div>
											<div class="d-flex justify-content-center">
												<table class="table table-hover house-detail-content mt-2">
													<tbody>
														<tr>
															<th scope="row " class="p-1 w-25 text-center">
																<small class="font-weight-bold">아파트</small>
															</th>
															<td class="px-3 p-1 w-75">{{ bookmark.apartmentName }}</td>
														</tr>
														<tr>
															<th scope="row " class="p-1 w-25 text-center">
																<small class="font-weight-bold">주소</small>
															</th>
															<td class="px-3 p-1 w-75">{{ bookmark.dong }}</td>
														</tr>
														<tr class="price">
															<th scope="row " class="p-1 w-25 text-center">
																<small class="font-weight-bold">가격</small>
															</th>
															<td class="px-3 p-1 w-75 text-danger font-weight-bold">
																{{ bookmark.dealAmount }} 만원
															</td>
														</tr>
													</tbody>
												</table>

												<!-- <div class="join-tag-container">
																				<badge v-show="board.joinTag" class="m-0" type="yellow" rounded>신청 완료</badge>
																		</div> -->
											</div>


										</div>

										<!-- <base-button tag="a" href="#" type="primary" class="mt-2 w-100"> 자세히 보기 </base-button> -->
										<!-- <base-checkbox ref="inputDiv" class="mb-3 d-flex justify-content-center mt-2" :value=bookmark.houseDealId v-model="checkedValues" >
                      {{checkedValues}}
                      </base-checkbox> -->

										<div class="row d-flex justify-content-center">
											<input type="checkbox" class="my-checkbox mb-3 d-flex justify-content-center mt-2"
												:value=bookmark.houseDealId ealId v-model="checkedValues" @change="test" />
										</div>
									</card>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
		<base-pagination class="mt-5"></base-pagination>

	</div>
</template>

<script>
import http from "@/common/axios.js";
import { mapGetters } from "vuex";
import alertify from 'alertifyjs';

export default {
	data() {
		return {
			checkboxes: {
				unchecked: false,
				checked: true,
				uncheckedDisabled: false,
				checkedDisabled: true,
				inputDiv: this.$refs.inputDiv

			},
			checkedValues: []
		};
	},
	props: {
		tagList: {
			type: Array,
			description: "게시글의 카테고리 유형 리스트",
			default: function () {
				return [{ tagName: "동별", tagValue: "동별" }];
			}
		},
		bookmarkBodyList: {
			type: Array,
			description: "게시글 목록 리스트"
		}
	},
	computed: {
		...mapGetters({
			getterHouseList: "getHouseList",
			isDetailOpen: "getIsDetailOpen",
		}),
	},

	methods: {
		async bookmarkList() {
			this.$store.dispatch("bookmarkList");
		},
		/* 북마크 선택 -> 아파트 매물 리스트 가져오기 */
		async getHouseList() {
			await this.$store.dispatch("getHouseList");
		},
		// 북마크 삭제 ( 북마크  화면 )
		async bookmarkDelete(bookmarkId) {
			try {
				let { data } = await http.delete("/bookmarks/" + bookmarkId);

				if (data.result == "login") {
					router.push("/login");
				} else {
					alertify.success("북마크 삭제 완료");
					this.bookmarkList();
				}
			} catch (error) {
				console.error(error);
			}
		},


		async bookmarkDetail(bookmark) {
			// 현재 클릭된 houseId와 houseDealId 저장
			await this.$store.dispatch("getHouseDetail", bookmark.houseDealId);
			this.$store.commit("SET_SEARCH_LIST", { houseId: bookmark.houseId, houseDealId: bookmark.houseDealId });
			this.$store.commit("SET_LIST_OPEN");
			this.$store.commit("SET_DETAIL_OPEN");
			this.$router.push("/house");
		},

		test() {
			console.log(this.checkedValues);
			// action으로 mutation -> setting 하기
			this.$store.commit("SET_CHECKED_LIST", this.checkedValues);
		}
	}
};
</script>

<style scoped lang="scss">
.table {
	width: 80%;
	background: white;
}

.event-thumbnail {
	width: 100%;
	height: 100%;
	overflow: hidden;
}

.event-thumbnail img {
	width: 100%;
}

// .join-tag-container{
//   height: 25px;
//   // text-align: center;
// }

.container {
	width: 80vw;
}

.my-checkbox {
	accent-color: grey;
	cursor: pointer;
}
</style>
