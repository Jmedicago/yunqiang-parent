package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.consts.ICodes;
import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.impl.BaseServiceImpl;
import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.common.utils.TimeUtils;
import com.vgit.yunqiang.mapper.FinRegionStockDailyMapper;
import com.vgit.yunqiang.pojo.FinRegionStockDaily;
import com.vgit.yunqiang.pojo.FinStockDaily;
import com.vgit.yunqiang.service.FinRegionStockDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class FinRegionStockDailyServiceImpl extends BaseServiceImpl<FinRegionStockDaily> implements FinRegionStockDailyService {

    @Autowired
    private FinRegionStockDailyMapper mapper;

    @Override
    protected BaseMapper<FinRegionStockDaily> getMapper() {
        return this.mapper;
    }

    @Override
    public FinRegionStockDaily saveOrUpdateDaily(FinRegionStockDaily regionStockDaily) {
        if (regionStockDaily.getId() == null) {
            regionStockDaily.setCreateTime(System.currentTimeMillis());
            this.mapper.savePart(regionStockDaily);
        } else {
            regionStockDaily.setUpdateTime(System.currentTimeMillis());
            this.mapper.updatePart(regionStockDaily);
        }
        return regionStockDaily;
    }

    @Override
    public Ret deleteById(Long id) {
        FinRegionStockDaily finRegionStockDaily = this.mapper.get(id);
        if (finRegionStockDaily != null) {
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
