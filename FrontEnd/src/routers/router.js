import Vue from "vue";
import Router from "vue-router";
import AppHeader from "../layout/AppHeader";
import AppFooter from "../layout/AppFooter";
import Components from "../views/Components.vue";
import Landing from "../views/Landing.vue";
import Login from "../views/Login.vue";
import Register from "../views/Register.vue";
import Profile from "../views/Profile.vue";
import NoticeListView from "../views/NoticeListView.vue";
import NoticeInputView from "../views/NoticeInputView.vue";
import NoticeDetailView from "../views/NoticeDetailView.vue";
import NoticeUpdateView from "../views/NoticeUpdateView.vue";
import EventListView from "../views/EventListView.vue";
import EventInputView from "../views/EventInputView.vue";
import EventDetailView from "../views/EventDetailView.vue";
import EventUpdateView from "../views/EventUpdateView.vue";
import Qna from "../views/Qna.vue";
import QnaInsert from "../views/QnaInsert.vue";
import QnaDetail from "../views/QnaDetail.vue";
import Bookmark from "../views/Bookmark.vue";

// Custom View
import HouseView from "../views/HouseView.vue";
// import HouseDetail from "@/views/components/HouseDetail.vue";
import NotFoundPageView from "../views/NotFoundPageView.vue";

import KakaoLoginJavascript from "@/components/oauth/KakaoLoginJavascript";
import KakaoLoginJavascriptBackEnd from "@/components/oauth/KakaoLoginJavascriptBackEnd";
import RegisterPage from "@/components/oauth/RegisterPage";


Vue.use(Router);

export default new Router({
	linkExactActiveClass: "active",
	routes: [
		{
			path: "/",
			name: "components",
			components: {
				header: AppHeader,
				default: Components,
				footer: AppFooter
			}
		},
		{
			path: "/landing",
			name: "landing",
			components: {
				header: AppHeader,
				default: Landing,
				footer: AppFooter
			}
		},
		{
			path: "/login",
			name: "login",
			components: {
				header: AppHeader,
				default: Login,
				footer: AppFooter
			}
		},
		{
			path: "/register",
			name: "register",
			components: {
				header: AppHeader,
				default: Register,
				footer: AppFooter
			}
		},
		{
			path: "/profile",
			name: "profile",
			components: {
				header: AppHeader,
				default: Profile,
				footer: AppFooter
			}
		},
		{
			path: "/house",
			name: "house",
			components: {
				header: AppHeader,
				default: HouseView
				// footer: AppFooter,
			}
		},
		// {
		// 	path: "/house/detail",
		// 	name: "house-detail",
		// 	components: {
		// 		header: AppHeader,
		// 		default: HouseDetail
		// 		// footer: AppFooter,
		// 	}
		// },
		{
			path: "/notice",
			name: "notice",
			components: {
				header: AppHeader,
				default: NoticeListView,
				footer: AppFooter
			}
		},
		{
			path: "/notice/input",
			name: "notice-input",
			components: {
				header: AppHeader,
				default: NoticeInputView,
				footer: AppFooter
			}
		},
		{
			path: "/notice/detail",
			name: "notice-detail",
			components: {
				header: AppHeader,
				default: NoticeDetailView,
				footer: AppFooter
			}
		},
		{
			path: "/notice/update",
			name: "notice-update",
			components: {
				header: AppHeader,
				default: NoticeUpdateView,
				footer: AppFooter
			}
		},
		{
			path: "/event",
			name: "event",
			components: {
				header: AppHeader,
				default: EventListView,
				footer: AppFooter
			}
		},
		{
			path: "/event/input",
			name: "event-input",
			components: {
				header: AppHeader,
				default: EventInputView,
				footer: AppFooter
			}
		},
		{
			path: "/event/detail",
			name: "event-detail",
			components: {
				header: AppHeader,
				default: EventDetailView,
				footer: AppFooter
			}
		},
		{
			path: "/event/update",
			name: "event-update",
			components: {
				header: AppHeader,
				default: EventUpdateView,
				footer: AppFooter
			}
		},
		{
			path: "/qna",
			name: "qna",
			components: {
				header: AppHeader,
				default: Qna,
				footer: AppFooter
			}
		},
		{
			path: "/qnaInsert",
			name: "qnaInsert",
			components: {
				header: AppHeader,
				default: QnaInsert,
				footer: AppFooter
			}
		},
		{
			path: "/qnaDetail",
			name: "qnaDetail",
			components: {
				header: AppHeader,
				default: QnaDetail,
				footer: AppFooter
			}
		},
		{
			path: "/bookmark",
			name: "bookmark",
			components: {
				header: AppHeader,
				default: Bookmark,
				footer: AppFooter
			}
		},

		{
			name: "KakaoLoginJavascript",
			path: "/kakaoLoginJavascript",
			component: KakaoLoginJavascript,
		},
		{
			name: "KakaoLoginJavascriptBackEnd",
			path: "/kakaoLoginJavascriptBackEnd",
			component: KakaoLoginJavascriptBackEnd,
		},
		{
			name: "RegisterPage",
			path: "/registerPage",
			component: RegisterPage,
		},
		
		{
			path: "*",
			components: {
				header: AppHeader,
				default: NotFoundPageView,
				footer: AppFooter
			}
		}
	],

	scrollBehavior: (to) => {
		if (to.hash) {
			return { selector: to.hash };
		} else {
			return { x: 0, y: 0 };
		}
	}
});
