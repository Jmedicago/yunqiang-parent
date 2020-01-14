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

    @Autowired
    private FinDailyExpendService finDailyExpendService;

    @Override
    protected BaseMapper<FinDzDaily> getMapper() {
        return this.mapper;
    }

    @Override
    public List<FinDzDaily> queryDailyList(Long stockId, String year, String quarterly) {
        return this.mapper.queryDailyList(stockId, year, quarterly);
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

        List<FinDzDaily> finDzDailyList = this.queryDailyList(query.getStockId(), query.getYear(), query.getQuarterly());
        for (FinDzDaily dzDaily : finDzDailyList) {
            double incomeSubTotal = 0;
            double expendSubTotal = 0;
            double arrearsSubTotal = 0;
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

                arrearsSubTotal += arrears;

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

            deposit = incomeTotal + beforeQuarterlyDeposit; // 0 表示上季度存款
            System.out.println("incomeTotal:" + incomeTotal);
            System.out.println("incomeSubTotal:" + incomeSubTotal);
            System.out.println("deposit" + deposit);

            //depositTotal += deposit;
            purchTotal += purch;

            salesTotal = incomeSubTotal + expendSubTotal - saveBank;

            detail.put("date", TimeUtils.dateFormat(dzDaily.getDate(), "yyyy\\MM\\dd"));
            detail.put("incomeSubTotal", incomeSubTotal);
            detail.put("expendSubTotal", expendSubTotal);
            detail.put("deposit", deposit); // 保险柜现金
            detail.put("purch", purch); // 上货金额
            detail.put("arrears", arrearsSubTotal); // 客商总额
            detail.put("sales", salesTotal);
            detail.put("dyDailies", dyDailies);
            details.add(detail);

            // TODO.持久化
            dzDaily.setIncomeSubTotal(incomeSubTotal * 100);
            dzDaily.setExpendSubTotal(expendSubTotal * 100);
            dzDaily.setDeposit(deposit);
            dzDaily.setArrears(arrearsSubTotal);
            dzDaily.setSales(salesTotal);
            this.mapper.updatePart(dzDaily);
        }

        // 自动更新最后一天当日该店员录入的欠款
        if (finDzDailyList != null && finDzDailyList.size() > 0) {
            arrearsTotal = details.get(details.size() - 1).get("arrears") != null ?  (double)details.get(details.size() - 1).get("arrears") : 0;
        }

        // 自动更新最后一天的保险柜现金
        if (finDzDailyList != null && finDzDailyList.size() > 0) {
            depositTotal = details.get(details.size() - 1).get("deposit") != null ? (double)details.get(details.size() - 1).get("deposit") : 0;
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

    /*@Override
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
    }*/

    @Override
    public FinDzDaily saveOrUpdateDaily(FinDzDaily dzDaily) {
        if (dzDaily.getId() == null) {
            if (exits(dzDaily)) {
                throw new BisException().setInfo("今日已填报，不能添加！");
            }

            double purch = this.bisOrderService.getCurDailyTackOrder(dzDaily.getStockId());
            dzDaily.setPurch(purch); // 上货现金
            dzDaily.setCreateTime(System.currentTimeMillis());
            this.mapper.savePart(dzDaily);
            dzDaily.setCode("Z" + dzDaily.getId());

            List<FinDailyExpend> dailyExpends = this.clearNullItem(dzDaily.getFinDailyExpendList());
            for (FinDailyExpend dailyExpend : dailyExpends) {
                if (dailyExpend != null) {
                    double amount = 0;

                    amount = dailyExpend.getAmount() != null ? dailyExpend.getAmount() * 100 : 0;

                    dailyExpend.setDailyCode(dzDaily.getCode());
                    dailyExpend.setStockName(this.getStockName(dzDaily.getStockId()));
                    dailyExpend.setAmount(amount);
                    this.finDailyExpendService.savePart(dailyExpend);
                }
            }
            this.mapper.updatePart(dzDaily);
        }
        return dzDaily;
    }

    private List<FinDailyExpend> clearNullItem(List<FinDailyExpend> finDailyExpendList) {
        List<FinDailyExpend> dailyExpends = new ArrayList<FinDailyExpend>();
        for (FinDailyExpend dailyExpend : finDailyExpendList) {
            if (dailyExpend.getExpendItemId() != null) {
                dailyExpends.add(dailyExpend);
            }
        }
        return dailyExpends;
    }

    private boolean exits(FinDzDaily dzDaily) {
        int count = this.mapper.exits(dzDaily.getStockId(), dzDaily.getDateFormatter());
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
