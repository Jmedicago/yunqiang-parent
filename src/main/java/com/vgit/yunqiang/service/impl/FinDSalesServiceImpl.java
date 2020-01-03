package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.query.ReportQuery;
import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.impl.BaseServiceImpl;
import com.vgit.yunqiang.mapper.FinDSalesMapper;
import com.vgit.yunqiang.pojo.BisStock;
import com.vgit.yunqiang.pojo.FinDSales;
import com.vgit.yunqiang.service.BisStockService;
import com.vgit.yunqiang.service.FinDSalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

@Service
public class FinDSalesServiceImpl extends BaseServiceImpl<FinDSales> implements FinDSalesService {

    @Autowired
    private FinDSalesMapper mapper;

    @Autowired
    private BisStockService bisStockService;

    @Override
    protected BaseMapper<FinDSales> getMapper() {
        return this.mapper;
    }

    @Override
    public List<BisStock> getDSalesList(Long stockId, String year, String month) {
        return this.mapper.getDSalesList(stockId, year, month);
    }

    @Override
    public Hashtable<String, Object> queryDSalesReport(ReportQuery query) {
        Hashtable<String, Object> report = new Hashtable<String, Object>();
        double allSalesTotal = 0;
        double oneDay = 0;
        double twoDay =0;
        double threeDay = 0;
        double fourDay = 0;
        double fiveDay = 0;
        double sixDay = 0;
        double sevenDay = 0;
        double eightDay = 0;
        double nineDay = 0;
        double tenDay = 0;
        double elevenDay = 0;
        double twelveDay = 0;
        double thirteenDay = 0;
        double fourteenDay =0;
        double fifteenDay = 0;
        double sixteenDay = 0;
        double seventeenDay = 0;
        double eighteenDay = 0;
        double nineteenDay = 0;
        double twentyDay = 0;
        double twentyOneDay = 0;
        double twentyTwoDay = 0;
        double twentyThreeDay = 0;
        double twentyFourDay = 0;
        double twentyFiveDay = 0;
        double twentySixDay = 0;
        double twentySevenDay = 0;
        double twentyEightDay = 0;
        double twentyNineDay = 0;
        double thirtyDay = 0;
        double thirtyOneDay = 0;

        List<Hashtable<String, Object>> stockRegionList = new ArrayList<Hashtable<String, Object>>();
        List<BisStock> bisStockList = this.bisStockService.getRegionStockList();
        for (BisStock bisStock : bisStockList) {
            double stockRegionTotal = 0;
            List<Hashtable<String, Object>> stockList = new ArrayList<Hashtable<String, Object>>();

            if (bisStock != null) {
                List<BisStock> childStockList = this.getDSalesList(bisStock.getId(), query.getYear(), query.getMonth());
                for (BisStock childStock : childStockList) {
                    double stockTotal = 0; // 店月销售额

                    if (childStock != null) {
                        for (FinDSales dSales : childStock.getSalesList()) {
                            dSales.setAmount(dSales.getAmount() * 0.01);
                            stockTotal += dSales.getAmount();

                            if (dSales.getDayId() == 1) {
                                oneDay += dSales.getAmount();
                            }
                            if (dSales.getDayId() == 2) {
                                twoDay += dSales.getAmount();
                            }
                            if (dSales.getDayId() == 3) {
                                threeDay += dSales.getAmount();
                            }
                            if (dSales.getDayId() == 4) {
                                fourDay += dSales.getAmount();
                            }
                            if (dSales.getDayId() == 5) {
                                fiveDay += dSales.getAmount();
                            }
                            if (dSales.getDayId() == 6) {
                                sixDay += dSales.getAmount();
                            }
                            if (dSales.getDayId() == 7) {
                                sevenDay += dSales.getAmount();
                            }
                            if (dSales.getDayId() == 8) {
                                eightDay += dSales.getAmount();
                            }
                            if (dSales.getDayId() == 9) {
                                nineDay += dSales.getAmount();
                            }
                            if (dSales.getDayId() == 10) {
                                tenDay += dSales.getAmount();
                            }
                            if (dSales.getDayId() == 11) {
                                elevenDay += dSales.getAmount();
                            }
                            if (dSales.getDayId() == 12) {
                                twelveDay += dSales.getAmount();
                            }
                            if (dSales.getDayId() == 13) {
                                thirteenDay += dSales.getAmount();
                            }
                            if (dSales.getDayId() == 14) {
                                fourteenDay += dSales.getAmount();
                            }
                            if (dSales.getDayId() == 15) {
                                fifteenDay += dSales.getAmount();
                            }
                            if (dSales.getDayId() == 16) {
                                sixteenDay += dSales.getAmount();
                            }
                            if (dSales.getDayId() == 17) {
                                seventeenDay += dSales.getAmount();
                            }
                            if (dSales.getDayId() == 18) {
                                eighteenDay += dSales.getAmount();
                            }
                            if (dSales.getDayId() == 19) {
                                nineteenDay += dSales.getAmount();
                            }
                            if (dSales.getDayId() == 20) {
                                twentyDay += dSales.getAmount();
                            }
                            if (dSales.getDayId() == 21) {
                                twentyOneDay += dSales.getAmount();
                            }
                            if (dSales.getDayId() == 22) {
                                twentyTwoDay += dSales.getAmount();
                            }
                            if (dSales.getDayId() == 23) {
                                twentyThreeDay += dSales.getAmount();
                            }
                            if (dSales.getDayId() == 24) {
                                twentyFourDay += dSales.getAmount();
                            }
                            if (dSales.getDayId() == 25) {
                                twentyFiveDay += dSales.getAmount();
                            }
                            if (dSales.getDayId() == 26) {
                                twentySixDay += dSales.getAmount();
                            }
                            if (dSales.getDayId() == 27) {
                                twentySevenDay += dSales.getAmount();
                            }
                            if (dSales.getDayId() == 28) {
                                twentyEightDay += dSales.getAmount();
                            }
                            if (dSales.getDayId() == 29) {
                                twentyNineDay += dSales.getAmount();
                            }
                            if (dSales.getDayId() == 30) {
                                thirtyDay += dSales.getAmount();
                            }
                            if (dSales.getDayId() == 31) {
                                thirtyOneDay += dSales.getAmount();
                            }

                        }
                        // 店
                        Hashtable<String, Object> stock = new Hashtable<String, Object>();
                        stock.put("name", childStock.getName());
                        stock.put("stockTotal", stockTotal);
                        stock.put("details", childStock.getSalesList());
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

        allSalesTotal = oneDay + twoDay + threeDay + fourDay + fiveDay + sixDay + sevenDay + eightDay + nineDay + tenDay
                + elevenDay + twelveDay + thirteenDay + fourteenDay + fifteenDay + sixteenDay + seventeenDay + eighteenDay
                + nineteenDay + twentyDay + twentyOneDay + twentyTwoDay + twentyThreeDay + twentyFourDay + twentyFiveDay
                + twentySixDay + twentySevenDay + twentyEightDay + twentyNineDay + thirtyDay + thirtyOneDay;

        report.put("year", query.getYear());
        report.put("month", query.getMonth());
        report.put("stockRegionList", stockRegionList);
        report.put("allSalesTotal", allSalesTotal);

        report.put("oneDay", oneDay);
        report.put("twoDay", twoDay);
        report.put("threeDay", threeDay);
        report.put("fourDay", fourDay);
        report.put("fiveDay", fiveDay);
        report.put("sixDay", sixDay);
        report.put("sevenDay", sevenDay);
        report.put("eightDay", eightDay);
        report.put("nineDay", nineDay);
        report.put("tenDay", tenDay);
        report.put("elevenDay", elevenDay);
        report.put("twelveDay", twelveDay);
        report.put("thirteenDay", thirteenDay);
        report.put("fourteenDay", fourteenDay);
        report.put("fifteenDay", fifteenDay);
        report.put("sixteenDay", sixteenDay);
        report.put("seventeenDay", seventeenDay);
        report.put("eighteenDay", eighteenDay);
        report.put("nineteenDay", nineteenDay);
        report.put("twentyDay", twentyDay);
        report.put("twentyOneDay", twentyOneDay);
        report.put("twentyTwoDay", twentyTwoDay);
        report.put("twentyThreeDay", twentyThreeDay);
        report.put("twentyFourDay", twentyFourDay);
        report.put("twentyFiveDay", twentyFiveDay);
        report.put("twentySixDay", twentySixDay);
        report.put("twentySevenDay", twentySevenDay);
        report.put("twentyEightDay", twentyEightDay);
        report.put("twentyNineDay", twentyNineDay);
        report.put("thirtyDay", thirtyDay);
        report.put("thirtyOneDay", thirtyOneDay);
        return report;
    }

}
