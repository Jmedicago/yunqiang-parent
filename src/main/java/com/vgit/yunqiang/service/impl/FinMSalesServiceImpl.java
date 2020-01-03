package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.query.ReportQuery;
import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.impl.BaseServiceImpl;
import com.vgit.yunqiang.mapper.FinMSalesMapper;
import com.vgit.yunqiang.pojo.BisStock;
import com.vgit.yunqiang.pojo.FinMSales;
import com.vgit.yunqiang.service.BisStockService;
import com.vgit.yunqiang.service.FinMSalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

@Service
public class FinMSalesServiceImpl extends BaseServiceImpl<FinMSales> implements FinMSalesService {

    @Autowired
    private FinMSalesMapper mapper;

    @Autowired
    private BisStockService bisStockService;

    @Override
    protected BaseMapper<FinMSales> getMapper() {
        return this.mapper;
    }

    @Override
    public List<BisStock> getMSalesList(Long stockId, String year) {
        return this.mapper.getMSalesList(stockId, year);
    }

    @Override
    public Hashtable<String, Object> queryDSalesReport(ReportQuery query) {
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

        List<Hashtable<String, Object>> stockRegionList = new ArrayList<Hashtable<String, Object>>();
        List<BisStock> bisStockList = this.bisStockService.getRegionStockList();
        for (BisStock bisStock : bisStockList) {
            double stockRegionTotal = 0;
            List<Hashtable<String, Object>> stockList = new ArrayList<Hashtable<String, Object>>();

            if (bisStock != null) {
                List<BisStock> childStockList = this.getMSalesList(bisStock.getId(), query.getYear());
                for (BisStock childStock : childStockList) {
                    double stockTotal = 0; // 店月销售额

                    if (childStock != null) {
                        for (FinMSales mSales : childStock.getmSalesList()) {
                            mSales.setAmount(mSales.getAmount() * 0.01);
                            stockTotal += mSales.getAmount();

                            if (mSales.getMonthId() == 1) {
                                janTotal += mSales.getAmount();
                            }
                            if (mSales.getMonthId() == 2) {
                                febTotal += mSales.getAmount();
                            }
                            if (mSales.getMonthId() == 3) {
                                marTotal += mSales.getAmount();
                            }
                            if (mSales.getMonthId() == 4) {
                                aprTotal += mSales.getAmount();
                            }
                            if (mSales.getMonthId() == 5) {
                                mayTotal += mSales.getAmount();
                            }
                            if (mSales.getMonthId() == 6) {
                                junTotal += mSales.getAmount();
                            }
                            if (mSales.getMonthId() == 7) {
                                julTotal += mSales.getAmount();
                            }
                            if (mSales.getMonthId() == 8) {
                                augTotal += mSales.getAmount();
                            }
                            if (mSales.getMonthId() == 9) {
                                septTotal += mSales.getAmount();
                            }
                            if (mSales.getMonthId() == 10) {
                                octTotal += mSales.getAmount();
                            }
                            if (mSales.getMonthId() == 11) {
                                novTotal += mSales.getAmount();
                            }
                            if (mSales.getMonthId() == 12) {
                                decTotal += mSales.getAmount();
                            }

                        }
                        // 店
                        Hashtable<String, Object> stock = new Hashtable<String, Object>();
                        stock.put("name", childStock.getName());
                        stock.put("stockTotal", stockTotal);
                        stock.put("details", childStock.getmSalesList());
                        stockList.add(stock);
                    }

                    // 区域月销售额
                    stockRegionTotal += stockTotal;
                }

                // 区域
                Hashtable<String, Object> stockRegion = new Hashtable<String, Object>();
                stockRegion.put("name", bisStock.getName());
                stockRegion.put("stockRegionTotal", stockRegionTotal);
                stockRegion.put("stockList", stockList);
                stockRegionList.add(stockRegion);
            }
        }

        allMonthTotal = janTotal + febTotal + marTotal + aprTotal + mayTotal + junTotal + +julTotal + augTotal + septTotal
                + octTotal + novTotal + decTotal;

        report.put("year", query.getYear());
        report.put("stockRegionList", stockRegionList);
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
