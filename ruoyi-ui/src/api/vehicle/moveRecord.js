import request from '@/utils/request'

// 查询车辆回车或出勤列表
export function listMoveRecord(query) {
  return request({
    url: '/vehicle/moveRecord/list',
    method: 'get',
    params: query
  })
}

// 查询车辆回车或出勤详细
export function getMoveRecord(id) {
  return request({
    url: '/vehicle/moveRecord/' + id,
    method: 'get'
  })
}

// 新增车辆回车或出勤
export function addMoveRecord(data) {
  return request({
    url: '/vehicle/moveRecord',
    method: 'post',
    data: data
  })
}

// 修改车辆回车或出勤
export function updateMoveRecord(data) {
  return request({
    url: '/vehicle/moveRecord',
    method: 'put',
    data: data
  })
}

// 删除车辆回车或出勤
export function delMoveRecord(id) {
  return request({
    url: '/vehicle/moveRecord/' + id,
    method: 'delete'
  })
}
