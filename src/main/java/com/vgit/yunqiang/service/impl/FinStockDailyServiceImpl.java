package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.consts.ICodes;
import com.vgit.yunqiang.common.consts.bis.StockDailyTypeConsts;
import com.vgit.yunqiang.common.exception.BisException;
import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.impl.BaseServiceImpl;
import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.common.utils.TimeUtils;
import com.vgit.yunqiang.mapper.FinStockDailyMapper;
import com.vgit.yunqiang.pojo.FinStockDaily;
import com.vgit.yunqiang.service.FinStockDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class FinStockDailyServiceImpl extends BaseServiceImpl<FinStockDaily> implements FinStockDailyService {

    @Autowired
    private FinStockDailyMapper mapper;

    @Override
    protected BaseMapper<FinStockDaily> getMapper() {
        return this.mapper;
    }

    @Override
    public FinStockDaily saveOrUpdateDaily(FinStockDaily finStockDaily) throws BisException {
        if (finStockDaily.getId() == null) {
            if (this.exist(System.currentTimeMillis(), finStockDaily.getType(), finStockDaily.getStockId())) {
                throw new BisException();
            } else {
                finStockDaily.setCreateTime(System.currentTimeMillis());
                this.mapper.savePart(finStockDaily);
                FinStockDaily prevStockDaily = this.getPrevStockDaily(finStockDaily.getId(), finStockDaily.getStockId());
                // finStockDaily.setDeposit(prevStockDaily.getDeposit());
                finStockDaily.setSafe(prevStockDaily.getSafe());
                this.mapper.updatePart(finStockDaily);
            }
        } else {
            // finStockDaily.setSafe(finStockDaily.getDeposit());
            finStockDaily.setUpdateTime(System.currentTimeMillis());
            this.mapper.updatePart(finStockDaily);
        }
        return finStockDaily;
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

    private FinStockDaily getPrevStockDaily(Long id, Long stockId) {
        FinStockDaily stockDaily = new FinStockDaily();
        // 查询上一次的记录
        FinStockDaily prevStockDaily = this.mapper.getPrevious(id, StockDailyTypeConsts.REGION_STOCK_DAILY, stockId);
        double deposit = 0; // 存
        double safe = 0; // 保险柜
        if (prevStockDaily != null) { // 如果存在
            // stockDaily.setDeposit(prevStockDaily.getSafe());
            stockDaily.setSales(prevStockDaily.getSafe());
        } else {
            // stockDaily.setDeposit(deposit);
            stockDaily.setSafe(safe);
        }
        return stockDaily;
    }

}
