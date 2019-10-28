package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.consts.bis.StockDailyTypeConsts;
import com.vgit.yunqiang.common.exception.BisException;
import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.impl.BaseServiceImpl;
import com.vgit.yunqiang.common.utils.TimeUtils;
import com.vgit.yunqiang.mapper.FinStockQuarterlyMapper;
import com.vgit.yunqiang.pojo.FinStockQuarterly;
import com.vgit.yunqiang.service.FinStockQuarterlyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class FinStockQuarterlyServiceImpl extends BaseServiceImpl<FinStockQuarterly> implements FinStockQuarterlyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FinStockQuarterlyServiceImpl.class);

    private static final double DEFAULT_VALUES = 0;

    @Autowired
    private FinStockQuarterlyMapper mapper;

    @Override
    protected BaseMapper<FinStockQuarterly> getMapper() {
        return this.mapper;
    }

    @Override
    public FinStockQuarterly saveOrUpdateQuarterly(FinStockQuarterly stockQuarterly) {
        if (stockQuarterly.getId() == null) {
            if (this.exist(System.currentTimeMillis(), stockQuarterly.getType(), stockQuarterly.getStockId())) {
                throw new BisException().setInfo("当前季度表报已存在！");
            } else {
                if (StockDailyTypeConsts.CHILD_STOCK_DAILY == stockQuarterly.getType()) {
                    this.init(stockQuarterly);
                } else if (StockDailyTypeConsts.REGION_STOCK_DAILY == stockQuarterly.getType()) {
                    this.initRegion(stockQuarterly);
                } else if (StockDailyTypeConsts.WMS_STOCK_DAILY == stockQuarterly.getType()) {
                    this.initWms(stockQuarterly);
                }
                stockQuarterly.setCreateTime(System.currentTimeMillis());
                this.savePart(stockQuarterly);
            }
        } else {
            if (this.validateQuarter(stockQuarterly)) {
                stockQuarterly.setUpdateTime(System.currentTimeMillis());
                this.updatePart(stockQuarterly);
            } else {
                throw new BisException().setInfo("已经超过当前季度填报时间");
            }
        }
        return stockQuarterly;
    }

    private boolean validateQuarter(FinStockQuarterly stockQuarterly) {
        Long[] times = TimeUtils.getQuarterStartAndEndTime();
        long startTime = times[0];
        long endTime = times[1];
        if (stockQuarterly.getCreateTime() >= startTime && stockQuarterly.getCreateTime() <= endTime) {
            return true;
        }
        return false;
    }

    private void initWms(FinStockQuarterly stockQuarterly) {

    }

    private void initRegion(FinStockQuarterly stockQuarterly) {
        String year = String.valueOf(TimeUtils.getNowYear());
        String quarter = String.valueOf(TimeUtils.getNowSeason());

        stockQuarterly.setYear(year); // 当前年份
        stockQuarterly.setQuarterly(quarter); // 当前季度

        // 当前季度
        stockQuarterly.setSafe(this.getSafe(stockQuarterly.getStockId(), quarter, year, stockQuarterly.getType())); // 保险柜现金
        stockQuarterly.setArrears(this.getArrearsTotal(stockQuarterly.getStockId(), quarter, year)); // 欠款
        stockQuarterly.setChanges(this.getChangesTotal(stockQuarterly.getStockId(), quarter, year)); // 零钱
        stockQuarterly.setInventory(this.getInventoryTotal(stockQuarterly.getStockId(), quarter, year)); // 库存货值
        stockQuarterly.setPurchTotal(this.getPurchTotalAll(stockQuarterly.getStockId(), quarter, year)); // 进货总值

        // 上季度
        FinStockQuarterly prevStockQuarter = this.getPrevQuarter(year, quarter, stockQuarterly.getType(), stockQuarterly.getStockId());
        if (prevStockQuarter != null) {
            stockQuarterly.setBeforeSafe(prevStockQuarter.getSafe()); // 上季度保险柜现金
            stockQuarterly.setBeforeArrears(prevStockQuarter.getArrears()); // 上季度欠款
            stockQuarterly.setBeforeChange(prevStockQuarter.getChanges()); // 上季度零钱
            stockQuarterly.setBeforeInventory(prevStockQuarter.getBeforeInventory()); // 上季度库存货值
        } else {
            stockQuarterly.setBeforeSafe(DEFAULT_VALUES);
            stockQuarterly.setBeforeArrears(DEFAULT_VALUES);
            stockQuarterly.setBeforeChange(DEFAULT_VALUES);
            stockQuarterly.setBeforeInventory(DEFAULT_VALUES);
        }

        // 出
        stockQuarterly.setExpendTotal(this.getExpendTotal(stockQuarterly.getStockId(), stockQuarterly.getType(), quarter, year));
        stockQuarterly.setDepositTotal(this.getDepositTotal(stockQuarterly.getStockId(), quarter, year));

        // 盈亏
        double pl = 0;
        double out = 0;
        double in =0;
        double save = 0;

        out = stockQuarterly.getExpendTotal() + stockQuarterly.getDepositTotal();
        LOGGER.info("出：{}", out);
        save = stockQuarterly.getSafe() + stockQuarterly.getArrears() + stockQuarterly.getChanges() + stockQuarterly.getInventory();
        LOGGER.info("存：{}", save);
        in = stockQuarterly.getBeforeSafe() + stockQuarterly.getPurchTotal() + stockQuarterly.getBeforeArrears() + stockQuarterly.getBeforeChange() + stockQuarterly.getBeforeInventory();
        LOGGER.info("进：{}", in);

        pl = out + save - in;
        stockQuarterly.setPl(pl);
    }

    private double getDepositTotal(Long stockId, String quarter, String year) {
        Long[] times = TimeUtils.getQuarterStartAndEndTime(Integer.valueOf(quarter), Integer.valueOf(year));
        long startTime = times[0];
        long endTime = times[1];
        Double depositTotal = this.mapper.getDepositTotal(stockId, startTime, endTime);
        if (depositTotal != null) {
            return depositTotal;
        }
        return 0;
    }

    private double getExpendTotal(Long stockId, Integer type, String quarter, String year) {
        Long[] times = TimeUtils.getQuarterStartAndEndTime(Integer.valueOf(quarter), Integer.valueOf(year));
        long startTime = times[0];
        long endTime = times[1];
        Double expendTotal = this.mapper.getExpendTotal(stockId, type, startTime, endTime);
        if (expendTotal != null) {
            return expendTotal;
        }
        return 0;
    }

    private double getPurchTotalAll(Long stockId, String quarter, String year) {
        Double purchTotalAll = this.mapper.getPurchTotalAll(stockId, year, quarter);
        if (purchTotalAll != null) {
            return purchTotalAll;
        }
        return 0;
    }

    private double getInventoryTotal(Long stockId, String quarter, String year) {
        Double inventoryTotal = this.mapper.getInventoryTotal(stockId, year, quarter);
        if (inventoryTotal != null) {
            return inventoryTotal;
        }
        return 0;
    }

    private double getChangesTotal(Long stockId, String quarter, String year) {
        Double changesTotal = this.mapper.getChangesTotal(stockId, year, quarter);
        if (changesTotal != null) {
            return changesTotal;
        }
        return 0;
    }

    private double getArrearsTotal(Long stockId, String quarter, String year) {
        Double arrearsTotal = this.mapper.getArrearsTotal(stockId, year, quarter);
        if (arrearsTotal != null) {
            return arrearsTotal;
        }
        return 0;
    }

    private double getSafe(Long stockId, String quarter, String year, Integer type) {
        Long[] times = TimeUtils.getQuarterStartAndEndTime(Integer.valueOf(quarter), Integer.valueOf(year));
        long startTime = times[0];
        long endTime = times[1];
        Double safe = this.mapper.getSafe(stockId, type, startTime, endTime);
        if (safe != null) {
            return safe;
        }
        return 0;
    }

    private void init(FinStockQuarterly stockQuarterly) {
        String year = String.valueOf(TimeUtils.getNowYear());
        String quarter = String.valueOf(TimeUtils.getNowSeason());

        stockQuarterly.setYear(year); // 当前年份
        stockQuarterly.setQuarterly(quarter); // 当前季度

        // 当前季度
        stockQuarterly.setPurchTotal(this.getPurchTotal(stockQuarterly.getStockId(), quarter, year, stockQuarterly.getType()));
        stockQuarterly.setSalesTotal(this.getSalesTotal(stockQuarterly.getStockId(), quarter, year, stockQuarterly.getType()));
        stockQuarterly.setArrears(this.getArrears(stockQuarterly.getStockId(), quarter, year, stockQuarterly.getType()));

        // 上季度
        FinStockQuarterly prevStockQuarter = this.getPrevQuarter(year, quarter, stockQuarterly.getType(), stockQuarterly.getStockId());
        if (prevStockQuarter != null) {
            stockQuarterly.setBeforeArrears(prevStockQuarter.getArrears());
            stockQuarterly.setBeforeChange(prevStockQuarter.getChanges());
            stockQuarterly.setBeforeInventory(prevStockQuarter.getInventory());
        } else {
            stockQuarterly.setBeforeArrears(DEFAULT_VALUES);
            stockQuarterly.setBeforeChange(DEFAULT_VALUES);
            stockQuarterly.setBeforeInventory(DEFAULT_VALUES);
        }

        // 盈亏
        double pl = 0;
        double out = 0;
        double in =0;
        double save = 0;

        out = stockQuarterly.getSalesTotal();
        LOGGER.info("出：{}", out);
        save = stockQuarterly.getArrears();
        LOGGER.info("存：{}", save);
        in = stockQuarterly.getPurchTotal() + stockQuarterly.getBeforeArrears() + stockQuarterly.getBeforeChange() + stockQuarterly.getBeforeInventory();
        LOGGER.info("进：{}", in);

        pl = out + save - in;
        stockQuarterly.setPl(pl);
    }

    private double getArrears(Long stockId, String quarter, String year, Integer type) {
        Long[] times = TimeUtils.getQuarterStartAndEndTime(Integer.valueOf(quarter), Integer.valueOf(year));
        long startTime = times[0];
        long endTime = times[1];

        Double arrears = this.mapper.getArrears(stockId, startTime, endTime, type);
        if (arrears != null) {
            return arrears;
        }
        return 0;
    }

    private double getPurchTotal(Long stockId, String quarter, String year, Integer type) {
        Long[] times = TimeUtils.getQuarterStartAndEndTime(Integer.valueOf(quarter), Integer.valueOf(year));
        long startTime = times[0];
        long endTime = times[1];

        Double purchTotal = this.mapper.getPurchTotal(stockId, startTime, endTime, type);
        if (purchTotal != null) {
            return purchTotal;
        }
        return 0;
    }

    private double getSalesTotal(Long stockId, String quarter, String year, Integer type) {
        Long[] times = TimeUtils.getQuarterStartAndEndTime(Integer.valueOf(quarter), Integer.valueOf(year));
        long startTime = times[0];
        long endTime = times[1];

        Double salesTotal = this.mapper.getSalesTotal(stockId, startTime, endTime, type);
        if (salesTotal != null) {
            return salesTotal;
        }
        return 0;
    }

    private FinStockQuarterly getPrevQuarter(String curYear, String curQuarter, Integer type, Long stockId) {
        String quarter = null;
        String year = null;
        if ((Integer.valueOf(curQuarter) - 1) > 0) {
            year = curYear;
            quarter = String.valueOf(Integer.valueOf(curQuarter) - 1);
        } else {
            year = String.valueOf(Integer.valueOf(curYear) - 1);
            quarter = String.valueOf(4);
        }
        return this.mapper.getQuarter(year, quarter, type, stockId);
    }

    private boolean exist(Long dateTime, Integer type, Long stockId) {
        String year = String.valueOf(TimeUtils.getYear(new Date(dateTime)));
        String quarter = String.valueOf(TimeUtils.getSeason(new Date(dateTime)));

        int flag = this.mapper.exist(year, quarter, type, stockId);
        if (flag > 0) {
            return true;
        }
        return false;
    }

}
