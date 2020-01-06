package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.query.ReportQuery;
import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.impl.BaseServiceImpl;
import com.vgit.yunqiang.mapper.FinMExpendsMapper;
import com.vgit.yunqiang.pojo.FinExpendItem;
import com.vgit.yunqiang.pojo.FinMExpends;
import com.vgit.yunqiang.service.FinMExpendsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

@Service
public class FinMExpendsServiceImpl extends BaseServiceImpl<FinMExpends> implements FinMExpendsService {

    @Autowired
    private FinMExpendsMapper mapper;

    @Override
    protected BaseMapper<FinMExpends> getMapper() {
        return this.mapper;
    }

    @Override
    public List<FinExpendItem> getMExpendsList(String year) {
        return this.mapper.getMExpendsList(year);
    }

    @Override
    public Hashtable<String, Object> queryYExpendsReport(ReportQuery query) {
        Hashtable<String, Object> report = new Hashtable<String, Object>();
        double expendTotal = 0;
        double janTotal = 0;
        double febTotal = 0;
        double marTotal = 0;
        double aprTotal = 0;
        double mayTotal = 0;
        double junTotal = 0;
        double julTotal = 0;
        double augTotal = 0;
        double septTotal = 0;
        double octTotal = 0;
        double novTotal = 0;
        double decTotal = 0;

        if (StringUtils.isBlank(query.getYear())) {
            return report;
        }

        List<Hashtable<String,Object>> mExpendItemsList = new ArrayList<Hashtable<String, Object>>();
        List<FinExpendItem> expendItems = this.getMExpendsList(query.getYear());
        for (FinExpendItem expendItem : expendItems) {
            double expendItemTotal = 0;

            if (expendItem != null) {
                for (FinMExpends expends : expendItem.getmExpendsList()) {
                    expends.setAmount(expends.getAmount() * 0.01);
                    expendItemTotal += expends.getAmount();

                    if (expends.getMonthId() == 1) {
                        janTotal += expends.getAmount();
                    }
                    if (expends.getMonthId() == 2) {
                        febTotal += expends.getAmount();
                    }
                    if (expends.getMonthId() == 3) {
                        marTotal += expends.getAmount();
                    }
                    if (expends.getMonthId() == 4) {
                        aprTotal += expends.getAmount();
                    }
                    if (expends.getMonthId() == 5) {
                        mayTotal += expends.getAmount();
                    }
                    if (expends.getMonthId() == 6) {
                        junTotal += expends.getAmount();
                    }
                    if (expends.getMonthId() == 7) {
                        julTotal += expends.getAmount();
                    }
                    if (expends.getMonthId() == 8) {
                        augTotal += expends.getAmount();
                    }
                    if (expends.getMonthId() == 9) {
                        septTotal += expends.getAmount();
                    }
                    if (expends.getMonthId() == 10) {
                        octTotal += expends.getAmount();
                    }
                    if (expends.getMonthId() == 11) {
                        novTotal += expends.getAmount();
                    }
                    if (expends.getMonthId() == 12) {
                        decTotal += expends.getAmount();
                    }
                }

                expendTotal += expendItemTotal;

                Hashtable<String,Object> mExpendItem = new Hashtable<String, Object>();
                mExpendItem.put("category", expendItem.getCategory());
                mExpendItem.put("article", expendItem.getArticle());
                mExpendItem.put("expendItemTotal", expendItemTotal);
                mExpendItem.put("expendList", expendItem.getmExpendsList());
                mExpendItemsList.add(mExpendItem);
            }
        }

        report.put("year", query.getYear());
        report.put("expendTotal", expendTotal);
        report.put("janTotal", janTotal);
        report.put("febTotal", febTotal);
        report.put("marTotal", marTotal);
        report.put("aprTotal", aprTotal);
        report.put("mayTotal", mayTotal);
        report.put("junTotal", junTotal);
        report.put("julTotal", julTotal);
        report.put("augTotal", augTotal);
        report.put("septTotal", septTotal);
        report.put("octTotal", octTotal);
        report.put("novTotal", novTotal);
        report.put("decTotal", decTotal);
        report.put("expendItemsList", mExpendItemsList);
        return report;
    }

}
