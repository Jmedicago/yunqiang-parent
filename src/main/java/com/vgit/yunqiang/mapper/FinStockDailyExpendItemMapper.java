package com.vgit.yunqiang.mapper;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.pojo.FinStockDailyExpendItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FinStockDailyExpendItemMapper extends BaseMapper<FinStockDailyExpendItem> {

    /**
     * 区域支出统计
     *
     * @param stockId
     * @return
     */
    List<FinStockDailyExpendItem> getExpendItemsByStockId(@Param("stockId") Long stockId, @Param("startTime") Long startTime, @Param("endTime") Long endTime);

}
