package com.vgit.yunqiang.service;

import com.vgit.yunqiang.common.query.ReportQuery;
import com.vgit.yunqiang.common.service.BaseService;
import com.vgit.yunqiang.pojo.FinExpendItem;
import com.vgit.yunqiang.pojo.FinMExpends;

import java.util.Hashtable;
import java.util.List;

public interface FinMExpendsService extends BaseService<FinMExpends> {

    /**
     * 每月连锁店支出明细列表
     *
     * @param year
     * @return
     */
    List<FinExpendItem> getMExpendsList(String year);

    /**
     * 年度支出明细表
     *
     * @param query
     * @return
     */
    Hashtable<String, Object> queryYExpendsReport(ReportQuery query);

}
