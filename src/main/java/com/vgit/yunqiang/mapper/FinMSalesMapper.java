package com.vgit.yunqiang.mapper;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.pojo.BisStock;
import com.vgit.yunqiang.pojo.FinMSales;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FinMSalesMapper extends BaseMapper<FinMSales> {

    /**
     * 每月店销售额汇总
     *
     * @param stockId
     * @param year
     * @return
     */
    List<BisStock> getMSalesList(@Param("stockId") Long stockId, @Param("year") String year);
}
