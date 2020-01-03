package com.vgit.yunqiang.mapper;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.pojo.BisStock;
import com.vgit.yunqiang.pojo.FinDSales;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FinDSalesMapper extends BaseMapper<FinDSales> {

    /**
     * 每日店销售额汇总
     *
     * @param stockId
     * @param year
     * @param month
     * @return
     */
    List<BisStock> getDSalesList(@Param("stockId") Long stockId, @Param("year") String year, @Param("month") String month);
}
