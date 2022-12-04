<template>
	<div class="profile-page">
			<section class="section-profile-cover mypage-section section-shaped my-0">
					<div class="shape shape-style-1 bg-gradient-default">

					</div>
			</section>
			<section class="section section-skew">
					<div class="container">
							<card shadow class="card-profile mt--300" no-body>
									<div class="px-4">
											<div class="row justify-content-center">
													<div class="col-lg-3 order-lg-2">
															<div class="card-profile-image">
																	<!-- 첨부파일  -->
																	<div class="mb-3 " id="imgFileUploadInsertWrapper">

																			<!-- "'../../../BackEnd/src/main/resources/static/'+{{getUserInfo.userProfileImageUrl}}" -->
																			<label for="file-input">
																					<img :src="getFileRealPath" class="rounded-circle" />
																			</label>
																			<input @change="changeFile" id="file-input" class="inputFileUploadInsert"
																					type="file" style="display: none;" />
																	</div>
															</div>

															<!--                                 <input @change="changeFile" type="file" id="inputFileUploadInsert" multiple />
																	<div id="imgFileUploadInsertThumbnail" class="thumbnail-wrapper">
																	<img v-for="(file, index) in fileList" v-bind:src="file" v-bind:key="index" />
															</div> -->
													</div>

													<div class="col-lg-4 order-lg-3 text-lg-right align-self-lg-center">
															<div class="card-profile-actions py-4 mt-lg-0">
																	<!-- <base-button type="default" class="mr-4" @click="modals.modal3 = true">정보 수정
																	</base-button>
																	<base-button type="danger" class="float-right" @click="userDelete">회원 탈퇴
																	</base-button> -->
															</div>
													</div>
													<div class="col-lg-4 order-lg-1">
															<div class="card-profile-stats d-flex justify-content-center">
																	<div>
																			<span class="heading"> {{ getterReviewListTotalCount }} </span>
																			<span class="description">Reviews</span>
																	</div>
																	<!-- <div>
																			<span class="heading">10</span>
																			<span class="description">Photos</span>
																	</div>
																	<div>
																			<span class="heading">89</span>
																			<span class="description">Comments</span>
																	</div> -->
																	<div class="display-4">
																			{{ myAvgReviewScoreToFixed }}점
																			<i class="fa fa-star text-yellow" aria-hidden="true"
																					v-for="index in myAvgReviewScoreFloor" :key="index"></i>
																			<i class="fa fa-star-o text-yellow" aria-hidden="true"
																					v-for="index in 5 - myAvgReviewScoreFloor" :key="`o-${index}`"></i>
																	</div>
															</div>
													</div>
											</div>

											<div class="text-center mt-4">
													<h3> {{ getUserInfo.userName }}
															<!-- <span class="font-weight-light">, 27</span> -->
													</h3>
													<div class="h6 font-weight-300"><i class="ni location_pin mr-2"></i> {{
																	getUserInfo.userEmail
													}} </div>
													<!-- <div class="h6 mt-4"><i class="ni business_briefcase-24 mr-2"></i>Nickname - {{ getUserInfo.userNickname }} </div>
													<div><i class="ni education_hat mr-2"></i>Register Date -  {{ getUserInfo.userRegisterDate }} </div> -->
											</div>



											<!-- 내가쓴리뷰 / 정보수정 / 회원탈퇴 선택 창  -->
											<div class="row justify-content-center">
													<div class="col-lg-10 mb-5">
															<!-- Tabs with icons -->
															<div class="mb-3">
																	<!-- <small class="text-uppercase font-weight-bold">With icons</small> -->
															</div>
															<tabs fill class="flex-column flex-md-row">
																	<card shadow>
																			<tab-pane key="tab1">
																					<template slot="title" class="profile-tab-title">
																							<!-- <i class="ni ni-cloud-upload-96 mr-2"></i> -->
																							내가 쓴 리뷰
																					</template>

																					<profile-my-review></profile-my-review>
																			</tab-pane>


																			<tab-pane key="tab3">
																					<template slot="title">
																							<!-- <i class="ni ni-bell-55 mr-2"></i> -->
																							정보수정
																					</template>


																					<form role="form">
																							<base-input alternative class="mb-3" addon-left-icon="ni ni-hat-3"
																									v-model=$store.state.userInfo.userName  >
																							</base-input>
																							<base-input alternative class="mb-3" addon-left-icon="fa fa-address-card-o"
																									v-model=$store.state.userInfo.userNickname>
																							</base-input>
																							<base-input alternative class="mb-3" addon-left-icon="ni ni-email-83"  @change="validateUserEmail" :valid="userEmailValidState"
																									v-model=$store.state.userInfo.userEmail>
																							</base-input>
																							<base-input alternative type="password" placeholder="Password" @change="validatePassword" :valid="userPasswordValidState" 
																									addon-left-icon="ni ni-lock-circle-open"
																									v-model=$store.state.userInfo.userPassword  >
																							</base-input>

																							<div class="text-center">
																									<base-button type="primary" class="my-4" @click="userUpdate">수정 완료
																									</base-button>
																							</div>
																					</form>

																			</tab-pane>

																			<tab-pane key="tab4">
																					<template slot="title">
																							<!-- <i class="ni ni-calendar-grid-58 mr-2"></i> -->
																							회원탈퇴
																					</template>
																					정말 탈퇴하시겠습니까?
																					<base-button type="danger" class="float-right" @click="userDelete">회원 탈퇴
																					</base-button>
																			</tab-pane>
																	</card>
															</tabs>
													</div>
											</div>

											<!-- <div class="mt-3 py-5 text-center">
													
											</div> -->
									</div>
							</card>
					</div>
			</section>



			<!-- modal 모달창 -->
			<modal :show.sync="modals.modal3" body-classes="p-0" modal-classes="modal-dialog-centered modal-sm">
					<card type="secondary" shadow header-classes="bg-white pb-5" body-classes="px-lg-5 py-lg-5"
							class="border-0">

							<template>
									<div class="display-3 text-left mb-4 font-weight-800 text-default"
											style="text-shadow: 2px 1px 2px rgba(0, 0, 0, 0.2)">
											회원정보 수정
									</div>

									<form role="form">
											<base-input alternative class="mb-3" placeholder="Name" addon-left-icon="ni ni-hat-3">
											</base-input>
											<base-input alternative class="mb-3" placeholder="Email" addon-left-icon="ni ni-email-83">
											</base-input>
											<base-input alternative type="password" placeholder="Password"
													addon-left-icon="ni ni-lock-circle-open">
											</base-input>

											<div class="text-center">
													<base-button type="primary" class="my-4">수정 완료</base-button>
											</div>
									</form>
							</template>
					</card>
			</modal>
	</div>


</template>
<script>
import http from "@/common/axios.js";
import BaseNav from "@/components/BaseNav";
import CloseButton from "@/components/CloseButton";
import Modal from "@/components/Modal.vue";
import Tabs from "@/components/Tabs/Tabs.vue";
import TabPane from "@/components/Tabs/TabPane.vue";
import ProfileMyReview from "./ProfileMyReview.vue";
import { mapGetters } from "vuex";
import alertify from 'alertifyjs';

export default {
	components: {
			BaseNav,
			CloseButton,
			Modal,
			Tabs,
			TabPane,
			ProfileMyReview
	},
	data() {
			return {
					modals: {
							modal1: false,
							modal2: false,
							modal3: false
					},
					checkboxes: {
							unchecked: false,
							checked: true,
							uncheckedDisabled: false,
							checkedDisabled: true
					},
					radio: {
							radio1: "radio1",
							radio2: "radio3"
					},
					switches: {
							off: false,
							on: true
					},
					sliders: {
							slider1: 0,
							slider2: [150, 400]
					},
					userDetail: {
							userName: '',
							userNickname: '',
							userEmail: '',
							userPassword: '',
					},

					// 첨부파일
					attachFile: false,
					fileList: [],

					// 파일 실제 경로
					fileRealPath: require("../../../BackEnd/src/main/resources/static/img/noProfile.png"),
					
					// 정보 수정 유효성 검사
					userEmailValidState: false,
					userPasswordValidState: false,

			};
	},
	created() {

	},
	computed: {
			// getters 로 store의 userInfo 가져오기
			getUserInfo() {
					return this.$store.getters.getUserInfo;
			},
			...mapGetters({
					getterReviewListTotalCount: "getReviewListTotalCount",
					myAvgReviewScore: "getmyReviewScore",
					getUserProfile: "getUserProfile",
			}),

			myAvgReviewScoreToFixed() {
					return this.myAvgReviewScore.toFixed(1);
			},

			myAvgReviewScoreFloor() {
					return Math.floor(this.myAvgReviewScore);
			},
			getFileRealPath() {
					// 파일 실제 경로
					if (this.getUserProfile) {
							// this.fileRealPath = require("../../../BackEnd/src/main/resources/static/" + this.getUserProfile)
							// this.fileRealPath = `../../../BackEnd/src/main/resources/static/upload/noProfile.png`
					}
					// else this.fileRealPath = require("../../../BackEnd/src/main/resources/static/upload/noProfile.png")

					return this.fileRealPath
			},
	},
	methods: {

			// 유효성 검사
			validateUserEmail(){
			if(this.$store.state.userInfo.userEmail.length >= 2) this.userEmailValidState = true;
			else this.userEmailValidState = false;
			},
			validatePassword() {
			
			let userPassword = this.$store.state.userInfo.userPassword;
			
			var patternEngAtListOne = new RegExp(/[a-zA-Z]+/);// + for at least one
			var patternSpeAtListOne = new RegExp(/[~!@#$%^&*()_+|<>?:{}]+/);// + for at least one
			var patternNumAtListOne = new RegExp(/[0-9]+/);// + for at least one

			if( patternEngAtListOne.test( userPassword ) 
							&& patternSpeAtListOne.test( userPassword )  
							&& patternNumAtListOne.test( userPassword )
							&& userPassword.length >= 4
			) this.userPasswordValidState = true;
			
			else this.userPasswordValidState = false;
			},
					
			handleFile(e) {
					const file = e.target.files[0];
					console.log(file.size);

					const reader = new FileReader();
					reader.onload = (e) => {
							if (file.size >= 1048576) {
									this.$toast.error("파일의 최대 크기는 1MB입니다", {
											position: "bottom-right",
											timeout: 1800,
											draggablePercent: 0.48,
									});
							} else {
									this.userProfileImage = file;
									this.$refs.previewImg.src = e.target.result;
							}
					};
					reader.readAsDataURL(file);
			},
			fileUpload() {
					this.$refs.File.click();
			},
			changeFile(fileEvent) {
					this.fileList = [];

					const fileArray = Array.from(fileEvent.target.files);
					fileArray.forEach((file) => {
							this.fileList.push(URL.createObjectURL(file));
					});

					this.userProfileUpdate();
			},
			async userDelete() {

					// let userInfo = this.$store.getters.getUserInfo;
					// console.log(userInfo)
					try {
							console.log(this.$store.state.userInfo)
							let { data } = await http.delete("/user/" + this.$store.state.userInfo.userId);
							console.log(data);

							if (data.result == "login") {
									alertify.error("세션이 만료되었습니다.", 1.5);
									this.$router.push("/login");

							} else {
									alertify.success("회원탈퇴가 완료되었습니다.", 1.5);
									this.doLogout();
									this.$router.push("/");
							}
					} catch (error) {
							console.log(error);
					}

			},
			doLogout() {
				this.$store.state.isLogin = false;
				this.$store.commit("SET_USER_INFO", {});
				this.$router.push("/login");
    	},
			async userUpdate() {

					// 수정 완료 누르면 일단 state값 먼저 변경.
					// this.$store.commit("SET_USER_INFO",  data.userDto )

					let formData = new FormData();
					formData.append("userId", this.$store.state.userInfo.userId);
					formData.append("userName", this.$store.state.userInfo.userName);
					formData.append("userEmail", this.$store.state.userInfo.userEmail);
					formData.append("userNickname", this.$store.state.userInfo.userNickname);
					formData.append("userPassword", this.$store.state.userInfo.userPassword);

					let options = {
							headers: { "Content-Type": "multipart/form-data" },
					};

					try {

							let { data } = await http.post("/user/" + this.$store.state.userInfo.userId, formData, options);
							console.log(data);

							if (data.result == "login") {
									//    this.doLogout();
									this.$router.push("/login");
							} else {
								alertify.success("정보 수정이 완료되었습니다.", 1.5);
									// this.$router.push("/");
									//    this.$alertify.success("회원 탈퇴가 완료되었습니다.");

							}
					} catch (error) {
							console.log(error);
					}

			},

			// 내가 쓴 리뷰 조회
			async userReview() {
					try {

							let { data } = await http.get("/users/review"); // params: params shorthand property, let response 도 제거
							console.log("review data : ");
							console.log(data);
							if (data.result == "login") {
									this.$router.push("/login");
							} else {
									this.$store.commit("SET_USER_REVIEW", data.review);
							}
					} catch (error) {
							console.error(error);
					}

			},

			// 프로필 이미지 변경
			async userProfileUpdate() {

					let formData = new FormData();

					// file upload
					let attachFiles = document.querySelector(".inputFileUploadInsert").files;

					if (attachFiles.length > 0) {
							const fileArray = Array.from(attachFiles);
							fileArray.forEach((file) => formData.append("file", file));
					}

					let options = {
							headers: { "Content-Type": "multipart/form-data" },
					};

					try {
							let { data } = await http.post("/user/profile", formData, options);
							console.log(data)
							if (data.result == "login") {
									// this.doLogout();
							} else {
									this.$store.commit("SET_USER_PROFILE", data.userProfileImageUrl);
									this.$alertify.success("프로필 사진이 수정되었습니다.");
							}
					} catch (error) {
							console.log(error);
							this.$alertify.error("수정을 실패하였습니다.");
					}

			}

	}

};

</script>
<style scoped>
.mypage-section::before {
	content: "";
	background-image: url("../../public/img/bg3.jpg");
	background-repeat: no-repeat;
	background-size: cover;
	background-position: center;
	opacity: 0.8;
	position: absolute;
	top: 0px;
	left: 0px;
	right: 0px;
	bottom: 0px;
}

.display-4 {
	font-size: 1.28rem;
}

.profile-tab-title {
	display: none !important;
}
</style>