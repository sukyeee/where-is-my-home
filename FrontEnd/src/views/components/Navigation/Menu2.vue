<template>
  <ul class="navbar-nav ml-lg-auto">
    <li class="nav-item">
      <a class="nav-link" href="#">
        Home
        <span class="sr-only">(current)</span>
      </a>
    </li>

    <li class="nav-item">
      <a class="nav-link" href="#">내 집 찾기</a>
    </li>

    <li class="nav-item">
      <a class="nav-link" href="#">회원가입</a>
    </li>

    <li class="nav-item dropdown">
      <a
        class="nav-link"
        href="#"
        id="navbar-primary_dropdown_1"
        role="button"
        data-toggle="dropdown"
        aria-haspopup="true"
        aria-expanded="false">
        Settings
      </a>
      <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbar-primary_dropdown_1">
        <a class="dropdown-item" href="#">Action</a>
        <a class="dropdown-item" href="#">Another action</a>
        <div class="dropdown-divider"></div>
        <a class="dropdown-item" href="#">Something else here</a>
      </div>
    </li>
  </ul>
</template>

<script>
export default {};
</script>
