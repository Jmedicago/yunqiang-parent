package com.vgit.yunqiang.service;

import com.vgit.yunqiang.common.query.ReportQuery;
import com.vgit.yunqiang.common.service.BaseService;
import com.vgit.yunqiang.pojo.FinQDzInventory;

import java.util.Hashtable;

public interface FinQDzInventoryService extends BaseService<FinQDzInventory> {

    /**
     * 查询店长季度盘点
     *
     * @param year
     * @param quarterly
     * @param stockId
     * @return
     */
    FinQDzInventory getFinDzInventory(String year, String quarterly, Long stockId);

    /**
     * 店长季度盘点报表
     *
     * @param query
     * @return
     */
    Hashtable<String, Object> queryQDzInventoryReport(ReportQuery query);
}
