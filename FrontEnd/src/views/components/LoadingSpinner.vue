<template>
  <!-- 스피너 -->
  <!-- <div class="d-flex align-items-center spinner m-0 p-0">
		<strong class="spinner-child">Loading...&nbsp;</strong>
		<div
			class="spinner-grow ms-auto text-warning spinner-child"
			style="margin: 20px;"
			role="status"
			aria-hidden="true"
		></div>
		<div
			class="spinner-grow ms-auto text-warning spinner-child"
			style="margin: 20px;"
			role="status"
			aria-hidden="true"
		></div>
		<div
			class="spinner-grow ms-auto text-warning spinner-child"
			style="margin: 20px;"
			role="status"
			aria-hidden="true"
		></div>
	</div> -->
  <div>
    <h1 class="spinner">
      <div class="spinner-child">Loading...</div>
    </h1>
  </div>
</template>

<script>
export default {};
</script>

<style>
.spinner {
  position: absolute;
  z-index: 100;
  top: 45%;
  left: 45.5%;
}
</style>
