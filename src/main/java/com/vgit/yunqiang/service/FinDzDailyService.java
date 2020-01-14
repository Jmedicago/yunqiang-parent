package com.vgit.yunqiang.service;

import com.vgit.yunqiang.common.query.ReportQuery;
import com.vgit.yunqiang.common.service.BaseService;
import com.vgit.yunqiang.pojo.FinDzDaily;

import java.util.Hashtable;
import java.util.List;

public interface FinDzDailyService extends BaseService<FinDzDaily> {

    /**
     * 查询店长每日资金进出账明细
     *
     * @param stockId
     * @return
     */
    List<FinDzDaily> queryDailyList(Long stockId, String year, String quarterly);

    /**
     * 查询店长每日资金进出账表报
     *
     * @param query
     * @return
     */
    Hashtable<String, Object> genDzDailyReport(ReportQuery query);

    /**
     * 店长填报
     *
     * @param dzDaily
     * @return
     */
    FinDzDaily saveOrUpdateDaily(FinDzDaily dzDaily);
}
