package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.query.ReportQuery;
import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.impl.BaseServiceImpl;
import com.vgit.yunqiang.common.utils.TimeUtils;
import com.vgit.yunqiang.mapper.FinDzDailyMapper;
import com.vgit.yunqiang.pojo.FinDailyExpend;
import com.vgit.yunqiang.pojo.FinDyDaily;
import com.vgit.yunqiang.pojo.FinDzDaily;
import com.vgit.yunqiang.service.BisStockService;
import com.vgit.yunqiang.service.FinDzDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

@Service
public class FinDzDailyServiceImpl extends BaseServiceImpl<FinDzDaily> implements FinDzDailyService {

    @Autowired
    private FinDzDailyMapper mapper;

    @Autowired
    private BisStockService bisStockService;

    @Override
    protected BaseMapper<FinDzDaily> getMapper() {
        return this.mapper;
    }

    @Override
    public List<FinDzDaily> queryDailyList(Long stockId) {
        return this.mapper.queryDailyList(stockId);
    }

    @Override
    public Hashtable<String, Object> genDzDailyReport(ReportQuery query) {
        Hashtable<String, Object> report = new Hashtable<String, Object>();
        double incomeTotal = 0; // 进
        double expendTotal = 0; // 出
        double depositTotal = 0; // 存
        double purchTotal = 0; //本季度累计上货
        double arrearsTotal = 0; // 客商总额
        double salesTotal = 0; // 本季度累计销售额
        List<Hashtable<String, Object>> details = new ArrayList<Hashtable<String, Object>>();

        if (query.getStockId() == null) {
            return report;
        }

        List<FinDzDaily> finDzDailyList = this.queryDailyList(query.getStockId());
        for (FinDzDaily dzDaily : finDzDailyList) {
            double incomeSubTotal = 0;
            double expendSubTotal = 0;
            double deposit = dzDaily.getDeposit() != null ? dzDaily.getDeposit() * 0.01 : 0;
            double purch = dzDaily.getPurch() != null ? dzDaily.getPurch() * 0.01 : 0;
            double arrears = dzDaily.getArrears() != null ? dzDaily.getArrears() * 0.01 : 0;
            double sales = dzDaily.getSales() != null ? dzDaily.getSales() * 0.01 : 0;

            // 店员收支明细
            Hashtable<String, Object> detail = new Hashtable<String, Object>();
            List<FinDyDaily> dyDailies = dzDaily.getFinDyDailyList();
            for (FinDyDaily dyDaily : dyDailies) {
                // 店员现金入账
                double income = dyDaily.getIncome() != null ? dyDaily.getIncome() * 0.01 : 0;
                dyDaily.setIncome(income);

                // 店员支出明细
                List<FinDailyExpend> dailyExpends = dyDaily.getFinDailyExpendList();
                for (FinDailyExpend dailyExpend : dailyExpends) {
                    dailyExpend.setAmount(dailyExpend.getAmount() != null ? dailyExpend.getAmount() * 0.01 : 0);
                    expendSubTotal += dailyExpend.getAmount();
                }
                // 店员日进总额
                incomeSubTotal += income;
            }

            // 区域店长收支明细
            incomeTotal += incomeSubTotal;
            expendTotal += expendSubTotal;
            depositTotal += deposit;
            purchTotal += purch;
            salesTotal += sales;

            detail.put("date", TimeUtils.dateFormat(dzDaily.getDate(), "yyyy\\MM\\dd"));
            detail.put("incomeSubTotal", incomeSubTotal);
            detail.put("expendSubTotal", expendSubTotal);
            detail.put("deposit", deposit); // 保险柜现金
            detail.put("purch", purch); // 上货金额
            detail.put("arrears", arrears); // 客商总额
            detail.put("sales", sales);
            detail.put("dyDailies", dyDailies);
            details.add(detail);
        }

        // 自动更新最后一天当日该店员录入的欠款
        if (finDzDailyList != null && finDzDailyList.size() > 0) {
            arrearsTotal = finDzDailyList.get(finDzDailyList.size() - 1).getArrears() != null ? finDzDailyList.get(finDzDailyList.size() - 1).getArrears() * 0.01 : 0;
        }

        report.put("stockName", getStockName(query.getStockId()));
        report.put("incomeTotal", incomeTotal);
        report.put("expendTotal", expendTotal);
        report.put("depositTotal", depositTotal);
        report.put("purchTotal", purchTotal);
        report.put("arrearsTotal", arrearsTotal);
        report.put("salesTotal", salesTotal);
        report.put("details", details);
        return report;
    }

    private String getStockName(Long stockId) {
        return this.bisStockService.get(stockId).getName();
    }

}