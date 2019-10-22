package com.vgit.yunqiang.mapper;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.pojo.FinStockDaily;
import com.vgit.yunqiang.pojo.FinStockDailyExpendItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FinStockDailyMapper extends BaseMapper<FinStockDaily> {

    /**
     * 查询门店每日明细
     *
     * @param stockId
     * @return
     */
    List<FinStockDaily> getChildren(@Param("stockId") Long stockId, @Param("startTime") Long startTime, @Param("endTime") Long endTime);

}
