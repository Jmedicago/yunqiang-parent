package com.vgit.yunqiang.service;

import com.vgit.yunqiang.common.query.ReportQuery;
import com.vgit.yunqiang.common.service.BaseService;
import com.vgit.yunqiang.pojo.BisStock;
import com.vgit.yunqiang.pojo.FinDSales;

import java.util.Hashtable;
import java.util.List;

public interface FinDSalesService extends BaseService<FinDSales> {

    /**
     * 每日区域销售额汇总
     *
     * @param stockId
     * @param year
     * @param month
     * @return
     */
    List<BisStock> getDSalesList(Long stockId, String year, String month);

    /**
     * 每日区域销售额汇总报表
     *
     * @param query
     * @return
     */
    Hashtable<String, Object> queryDSalesReport(ReportQuery query);
}
