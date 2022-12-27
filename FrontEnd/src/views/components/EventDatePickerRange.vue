<template>
  <div class="date-picker">
    <div class="input-daterange datepicker row align-items-center">
      <div class="col-6 d-flex">
        <!-- 시작일 -->
        <div class="date-picker-container">
          <base-input addon-left-icon="ni ni-calendar-grid-58">
            <flat-picker
              slot-scope="{ focus, blur }"
              @on-open="focus"
              @on-close="blur"
              :config="{ ...commonConfig, ...startConfig }"
              class="form-control datepicker"
              v-model="startDate"
            >
            </flat-picker>
          </base-input>
        </div>
        <div class="date-picker-label mx-auto form-group align-self-center">부터</div>
      </div>
      <div class="col-6 d-flex">
        <!-- 종료일 -->
        <div class="date-picker-container">
          <base-input addon-left-icon="ni ni-calendar-grid-58">
            <flat-picker
              slot-scope="{ focus, blur }"
              @on-open="focus"
              @on-close="blur"
              :config="{ ...commonConfig, ...endConfig }"
              class="form-control datepicker"
              v-model="endDate"
            >
            </flat-picker>
          </base-input>
        </div>
        <div class="date-picker-label mx-auto form-group align-self-center">까지</div>
      </div>
    </div>
  </div>
</template>

<script>
import flatPicker from "vue-flatpickr-component";
import "flatpickr/dist/flatpickr.css";

let $this = null;
export default {
  props: {
    initStartDate: {
      type: String,
      default: null,
    },
    initEndDate: {
      type: String,
      default: null,
    },
  },
  components: {
    flatPicker,
  },
  data() {
    return {
      commonConfig: {
        enableTime: true,
        dateFormat: "Y-m-d H:i:ss",
        time_24hr: true,
      },
      // 이벤트 시작일
      startDate: null,
      startConfig: {},
      // 이벤트 종료일
      endDate: null,
      endConfig: {
        disable: [
          function (date) {
            // 시작일 이전은 선택할 수 없다.
            if ($this.startDate != null) {
              return date.getDate() < new Date($this.startDate).getDate();
            } else {
              return true; // 시작일을 선택하지 않으면 아무것도 선택할 수 없다.
            }
          },
        ],
      },
    };
  },
  watch: {
    initStartDate() {
      this.startDate = this.initStartDate;
    },
    initEndDate() {
      this.endDate = this.initEndDate;
    },
    startDate() {
      this.$emit("select-start-date", this.startDate);
    },
    endDate() {
      this.$emit("select-end-date", this.endDate);
    },
  },
  created() {
    $this = this; // endConfig의 disable function에 사용
  },
};
</script>

<style scoped>
.date-picker >>> .date-picker-label {
  min-width: max-content;
  padding-left: 10px;
}
.date-picker >>> .date-picker-container {
  width: 90%;
}
</style>
