<template>
	<div>
		<hero></hero>
		<basic-elements></basic-elements>
		<inputs></inputs>
		<custom-controls></custom-controls>
		<navigation></navigation>
		<javascript-components></javascript-components>
		<icons></icons>
		<examples></examples>
		<download-section></download-section>
		<carousel></carousel>
	</div>
</template>
<script>
	import Hero from "./components/Hero";

	export default {
		name: "components",
		components: {
			Hero
		}
	};
</script>
