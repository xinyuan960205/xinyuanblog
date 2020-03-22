// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import VueCookie from 'vue-cookie'
import './plugins/element.js'
import httpRequest from '@/utils/httpRequest' // api: https://github.com/axios/axios
import cloneDeep from 'lodash/cloneDeep'
import '@/icons'
import '@/assets/scss/index.scss'
import { isAuth } from '@/utils'
import { getSysParam } from '@/utils'
import { getSysParamArr } from '@/utils'

Vue.use(VueCookie)
Vue.config.productionTip = false

// 挂载全局
Vue.prototype.$http = httpRequest // 全局注册，使用方法为:this.$http
Vue.prototype.isAuth = isAuth // 权限方法
Vue.prototype.getSysParam = getSysParam // 获取参数
Vue.prototype.getSysParamArr = getSysParamArr // 获取参数列表

// 保存整站vuex本地储存初始状态
window.SITE_CONFIG['storeState'] = cloneDeep(store.state)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
