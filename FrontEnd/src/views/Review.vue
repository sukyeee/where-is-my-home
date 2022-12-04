<template>
    <section class="section section-shaped login-section section-lg my-0">
        <div class="shape shape-style-1 bg-gradient-default">
            <!-- <span></span>
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            <span></span> -->
        </div>
        <!-- test -->
        <!-- left Menu -->
        <div class="card" style="width: 25rem;">
            <img src="https://picsum.photos/250/100" class="card-img-top" alt="...">

            <!-- navBar -->
            <base-nav expand effect="dark" type="default" round title="Menu">
                <div class="row" slot="content-header" slot-scope="{closeMenu}">
                    <div class="col-6 collapse-brand">
                        <a href="./index.html">
                            <img src="img/brand/blue.png">
                        </a>
                    </div>
                    <div class="col-6 collapse-close">
                        <close-button target="nav-inner-primary" @click="closeMenu">

                        </close-button>
                    </div>
                </div>

                <ul class="navbar-nav ml-lg-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="#">단지정보
                            <span class="sr-only">(current)</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">시새/실거래가</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link" href="#" id="nav-inner-primary_dropdown_1"
                            role="button" data-toggle="dropdown" aria-haspopup="true"
                            aria-expanded="false">리뷰</a>
                        <div class="dropdown-menu dropdown-menu-right"
                                aria-labelledby="nav-inner-primary_dropdown_1">
                            <a class="dropdown-item" href="#">Action</a>
                            <a class="dropdown-item" href="#">Another action</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#">Something else here</a>
                        </div>
                    </li>
                </ul>
            </base-nav>
            <div class="card-body">
                <div class="card-title">
                    리뷰 점수
                    <span addon-left-icon="ni ni-zoom-split-in"> 
                        ★★★★★
                     </span>
                                
                

                </div>
                
                <div class="row">
                    
                    <div class="h-100 bg-secondary rounded p-4 container-fluid">
                        <div class="d-flex align-items-center justify-content-between mb-2">
                            <h6 class="mb-0">리뷰 목록</h6>
                            <a href="">Show All</a>
                        </div>
                        <div class="d-flex align-items-center border-bottom py-3">
                            <img class="rounded-circle mr-3" src="https://picsum.photos/250/100?1" alt="" style="width: 40px; height: 40px;">
                            <div class="w-100 ms-3">
                                <div class="d-flex w-100 justify-content-between">
                                    <h6 class="mb-0">Jhon Doe</h6>
                                    <small>★★★★★</small>
                                </div>
                                <span>주변에 맛집이 많음</span>
                            </div>
                        </div>
                        <div class="d-flex align-items-center border-bottom py-3">
                            <img class="rounded-circle mr-3" src="https://picsum.photos/250/100?2" alt="" style="width: 40px; height: 40px;">
                            <div class="w-100 ms-3">
                                <div class="d-flex w-100 justify-content-between">
                                    <h6 class="mb-0">Jhon Doe</h6>
                                    <small>★★★★★</small>
                                </div>
                                <span>역이랑 가까워요</span>
                            </div>
                        </div>
                        <div class="d-flex align-items-center border-bottom py-3">
                            <img class="rounded-circle mr-3" src="https://picsum.photos/250/100?3" alt="" style="width: 40px; height: 40px;">
                            <div class="w-100 ms-3">
                                <div class="d-flex w-100 justify-content-between">
                                    <h6 class="mb-0">Jhon Doe</h6>
                                    <small>★★★☆☆</small>
                                </div>
                                <span>별로에요</span>
                            </div>
                        </div>
                        <div class="d-flex align-items-center pt-3">
                            <img class="rounded-circle mr-3" src="https://picsum.photos/250/100?4" alt="" style="width: 40px; height: 40px;">
                            <div class="w-100 ms-3">
                                <div class="d-flex w-100 justify-content-between">
                                    <h6 class="mb-0">Jhon Doe</h6>
                                    <small>★★★★★</small>
                                </div>
                                <span class="review-comment">좋음</span>
                            </div>
                        </div>
                    </div>
                    
                </div>
            </div>

        </div>
      
    </section>
  </template>
  <script>
  import BaseNav from "@/components/BaseNav";

  export default {
    components: {
    BaseNav,
    
  },

  };
  
  </script>
  <style scoped>
  .login-section::before {
    content: "";
    background-image: url("../../public/img/bg3.jpg");
    background-repeat: no-repeat;
    background-size: cover;
    background-position: center;
    opacity: 0.7;
    position: absolute;
    top: 0px;
    left: 0px;
    right: 0px;
    bottom: 0px;
  }
.review-comment{
    width:100%;
}


  </style>
  