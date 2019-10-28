package com.vgit.yunqiang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.pojo.BisOrderDetail;

public interface BisOrderDetailMapper extends BaseMapper<BisOrderDetail> {

	/**
	 * 查询订单明细
	 * 
	 * @param sn
	 * @return
	 */
	List<BisOrderDetail> getOrderDetail(@Param("sn") String sn);
}
