package com.vgit.yunqiang.mapper;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.pojo.BisStockShunt;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BisStockShuntMapper extends BaseMapper<BisStockShunt> {

    /**
     * 查询仓库库存数量
     *
     * @param stockId
     * @param skuId
     * @return
     */
    BisStockShunt getAmountBy(@Param("stockId") Long stockId, @Param("skuId") Long skuId);

    /**
     * 查询库存量
     *
     * @param skuId
     * @return
     */
    List<BisStockShunt> getList(Long skuId);

}
