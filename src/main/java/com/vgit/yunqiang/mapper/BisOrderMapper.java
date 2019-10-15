package com.vgit.yunqiang.mapper;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.pojo.BisOrder;

public interface BisOrderMapper extends BaseMapper<BisOrder> {

    /**
     * 通过订单id获取订单
     *
     * @param id
     * @return
     */
    BisOrder getById(Long id);

}
