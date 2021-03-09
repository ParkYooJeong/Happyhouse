import Vue from 'vue';
import Vuex from 'vuex';
import axios from '../axios/axios-common.js';
import router from '../routes/routes.js';
import Card from 'src/components/Cards/Card.vue'
Vue.use(Vuex); // App 전역에서 Vuex 사용 가능하도록 Vue 객체에 등록 (this.$store로 외부에서 접근)

const SERVER_URL = process.env.VUE_APP_SERVER_URL;
// Store 생성
const store = new Vuex.Store({
  state: {
    sido: {},
    gugun: {},
    dong: {},
    apt: [],
    deal: {},
    memberlist: [],
     accessToken: null,
    userId: "",
    userName: "",    
    member: null,
    boardlist: [],
    b: {},
    oneApt: {},
    newsList: [],
    notifications: {
      topCenter: false
    },
    dealList: {}
  },
  //단순히 값을 가져 가는 것이라서  component안의 computed 안에서 사용
  getters: {
    getSido(state) {
      return state.sido;
    },
    getGugun(state) {
      return state.gugun;
    },
    getDong(state) {
      return state.dong;
    },
    getApt(state) {
      return state.apt;
    },
    getDeal(state) {
      return state.deal;
    },
    getMemberList(state) {
      return state.memberlist;
    },
    getMemberInfo(state) {
      return state.member;
    },
      getAccessToken(state) {
      return state.accessToken;
    },
    getUserId(state) {
      return state.userId;
    },
    getUserName(state) {
      return state.userName;
    },
    getBoardList(state) {
      return state.boardlist;
    },
    getBoard(state) {
      return state.b;
    },
    getOneApt(state) {
      return state.oneApt;
    },
    getNewsList(state) {
      return state.newsList;
    },
    getDealList(state) {
      return state.dealList;
    },

  },
  actions: {
    selectSido: (store) => {
      axios
        .get('/map/sido')

        .then((response) => {
          store.commit('selectSido', {
            sido: response.data,
          });
          console.log('SUCCESS : ' + response.data.length);
        })
        .catch((response, error) => {
          console.log('FAIL : ' + response.status);
          console.log(error);
        });
    },
    selectGugun: (store, payload) => {
      axios
        .get('/map/gugun/' + payload)
        .then((response) => {
          store.commit('selectGugun', {
            gugun: response.data,
          });
          console.log('SUCCESS : ' + response.data.length);
        })
        .catch((response, error) => {
          console.log('FAIL : ' + response.status);
          console.log(error);
        });
    },
    selectDong: (store, payload) => {
      axios
        .get('/map/dong/' + payload)

        .then((response) => {
          store.commit('selectDong', {
            dong: response.data,
          });
          console.log('SUCCESS : ' + response.data.length);
        })
        .catch((response, error) => {
          console.log('FAIL : ' + response.status);
          console.log(error);
        });
    },
    selectApt: (store, payload) => {
      axios
        .get('/map/apt/' + payload)

        .then((response) => {
          store.commit('selectApt', {
            apt: response.data,
          });

          console.log('SUCCESS : ' + response.data.length);
        })
        .catch((response, error) => {
          console.log('FAIL : ' + response.status);
          console.log(error);
        });
    },
    searchDealDong: (store, payload) => {
      console.log('searchDealDong===========');
      if (typeof payload !== "undefined") {
        axios
          .get('/map/deal_dong/' + payload.dong+'&'+payload.aptName)
          .then((response) => {
            console.log(response.data);
            store.commit('selectDeal', {
              deal: response.data,
            });
            console.log('SUCCESS : ' + response.data.length);
          })
          .catch((response, error) => {
            console.log('FAIL : ' + response.status);
            console.log(error);
          });
      }
    },
    searchDealApt: (store, payload) => {
      console.log('/map/deal_apt/' + payload.dong + '/' + payload.aptName);
      axios        
        .get('/map/deal_apt/' + payload.dong + '/' + payload.aptName)

        .then((response) => {
          store.commit('selectDeal', {
            deal: response.data,
          });
          console.log('SUCCESS : ' + response.data.length);
        })
        .catch((response, error) => {
          console.log('FAIL : ' + response.status);
          console.log(error);
        });
    },
    searchOneApt: (store, payload) => {
      console.log('/map/aptDetail/' + payload);
      axios
        .get('/map/aptDetail/' + payload)

        .then((response) => {
          store.commit('searchOneApt', {
            oneApt: response.data,
          });
          console.log('SUCCESS : ' + response.data.length);
        })
        .catch((response, error) => {
          console.log('FAIL : ' + response.status);
          console.log(error);
        });
    },
    selectAll: (store) => {
      console.log('Store actions - selectAll');
      axios
        .get('/member/management')
        .then((response) => {
          store.commit('selectAll', {
            memberlist: response.data,
          });
          console.log('SUCCESS : ' + response.data.length);
        })
        .catch((response) => {
          console.log('FAIL : ' + response.status);
        });
    },
    delete: (store, payload) => {
      console.log('Store actions - delete');
      axios
        .delete('/member/delete/' + payload)
        .then((response) => {
          store.dispatch('selectAll');
          console.log('SUCCESS : ' + response.data);
          
        })
        .catch((response) => {
          console.log('FAIL : ' + response.status);
        });
    },
    searchinfo: (store, payload) => {
      console.log('Store actions - search');
      console.log(payload);
      axios
        .get('/member/search/' + payload.option + '&&' + payload.content)
        .then((response) => {
          store.commit('selectAll', {
            memberlist: response.data,
          });
          console.log('SUCCESS : ' + response.data);
        })
        .catch((response) => {
          console.log('FAIL : ' + response.status);
        });
    },

    selectOne: (store, payload) => {
      console.log('Store actions - selectOne');
      store.commit('selectOne', {
        member: payload,
      });
    },
    update: (store, payload) => {
      console.log('Store actions - update');
      console.log(payload);
      axios
        .put('/member/update',payload)
        .then((response) => {
          store.dispatch('selectAll');
          console.log('SUCCESS : ' + response.data);
        })
        .catch((response) => {
          console.log('FAIL : ' + response.status);
        });
    },
    insert: (store, payload) => {
      console.log('Store actions - insert');
      console.log(payload);
      axios
        .put('/member/signup',payload)
        .then(() => {
          router.push("/");    
          console.log('SUCCESS : insert');
          window.alert('회원가입 완료');
        })
        .catch(() => {
          console.log('FAIL : ');
          window.alert('아이디가 중복됩니다.');
        })
        
    },
    login: (store,user) => {
      return axios
        .post(`${SERVER_URL}/member/login`, user)
        .then((response) => {
          localStorage.setItem('auth-token', response.data["auth-token"]);
          store.dispatch('getMemberInfo');
          const token = localStorage.getItem("auth-token");
          if (token == "undefined") {
              window.alert('아이디와 비밀번호를 확인해주세요');
            // swal("Something went wrong!");
            // this.notifications.notify({
            //   message: `아이디와 비밀번호를 확인해주세요`,
            //   icon: "exclamation-triangle",
            //   horizontalAlign: "center",
            //   verticalAlign: "top",
            //   type: "danger"
            // });
          } 
          axios.defaults.headers.common[
            "auth-token"
          ] = `${response.data["auth-token"]}`;
        })   
     
    },
   
      logout(context) {
      context.commit("LOGOUT");
      axios.defaults.headers.common["auth-token"] = undefined;
      localStorage.removeItem("auth-token");
    },
    getMemberInfo(context) {
      const token = localStorage.getItem("auth-token");
      console.log("getinfo "+token)
      if (token!=="undefined"&&token!==null){
        console.log(typeof token);
        let config = {
          headers: {
            "auth-token": token
          }
        }
        axios.get("/member/info", config)
          .then(response => {
            context.commit("LOGIN2", response.data.user);
            console.log("success getinfo");
            router.push("/");   
          })
          .catch((response) => {
            console.log('FAIL getinfo : ' + response.status);
            console.log(token + "token name");
            localStorage.removeItem("auth-token");
            accessToken = null;
          })
      } 
    },
    notifyVue() {
      // const color = Math.floor(Math.random() * 4 + 1);
      this.$notifications.notify({
        message: `아이디와 비밀번호를 확인해주세요`,
        icon: "exclamation-triangle",
        horizontalAlign: "center",
        verticalAlign: "top",
        type: "danger"
      });
    },
    selectAllB: (store) => {
      console.log('Store actions - selectAllB');
      axios
        .get('/board')
        .then((response) => {
          store.commit('selectB', {
            boardlist: response.data,
          });
          console.log('SUCCESS : ' + response.data.length);
        })
        .catch((response) => {
          console.log('FAIL : ' + response.status);
        });
    },
    deleteB: (store, payload) => {
      console.log('Store actions - delete');
      axios
        .delete('/board/delete/' + payload)
        .then((response) => {
          store.dispatch('selectAllB');
          console.log('SUCCESS : ' + response.data);
        })
        .catch((response) => {
          console.log('FAIL : ' + response.status);
        });
    },
    searchB: (store, payload) => {
      console.log('Store actions - search');
      console.log(payload);
      axios
        .get('/board/search/' + payload)
        .then((response) => {
          store.commit('selectB', {
            boardlist: response.data,
          });
          console.log('SUCCESS : ' + response.data);
        })
        .catch((response) => {
          console.log('FAIL : ' + response.status);
        });
    },
    searchTitleB: (store, payload) => {
      console.log('Store actions - search');
      console.log(payload);
      axios
        .get('/board/searchTitle/' + payload)
        .then((response) => {
          store.commit('selectB', {
            boardlist: response.data,
          });
          console.log('SUCCESS : ' + response.data);
        })
        .catch((response) => {
          console.log('FAIL : ' + response.status);
        });
    },

    insertB: (state, payload) => {
      console.log(state);
      axios
        .post('/board', payload)
        .then((response) => {
          console.log('SUCCESS : ' + response.data);
        })
        .catch((response) => {
          console.log('FAIL : ' + response.status);
        });
    },
    selectOneB: (store, payload) => {
      console.log('Store actions - search');
      console.log(payload);
      axios
        .get('/board/selectOne/' + payload)
        .then((response) => {
          store.commit('selectOneB', {
            b: response.data,
          });
          console.log('SUCCESS : ' + response.data);
        })
        .catch((response) => {
          console.log('FAIL : ' + response.status);
        });
    },
    updateB: (state, payload) => {
      console.log(state + '  ' + payload);
      axios
        .put('/board', payload)
        .then((response) => {
          console.log('SUCCESS : ' + response.data);
        })
        .catch((response) => {
          console.log('FAIL : ' + response.status);
        });
    },
    selectNews: (store) => {
      axios
        .get('/news')
        .then((response) => {
          store.commit('selectNews', {
            newsList: response.data,
          });
          console.log('SUCCESS : ' + response.data.length);
        })
        .catch((response) => {
          console.log('FAIL : ' + response.status);
        });
    },
    // 아파트클릭시 매물 리스트받아오기
    selectDealList: (store,payload) => {
      axios
        .get('/map/getInfo/'+payload)
        .then((response) => {
          store.commit('selectDealList', {
            dealList: response.data,
          });
          console.log('SUCCESS : ' + response.data.length);
        })
        .catch((response) => {
          console.log('FAIL : ' + response.status);
        });
    },
    //드래그이벤트시 지도 범위 매물가져옴
    mapbounds: (store, payload) => {
      console.log(payload);
      axios
        .post('/map/getmap',payload)
        .then((response) => {
          store.commit('selectApt', {
            apt: response.data,
          });
          console.log('SUCCESS : ' + response.data.length);
        })
        .catch((response) => {
          console.log('FAIL : ' + response.status);
        });
    },
  },
  
  mutations: {
    selectSido: function(state, payload) {
      state.sido = payload.sido;
    },
    selectGugun: function(state, payload) {
      state.gugun = payload.gugun;
    },
    selectDong: function(state, payload) {
      state.dong = payload.dong;
    },
    selectApt: function(state, payload) {
      state.apt = payload.apt;
    },
    selectDeal: function(state, payload) {
      state.deal = payload.deal;
    },
    selectAll: function(state, payload) {
      state.memberlist = payload.memberlist;
      console.log('Mutations selectAll : ' + payload.memberlist.length);
    },
    selectOne: function(state, payload) {
      state.member = payload.member;
      //   console.log("Mutations selectAll : " + payload.member.length);
    },
    selectB: function(state, payload) {
      state.boardlist = payload.boardlist;
    },
    selectOneB: function(state, payload) {
      state.b = payload.b;
    },
    searchOneApt: function(state, payload) {
      state.oneApt = payload.oneApt;
    },
    selectNews: function(state, payload) {
      state.newsList = payload.newsList;
    },
    init: function (state) {
      state.loginflag = false;
      state.id = null;
    },
    LOGIN(state, payload) {
      console.log(payload);
      state.accessToken = payload["auth-token"];
      state.userId = payload["user-id"];
      state.userName = payload["user-name"];
    },
    LOGIN2(state, payload) {//새로고침용
      console.log(payload);
      state.accessToken = localStorage.getItem("auth-token");
      state.userId = payload["id"];
      state.userName = payload["name"];
    },
    LOGOUT(state) {
      state.accessToken = null;
      state.userId = "";
      state.userName = "";
    },
    selectDealList(state, payload) {
      state.dealList = payload.dealList;
    },
  },
});

export default store;
