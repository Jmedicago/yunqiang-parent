package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.query.ReportQuery;
import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.impl.BaseServiceImpl;
import com.vgit.yunqiang.mapper.FinArrearsMapper;
import com.vgit.yunqiang.pojo.BisStock;
import com.vgit.yunqiang.pojo.FinArrears;
import com.vgit.yunqiang.service.FinArrearsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

@Service
public class FinArrearsServiceImpl extends BaseServiceImpl<FinArrears> implements FinArrearsService {

    @Autowired
    private FinArrearsMapper mapper;

    @Override
    protected BaseMapper<FinArrears> getMapper() {
        return this.mapper;
    }

    @Override
    public List<BisStock> queryArrearsList(String year) {
        return this.mapper.queryArrearsList(year);
    }

    @Override
    public Hashtable<String, Object> queryArrearsReport(ReportQuery query) {
        Hashtable<String, Object> report = new Hashtable<String, Object>();
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
        double allMonthTotal = 0;

        if (query.getYear() == null) {
            return report;
        }

        List<Hashtable<String, Object>> stockTableList = new ArrayList<Hashtable<String, Object>>();
        List<BisStock> stockList = this.queryArrearsList(query.getYear());
        for (BisStock bisStock : stockList) {
            double total = 0;

            if (bisStock != null && bisStock.getArrearsList().size() > 0) {
                for (FinArrears arrears : bisStock.getArrearsList()) {
                    if (arrears != null) {
                        arrears.setAmount(arrears.getAmount() * 0.01);
                        total += arrears.getAmount();

                        if (arrears.getMonthId() == 1) {
                            janTotal += arrears.getAmount();
                        }
                        if (arrears.getMonthId() == 2) {
                            febTotal += arrears.getAmount();
                        }
                        if (arrears.getMonthId() == 3) {
                            marTotal += arrears.getAmount();
                        }
                        if (arrears.getMonthId() == 4) {
                            aprTotal += arrears.getAmount();
                        }
                        if (arrears.getMonthId() == 5) {
                            mayTotal += arrears.getAmount();
                        }
                        if (arrears.getMonthId() == 6) {
                            junTotal += arrears.getAmount();
                        }
                        if (arrears.getMonthId() == 7) {
                            julTotal += arrears.getAmount();
                        }
                        if (arrears.getMonthId() == 8) {
                            augTotal += arrears.getAmount();
                        }
                        if (arrears.getMonthId() == 9) {
                            septTotal += arrears.getAmount();
                        }
                        if (arrears.getMonthId() == 10) {
                            octTotal += arrears.getAmount();
                        }
                        if (arrears.getMonthId() == 11) {
                            novTotal += arrears.getAmount();
                        }
                        if (arrears.getMonthId() == 12) {
                            decTotal += arrears.getAmount();
                        }
                    }
                }
            }

            allMonthTotal += total;

            Hashtable<String, Object> stockTable = new Hashtable<String, Object>();
            stockTable.put("name", bisStock.getName());
            stockTable.put("arrearsList", bisStock.getArrearsList());
            stockTable.put("total", total);
            stockTableList.add(stockTable);
        }

        report.put("year", query.getYear());
        report.put("stockList", stockTableList);
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
        report.put("allMonthTotal", allMonthTotal);
        return report;
    }

}
