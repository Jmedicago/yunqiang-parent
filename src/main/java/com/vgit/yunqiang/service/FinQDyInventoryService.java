package com.vgit.yunqiang.service;

import com.vgit.yunqiang.common.query.ReportQuery;
import com.vgit.yunqiang.common.service.BaseService;
import com.vgit.yunqiang.pojo.FinQDyInventory;

import java.util.Hashtable;

public interface FinQDyInventoryService extends BaseService<FinQDyInventory> {

    /**
     * 店员季度盘点报表
     *
     * @param year
     * @param quarterly
     * @param stockId
     * @return
     */
    FinQDyInventory getQDyInventory(String year, String quarterly, Long stockId);

    /**
     * 店员季度盘点报表
     *
     * @param query
     * @return
     */
    Hashtable<String, Object> queryQDyInventoryReport(ReportQuery query);

    /**
     * 编辑季度盘点
     *
     * @param qDyInventory
     * @return
     */
    FinQDyInventory saveOrUpdateQuarterly(FinQDyInventory qDyInventory);
}
