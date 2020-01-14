package com.vgit.yunqiang.service;

import com.vgit.yunqiang.common.exception.BisException;
import com.vgit.yunqiang.common.query.ReportQuery;
import com.vgit.yunqiang.common.service.BaseService;
import com.vgit.yunqiang.pojo.FinDyDaily;

import java.util.Hashtable;
import java.util.List;

public interface FinDyDailyService extends BaseService<FinDyDaily> {

    /**
     * 查询店员每日资金进出账明细
     *
     * @param stockId
     * @return
     */
    List<FinDyDaily> queryDailyList(Long stockId);

    /**
     * 查询店员每日资金进出账表报
     *
     * @param query
     * @return
     */
    Hashtable<String, Object> genDyDailyReport(ReportQuery query);

    /**
     * 店员填报日报
     *
     * @param dyDaily
     * @throws BisException
     */
    FinDyDaily saveOrUpdateDaily(FinDyDaily dyDaily) throws BisException;

    /**
     * 根据日报code查询日报信息
     *
     * @param dailyCode
     * @return
     */
    FinDyDaily getByCode(String dailyCode);

    /**
     * 查询所有店员列表
     *
     * @param stockId
     * @return
     */
    List<FinDyDaily> getDyDailyList(Long stockId, String date);

    /**
     * 根据时间区间查询店员日报列表
     *
     * @param startDate
     * @param endDate
     * @param stockId
     * @return
     */
    List<FinDyDaily> getDyDailyList(String startDate, String endDate, Long stockId);
}
