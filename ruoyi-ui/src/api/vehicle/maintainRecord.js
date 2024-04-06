import request from '@/utils/request'

// 查询车辆保养记录列表
export function listMaintainRecord(query) {
  return request({
    url: '/vehicle/maintainRecord/list',
    method: 'get',
    params: query
  })
}

// 查询车辆保养记录详细
export function getMaintainRecord(id) {
  return request({
    url: '/vehicle/maintainRecord/' + id,
    method: 'get'
  })
}

// 新增车辆保养记录
export function addMaintainRecord(data) {
  return request({
    url: '/vehicle/maintainRecord',
    method: 'post',
    data: data
  })
}

// 修改车辆保养记录
export function updateMaintainRecord(data) {
  return request({
    url: '/vehicle/maintainRecord',
    method: 'put',
    data: data
  })
}

// 删除车辆保养记录
export function delMaintainRecord(id) {
  return request({
    url: '/vehicle/maintainRecord/' + id,
    method: 'delete'
  })
}
