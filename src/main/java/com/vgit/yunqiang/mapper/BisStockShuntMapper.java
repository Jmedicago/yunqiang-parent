package com.vgit.yunqiang.mapper;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.pojo.BisStockShunt;
import org.apache.ibatis.annotations.Param;

public interface BisStockShuntMapper extends BaseMapper<BisStockShunt> {

    /**
     * 查询仓库库存数量
     *
     * @param stockId
     * @param skuId
     * @return
     */
    BisStockShunt getAmountBy(@Param("stockId") Long stockId, @Param("skuId") Long skuId);
}
