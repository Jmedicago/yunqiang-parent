package com.vgit.yunqiang.service;

import com.vgit.yunqiang.common.query.ReportQuery;
import com.vgit.yunqiang.common.service.BaseService;
import com.vgit.yunqiang.pojo.BisStock;
import com.vgit.yunqiang.pojo.FinQExpends;
import com.vgit.yunqiang.pojo.FinQuarterly;

import java.util.Hashtable;
import java.util.List;

public interface FinQExpendsService extends BaseService<FinQExpends> {

    /**
     * 区域各季度支出统计列表
     *
     * @return
     */
    List<FinQuarterly> getQExpendsList(String year, Long stockId);

    /**
     * 区域各季度支出统计报表
     *
     * @return
     */
    Hashtable<String, Object> queryQExpendsReport(ReportQuery query);

    /**
     * 区域各季度支出统计列表
     *
     * @return
     */
    List<BisStock> getExpendsList(String year, String quarterly, Long stockId);

    /**
     * 所有区域各季度支出统计报表
     *
     * @return
     */
    Hashtable<String, Object> queryExpendsReport(ReportQuery query);
}
