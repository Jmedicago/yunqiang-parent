package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.query.ReportQuery;
import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.impl.BaseServiceImpl;
import com.vgit.yunqiang.mapper.FinQDzInventoryMapper;
import com.vgit.yunqiang.pojo.BisStock;
import com.vgit.yunqiang.pojo.FinQDyInventory;
import com.vgit.yunqiang.pojo.FinQDzInventory;
import com.vgit.yunqiang.service.BisStockService;
import com.vgit.yunqiang.service.FinQDyInventoryService;
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

    private String getRegionStockName(Long stockId) {
        BisStock bisStock = this.bisStockService.get(stockId);
        return this.bisStockService.get(bisStock.getParentId()).getName();
    }

    private String getStockName(Long stockId) {
        return this.bisStockService.get(stockId).getName();
    }

}
