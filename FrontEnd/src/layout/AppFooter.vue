<template>
	<!-- <footer class="footer has-cards">
        <div class="container container-lg">
            <div class="row">
                <div class="col-md-6 mb-5 mb-md-0">
                    <div class="card card-lift--hover shadow border-0">
                        <router-link to="/landing" title="Landing Page">
                            <img v-lazy="'img/theme/landing.jpg'" class="card-img">
                        </router-link>
                    </div>
                </div>
                <div class="col-md-6 mb-5 mb-lg-0">
                    <div class="card card-lift--hover shadow border-0">
                        <router-link to="/profile" title="Profile Page">
                            <img v-lazy="'img/theme/profile.jpg'" class="card-img">
                        </router-link>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row row-grid align-items-center my-md">
                <div class="col-lg-6">
                    <h3 class="text-primary font-weight-light mb-2">Thank you for supporting us!</h3>
                    <h4 class="mb-0 font-weight-light">Let's get in touch on any of these platforms.</h4>
                </div>
                <div class="col-lg-6 text-lg-center btn-wrapper">
                    <a target="_blank" rel="noopener" href="https://twitter.com/creativetim"
                       class="btn btn-neutral btn-icon-only btn-twitter btn-round btn-lg" data-toggle="tooltip"
                       data-original-title="Follow us">
                        <i class="fa fa-twitter"></i>
                    </a>
                    <a target="_blank" rel="noopener" href="https://www.facebook.com/creativetim"
                       class="btn btn-neutral btn-icon-only btn-facebook btn-round btn-lg" data-toggle="tooltip"
                       data-original-title="Like us">
                        <i class="fa fa-facebook-square"></i>
                    </a>
                    <a target="_blank" rel="noopener" href="https://dribbble.com/creativetim"
                       class="btn btn-neutral btn-icon-only btn-dribbble btn-lg btn-round" data-toggle="tooltip"
                       data-original-title="Follow us">
                        <i class="fa fa-dribbble"></i>
                    </a>
                    <a target="_blank" rel="noopener" href="https://github.com/creativetimofficial"
                       class="btn btn-neutral btn-icon-only btn-github btn-round btn-lg" data-toggle="tooltip"
                       data-original-title="Star on Github">
                        <i class="fa fa-github"></i>
                    </a>
                </div>
            </div>
            <hr>
            <div class="row align-items-center justify-content-md-between">
                <div class="col-md-6">
                    <div class="copyright">
                        &copy; {{year}}
                        <a href="https://www.creative-tim.com" target="_blank" rel="noopener">Creative Tim</a> & <a href="https://www.binarcode.com" target="_blank" rel="noopener">Binar Code</a>
                    </div>
                </div>
                <div class="col-md-6">
                    <ul class="nav nav-footer justify-content-end">
                        <li class="nav-item">
                            <a href="https://www.creative-tim.com" class="nav-link" target="_blank" rel="noopener">Creative Tim</a>
                        </li>
                        <li class="nav-item">
                            <a href="https://www.creative-tim.com/presentation" class="nav-link" target="_blank" rel="noopener">About
                                Us</a>
                        </li>
                        <li class="nav-item">
                            <a href="http://blog.creative-tim.com" class="nav-link" target="_blank" rel="noopener">Blog</a>
                        </li>
                        <li class="nav-item">
                            <a href="https://github.com/creativetimofficial/argon-design-system/blob/master/LICENSE.md"
                               class="nav-link" target="_blank" rel="noopener">MIT License</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </footer> -->
	<footer class="footer has-cards">
		<div class="container">
			<div class="row row-grid align-items-center my-md">
				<div class="col-lg-6">
					<h3 class="text-primary font-weight-700 mb-2">We Are The King</h3>
					<h4 class="mb-0 font-weight-light">팀원을 소개합니다</h4>
				</div>
			</div>
			<div class="container container-lg">
				<div class="row">
					<div class="col-md-4 mb-5 mb-md-0">
						<!-- <div class="card card-lift--hover shadow border-0"> -->
						<router-link to="/profile" title="Profile Page">
							<figure class="profile-overlay align-item-center">
								<img
									v-lazy="'img/theme/team_1_profile.jpg'"
									class="card-img rounded-circle shadow"
								/>
								<figcaption>
									<span>말랭이</span>
								</figcaption>
							</figure>
						</router-link>
						<div class="profile-name mt-3 font-weight-light text-center">염정아</div>
						<!-- </div> -->
					</div>
					<div class="col-md-4 mb-5 mb-lg-0">
						<!-- <div class="card card-lift--hover shadow border-0"> -->
						<router-link to="/profile" title="Profile Page">
							<figure class="profile-overlay align-item-center">
								<img
									v-lazy="'img/theme/team_2_profile.jpg'"
									class="card-img rounded-circle shadow"
								/>
								<figcaption>
									<span>콩떡이</span>
								</figcaption>
							</figure>
						</router-link>
						<!-- </div> -->
						<div class="profile-name mt-3 font-weight-light text-center">이수경</div>
					</div>
					<div class="col-md-4 mb-5 mb-lg-0">
						<!-- <div class="card card-lift--hover shadow border-0"> -->
						<router-link to="/profile" title="Profile Page">
							<figure class="profile-overlay align-item-center">
								<img
									v-lazy="'img/theme/team_3_profile.jpg'"
									class="card-img rounded-circle shadow"
								/>
								<figcaption><span>찌랭이</span></figcaption>
							</figure>
						</router-link>
						<!-- </div> -->
						<div class="profile-name mt-3 font-weight-light text-center">정채원</div>
					</div>
				</div>
			</div>
			<hr />
			<div class="row align-items-center justify-content-md-between">
				<div class="col-md-6">
					<div class="copyright">
						&copy; {{ year }} SSAFY 8기
						<!-- <a href="https://www.creative-tim.com" target="_blank" rel="noopener">Creative Tim</a> &
            <a href="https://www.binarcode.com" target="_blank" rel="noopener">Binar Code</a> -->
					</div>
				</div>
				<div class="col-md-6">
					<ul class="nav nav-footer justify-content-end">
						<li class="nav-item">
							<a
								href="https://lab.ssafy.com/dua9920/final"
								class="nav-link"
								target="_blank"
								rel="noopener"
							>
								<i class="fa fa-gitlab"></i>
							</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</footer>
</template>
<script>
	import ChannelService from "@/common/chatbot";
	export default {
		name: "app-footer",
		data() {
			return {
				year: new Date().getFullYear()
			};
		},
		created() {
			ChannelService.boot({
				pluginKey: process.env.VUE_APP_CHANNEL_BOT_PLUGIN_KEY
			});
		}
	};
</script>
<style>
	.profile-overlay,
	.profile-name {
		--profile-width: 300px;
		--profile-height: 300px;
	}

	.profile-overlay {
		position: relative;
		display: inline-block;
		width: 100%;
		color: #bbb;
		font-size: 16px;
		box-shadow: none !important;
		-webkit-transform: translateZ(0);
		transform: translateZ(0);
	}

	.profile-overlay *,
	.profile-overlay:before,
	.profile-overlay:after {
		-webkit-box-sizing: border-box;
		box-sizing: border-box;
		-webkit-transition: all 0.3s linear;
		transition: all 0.3s linear;
	}

	.profile-overlay:before,
	.profile-overlay:after {
		-webkit-box-sizing: border-box;
		box-sizing: border-box;
		border-radius: 50%;
		content: "";
		position: absolute;
		top: 0px;
		bottom: 0px;
		left: 0px;
		right: 0px;
		z-index: -1;
	}

	.profile-overlay img,
	.profile-overlay figcaption {
		width: var(--profile-width);
		height: var(--profile-height);
	}
	.profile-overlay img {
		backface-visibility: hidden;
		vertical-align: top;
		border-radius: 50%;
	}

	.profile-overlay figcaption {
		position: absolute;
		top: 0px;
		bottom: 0px;
		left: 0px;
		right: 0px;
		opacity: 0;
		background-color: rgba(0, 0, 0, 0.8);
		border-radius: 50%;
	}
	/*
.profile-overlay i {
  position: absolute;
  top: 50%;
  left: 50%;
  -webkit-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
  font-size: 4em;
  z-index: 1;
}
*/
	.profile-overlay span {
		position: absolute;
		top: 50%;
		left: 50%;
		-webkit-transform: translate(-50%, -50%);
		transform: translate(-50%, -50%);
		z-index: 1;
	}

	.profile-overlay a {
		position: absolute;
		top: 0;
		bottom: 0;
		left: 0;
		right: 0;
		z-index: 1;
	}

	.profile-overlay:hover figcaption,
	.profile-overlay.hover figcaption {
		opacity: 1;
		-webkit-transform: translateX(0);
		transform: translateX(0);
	}

	.profile-name {
		width: var(--profile-width);
	}
</style>
