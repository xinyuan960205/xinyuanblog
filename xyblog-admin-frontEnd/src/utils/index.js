import Vue from 'vue'
import router from '@/router'
import store from '@/store'
/**
 * 获取uuid
 * @returns {string}
 */
export function getUUID () {
  return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, c => {
    return (c === 'x' ? (Math.random() * 16 | 0) : ('r&0x3' | '0x8')).toString(16)
  })
}

/**
 * @Description: 是否有权限
 * @author Bobbi
 * @date 18-9-28
 */
export function isAuth (key) {
  return JSON.parse(sessionStorage.getItem('permissions') || '[]').indexOf(key) !== -1 || false
}
/**
 * 树形数据转换
 * @param {*} data
 * @param {*} id
 * @param {*} pid
 */
export function treeDataTranslate (data, id = 'id', pid = 'parentId') {
  var res = []
  var temp = {}
  for (var i = 0; i < data.length; i++) {
    temp[data[i][id]] = data[i]
  }
  for (var k = 0; k < data.length; k++) {
    if (temp[data[k][pid]] && data[k][id] !== data[k][pid]) {
      if (!temp[data[k][pid]]['children']) {
        temp[data[k][pid]]['children'] = []
      }
      if (!temp[data[k][pid]]['_level']) {
        temp[data[k][pid]]['_level'] = 1
      }
      data[k]['_level'] = temp[data[k][pid]]._level + 1
      temp[data[k][pid]]['children'].push(data[k])
    } else {
      res.push(data[k])
    }
  }
  return res
}

/**
 * @Description: 清除登录信息
 * @author Bobbi
 * @date 18-9-28
 */
export function clearLoginInfo () {
  Vue.cookie.delete('token')
  store.commit('resetStore')
  router.options.isAddDynamicMenuRoutes = false
}

/**
 * 根据Type获取参数
 * @param type
 * @param key
 * @returns {*}
 */
export function getSysParam (type, key, list) {
  let sysParamList = list || JSON.parse(localStorage.getItem('sysParamList'))
  for (let i = 0; i < sysParamList.length; i++) {
    let sysParam = sysParamList[i]
    if (sysParam.type === type && sysParam.parKey === (key)) {
      return sysParam.parValue
    }
  }
  return ""
}

/**
 * 根据type获取参数列表
 * @param type
 * @returns {Array}
 */
export function getSysParamArr (type) {
  let sysParamList = JSON.parse(localStorage.getItem('sysParamList'))
  let resultArr = []
  for (let i = 0; i < sysParamList.length; i++) {
    let sysParam = sysParamList[i]
    if (sysParam.type === type) {
      resultArr.push(sysParam)
    }
  }
  return resultArr
}

export function debounce (func, wait, immediate) {
  let timeout, args, context, timestamp, result

  const later = function () {
    // 据上一次触发时间间隔
    const last = +new Date() - timestamp

    // 上次被包装函数被调用时间间隔last小于设定时间间隔wait
    if (last < wait && last > 0) {
      timeout = setTimeout(later, wait - last)
    } else {
      timeout = null
      // 如果设定为immediate===true，因为开始边界已经调用过了此处无需调用
      if (!immediate) {
        result = func.apply(context, args)
        if (!timeout) context = args = null
      }
    }
  }

  return function (...args) {
    context = this
    timestamp = +new Date()
    const callNow = immediate && !timeout
    // 如果延时不存在，重新设定延时
    if (!timeout) timeout = setTimeout(later, wait)
    if (callNow) {
      result = func.apply(context, args)
      context = args = null
    }

    return result
  }
}

/**
 * [Datestr 时间戳转字符串格式]
 */
export function socialDateFormat (date) {
  // 获取js 时间戳
  var time = new Date().getTime()
  // 去掉 js 时间戳后三位
  time = parseInt((time - date) / 1000)
  // 存储转换值
  var s
  if (time < 60 * 10) { // 十分钟内
    return '刚刚'
  } else if ((time < 60 * 60) && (time >= 60 * 10)) {
    // 超过十分钟少于1小时
    s = Math.floor(time / 60)
    return s + '分钟前'
  } else if ((time < 60 * 60 * 24) && (time >= 60 * 60)) {
    // 超过1小时少于24小时
    s = Math.floor(time / 60 / 60)
    return s + '小时前'
  } else if ((time < 60 * 60 * 24 * 3) && (time >= 60 * 60 * 24)) {
    // 超过1天少于3天内
    s = Math.floor(time / 60 / 60 / 24)
    return s + '天前'
  } else {
    // 超过3天
    date = new Date(parseInt(date))
    return date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate()
  }
}

export const mixin = {
  filters: {
    // 用于格式化时间的过滤器
    socialDate: function (formatedDate) {
      return socialDateFormat(formatedDate)
    },
  }
}
