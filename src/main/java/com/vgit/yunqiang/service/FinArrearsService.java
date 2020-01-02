package com.vgit.yunqiang.service;

import com.vgit.yunqiang.common.query.ReportQuery;
import com.vgit.yunqiang.common.service.BaseService;
import com.vgit.yunqiang.pojo.BisStock;
import com.vgit.yunqiang.pojo.FinArrears;
import com.vgit.yunqiang.pojo.FinYear;

import java.util.Hashtable;
import java.util.List;

public interface FinArrearsService extends BaseService<FinArrears> {

    /**
     * 各区域连锁店客商欠款
     *
     * @param year
     * @return
     */
    //FinYear queryArrearsList(String year);
    List<BisStock> queryArrearsList(String year);

    /**
     * 各区域连锁店客商欠款表报
     *
     * @param query year年份
     * @return
     */
    Hashtable<String, Object> queryArrearsReport(ReportQuery query);
}
