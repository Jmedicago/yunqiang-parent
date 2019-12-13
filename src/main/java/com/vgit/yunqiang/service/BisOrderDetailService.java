package com.vgit.yunqiang.service;

import com.vgit.yunqiang.common.exception.BisException;
import com.vgit.yunqiang.common.service.BaseService;
import com.vgit.yunqiang.common.utils.Page;
import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.pojo.BisOrder;
import com.vgit.yunqiang.pojo.BisOrderDetail;

import java.util.List;

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


	/**
	 * 订单类别统计
	 *
	 * @param orderId
	 * @return
	 */
	String digest(Long orderId);

	/**
	 *
	 *
	 * @return
	 */
	BisOrderDetail getOrderDetail(Long orderId, Long skuId);

	/**
	 * 订单明细列表
	 *
	 * @param orderId
	 * @return
	 */
	List<BisOrderDetail> getList(Long orderId);

	BisOrder updateOrderDetail(Integer o, Long id, Long orderId, Integer amount);

	BisOrder delOrderDetail(Integer o, Long id, Long orderId);

	boolean isLocked(Long orderId);
}
