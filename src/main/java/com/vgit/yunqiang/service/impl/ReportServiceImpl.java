package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.query.ReportQuery;
import com.vgit.yunqiang.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Hashtable;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private FinDyDailyService finDyDailyService;

    @Autowired
    private FinDzDailyService finDzDailyService;

    @Autowired
    private FinArrearsService finArrearsService;

    @Autowired
    private FinDSalesService finDSalesService;

    @Autowired
    private FinMSalesService finMSalesService;

    @Autowired
    private FinQExpendsService finQExpendsService;

    @Autowired
    private FinMExpendsService finMExpendsService;

    @Override
    public Hashtable<String, Object> report(ReportQuery query) {
        Hashtable<String, Object> report = null;
        if (query != null && StringUtils.isNotBlank(query.getRn())) {
            String rn = query.getRn();
            switch (rn) {
                case "dy-daily": // 店员日报?stockId=1004
                    report = this.finDyDailyService.genDyDailyReport(query);
                    break;
                case "dz-daily": // 店长日报?stockId=1001
                    report = this.finDzDailyService.genDzDailyReport(query);
                    break;
                case "rp-arrears": // 区域连锁店客商欠款?year=2019
                    report = this.finArrearsService.queryArrearsReport(query);
                    break;
                case "rp-d-sales": // 每日区域销售汇总?year=2019&month=2
                    report = this.finDSalesService.queryDSalesReport(query);
                    break;
                case "rp-m-sales": // 每月区域销售汇总?year=2019
                    report = this.finMSalesService.queryDSalesReport(query);
                    break;
                case "rp-q-expends": // 某区域季度支出报表?year=2019&stockId=1001
                    report = this.finQExpendsService.queryQExpendsReport(query);
                    break;
                case "rp-expends": // 所有区域支出统计报表?year=2019&quarterly=Q2
                    report = this.finQExpendsService.queryExpendsReport(query);
                    break;
                case "rp-y-expends": // 年度支出明细
                    report = this.finMExpendsService.queryYExpendsReport(query);
                    break;
            }
        }
        return report;
    }

}
