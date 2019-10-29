package com.vgit.yunqiang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.pojo.BisOrderDetail;

public interface BisOrderDetailMapper extends BaseMapper<BisOrderDetail> {

	/**
	 * 查询订单明细
	 * 
	 * @param orderId
	 * @return
	 */
	List<BisOrderDetail> getOrderDetail(@Param("orderId") Long orderId);

	/**
	 * 修改订单数量
	 *
	 * @param orderId
	 * @param number
	 */
    void changeNumber(@Param("orderId") Long orderId, @Param("number") Integer number);
}
