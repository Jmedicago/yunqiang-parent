package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.exception.BisException;
import com.vgit.yunqiang.common.query.ReportQuery;
import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.impl.BaseServiceImpl;
import com.vgit.yunqiang.common.utils.TimeUtils;
import com.vgit.yunqiang.mapper.FinDyDailyMapper;
import com.vgit.yunqiang.pojo.FinDailyExpend;
import com.vgit.yunqiang.pojo.FinDyDaily;
import com.vgit.yunqiang.service.BisStockService;
import com.vgit.yunqiang.service.FinDyDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

@Service
public class FinDyDailyServiceImpl extends BaseServiceImpl<FinDyDaily> implements FinDyDailyService {

    @Autowired
    private FinDyDailyMapper mapper;

    @Autowired
    private BisStockService bisStockService;

    @Override
    protected BaseMapper<FinDyDaily> getMapper() {
        return this.mapper;
    }

    @Override
    public List<FinDyDaily> queryDailyList(Long stockId) {
        return this.mapper.queryDailyList(stockId);
    }

    @Override
    public Hashtable<String, Object> genDyDailyReport(ReportQuery query) {
        Hashtable<String, Object> report = new Hashtable<String, Object>();
        double incomeTotal = 0; // 进
        double expendTotal = 0; // 出
        double purchTotal = 0; //本季度累计上货
        double arrearsTotal = 0; // 客商总额
        double salesTotal = 0; // 本季度累计销售额
        List<Hashtable<String, Object>> details = new ArrayList<Hashtable<String, Object>>();

        if (query.getStockId() == null) {
            return report;
        }

        List<FinDyDaily> dailies = this.mapper.queryDailyList(query.getStockId());
        for (FinDyDaily daily : dailies) {
            double income = daily.getIncome() != null ? daily.getIncome() * 0.01 : 0;
            double expendSubTotal = daily.getExpendSubTotal() != null ? daily.getExpendSubTotal() * 0.01 : 0;
            double purch = daily.getPurch() != null ? daily.getPurch() * 0.01 : 0;
            double arrears = daily.getArrears() != null ? daily.getArrears() * 0.01 : 0;
            double sales = daily.getSales() != null ? daily.getSales() * 0.01 : 0;

            // 汇总
            incomeTotal += income;
            expendTotal += expendSubTotal;
            purchTotal += purch;
            salesTotal += sales;

            // 明细
            Hashtable<String, Object> detail = new Hashtable<String, Object>();
            detail.put("date", TimeUtils.dateFormat(daily.getDate(), "yyyy\\MM\\dd"));
            detail.put("income", income);
            detail.put("expendSubTotal", expendSubTotal);
            detail.put("purch", purch);
            detail.put("arrears", arrears);
            detail.put("sales", sales);
            List<FinDailyExpend> dailyExpends = daily.getFinDailyExpendList();
            for (FinDailyExpend dailyExpend : dailyExpends) {
                dailyExpend.setAmount(dailyExpend.getAmount() != null ? dailyExpend.getAmount() * 0.01 : 0);
            }
            detail.put("details", dailyExpends);
            details.add(detail);
        }

        // 自动更新最后一天当日该店员录入的欠款
        if (dailies != null && dailies.size() > 0) {
            arrearsTotal = dailies.get(dailies.size() - 1).getArrears() != null ? dailies.get(dailies.size() - 1).getArrears() * 0.01 : 0;
        }

        report.put("stockName", getStockName(query.getStockId()));
        report.put("incomeTotal", incomeTotal);
        report.put("expendTotal", expendTotal);
        report.put("purchTotal", purchTotal);
        report.put("arrearsTotal", arrearsTotal);
        report.put("salesTotal", salesTotal);
        report.put("details", details);
        return report;
    }

    @Override
    public FinDyDaily saveOrUpdateDaily(FinDyDaily dyDaily) throws BisException {
        double income = dyDaily.getIncome() != null ? dyDaily.getIncome() * 100 : 0;
        double purch = dyDaily.getPurch() != null ? dyDaily.getPurch() * 100 : 0;
        double arrears = dyDaily.getArrears() != null ? dyDaily.getArrears() * 100 : 0;
        double sales =  dyDaily.getSales() != null ? dyDaily.getSales() * 100 : 0;

        if (dyDaily.getId() == null) {
            if (exits(dyDaily)) {
                throw new BisException().setInfo("今日已填报，不能添加！");
            }

            dyDaily.setIncome(income);
            dyDaily.setPurch(purch);
            dyDaily.setArrears(arrears);
            dyDaily.setSales(sales);
            dyDaily.setDate(new Date());
            dyDaily.setCreateTime(System.currentTimeMillis());
            this.mapper.savePart(dyDaily);
            dyDaily.setCode("Y" + dyDaily.getId());
            this.mapper.updatePart(dyDaily);
        } else {
            dyDaily.setIncome(income);
            dyDaily.setPurch(purch);
            dyDaily.setArrears(arrears);
            dyDaily.setSales(sales);
            //dyDaily.setDate(new Date());
            dyDaily.setCreateTime(System.currentTimeMillis());
            this.mapper.updatePart(dyDaily);
        }
        dyDaily = this.mapper.get(dyDaily.getId());
        return dyDaily;
    }

    private boolean exits(FinDyDaily dyDaily) {
        int count = this.mapper.exits(dyDaily.getStockId());
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public FinDyDaily getByCode(String dailyCode) {
        return this.mapper.getByCode(dailyCode);
    }

    private String getStockName(Long stockId) {
        return this.bisStockService.get(stockId).getName();
    }

}
