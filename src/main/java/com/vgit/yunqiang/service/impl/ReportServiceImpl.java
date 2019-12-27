package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.query.ReportQuery;
import com.vgit.yunqiang.service.FinStockDailyService;
import com.vgit.yunqiang.service.ReportService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Hashtable;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private FinStockDailyService dailyService;

    @Override
    public Hashtable<String, Object> report(ReportQuery query) {
        Hashtable<String, Object> report = null;
        if (query != null && StringUtils.isNotBlank(query.getRn())) {
            String rn = query.getRn();
            switch (rn) {
                case "dy-daily": // 店员日报 param: stockId, tn
                    report = this.dailyService.genDailyReport(query);
                    break;
            }
        }
        return report;
    }

}
