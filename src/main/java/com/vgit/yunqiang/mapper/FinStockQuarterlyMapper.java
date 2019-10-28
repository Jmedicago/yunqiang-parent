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

    /**
     * 获取最后一次日报保险柜现金
     *
     * @param stockId
     * @param type
     * @param startTime
     * @param endTime
     * @return
     */
    Double getSafe(@Param("stockId") Long stockId, @Param("type") Integer type, @Param("startTime") long startTime, @Param("endTime") long endTime);

    /**
     * 查询当前季度欠款总计
     *
     * @param stockId
     * @param year
     * @param quarter
     * @return
     */
    Double getArrearsTotal(@Param("stockId") Long stockId, @Param("year") String year, @Param("quarter") String quarter);

    /**
     * 查询当前季度零钱总计
     *
     * @param stockId
     * @param year
     * @param quarter
     * @return
     */
    Double getChangesTotal(@Param("stockId") Long stockId, @Param("year") String year, @Param("quarter") String quarter);

    /**
     * 查询当前季度库存货值总计
     *
     * @param stockId
     * @param year
     * @param quarter
     * @return
     */
    Double getInventoryTotal(@Param("stockId") Long stockId, @Param("year") String year, @Param("quarter") String quarter);

    /**
     * 查询当前季度进货总值
     *
     * @param stockId
     * @param year
     * @param quarter
     * @return
     */
    Double getPurchTotalAll(@Param("stockId") Long stockId, @Param("year") String year, @Param("quarter") String quarter);

    /**
     * 当前季度支出总计
     *
     * @param stockId
     * @param type
     * @param startTime
     * @param endTime
     * @return
     */
    Double getExpendTotal(@Param("stockId") Long stockId, @Param("type") Integer type, @Param("startTime") long startTime, @Param("endTime") long endTime);

    /**
     * 当前季度存银行总计
     *
     * @param stockId
     * @param startTime
     * @param endTime
     * @return
     */
    Double getDepositTotal(@Param("stockId") Long stockId, @Param("startTime") long startTime, @Param("endTime") long endTime);
}
