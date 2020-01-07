package com.vgit.yunqiang.service;

import com.vgit.yunqiang.common.query.ReportQuery;
import com.vgit.yunqiang.common.service.BaseService;
import com.vgit.yunqiang.pojo.FinQDzInventory;

import java.util.Hashtable;
import java.util.List;

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

    /**
     * 各区域连锁店盘点总列表
     *
     * @return
     */
    List<FinQDzInventory> getQDzInventoryList(String year, String quarterly);

    /**
     * 各区域连锁店盘点总表
     *
     * @param query
     * @return
     */
    Hashtable<String, Object> queryYQInventoryReport(ReportQuery query);
}
