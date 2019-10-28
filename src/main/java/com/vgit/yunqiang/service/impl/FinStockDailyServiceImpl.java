package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.consts.ICodes;
import com.vgit.yunqiang.common.consts.bis.StockDailyTypeConsts;
import com.vgit.yunqiang.common.exception.BisException;
import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.impl.BaseServiceImpl;
import com.vgit.yunqiang.common.utils.Page;
import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.common.utils.TimeUtils;
import com.vgit.yunqiang.mapper.FinStockDailyMapper;
import com.vgit.yunqiang.pojo.FinExpendItem;
import com.vgit.yunqiang.pojo.FinStockDaily;
import com.vgit.yunqiang.pojo.FinStockDailyExpendItem;
import com.vgit.yunqiang.service.FinExpendItemService;
import com.vgit.yunqiang.service.FinStockDailyExpendItemService;
import com.vgit.yunqiang.service.FinStockDailyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class FinStockDailyServiceImpl extends BaseServiceImpl<FinStockDaily> implements FinStockDailyService {

    public static final Logger LOGGER = LoggerFactory.getLogger(FinStockDailyServiceImpl.class);

    @Autowired
    private FinStockDailyMapper mapper;

    @Autowired
    private FinStockDailyExpendItemService finStockDailyExpendItemService;

    @Autowired
    private FinExpendItemService finExpendItemService;

    @Override
    protected BaseMapper<FinStockDaily> getMapper() {
        return this.mapper;
    }

    @Override
    public FinStockDaily saveOrUpdateDaily(FinStockDaily finStockDaily) throws BisException {
        if (finStockDaily.getId() == null) {
            if (this.exist(System.currentTimeMillis(), finStockDaily.getType(), finStockDaily.getStockId())) {
                LOGGER.info("今日日报已存在，无法新增日报！");
                throw new BisException().setInfo("今日日报已存在，无法新增日报！");
            } else {
                finStockDaily.setCreateTime(System.currentTimeMillis());
                this.mapper.savePart(finStockDaily);
                FinStockDaily prevStockDaily = this.getPrevStockDaily(finStockDaily.getId(), finStockDaily.getStockId());
                finStockDaily.setDeposit(prevStockDaily.getDeposit());
                finStockDaily.setSafe(prevStockDaily.getSafe());
                this.mapper.updatePart(finStockDaily);
            }
        } else {
            // TODO.检查是否超过当天填报时间
            if (this.validateTime(finStockDaily.getId())) {
                finStockDaily.setUpdateTime(System.currentTimeMillis());
                // 保险柜的钱等于存的钱
                finStockDaily.setSafe(finStockDaily.getDeposit());
                this.mapper.updatePart(finStockDaily);
            } else {
                LOGGER.info("已超过当天填报时间！");
                throw new BisException().setInfo("已超过当天填报时间！");
            }
        }
        return finStockDaily;
    }

    @Override
    public boolean validateTime(Long dailyId) {
        FinStockDaily stockDaily = this.get(dailyId);
        Timestamp startTime = TimeUtils.getDayStartTime(new Date());
        Timestamp endTime = TimeUtils.getDayEndTime(new Date());
        if (stockDaily.getCreateTime() >= startTime.getTime() && stockDaily.getCreateTime() <= endTime.getTime()) {
            return true;
        }
        return false;
    }

    @Override
    public Ret deleteById(Long id) {
        FinStockDaily finStockDaily = this.mapper.get(id);
        if (finStockDaily != null) {
            return Ret.me().setSuccess(false).setCode(ICodes.NOT_AUTHORIZED);
        }
        return Ret.me().setSuccess(true).setCode(ICodes.SUCCESS);
    }

    @Override
    public List<FinStockDaily> getChildren(Long stockId, Long dateTime) {
        Timestamp startTime = TimeUtils.getDayStartTime(new Date(dateTime));
        Timestamp endTime = TimeUtils.getDayEndTime(new Date(dateTime));
        return this.mapper.getChildren(stockId, startTime.getTime(), endTime.getTime());
    }

    @Override
    public boolean exist(Long dateTime, Integer type, Long stockId) {
        Timestamp startTime = TimeUtils.getDayStartTime(new Date(dateTime));
        Timestamp endTime = TimeUtils.getDayEndTime(new Date(dateTime));
        int i = this.mapper.exist(startTime.getTime(), endTime.getTime(), type, stockId);
        if (i > 0) {
            return true;
        }
        return false;
    }

    private double getExpendTotal(Long stockId, Long dateTime) {
        long expendTotal = 0;
        Page<FinStockDailyExpendItem> page = this.finStockDailyExpendItemService.getExpendItemsByStockId(stockId, dateTime);
        if (page != null) {
            for (FinStockDailyExpendItem item : page.getRows()) {
                FinExpendItem expendItem = this.finExpendItemService.get(item.getExpendItemId());
                if ("C".equals(expendItem.getCategory())) {
                    expendTotal += item.getAmount();
                }
            }
        }
        return expendTotal;
    }

    private double getIncomeTotal(Long stockId, Long dataTime) {
        long incomeTotal = 0;
        List<FinStockDaily> stockDailyList = this.getChildren(stockId, dataTime);
        for (FinStockDaily stockDaily : stockDailyList) {
            incomeTotal += stockDaily.getIncome();
        }
        return incomeTotal;
    }

    private FinStockDaily getPrevStockDaily(Long id, Long stockId) {
        FinStockDaily stockDaily = new FinStockDaily();
        // 查询上一次的记录
        FinStockDaily prevStockDaily = this.mapper.getPrevious(id, StockDailyTypeConsts.REGION_STOCK_DAILY, stockId);
        double deposit = 0; // 存
        double safe = 0; // 保险柜
        double incomeTotal = 0;
        double expendTotal = 0;
        if (prevStockDaily != null) { // 如果存在
            safe = prevStockDaily.getSafe();
            incomeTotal = this.getIncomeTotal(stockId, System.currentTimeMillis());
            expendTotal = this.getExpendTotal(stockId, System.currentTimeMillis());
            // 计算存
            deposit = incomeTotal + safe - expendTotal;
            stockDaily.setSafe(safe);
            stockDaily.setDeposit(deposit);
        } else {
            stockDaily.setDeposit(deposit);
            stockDaily.setSafe(safe);
        }
        return stockDaily;
    }

}
