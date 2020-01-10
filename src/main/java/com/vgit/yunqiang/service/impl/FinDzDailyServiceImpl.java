package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.exception.BisException;
import com.vgit.yunqiang.common.query.ReportQuery;
import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.impl.BaseServiceImpl;
import com.vgit.yunqiang.common.utils.TimeUtils;
import com.vgit.yunqiang.mapper.FinDzDailyMapper;
import com.vgit.yunqiang.pojo.FinDailyExpend;
import com.vgit.yunqiang.pojo.FinDyDaily;
import com.vgit.yunqiang.pojo.FinDzDaily;
import com.vgit.yunqiang.pojo.FinQDzInventory;
import com.vgit.yunqiang.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

@Service
public class FinDzDailyServiceImpl extends BaseServiceImpl<FinDzDaily> implements FinDzDailyService {

    @Autowired
    private FinDzDailyMapper mapper;

    @Autowired
    private BisStockService bisStockService;

    @Autowired
    private FinQDzInventoryService finQDzInventoryService;

    @Autowired
    private FinDyDailyService finDyDailyService;

    @Autowired
    private BisOrderService bisOrderService;

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

        Integer year = TimeUtils.getNowYear();
        Integer season = TimeUtils.getNowSeason();

        if (season - 1 > 0) {
            season = season - 1;
        } else {
            season = 4;
            year = year - 1;
        }

        System.out.println("year:" + year);
        System.out.println("season:" + season);

        double beforeQuarterlyDeposit = 0;
        FinQDzInventory finQDzInventory = this.finQDzInventoryService.getBeforeQInventory(String.valueOf(year), String.valueOf(season), query.getStockId());
        if (finQDzInventory != null) {
            beforeQuarterlyDeposit = finQDzInventory.getSafe();
        }

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
            //double arrears = dzDaily.getArrears() != null ? dzDaily.getArrears() * 0.01 : 0;
            //double sales = dzDaily.getSales() != null ? dzDaily.getSales() * 0.01 : 0;
            double saveBank = 0;
            // 店员收支明细
            Hashtable<String, Object> detail = new Hashtable<String, Object>();
            List<FinDyDaily> dyDailies = dzDaily.getFinDyDailyList();
            for (FinDyDaily dyDaily : dyDailies) {
                // 店员现金入账
                double income = dyDaily.getIncome() != null ? dyDaily.getIncome() * 0.01 : 0;
                double arrears = dyDaily.getArrears() != null ? dyDaily.getArrears() * 0.01 : 0;

                arrearsTotal += arrears;

                dyDaily.setIncome(income);

                // 店员支出明细
                List<FinDailyExpend> dailyExpends = dyDaily.getFinDailyExpendList();
                for (FinDailyExpend dailyExpend : dailyExpends) {
                    dailyExpend.setAmount(dailyExpend.getAmount() != null ? dailyExpend.getAmount() * 0.01 : 0);
                    expendSubTotal += dailyExpend.getAmount();

                    if ("C1".equals(dailyExpend.getFinExpendItem().getCategory())) {
                        saveBank += dailyExpend.getAmount();
                    }
                }
                // 店员日进总额
                incomeSubTotal += income;
            }

            // 区域店长收支明细
            incomeTotal += incomeSubTotal;
            expendTotal += expendSubTotal;

            deposit = incomeSubTotal + 0; // 0 表示上季度存款
            depositTotal += deposit;
            purchTotal += purch;

            salesTotal = incomeTotal + expendSubTotal - saveBank;

            detail.put("date", TimeUtils.dateFormat(dzDaily.getDate(), "yyyy\\MM\\dd"));
            detail.put("incomeSubTotal", incomeSubTotal);
            detail.put("expendSubTotal", expendSubTotal);
            detail.put("deposit", deposit); // 保险柜现金
            detail.put("purch", purch); // 上货金额
            detail.put("arrears", arrearsTotal); // 客商总额
            detail.put("sales", salesTotal);
            detail.put("dyDailies", dyDailies);
            details.add(detail);

            // TODO.持久化
        }

        // 自动更新最后一天当日该店员录入的欠款
        if (finDzDailyList != null && finDzDailyList.size() > 0) {
            arrearsTotal = details.get(details.size() - 1).get("arrears") != null ?  (double)details.get(details.size() - 1).get("arrears") : 0;
        }

        report.put("stockName", getStockName(query.getStockId()));
        report.put("quarterly", season);
        report.put("beforeQuarterlyDeposit", beforeQuarterlyDeposit);
        report.put("incomeTotal", incomeTotal);
        report.put("expendTotal", expendTotal);
        report.put("depositTotal", depositTotal);
        report.put("purchTotal", purchTotal);
        report.put("arrearsTotal", arrearsTotal);
        report.put("salesTotal", salesTotal);
        report.put("details", details);
        return report;
    }

    /*@Override
    public FinDzDaily saveOrUpdateDaily(FinDzDaily dzDaily) {
        if (dzDaily.getId() == null) {

            double purch = this.bisOrderService.getCurDailyTackOrder(dzDaily.getStockId());
            double arrearsTotal = 0;
            double incomeSubTotal = 0;
            double expendTotal = 0;
            double sales = 0;

            String date = TimeUtils.dateFormat(new Date(), "yyyy-MM-dd");
            List<FinDyDaily> dyDailies = this.finDyDailyService.getDyDailyList(dzDaily.getStockId(), date);
            for (FinDyDaily dyDaily : dyDailies) {
                double arrears = dyDaily.getArrears() != null ? dyDaily.getArrears() : 0;
                double income = dyDaily.getIncome() != null ? dyDaily.getIncome() : 0;
                double expendSubTotal = 0;

                arrearsTotal += arrears;
                incomeSubTotal += income;


                if (dyDaily.getFinDailyExpendList() != null) {
                    for (FinDailyExpend dailyExpend : dyDaily.getFinDailyExpendList()) {
                        if (dailyExpend.getFinExpendItem() != null) {
                            expendSubTotal += dailyExpend.getAmount();
                            expendTotal += expendSubTotal;
                        }
                    }
                }
            }

            sales = incomeSubTotal + expendTotal;

            dzDaily.setIncomeSubTotal(incomeSubTotal);
            dzDaily.setPurch(purch); // 上货现金
            dzDaily.setArrears(arrearsTotal);
            dzDaily.setSales(sales);
            dzDaily.setExpendSubTotal(expendTotal);

            dzDaily.setDate(new Date());
            dzDaily.setCreateTime(System.currentTimeMillis());
            this.mapper.savePart(dzDaily);
            dzDaily.setCode("Z" + dzDaily.getId());
            this.mapper.updatePart(dzDaily);
        } else {
            this.mapper.updatePart(dzDaily);
        }

        dzDaily = this.mapper.get(dzDaily.getId());
        return dzDaily;
    }*/

    @Override
    public FinDzDaily saveOrUpdateDaily(FinDzDaily dzDaily) {
        if (dzDaily.getId() == null) {
            if (exits(dzDaily)) {
                throw new BisException().setInfo("今日已填报，不能添加！");
            }

            double purch = this.bisOrderService.getCurDailyTackOrder(dzDaily.getStockId());
            dzDaily.setPurch(purch); // 上货现金
            dzDaily.setDate(new Date());
            dzDaily.setCreateTime(System.currentTimeMillis());
            this.mapper.savePart(dzDaily);
            dzDaily.setCode("Z" + dzDaily.getId());
            this.mapper.updatePart(dzDaily);
        } else {
            this.mapper.updatePart(dzDaily);
        }

        dzDaily = this.mapper.get(dzDaily.getId());
        return dzDaily;
    }
    private boolean exits(FinDzDaily dzDaily) {
        int count = this.mapper.exits(dzDaily.getStockId());
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    private String getStockName(Long stockId) {
        return this.bisStockService.get(stockId).getName();
    }

}
