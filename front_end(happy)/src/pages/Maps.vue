<template>
  <div style="position:relative">
    <div style="display:inline; ">
      <center>
        년도 :
        <b-form-select style="width:5%" v-model="selectedYear">
          <!-- @change="selectGugun(selectedSido)" -->
          <option value="2020" selected></option>
          <option v-for="(year, index) in year" :key="index" :value="year">{{
            year
          }}</option>
        </b-form-select>
        월 :
        <b-form-select style="width:5%" v-model="selectedMonth">
          <!-- @change="selectGugun(selectedSido)" -->
          <option value="1" selected></option>
          <option v-for="(month, index) in month" :key="index" :value="month">{{
            month
          }}</option>
        </b-form-select>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        시도 :
        <b-form-select
          style="width:10%"
          id="sido"
          v-model="selectedSido"
          @change="selectGugun(selectedSido)"
        >
          <option value="0" selected>선택</option>
          <option
            v-for="(sido, index) in getSido"
            :key="index"
            :value="sido.sidoCode"
            >{{ sido.sidoName }}</option
          >
        </b-form-select>
        구군 :
        <b-form-select
          id="gugun"
          style="width:10%"
          v-model="selectedGugun"
          @change="selectDong(selectedGugun)"
        >
          <option value="0" selected>선택</option>
          <option
            v-for="(gugun, index) in getGugun"
            :key="index"
            :value="gugun.gugunCode"
            >{{ gugun.gugunName }}</option
          >
        </b-form-select>
        읍면동 :
        <b-form-select
          id="dong"
          style="width:10%"
          v-model="selectedDong"
          @change="selectOption()"
        >
          <option value="0">선택</option>
          <option
            v-for="(dong, index) in getDong"
            :key="index"
            :value="dong.dong"
            >{{ dong.dong }}</option
          >
        </b-form-select>
      </center>
    </div>
    <!-- 카카오지도 -->
    <div id="map" style="width:100%;height:100%;"></div>
    <!-- 사이드바 여는 아이콘 -->
    <div>
      <b-icon
        v-b-toggle.sidebar-right
        icon="chevron-double-left"
        animation="cylon"
        font-scale="4"
        style="position: absolute; top: 48% ;left:93%; z-index: 2"
      ></b-icon>
      <!-- <b-button v-b-toggle.sidebar-backdrop>Toggle Sidebar</b-button> -->
      <!-- 사이드바 -->
      <b-sidebar id="sidebar-right" right shadow>
        <div class="col-15">
          <card class="card-plain">
            <div class="table-responsive">
              <b-table
                striped
                hover
                sticky-header="1500px"
                :items="getApt"
                :fields="tableColumns"
                @row-clicked="item => this.popup(item)"
              >
              </b-table>
            </div>
          </card>
        </div>
      </b-sidebar>
    </div>

    <!-- 모달 -->
    <b-modal id="info" hide-footer>
      <card>
        <br />
        <center>
          <h4 slot="header" class="card-title">apt Info</h4>
        </center>
        <br />
        <form>
          <div class="row">
            <div class="col-md-6">
              <base-input
                type="text"
                label="지역"
                v-model="aptinfo.dong"
                readonly
                style="background-color='black';"
              >
              </base-input>
            </div>
            <div class="col-md-6">
              <base-input
                type="text"
                label="Name"
                v-model="aptinfo.aptName"
                readonly
              >
              </base-input>
            </div>
          </div>

          <div class="row">
            <div class="col-md-6">
              <base-input
                type="text"
                label="지번"
                v-model="aptinfo.jibun"
                readonly
              >
              </base-input>
            </div>
            <div class="col-md-6">
              <base-input
                type="text"
                label="건축년도"
                v-model="aptinfo.buildYear"
                readonly
              >
              </base-input>
            </div>
          </div>

          <!-- <div class="clearfix"></div> -->
          <b-table
            class="table-hover table-striped"
            :sticky-header="true"
            :fields="tableColumns2"
            :items="getDeal"
          >
            <!-- @row-clicked="item => pop(item)" -->
          </b-table>
        </form>
      </card>
      <b-button class="mt-2" block @click="close">Close Me</b-button>
    </b-modal>
  </div>
</template>
<script>
import Vue from "vue";
import { mapGetters, mapActions, mapState } from "vuex";
import LTable from "src/components/Table.vue";
import Card from "src/components/Cards/Card.vue";
export default {
  components: {
    LTable,
    Card
  },
  data() {
    return {
      selectedSido: "",
      selectedGugun: "",
      selectedDong: "",
      apts: [],
      map: "",
      markers: [],
      key: "",
      pop: false,
      tableColumns: ["dong", "aptName"],
      list: [],
      aptinfo: "",
      tableColumns2: [
        "dealYear",
        "dealMonth",
        "dealDay",
        "floor",
        "dealAmount"
      ],
      year: [2020, 2019, 2018, 2017, 2016, 2015],
      month: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12],
      selectedYear: "",
      selectedMonth: ""
    };
  },
  created() {
    this.selectSido();
  },
  computed: {
    ...mapGetters([
      "getSido",
      "getGugun",
      "getDong",
      "getApt",
      "getDeal",
      "getDealList",
      "getDealList"
    ]),
    ...mapState(["apt", "dealList"])
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");

      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=8b04fa303e6950b79bed372fba500c8e&libraries=services";
      document.head.appendChild(script);
    }
  },
  watch: {
    apt(newValue) {
      if (newValue.length == 0) return;
      for (var mar in this.markers) {
        //   console.log(this.markers[mar]);
        this.markers[mar].setMap(null);
      }
      this.markers = [];
      this.apts = newValue;
      console.log(this.apts[0].dong);

      var num = this.apts.length;
      var sumLat = 0,
        sumLng = 0;

      for (let item in this.apts) {
        var check = 0;

        // for (var li in this.list) {
        //   if (li.aptName == item.aptName) {
        //     check = 1;
        //     break;
        //   }
        // }
        // if (check == 1) {
        sumLat += Number(this.apts[item].lat);
        sumLng += Number(this.apts[item].lng);
        var markerPosition = new kakao.maps.LatLng(
          this.apts[item].lat,
          this.apts[item].lng
        );
        var marker = new kakao.maps.Marker({
          map: this.map,
          position: markerPosition
        });
        this.markers.push(marker);
        var content =
          '<div class="wrap">' +
          '    <div class="info"  aria-controls="sidebar-right" aria-expanded="false">' +
          '        <div class="title">' +
          this.apt[item].aptName +
          "        </div>" +
          '        <div class="body">' +
          '            <div class="desc">' +
          '                <div class="ellipsis" >' +
          this.apt[item].dong +
          " " +
          this.apt[item].jibun +
          "</div>" +
          "            </div>" +
          "        </div>" +
          "    </div>" +
          "</div>";

        var start = document.createElement("div");
        start.className = "wrap";

        var info = document.createElement("div");
        info.className = "info";

        var title = document.createElement("div");
        title.className = "title";
        title.innerText = this.apt[item].aptName;

        var body = document.createElement("div");
        body.className = "body";
        var desc = document.createElement("div");
        desc.className = "dsec";
        var ellipsis = document.createElement("div");
        ellipsis.className = "ellipsis";
        ellipsis.innerText = this.apt[item].dong + " " + this.apt[item].jibun;

        desc.appendChild(ellipsis);
        body.appendChild(desc);
        info.appendChild(title);
        info.appendChild(body);

        start.appendChild(info);
        start.addEventListener("click", event => this.popup(this.apt[item]));
        // 커스텀 오버레이가 표시될 위치입니다
        var position = new kakao.maps.LatLng(
          this.apts[item].lat,
          this.apts[item].lng
        );

        // 커스텀 오버레이를 생성합니다
        var customOverlay = new kakao.maps.CustomOverlay({
          map: this.map,
          position: position,
          content: start,
          yAnchor: 1,
          clickable: true
        });

        marker.setMap(this.map);
        // }
      }
      if (this.select) {
        var moveLatLon = new kakao.maps.LatLng(sumLat / num, sumLng / num);
        this.map.setCenter(moveLatLon);
        this.select = false;
        this.map.setLevel(4);
      }
    }
  },
  methods: {
    ...mapActions([
      "selectSido",
      "selectGugun",
      "selectDong",
      "selectApt",
      "searchDealDong",
      "searchDealApt",
      "selectDealList",
      "mapbounds"
    ]),

    initMap() {
      var container = document.getElementById("map");
      var options = {
        center: new kakao.maps.LatLng(37.500976, 127.03684),
        level: 3
      };

      var map = new kakao.maps.Map(container, options); //지도 생성

      // 지도 중심좌표를 얻어옵니다
      var latlng = map.getCenter();

      //지도 드래그 이벤트
      this.map = map;
      kakao.maps.event.addListener(this.map, "dragend", this.dragEvent);
    },
    dragEvent() {
      var bounds = this.map.getBounds();
      var swLatLng = bounds.getSouthWest().toString();

      // 영역의 북동쪽 좌표를 얻어옵니다*(북,동)
      var neLatLng = bounds.getNorthEast().toString();
      // 영역의 남서쪽 좌표를 얻어옵니다(남,서)
      //  console.log(swLatLng + "남서" + neLatLng + "북동");

      //범위 남<x<북 서<Y<동에 있는 것 불러오기.
      this.mapbounds(swLatLng + neLatLng);
    },
    findDeal(aptname) {
      this.selectDealList(aptname);
    },
    popup(item) {
      this.aptinfo = item;
      console.log(item);
      this.list = this.searchDealDong(item); //해당 동의 매물리스트 받아오기
      for (var li in this.list) {
        if (li.aptName == item.aptName) {
          console.log(li);
          console.log("찾음");
        }
      }
      this.$bvModal.show("info");
    },
    close() {
      this.$bvModal.hide("info");
    },
    test() {
      console.log("test");
    },
    selectOption() {
      this.select = true;
      this.selectApt(this.selectedDong);
    }
  }
};
</script>
<style>
#map {
  min-height: calc(100vh - 123px);
  position: relative;
  z-index: 1;
}
.wrap {
  position: absolute;
  left: 0;
  bottom: 40px;
  width: 170px;
  height: 70px;
  margin-left: -85px;
  text-align: center;
  overflow: hidden;
  font-size: 13px;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
  line-height: 1.5;
}
.wrap * {
  padding: 0;
  margin: 0;
}
.wrap .info {
  width: 170px;
  height: 60px;
  border-radius: 5px;
  border-bottom: 2px solid #ccc;
  border-right: 1px solid #ccc;
  overflow: hidden;
  background: #fff;
}
.wrap .info:nth-child(1) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.info .title {
  padding: 5px 0 0 10px;
  height: 30px;
  background: #eee;
  border-bottom: 1px solid #ddd;
  font-size: 15px;
  font-weight: bold;
}
.info .close {
  position: absolute;
  top: 10px;
  right: 10px;
  color: #888;
  width: 17px;
  height: 17px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png");
}
.info .close:hover {
  cursor: pointer;
}
.info .body {
  position: relative;
  overflow: hidden;
}
.info .desc {
  position: relative;
  margin: 5px 0 0 0;
  text-align: center;
  height: 75px;
}
.desc .ellipsis {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.desc .jibun {
  font-size: 11px;
  color: #888;
  margin-top: -2px;
}
.info .img {
  position: absolute;
  top: 6px;
  left: 5px;
  width: 73px;
  height: 71px;
  border: 1px solid #ddd;
  color: #888;
  overflow: hidden;
}
.info:after {
  content: "";
  position: absolute;
  margin-left: -12px;
  left: 50%;
  bottom: 0;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
.info .link {
  color: #5085bb;
}
</style>
