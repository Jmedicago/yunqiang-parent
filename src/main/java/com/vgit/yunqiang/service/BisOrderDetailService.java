package com.vgit.yunqiang.service;

import com.vgit.yunqiang.common.exception.BisException;
import com.vgit.yunqiang.common.service.BaseService;
import com.vgit.yunqiang.common.utils.Page;
import com.vgit.yunqiang.pojo.BisOrder;
import com.vgit.yunqiang.pojo.BisOrderDetail;

public interface BisOrderDetailService extends BaseService<BisOrderDetail> {

	/**
	 * 查询订单明细
	 * 
	 * @param orderId
	 * @return
	 */
	Page<BisOrderDetail> getOrderDetail(Long orderId);

	/**
	 * 编辑订单明细
	 *
	 * @param orderDetail
	 * @return
	 */
	BisOrder updateOrderDetail(BisOrderDetail orderDetail) throws BisException;
}
