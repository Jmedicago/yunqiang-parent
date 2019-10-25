package com.vgit.yunqiang.mapper;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.pojo.FinStockQuarterly;
import org.apache.ibatis.annotations.Param;

public interface FinStockQuarterlyMapper extends BaseMapper<FinStockQuarterly> {

    /**
     * 查看季度报表是否存在
     *
     * @param year
     * @param quarter
     * @param type
     * @param stockId
     * @return
     */
    int exist(@Param("year") String year, @Param("quarter") String quarter, @Param("type") Integer type, @Param("stockId") Long stockId);

    /**
     * 查询季度表报
     *
     * @param year
     * @param quarter
     * @param type
     * @param stockId
     * @return
     */
    FinStockQuarterly getQuarter(@Param("year") String year, @Param("quarter") String quarter, @Param("type") Integer type, @Param("stockId") Long stockId);

    /**
     * 获取时间段的库存货值总计
     *
     * @param stockId
     * @param startTime
     * @param endTime
     * @param type
     * @return
     */
    Double getPurchTotal(@Param("stockId") Long stockId, @Param("startTime") Long startTime, @Param("endTime") Long endTime, @Param("type") Integer type);

    /**
     * 获取总销售额
     *
     * @param stockId
     * @param startTime
     * @param endTime
     * @param type
     * @return
     */
    Double getSalesTotal(@Param("stockId") Long stockId, @Param("startTime") Long startTime, @Param("endTime") Long endTime, @Param("type") Integer type);

    /**
     * 获取最后一次日报欠款
     *
     * @param stockId
     * @param startTime
     * @param endTime
     * @param type
     * @return
     */
    Double getArrears(@Param("stockId") Long stockId, @Param("startTime") long startTime, @Param("endTime") long endTime, @Param("type") Integer type);
}
