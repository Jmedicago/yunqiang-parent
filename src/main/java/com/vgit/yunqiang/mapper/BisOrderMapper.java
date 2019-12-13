package com.vgit.yunqiang.mapper;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.pojo.BisOrder;
import org.apache.ibatis.annotations.Param;

public interface BisOrderMapper extends BaseMapper<BisOrder> {

    /**
     * 通过订单id获取订单
     *
     * @param id
     * @return
     */
    BisOrder getById(Long id);

    /**
     * 是否存在未评价订单
     *
     * @param id
     * @return
     */
    int hasNotComment(@Param("orderId") Long id);

    void updateState(@Param("id") Long id, @Param("status") Integer status);
}
