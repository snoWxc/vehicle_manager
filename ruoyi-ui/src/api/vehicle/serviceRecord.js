import request from '@/utils/request'

// 查询车辆维修记录列表
export function listServiceRecord(query) {
  return request({
    url: '/vehicle/serviceRecord/list',
    method: 'get',
    params: query
  })
}

// 查询车辆维修记录详细
export function getServiceRecord(id) {
  return request({
    url: '/vehicle/serviceRecord/' + id,
    method: 'get'
  })
}

// 新增车辆维修记录
export function addServiceRecord(data) {
  return request({
    url: '/vehicle/serviceRecord',
    method: 'post',
    data: data
  })
}

// 修改车辆维修记录
export function updateServiceRecord(data) {
  return request({
    url: '/vehicle/serviceRecord',
    method: 'put',
    data: data
  })
}

// 删除车辆维修记录
export function delServiceRecord(id) {
  return request({
    url: '/vehicle/serviceRecord/' + id,
    method: 'delete'
  })
}
