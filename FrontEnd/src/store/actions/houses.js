import http from "@/common/axios";

export default {
	/* House View */
	/* 아파트 매물 리스트 가져오기 */
	async getHouseList(context) {
		console.log("async getHouseList");

		let params = {
			limit: this.state.houses.limit,
			offset: this.state.houses.houseOffset,
			searchWord: this.state.houses.searchWord,
			dongCode: this.state.houses.dongCode,
			minYear: this.state.houses.minYear,
			maxYear: this.state.houses.maxYear,
			minMonth: this.state.houses.minMonth,
			maxMonth: this.state.houses.maxMonth,
			minDay: this.state.houses.minDay,
			maxDay: this.state.houses.maxDay,
			minDealAmount: this.state.houses.minDealAmount,
			maxDealAmount: this.state.houses.maxDealAmount,
			minArea: this.state.houses.minArea,
			maxArea: this.state.houses.maxArea
		};

		try {
			let { data } = await http.get("/houses", { params });
			console.log("houseList:", data);

			if (data.result == 1) {
				context.commit("SET_HOUSE_LIST", data.houseList);
				context.commit("SET_HOUSE_LIST_TOTAL_COUNT", data.totalCount);
			} else {
			}
		} catch (err) {
			console.error(err);
		}
	},

	/* 지역구별 매물 리스트 가져오기 */
	async getHouseGugunList(context) {
		console.log("async getHouseGugunList");

		let params = {
			limit: this.state.houses.limit,
			offset: this.state.houses.houseOffset,
			searchWord: this.state.houses.searchWord,
			dongCode: this.state.houses.dongCode,
			minYear: this.state.houses.minYear,
			maxYear: this.state.houses.maxYear,
			minMonth: this.state.houses.minMonth,
			maxMonth: this.state.houses.maxMonth,
			minDay: this.state.houses.minDay,
			maxDay: this.state.houses.maxDay,
			minDealAmount: this.state.houses.minDealAmount,
			maxDealAmount: this.state.houses.maxDealAmount,
			minArea: this.state.houses.minArea,
			maxArea: this.state.houses.maxArea
		};

		try {
			let { data } = await http.get("/houses/gugun", { params });
			console.log(data);

			if (data.result == 1) {
				context.commit("SET_HOUSE_LIST", data.houseList);
				context.commit("SET_HOUSE_LIST_TOTAL_COUNT", data.totalCount);
			} else {
			}
		} catch (err) {
			console.error(err);
		}
	},

	/* 아파트 매물 리스트 위치 평균치 가져오기 */
	async getHouseListLocationAvg(context) {
		let params = {
			limit: this.state.houses.limit,
			offset: this.state.houses.houseOffset,
			searchWord: this.state.houses.searchWord,
			dongCode: this.state.houses.dongCode,
			minYear: this.state.houses.minYear,
			maxYear: this.state.houses.maxYear,
			minMonth: this.state.houses.minMonth,
			maxMonth: this.state.houses.maxMonth,
			minDay: this.state.houses.minDay,
			maxDay: this.state.houses.maxDay,
			minDealAmount: this.state.houses.minDealAmount,
			maxDealAmount: this.state.houses.maxDealAmount,
			minArea: this.state.houses.minArea,
			maxArea: this.state.houses.maxArea
		};

		try {
			let { data } = await http.get("/houses/loc", { params });

			if (data.result == 1) {
				if (data.locationAvg != undefined) {
					context.commit("SET_HOUSE_LIST_LOCATION_AVG", data.locationAvg);
					return 1;
				} else {
					return -1;
				}
			} else {
			}
		} catch (err) {
			console.error(err);
		}
	},

	/* 아파트 상세 정보 가져오기 */
	async getHouseDetail(context, houseDealId) {
		try {
			let { data } = await http.get(`/houses/${houseDealId}`);

			if (data.result == 1) {
				context.commit("SET_HOUSE_DETAIL", data.houseDetail);
			} else {
			}
		} catch (err) {
			console.error(err);
		}
	},

	/* 아파트 리뷰 리스트 가져오기  */
	async getHouseReviewList(context, houseId) {
		let params = {
			houseId: houseId,
			limit: this.state.houses.limit,
			offset: this.state.houses.reviewOffset
		};

		try {
			let { data } = await http.get(`/reviews/house`, { params });

			if (data.result == 1) {
				context.commit("SET_HOUSE_REVIEW_LIST", data.houseReviewList);
				context.commit("SET_HOUSE_REVIEW_TOTAL_COUNT", data.houseReviewTotalCount);
				context.commit("SET_HOUSE_REVIEW_SCORE", data.houseReviewScore);
			} else {
			}
		} catch (err) {
			console.error(err);
		}
	},

	/* Review View */
	/* 아파트 리뷰 등록 */
	async houseReviewInsert(context, review) {
		try {
			let { data } = await http.post(`/reviews/house`, review);

			if (data.result == 1) {
				if (data.houseReviewTotalCount > 0) {
					context.commit("SET_HOUSE_REVIEW_LIST", data.houseReviewList);
				}
			} else {
			}

			return data.result;
		} catch (err) {
			if (err.response.status == 403) {
				alert("로그인 하세요");
			}
			console.error(err);

			return -1;
		}
	},

	/* 아파트 리뷰 수정 */
	async houseReviewUpdate(context, review) {
		try {
			let { data } = await http.put(`/reviews/house`, review);

			if (data.result == 1) {
			} else {
			}

			return data.result;
		} catch (err) {
			if (err.response.status == 403) {
				alert("로그인 하세요");
			}
			console.error(err);
			return -1;
		}
	},

	/* 아파트 리뷰 삭제 */
	async houseReviewDelete(context, reviewId) {
		try {
			let { data } = await http.delete(`/reviews/house/${reviewId}`);

			if (data.result == 1) {
				// if (data.houseReviewTotalCount > 0) {
				// 	context.commit("SET_HOUSE_REVIEW_SCORE", data.houseReviewScore);
				// }
				// context.commit("SET_DONG_REVIEW_SCORE", data.dongReviewScore);
			} else {
			}

			return data.result;
		} catch (err) {
			if (err.response.status == 403) {
				alert("로그인 하세요");
			}
			console.error(err);
			return -1;
		}
	},

	/* 아파트 리뷰 총점 */
	async getHouseReviewScore(context, houseId) {
		try {
			let { data } = await http.get(`/reviews/house/${houseId}`);

			if (data.result == 1) {
				if (data.houseReviewTotalCount > 0) {
					context.commit("SET_HOUSE_REVIEW_SCORE", data.houseReviewScore);
				}
			} else {
			}

			return data.result;
		} catch (err) {
			if (err.response.status == 403) {
				alert("로그인 하세요");
			}
			console.error(err);
			return -1;
		}
	},

	/* 아파트 거래 동향 정보 리스트 */
	async getHouseDealList(context, houseId) {
		try {
			let { data } = await http.get(`/houses/deals/${houseId}`);
			if (data.result == 1) {
				data.houseList.sort(function (a, b) {
					if (a.dealYear == b.dealYear) {
						if (a.dealMonth == b.dealMonth) {
							return a.dealDay - b.dealDay;
						}
						return a.dealMonth - b.dealMonth;
					}
					return a.dealYear - b.dealYear;
				});
				context.commit("SET_DEAL_LIST", data.houseList);
			} else {
				console.error("아파트 거래 동량 정보 리스트를 조회하는 것을 실패했습니다.");
			}
		} catch (error) {
			console.error("아파트 거래 동량 정보 리스트를 조회하는 과정에서 에러가 발생하였습니다.");
			console.error(error);
		}
	},

	async getGugunCodeList(context, gugunCodeList) {
		try {
			let { data } = await http.post(`/houses/count`, gugunCodeList);

			if (data.result == 1) {
				context.commit("SET_DONG_COUNT_LIST", data.dongCountList);
			} else {
			}

			return data.result;
		} catch (err) {
			if (err.response.status == 403) {
				alert("로그인 하세요");
			}
			console.error(err);

			return -1;
		}
	}
};
