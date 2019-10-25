package com.vgit.yunqiang.service.impl;

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
                throw new BisException();
            } else {
                this.init(stockQuarterly);
                this.savePart(stockQuarterly);
            }
        } else {
            stockQuarterly.setUpdateTime(System.currentTimeMillis());
            this.updatePart(stockQuarterly);
        }
        return stockQuarterly;
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
