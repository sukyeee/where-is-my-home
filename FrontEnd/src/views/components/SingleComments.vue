<template>
	<div>
		<!-- 댓글 위쪽 -->
		<div class="d-flex align-items-center">
			<img
				class="rounded-circle mr-3"
				src="https://picsum.photos/250/100?1"
				alt=""
				style="width: 40px; height: 40px;"
			/>
			<div class="w-100 ms-3">
				<div class="d-flex w-100 justify-content-between">
					<h6 class="mb-0">{{ comment.userName }} &nbsp;<small>{{ comment.registerDate.date.year }}/{{ comment.registerDate.date.month }}/{{ comment.registerDate.date.day }}</small></h6>

					<!-- ... 버튼 누르면 나오는 수정/삭제 창 -->
					<div v-if="commentMoreState">
						<!-- 수정 삭제 모달 창 띄우기  -->
						<base-button type="bg-secondary" class="btn-sm" @click="commentUpdateTrue"
							>수정</base-button
						>
						<base-button
							type="bg-secondary"
							class="btn-sm"
							@click="commentDeleteConfirm(comment.commentId)"
							>삭제</base-button
						>
					</div>
					<div v-else>
						<!-- 댓글 user_id와 로그인 user_id가 일치할 때만 보이게 -->

						<!-- 드롭다운 -->
						<base-dropdown tag="li" class="nav-item" position="right">
							<a slot="title" class="nav-link" data-toggle="dropdown" role="button">
								<div class="nav-link-inner--text d-lg-none font-weight-600">...</div>
							</a>
							<button @click="commentUpdateTrue" class="dropdown-item dropdown-menu-comment">
								수정
							</button>
							<button
								@click="commentDeleteConfirm(comment.commentId)"
								class="dropdown-item dropdown-menu-comment">
								삭제
							</button>
						</base-dropdown>
						<!-- <base-button v-if="comment.userId == $store.state.userInfo.userId" type="bg-secondary" class="btn-sm" @click="commentMoreBtn">...</base-button> -->
					</div>
				</div>
				<div v-if="commentUpdateState">
					<base-input alternative class="mb-3 mt-2" v-model="commentContent"> </base-input>
					<base-button type="bg-secondary" class="btn-sm" @click="commentUpdateFalse"
						>취소</base-button
					>
					<base-button type="bg-secondary" class="btn-sm" @click="commentUpdate(comment.commentId)"
						>저장</base-button
					>
				</div>
				<div v-else>
					<span>{{ comment.content }}</span>
				</div>
			</div>
		</div>

		<!--댓글 아래쪽 -->
		<div class="row justify-content-end">
			<base-button type="bg-secondary" class="btn-sm mr-3 row justify-content-end" @click="replyBtn(comment.commentId)"
				>답글</base-button>
		</div>

		<!-- 답글 추가 input   해당 commentId 바로 밑에 생겨야 함 -->
		<div v-if="replyToggle" class="d-flex align-items-center py-3">
			<img
				class="rounded-circle mr-3"
				src="https://picsum.photos/50/50?1"
				alt=""
				style="width: 25px; height: 25px;"
			/>
			<div class="w-100 ms-3">
				<div class="d-flex w-100 justify-content-between">
					<h6 class="mb-0">{{ $store.state.userInfo.userName }}</h6>
				</div>
				<base-input alternative class="mb-3 input-group-sm" v-model="replyContent" replaceholder="답글 추가...">
				</base-input>
			</div>
			<base-button type="primary" class="btn-sm mt-2" @click="replyInsert">답글</base-button>
		</div>
	</div>
</template>

<script>
	import http from "@/common/axios.js";
	import BaseDropdown from "@/components/BaseDropdown";
	import alertify from 'alertifyjs';

	export default {
		props: ["comment", "qnaId", "commentId" ],
		components: {
			// Modal,
			BaseDropdown
		},
		data() {
			return {
				commentContent: this.$store.state.qnas.comment.content,
				commentMoreState: false,
				commentUpdateState: false,
				replyToggle: false,
				replyContent: "",

				modals: {
					modal1: false
				}
			};
		},
		computed: {
			listGetters() {
				return this.$store.getters.getCommentList;
			}
		},
		created() {
			console.log(this.comment);
			this.commentList();
		},
		methods: {
			// comment 목록 불러오기
			commentList() {
				this.$store.dispatch("commentList");
			},

			// 댓글 내용 초기화
			init() {
				this.commentContent = "";
			},
			/* 댓글 ...(더보기) */
			commentMoreBtn() {
				this.commentMoreState = !this.commentMoreState;
			},
			// 수정 버튼 클릭시 input 창
			commentUpdateTrue() {
				this.commentUpdateState = true;
			},
			// 수정 취소 시
			commentUpdateFalse() {
				this.commentUpdateState = false;
			},
			// 삭제 버튼 클릭 시 삭제 확인 창
			commentDeleteConfirm(commentId) {
				let result = confirm("댓글을 삭제할까요?");
				if (result) {
					this.commentDelete(commentId);
				} else {
					alertify.error("취소되었습니다", 1.5);
				}
			},

			// 답글 버튼 클릭 시 input toggle
			replyBtn(commentId) {
				this.replyToggle = !this.replyToggle;
				console.log("현재 댓글의 commentId:" + commentId);
				this.commentId = commentId;
				// 클릭한 답글의 commentId를 store에 저장
				this.$store.state.qnas.comment.commentId = commentId;
			},

			// comment 수정
			async commentUpdate(commentId) {
				let formData = new FormData();
				formData.append("qnaId", this.$store.state.qnas.qna.qnaId);
				formData.append("content", this.commentContent);

				try {
					let { data } = await http.put("/comments/" + commentId, formData);
					console.log("qnaUpdate data : ");
					console.log(data);

					if (data.result == "login") {
						this.$router.push("/login");
					} else {
						alertify.success("댓글이 수정되었습니다.", 1.5);
						this.commentList();
						this.commentUpdateState = false;
					}
				} catch (error) {
					console.error(error);
				}
			},
			// comment 삭제
			async commentDelete(commentId) {
				try {
					let { data } = await http.delete("/comments/" + commentId);
					console.log("commentDelete data : ");

					if (data.result == "login") {
						router.push("/login");
					} else {
						this.commentList();
					}
				} catch (error) {
					console.error(error);
				}
			},

			async replyInsert() {
				let formData = new FormData();
				formData.append("content", this.replyContent);
				formData.append("qnaId", this.$store.state.qnas.qna.qnaId);
				formData.append("parentCommentId", this.$store.state.qnas.comment.commentId);

				try {
					let { data } = await http.post("/comments", formData);
					console.log(data);

					if (data.result == "login") {
						// this.doLogout();
						this.$router.push("/login");
					} else {
						alertify.success("답글이 등록되었습니다.", 1.5);

						// 답글 입력 form 닫기
						this.replyToggle = false;
						this.commentList();
						this.init();
					}
				} catch (error) {
					console.log(error);
				}
			}
		}
	};
</script>

<style scoped>
	.down-menu-comment {
		width:20px !important;
	}
</style>
