/*!

 =========================================================
 * Vue Light Bootstrap Dashboard - v2.0.0 (Bootstrap 4)
 =========================================================

 * Product Page: http://www.creative-tim.com/product/light-bootstrap-dashboard
 * Copyright 2019 Creative Tim (http://www.creative-tim.com)
 * Licensed under MIT (https://github.com/creativetimofficial/light-bootstrap-dashboard/blob/master/LICENSE.md)

 =========================================================

 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

 */
import Vue from 'vue'
import VueRouter from 'vue-router'
import App from './App.vue'
import store from './store/store.js'
// LightBootstrap plugin
import LightBootstrap from './light-bootstrap-main'
import NowUlKit from './plugins/now-ui-kit.js'

// router setup
import router from './routes/routes.js'

import './registerServiceWorker'
// plugin setup
Vue.use(VueRouter)
Vue.use(LightBootstrap)
Vue.use(NowUlKit)

Vue.config.productionTip = false

// configure router


/* eslint-disable no-new */
new Vue({
  render: h => h(App),
  store,
  router,
  beforeCreate() {
    this.$store.dispatch("getMemberInfo")
  },
}).$mount('#app')
