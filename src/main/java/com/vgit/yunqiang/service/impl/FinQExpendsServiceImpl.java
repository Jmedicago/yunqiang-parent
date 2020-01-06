package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.query.ReportQuery;
import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.BaseService;
import com.vgit.yunqiang.common.service.impl.BaseServiceImpl;
import com.vgit.yunqiang.mapper.FinQExpendsMapper;
import com.vgit.yunqiang.pojo.*;
import com.vgit.yunqiang.service.BisStockService;
import com.vgit.yunqiang.service.FinQExpendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

@Service
public class FinQExpendsServiceImpl extends BaseServiceImpl<FinQExpends> implements FinQExpendsService {

    @Autowired
    private FinQExpendsMapper mapper;

    @Autowired
    private BisStockService bisStockService;

    @Override
    protected BaseMapper<FinQExpends> getMapper() {
        return this.mapper;
    }

    @Override
    public List<FinQuarterly> getQExpendsList(String year, Long stockId) {
        return this.mapper.getQExpendsList(year, stockId);
    }

    @Override
    public List<BisStock> getExpendsList(String year, String quarterly, Long stockId) {
        return this.mapper.getExpendsList(year, quarterly, stockId);
    }

    @Override
    public Hashtable<String, Object> queryQExpendsReport(ReportQuery query) {
        Hashtable<String, Object> report = new Hashtable<String, Object>();
        double expendTotal = 0;
        double expendA = 0;
        double expendB = 0;
        double expendD = 0;
        double expendE = 0;
        double expendF = 0;
        double expendG = 0;
        double expendH = 0;
        double expendI = 0;
        double expendJ = 0;

        List<Hashtable<String, Object>> quarterlyListTable = new ArrayList<Hashtable<String, Object>>();
        List<FinQuarterly> quarterlyList = this.mapper.getQExpendsList(query.getYear(), query.getStockId());
        for (FinQuarterly finQuarterly : quarterlyList) {
            List<Hashtable<String, Object>> stockList = new ArrayList<Hashtable<String, Object>>();
            double regionStockExpendsTotal = 0;

            if (finQuarterly != null) {
                for (BisStock bisStock : finQuarterly.getBisStockList()) {
                    if (bisStock != null) {
                        Hashtable<String, Object> stock = new Hashtable<String, Object>();
                        double stockExpendsTotal = 0;

                        for (FinQExpends qExpends : bisStock.getqExpendsList()) {
                            if (qExpends != null) {
                                qExpends.setAmount(qExpends.getAmount() * 0.01);
                                stockExpendsTotal += qExpends.getAmount();

                                if ("A".equals(qExpends.getExpendItem().getCategory())) {
                                    expendA += qExpends.getAmount();
                                }
                                if ("B".equals(qExpends.getExpendItem().getCategory())) {
                                    expendB += qExpends.getAmount();
                                }
                                if ("D".equals(qExpends.getExpendItem().getCategory())) {
                                    expendD += qExpends.getAmount();
                                }
                                if ("E".equals(qExpends.getExpendItem().getCategory())) {
                                    expendE += qExpends.getAmount();
                                }
                                if ("F".equals(qExpends.getExpendItem().getCategory())) {
                                    expendF += qExpends.getAmount();
                                }
                                if ("G".equals(qExpends.getExpendItem().getCategory())) {
                                    expendG += qExpends.getAmount();
                                }
                                if ("H".equals(qExpends.getExpendItem().getCategory())) {
                                    expendH += qExpends.getAmount();
                                }
                                if ("I".equals(qExpends.getExpendItem().getCategory())) {
                                    expendI += qExpends.getAmount();
                                }
                                if ("J".equals(qExpends.getExpendItem().getCategory())) {
                                    expendJ += qExpends.getAmount();
                                }
                            }
                        }

                        regionStockExpendsTotal += stockExpendsTotal;

                        stock.put("name", bisStock.getName());
                        stock.put("stockExpendsTotal", stockExpendsTotal);
                        stock.put("expendsList", bisStock.getqExpendsList());
                        stockList.add(stock);
                    }
                }
            }

            expendTotal += regionStockExpendsTotal;

            Hashtable<String, Object> quarterly = new Hashtable<String, Object>();
            quarterly.put("name", finQuarterly.getName());
            quarterly.put("regionStockExpendsTotal", regionStockExpendsTotal);
            quarterly.put("stockList", stockList);
            quarterlyListTable.add(quarterly);
        }

        report.put("year", query.getYear());
        report.put("stockName", getStockName(query.getStockId()));
        report.put("quarterlyList", quarterlyListTable);
        report.put("expendTotal", expendTotal);
        report.put("expendA", expendA);
        report.put("expendB", expendB);
        report.put("expendD", expendD);
        report.put("expendE", expendE);
        report.put("expendF", expendF);
        report.put("expendG", expendG);
        report.put("expendH", expendH);
        report.put("expendI", expendI);
        report.put("expendJ", expendJ);
        return report;
    }

    @Override
    public Hashtable<String, Object> queryExpendsReport(ReportQuery query) {
        Hashtable<String, Object> report = new Hashtable<String, Object>();
        double expendTotal = 0;
        double expendA = 0;
        double expendB = 0;
        double expendD = 0;
        double expendE = 0;
        double expendF = 0;
        double expendG = 0;
        double expendH = 0;
        double expendI = 0;
        double expendJ = 0;

        List<Hashtable<String, Object>> stockRegionList = new ArrayList<Hashtable<String, Object>>();
        List<BisStock> bisStockList = this.bisStockService.getRegionStockList();
        for (BisStock bisStock : bisStockList) {
            if (bisStock != null) {
                List<Hashtable<String, Object>> stockList = new ArrayList<Hashtable<String, Object>>();
                double regionExpendsTotal = 0;

                List<BisStock> childStockList = this.getExpendsList(query.getYear(), query.getQuarterly(), bisStock.getId());
                for (BisStock childStock : childStockList) {
                    if (childStock != null) {
                        double stockExpendsTotal = 0;

                        for (FinQExpends qExpends : childStock.getqExpendsList()) {
                            if (qExpends != null) {
                                qExpends.setAmount(qExpends.getAmount() * 0.01);
                                stockExpendsTotal += qExpends.getAmount();

                                if ("A".equals(qExpends.getExpendItem().getCategory())) {
                                    expendA += qExpends.getAmount();
                                }
                                if ("B".equals(qExpends.getExpendItem().getCategory())) {
                                    expendB += qExpends.getAmount();
                                }
                                if ("D".equals(qExpends.getExpendItem().getCategory())) {
                                    expendD += qExpends.getAmount();
                                }
                                if ("E".equals(qExpends.getExpendItem().getCategory())) {
                                    expendE += qExpends.getAmount();
                                }
                                if ("F".equals(qExpends.getExpendItem().getCategory())) {
                                    expendF += qExpends.getAmount();
                                }
                                if ("G".equals(qExpends.getExpendItem().getCategory())) {
                                    expendG += qExpends.getAmount();
                                }
                                if ("H".equals(qExpends.getExpendItem().getCategory())) {
                                    expendH += qExpends.getAmount();
                                }
                                if ("I".equals(qExpends.getExpendItem().getCategory())) {
                                    expendI += qExpends.getAmount();
                                }
                                if ("J".equals(qExpends.getExpendItem().getCategory())) {
                                    expendJ += qExpends.getAmount();
                                }

                            }
                        }

                        regionExpendsTotal += stockExpendsTotal;

                        // 店
                        Hashtable<String, Object> stock = new Hashtable<String, Object>();
                        stock.put("name", childStock.getName());
                        stock.put("stockExpendsTotal", stockExpendsTotal);
                        stock.put("details", childStock.getqExpendsList());
                        stockList.add(stock);
                    }
                }

                expendTotal += regionExpendsTotal;

                // 区域
                Hashtable<String, Object> stockRegion = new Hashtable<String, Object>();
                stockRegion.put("name", bisStock.getName());
                stockRegion.put("regionExpendsTotal", regionExpendsTotal);
                stockRegion.put("stockList", stockList);
                stockRegionList.add(stockRegion);
            }
        }
        report.put("year", query.getYear());
        report.put("quarterly", query.getQuarterly());
        report.put("stockRegionList", stockRegionList);
        report.put("expendTotal", expendTotal);
        report.put("expendA", expendA);
        report.put("expendB", expendB);
        report.put("expendD", expendD);
        report.put("expendE", expendE);
        report.put("expendF", expendF);
        report.put("expendG", expendG);
        report.put("expendH", expendH);
        report.put("expendI", expendI);
        report.put("expendJ", expendJ);
        return report;
    }

    private String getStockName(Long stockId) {
        return this.bisStockService.get(stockId).getName();
    }

}
