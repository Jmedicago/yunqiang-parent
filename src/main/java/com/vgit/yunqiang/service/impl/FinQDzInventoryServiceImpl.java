package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.query.ReportQuery;
import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.impl.BaseServiceImpl;
import com.vgit.yunqiang.mapper.FinQDzInventoryMapper;
import com.vgit.yunqiang.pojo.BisStock;
import com.vgit.yunqiang.pojo.FinQDyInventory;
import com.vgit.yunqiang.pojo.FinQDzInventory;
import com.vgit.yunqiang.service.BisStockService;
import com.vgit.yunqiang.service.FinQDzInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

@Service
public class FinQDzInventoryServiceImpl extends BaseServiceImpl<FinQDzInventory> implements FinQDzInventoryService {

    @Autowired
    private FinQDzInventoryMapper mapper;

    @Autowired
    private BisStockService bisStockService;

    @Override
    protected BaseMapper<FinQDzInventory> getMapper() {
        return this.mapper;
    }

    @Override
    public FinQDzInventory getFinDzInventory(String year, String quarterly, Long stockId) {
        return this.mapper.getQDzInventory(year, quarterly, stockId);
    }

    @Override
    public Hashtable<String, Object> queryQDzInventoryReport(ReportQuery query) {
        Hashtable<String, Object> report = new Hashtable<String, Object>();
        double total = 0;

        // 店长
        Hashtable<String, Object> dzInventory = new Hashtable<String, Object>();
        FinQDzInventory qDzInventory = this.getFinDzInventory(query.getYear(), query.getQuarterly(), query.getStockId());
        if (qDzInventory != null) {
            double beforeArrearsTotal = 0;
            double beforeChangeTotal = 0;
            double beforePurchTotal = 0;
            double incomeTotal = 0;

            double expendTotal = 0;

            double arrearsTotal = 0;
            double changeTotal = 0;
            double purchTotal = 0;

            // 店员
            List<Hashtable<String, Object>> dyInventoryList = new ArrayList<Hashtable<String, Object>>();
            List<FinQDyInventory> qDyInventoryList = qDzInventory.getqDyInventoryList();
            for (FinQDyInventory qDyInventory : qDyInventoryList) {
                Hashtable<String, Object> dyInventory = new Hashtable<String, Object>();
                double beforeSafe = qDyInventory.getBeforeSafe() != null ? qDyInventory.getBeforeSafe() * 0.01 : 0;
                double beforeChange = qDyInventory.getBeforeChange() != null ? qDyInventory.getBeforeChange() * 0.01 : 0;
                double beforeArrears = qDyInventory.getBeforeArrears() != null ? qDyInventory.getBeforeArrears() * 0.01 : 0;
                double beforePurch = qDyInventory.getBeforePurch() != null ? qDyInventory.getBeforePurch() * 0.01 : 0;
                double income = qDyInventory.getIncome() != null ? qDyInventory.getIncome() * 0.01 : 0;
                double inTotal = beforeArrears + beforeChange + beforePurch + income;

                double expend = qDyInventory.getExpend() != null ? qDyInventory.getExpend() * 0.01 : 0;
                double dailyCash = qDyInventory.getDailyCash() != null ? qDyInventory.getDailyCash() * 0.01 : 0;
                double outTotal = expend + dailyCash;

                double safe = qDyInventory.getSafe() != null ? qDyInventory.getSafe() * 0.01 : 0;
                double change = qDyInventory.getChange() != null ? qDyInventory.getChange() * 0.01 : 0;
                double arrears = qDyInventory.getArrears() != null ? qDyInventory.getArrears() * 0.01 : 0;
                double purch = qDyInventory.getPurch() != null ? qDyInventory.getPurch() * 0.01 : 0;
                double saveTotal = safe + change + arrears + purch;

                beforeArrearsTotal += beforeArrears;
                beforeChangeTotal += beforeChange;
                beforePurchTotal += beforePurch;
                incomeTotal += income;

                expendTotal += expend;

                arrearsTotal += arrears;
                changeTotal += change;
                purchTotal += purch;

                dyInventory.put("beforeSafe", beforeSafe);
                dyInventory.put("beforeChange", beforeChange);
                dyInventory.put("beforeArrears", beforeArrears);
                dyInventory.put("beforePurch", beforePurch);
                dyInventory.put("income", income);
                dyInventory.put("inTotal", inTotal);

                dyInventory.put("expend", expend);
                dyInventory.put("dailyCash", dailyCash);
                dyInventory.put("outTotal", outTotal);

                dyInventory.put("safe", safe);
                dyInventory.put("change", change);
                dyInventory.put("arrears", arrears);
                dyInventory.put("purch", purch);
                dyInventory.put("saveTotal", saveTotal);
                dyInventory.put("stockName", this.getStockName(qDyInventory.getStockId()));

                dyInventoryList.add(dyInventory);
            }

            double beforeSafe = qDzInventory.getBeforeSafe() != null ? qDzInventory.getBeforeSafe() * 0.01 : 0;
            double dailyCash = qDzInventory.getDailyCash() != null ? qDzInventory.getDailyCash() * 0.01 : 0;
            double safe = qDzInventory.getSafe() != null ? qDzInventory.getSafe() * 0.01 : 0;
            double inTotal = 0;
            double outTotal = 0;
            double saveTotal = 0;

            inTotal = beforeSafe + beforeArrearsTotal + beforeChangeTotal + beforePurchTotal + incomeTotal;
            outTotal = expendTotal + dailyCash;
            saveTotal = safe + arrearsTotal + changeTotal + purchTotal;

            total = outTotal + saveTotal - inTotal;

            dzInventory.put("beforeSafe", beforeSafe);
            dzInventory.put("beforeArrearsTotal", beforeArrearsTotal);
            dzInventory.put("beforeChangeTotal", beforeChangeTotal);
            dzInventory.put("beforePurchTotal", beforePurchTotal);
            dzInventory.put("incomeTotal", incomeTotal);
            dzInventory.put("inTotal", inTotal);

            dzInventory.put("expendTotal", expendTotal);
            dzInventory.put("dailyCash", dailyCash);
            dzInventory.put("outTotal", outTotal);

            dzInventory.put("safe", safe);
            dzInventory.put("arrearsTotal", arrearsTotal);
            dzInventory.put("changeTotal", changeTotal);
            dzInventory.put("purchTotal", purchTotal);
            dzInventory.put("saveTotal", saveTotal);
            dzInventory.put("total", total);
            dzInventory.put("pl", total > 0 ? "盈" : "亏");
            dzInventory.put("dyInventoryList", dyInventoryList);
        }

        report.put("year", query.getYear());
        report.put("quarterly", query.getQuarterly());
        report.put("stockName", getStockName(query.getStockId()));
        report.put("dzInventory", dzInventory);
        return report;
    }

    @Override
    public List<FinQDzInventory> getQDzInventoryList(String year, String quarterly) {
        return this.mapper.getQDzInventoryList(year, quarterly);
    }

    @Override
    public Hashtable<String, Object> queryYQInventoryReport(ReportQuery query) {
        Hashtable<String, Object> report = new Hashtable<String, Object>();
        double allSafeTotal = 0;
        double allArrearsTotal = 0;
        double allChangeTotal = 0;
        double allPurchTotal = 0;
        double allInventoryTotal = 0;
        double sumAllOutTotal = 0;
        double sumAllOrderTotal = 0;
        double sumAllBeforeInventoryTotal = 0;
        double sumAllPl = 0;

        // 店长
        List<Hashtable<String, Object>> dzQInventoryList = new ArrayList<Hashtable<String, Object>>();
        List<FinQDzInventory> qDzInventoryList = this.getQDzInventoryList(query.getYear(), query.getQuarterly());
        for (FinQDzInventory qDzInventory : qDzInventoryList) {
            if (qDzInventory != null) {
                Hashtable<String, Object> dzInventory = new Hashtable<String, Object>();
                double safeTotal = 0;
                double arrearsTotal = 0;
                double changeTotal = 0;
                double purchTotal = 0;
                double allOutTotal = 0;
                double allOrderTotal = 0;
                double allBeforeInventoryTotal = 0;
                double allPl = 0;

                List<Hashtable<String, Object>> dyInventoryList = new ArrayList<Hashtable<String, Object>>();
                List<FinQDyInventory> qDyInventoryList = qDzInventory.getqDyInventoryList();
                for (FinQDyInventory qDyInventory : qDyInventoryList) {
                    Hashtable<String, Object> dyInventory = new Hashtable<String, Object>();
                    double inventoryTotal = 0;
                    double outTotal = 0;
                    double orderTotal = 0;
                    double beforeInventoryTotal = 0;
                    double pl = 0;

                    double safe = qDyInventory.getSafe() != null ? qDyInventory.getSafe() * 0.01 : 0;
                    double arrears = qDyInventory.getArrears() != null ? qDyInventory.getArrears() * 0.01 : 0;
                    double change = qDyInventory.getChange() != null ? qDyInventory.getChange() * 0.01 : 0;
                    double purch = qDyInventory.getPurch() != null ? qDyInventory.getPurch() * 0.01 : 0;
                    arrearsTotal += arrears;
                    changeTotal += change;
                    purchTotal += purch;
                    inventoryTotal = safe + arrears + change + purch;

                    double expend = qDyInventory.getExpend() != null ? qDyInventory.getExpend() * 0.01 : 0;
                    double dailyCash = qDyInventory.getDailyCash() != null ? qDyInventory.getDailyCash() * 0.01 : 0;
                    outTotal = expend + dailyCash;
                    allOutTotal += outTotal;
                    sumAllOutTotal += allOutTotal;

                    // TODO.计算“店员”上货总值（店员不能点货？）
                    orderTotal = 0;
                    allOrderTotal += orderTotal;
                    sumAllOrderTotal += allOrderTotal;

                    // TODO.计算Q1盘点总值
                    beforeInventoryTotal = 0;
                    allBeforeInventoryTotal += beforeInventoryTotal;
                    sumAllBeforeInventoryTotal += allBeforeInventoryTotal;

                    pl = inventoryTotal + outTotal - orderTotal - beforeInventoryTotal;
                    allPl += pl;

                    dyInventory.put("safe", safe);
                    dyInventory.put("arrears", arrears);
                    dyInventory.put("change", change);
                    dyInventory.put("purch", purch);
                    dyInventory.put("inventoryTotal", inventoryTotal); // Q2盘点总值
                    dyInventory.put("outTotal", outTotal); // Q2销售额
                    dyInventory.put("orderTotal", orderTotal); // Q2上货值
                    dyInventory.put("beforeInventoryTotal", beforeInventoryTotal); // Q1盘点总值
                    dyInventory.put("pl", pl); // Q2盈亏情况
                    dyInventory.put("stockName", getStockName(qDyInventory.getStockId()));
                    dyInventoryList.add(dyInventory);
                }

                double safe = qDzInventory.getSafe() != null ? qDzInventory.getSafe() * 0.01 : 0;
                safeTotal = safe;

                double inventoryTotal = safeTotal + arrearsTotal + changeTotal + purchTotal;

                allSafeTotal += safeTotal; //连锁店保险柜现金
                allArrearsTotal += arrearsTotal; //客商总欠款
                allChangeTotal += changeTotal; //柜台零钱
                allPurchTotal += purchTotal;
                allInventoryTotal = allSafeTotal + allArrearsTotal + allChangeTotal + allPurchTotal;

                dzInventory.put("safeTotal", safeTotal);
                dzInventory.put("arrearsTotal", arrearsTotal);
                dzInventory.put("changeTotal", changeTotal);
                dzInventory.put("purchTotal", purchTotal);
                dzInventory.put("inventoryTotal", inventoryTotal); //总计Q2盘点总值
                dzInventory.put("allOutTotal", allOutTotal); //总计Q2销售额
                dzInventory.put("allOrderTotal", allOrderTotal); //总计Q2上货值
                dzInventory.put("allBeforeInventoryTotal", allBeforeInventoryTotal); //总计Q1盘点总值
                dzInventory.put("allPl", allPl);
                dzInventory.put("stockName", getStockName(qDzInventory.getStockId()));
                dzInventory.put("dyInventoryList", dyInventoryList);
                dzQInventoryList.add(dzInventory);
            }
        }

        sumAllPl = allInventoryTotal + sumAllOutTotal - sumAllOrderTotal - sumAllBeforeInventoryTotal;

        report.put("year", query.getYear());
        report.put("quarterly", query.getQuarterly());
        report.put("allSafeTotal", allSafeTotal);
        report.put("allArrearsTotal", allArrearsTotal);
        report.put("allChangeTotal", allChangeTotal);
        report.put("allPurchTotal", allPurchTotal);
        report.put("allInventoryTotal", allInventoryTotal);
        report.put("sumAllOutTotal", sumAllOutTotal);
        report.put("sumAllOrderTotal", sumAllOrderTotal);
        report.put("sumAllBeforeInventoryTotal", sumAllBeforeInventoryTotal);
        report.put("sumAllPl", sumAllPl);
        report.put("dzQInventoryList", dzQInventoryList);
        return report;
    }

    private String getRegionStockName(Long stockId) {
        BisStock bisStock = this.bisStockService.get(stockId);
        return this.bisStockService.get(bisStock.getParentId()).getName();
    }

    private String getStockName(Long stockId) {
        return this.bisStockService.get(stockId).getName();
    }

}
