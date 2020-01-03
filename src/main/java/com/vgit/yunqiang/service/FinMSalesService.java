package com.vgit.yunqiang.service;

import com.vgit.yunqiang.common.query.ReportQuery;
import com.vgit.yunqiang.common.service.BaseService;
import com.vgit.yunqiang.pojo.BisStock;
import com.vgit.yunqiang.pojo.FinMSales;

import java.util.Hashtable;
import java.util.List;

public interface FinMSalesService extends BaseService<FinMSales> {

    /**
     * 每月区域销售额汇总
     *
     * @param stockId
     * @param year
     * @return
     */
    List<BisStock> getMSalesList(Long stockId, String year);

    /**
     * 每月区域销售额汇总
     *
     * @param query
     * @return
     */
    Hashtable<String, Object> queryDSalesReport(ReportQuery query);

}
