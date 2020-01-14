package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.query.ReportQuery;
import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.impl.BaseServiceImpl;
import com.vgit.yunqiang.common.utils.TimeUtils;
import com.vgit.yunqiang.mapper.FinQDyInventoryMapper;
import com.vgit.yunqiang.mapper.FinQInventoryMapper;
import com.vgit.yunqiang.pojo.BisStock;
import com.vgit.yunqiang.pojo.FinDyDaily;
import com.vgit.yunqiang.pojo.FinQDyInventory;
import com.vgit.yunqiang.pojo.FinQInventory;
import com.vgit.yunqiang.service.BisStockService;
import com.vgit.yunqiang.service.FinDyDailyService;
import com.vgit.yunqiang.service.FinQDyInventoryService;
import com.vgit.yunqiang.service.FinQInventoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

@Service
public class FinQDyInventoryServiceImpl extends BaseServiceImpl<FinQDyInventory> implements FinQDyInventoryService {

    @Autowired
    private FinQDyInventoryMapper mapper;

    @Autowired
    private BisStockService bisStockService;

    @Autowired
    private FinQInventoryService finQInventoryService;

    @Autowired
    private FinDyDailyService finDyDailyService;

    @Override
    protected BaseMapper<FinQDyInventory> getMapper() {
        return this.mapper;
    }

    @Override
    public FinQDyInventory getQDyInventory(String year, String quarterly, Long stockId) {
        return this.mapper.getQDyInventory(year, quarterly, stockId);
    }

    @Override
    public Hashtable<String, Object> queryQDyInventoryReport(ReportQuery query) {
        Hashtable<String, Object> report = new Hashtable<String, Object>();
        double beforeSafe = 0;
        double beforeArrears = 0;
        double beforeChange = 0;
        double beforePurch = 0;
        double income = 0;
        double inTotal = 0;

        double expend = 0;
        double dailyCash = 0;
        double outTotal = 0;

        double safe = 0;
        double arrears = 0;
        double change = 0;
        double purch = 0;
        double saveTotal = 0;

        double total = 0;

        if (StringUtils.isBlank(query.getYear()) && StringUtils.isBlank(query.getQuarterly()) && query.getStockId() == null) {
            return report;
        }

        FinQDyInventory qDyInventory = this.getQDyInventory(query.getYear(), query.getQuarterly(), query.getStockId());
        if (qDyInventory != null) {
            // 进
            beforeSafe = qDyInventory.getBeforeSafe() != null ? qDyInventory.getBeforeSafe() * 0.01 : 0;
            beforeArrears = qDyInventory.getBeforeArrears() != null ? qDyInventory.getBeforeArrears() * 0.01 : 0;
            beforeChange = qDyInventory.getBeforeChange() != null ? qDyInventory.getBeforeChange() * 0.01 : 0;
            beforePurch = qDyInventory.getBeforePurch() != null ? qDyInventory.getBeforePurch() * 0.01 : 0;

            income = qDyInventory.getIncome() != null ? qDyInventory.getIncome() * 0.01 : 0;
            inTotal = beforeArrears + beforeChange + beforePurch + income;

            // 销
            expend = qDyInventory.getExpend() != null ? qDyInventory.getExpend() * 0.01 : 0;
            dailyCash = qDyInventory.getDailyCash() != null ? qDyInventory.getDailyCash() * 0.01 : 0;
            outTotal = expend + dailyCash;

            // 存
            safe = qDyInventory.getSafe() != null ? qDyInventory.getSafe() * 0.01 : 0;
            arrears = qDyInventory.getArrears() != null ? qDyInventory.getArrears() * 0.01 : 0;
            change = qDyInventory.getChange() != null ? qDyInventory.getChange() * 0.01 : 0;
            purch = qDyInventory.getPurch() != null ? qDyInventory.getPurch() * 0.01 : 0;
            saveTotal = safe + arrears + change + purch;

            report.put("beforeSafe", beforeSafe);
            report.put("beforeArrears", beforeArrears);
            report.put("beforeChange", beforeChange);
            report.put("beforePurch", beforePurch);
            report.put("income", income);
            report.put("inTotal", inTotal);

            report.put("expend", expend);
            report.put("dailyCash", dailyCash);
            report.put("outTotal", outTotal);

            report.put("safe", safe);
            report.put("arrears", arrears);
            report.put("change", change);
            report.put("purch", purch);
            report.put("saveTotal", saveTotal);

            total = outTotal + saveTotal - inTotal;
            report.put("pl", total > 0 ? "盈" : "亏");
            report.put("total", total);
        }

        report.put("year", query.getYear());
        report.put("quarterly", query.getQuarterly());
        report.put("regionStockName", getRegionStockName(query.getStockId()));
        report.put("stockName", getStockName(query.getStockId()));
        return report;
    }

    @Override
    public FinQDyInventory saveOrUpdateQuarterly(FinQDyInventory qDyInventory) {
        if (qDyInventory != null) {

            try {
                FinQInventory qInventory = new FinQInventory();
                qInventory.setYearId(qDyInventory.getYearId());
                qInventory.setQuarterlyId(qDyInventory.getQuarterlyId());
                qInventory.setStartDate(TimeUtils.StringToDate(qDyInventory.getStartDate(), "yyyy-MM-dd"));
                qInventory.setEndDate(TimeUtils.StringToDate(qDyInventory.getEndDate(), "yyyy-MM-dd"));
                this.finQInventoryService.savePart(qInventory);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            // 查询上一季度盘点情况
            FinQDyInventory finQInventory = this.mapper.getBeforeQDyInventory(qDyInventory.getStockId());
            if (finQInventory != null) {
                // 上季度盘点信息
                qDyInventory.setBeforeArrears(finQInventory.getArrears());
                qDyInventory.setBeforeChange(finQInventory.getChange());
                qDyInventory.setBeforePurch(finQInventory.getBeforePurch());
                qDyInventory.setBeforeSafe(finQInventory.getBeforeSafe());

                List<FinDyDaily> dyDailies = this.finDyDailyService.getDyDailyList(qDyInventory.getStartDate(), qDyInventory.getEndDate(), qDyInventory.getStockId());
                if (dyDailies != null) {
                    double incomeTotal = 0;
                    double expendTotal = 0;
                    double arrears = 0;

                    for (FinDyDaily dyDaily : dyDailies) {
                        incomeTotal += dyDaily.getIncome() != null ? dyDaily.getIncome() : 0;
                        expendTotal += dyDaily.getExpendSubTotal() != null ? dyDaily.getExpendSubTotal() : 0;
                    }
                    qDyInventory.setExpend(expendTotal);
                    qDyInventory.setDailyCash(incomeTotal);

                    arrears = dyDailies.get(dyDailies.size() - 1) != null ? (double) dyDailies.get(dyDailies.size() - 1).getArrears() : 0;
                    qDyInventory.setArrears(arrears);
                }
            }

            qDyInventory.setChange(qDyInventory.getChange() != null ? qDyInventory.getChange() * 100 : 0);
            qDyInventory.setPurch(qDyInventory.getPurch() != null ? qDyInventory.getPurch() * 100 : 0);
            this.mapper.savePart(qDyInventory);
        }
        return qDyInventory;
    }

    private String getRegionStockName(Long stockId) {
        BisStock bisStock = this.bisStockService.get(stockId);
        return this.bisStockService.get(bisStock.getParentId()).getName();
    }

    private String getStockName(Long stockId) {
        return this.bisStockService.get(stockId).getName();
    }

}
