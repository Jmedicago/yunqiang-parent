package com.vgit.yunqiang.service;

import java.util.List;

import com.vgit.yunqiang.common.service.BaseService;
import com.vgit.yunqiang.common.utils.Page;
import com.vgit.yunqiang.pojo.BisOrderDetail;

public interface BisOrderDetailService extends BaseService<BisOrderDetail> {

	/**
	 * 查询订单明细
	 * 
	 * @param sn
	 * @return
	 */
	Page<BisOrderDetail> getOrderDetail(String sn);
}
