package com.vgit.yunqiang.mapper;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.pojo.FinRegionStockDaily;
import com.vgit.yunqiang.pojo.FinStockDaily;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FinRegionStockDailyMapper extends BaseMapper<FinRegionStockDaily> {

    /**
     * 查询门店每日明细
     *
     * @param stockId
     * @return
     */
    List<FinStockDaily> getChildren(@Param("stockId") Long stockId, @Param("startTime") Long startTime, @Param("endTime") Long endTime);

}
