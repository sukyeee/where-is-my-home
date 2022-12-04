<template>
	<div class="p-4">
		<!-- <div class="border-top mb-2 mt-5"></div> -->
		<div class="house-review-title font-weight-bold">
			<i class="ni ni-chart-bar-32" id="review-icon"></i>
			매물 가격 추이
		</div>
		<!-- <div class="border-bottom mt-1"></div> -->
		<LineChartGenerator
			:chart-options="chartOptions"
			:chart-data="chartData"
			chart-id="line-chart"
			dataset-id-key="label"
			:plugins="plugins"
			:css-classes="cssClasses"
			:styles="styles"
			:width="width"
			:height="height"
		/>
	</div>
</template>

<script>
	import { mapGetters } from "vuex";
	import { Line as LineChartGenerator } from "vue-chartjs/legacy";
	import {
		Chart as ChartJS,
		Title,
		Tooltip,
		Legend,
		LineElement,
		LinearScale,
		CategoryScale,
		PointElement
	} from "chart.js";

	ChartJS.register(Title, Tooltip, Legend, LineElement, LinearScale, CategoryScale, PointElement);

	export default {
		components: { LineChartGenerator },
		data() {
			return {
				// chart config
				width: 400,
				height: 250,
				cssClasses: "",
				styles: {},
				plugins: [],
				chartOptions: {
					responsive: true,
					maintainAspectRatio: false,
					scales: {
						x: {
							ticks: {
								// For a category axis, the val is the index so the lookup via getLabelForValue is needed
								callback: function (val, index) {
									// Hide every 2nd tick label
									return index % 2 === 0 ? this.getLabelForValue(val) : "";
								}
							}
						},
						y: {
							ticks: {
								callback: function (val, index) {
									// Hide every 2nd tick label
									return index % 2 === 0 ? this.getLabelForValue(val) + "만" : "";
								}
							}
						}
					}
				},

				// chart data
				chartData: {
					labels: [], // 거래 minDate부터 maxDate까지 월 단위
					datasets: [
						{
							label: "거래 금액",
							borderColor: "rgb(255, 99, 132)",
							backgroundColor: "rgba(255, 99, 132, 0.5)", // point color
							data: []
						}
					]
				}
			};
		},
		computed: {
			...mapGetters({
				houseDealList: "getDealList"
			})
		},
		watch: {
			houseDealList() {
				this.makeLabels();
				this.makeDatasets();
			}
		},
		methods: {
			makeLabels() {
				let labels = [];
				this.houseDealList.forEach((el) => {
					labels.push(`${el.dealYear}.${el.dealMonth}`);
				});
				this.chartData.labels = labels;
			},
			makeDatasets() {
				let data = [];
				this.houseDealList.forEach((el) => {
					data.push(el.dealAmount);
				});
				this.chartData.datasets[0].data = data;
			}
		}
	};
</script>

<style lang="scss" scoped>
	.house-review-title {
		// background-color: lightgray;
		color: $default;
		border-radius: 3px;
	}
</style>
