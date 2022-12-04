<template>
	<div>
		<div class="section-shaped my-0 apt-section">
			<div class="shape shape-style-1 shape-dark"></div>
		</div>

		<div class="apt-main">
			<HouseSettings :toggleSettings="toggleSettings"></HouseSettings>
			<HouseListView></HouseListView>
			<KakaoMap></KakaoMap>
		</div>
	</div>
</template>

<script>
	import KakaoMap from "./components/KakaoMap.vue";
	import HouseSettings from "./components/HouseSettings.vue";
	import HouseListView from "./HouseListView.vue";

	export default {
		components: {
			KakaoMap,
			HouseSettings,
			HouseListView
		},

		data() {
			return {
				toggleSettings: true
			};
		},

		methods: {
			onToggleSettings() {
				this.toggleSettings = !this.toggleSettings;
			}
		}
	};
</script>

<style scoped>
	.apt-section::before {
		content: "";
		background-image: url("../../public/img/bg3.jpg");
		background-repeat: no-repeat;
		background-size: cover;
		opacity: 0.7;
		position: absolute;
		top: 0px;
		left: 0px;
		right: 0px;
		bottom: 0px;
	}

	.apt-section {
		height: 100px;
	}

	.apt-main {
		position: relative;
	}
</style>
