package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.consts.ICodes;
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
    public FinStockDaily saveOrUpdateDaily(FinStockDaily finStockDaily) {
        if (finStockDaily.getId() == null) {
            finStockDaily.setCreateTime(System.currentTimeMillis());
            this.mapper.savePart(finStockDaily);
        } else {
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

}
