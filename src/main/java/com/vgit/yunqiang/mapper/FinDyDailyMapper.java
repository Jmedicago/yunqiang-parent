package com.vgit.yunqiang.mapper;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.pojo.FinDyDaily;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface FinDyDailyMapper extends BaseMapper<FinDyDaily> {

    /**
     * 查询店员每日资金进出账明细
     *
     * @param stockId
     * @return
     */
    List<FinDyDaily> queryDailyList(@Param("stockId") Long stockId, @Param("year") String year, @Param("quarterly") String quarterly);

    /**
     * 查询店员日报信息
     *
     * @param dailyCode
     * @return
     */
    FinDyDaily getByCode(String dailyCode);

    /**
     * 检查今日是否填报
     *
     * @param stockId
     * @return
     */
    int exits(@Param("stockId") Long stockId, @Param("date")String date);

    /**
     * 查询所有店员填报列表
     *
     * @param stockId
     * @return
     */
    List<FinDyDaily> getDyDailyList(@Param("stockId") Long stockId, @Param("date") String date);

    /**
     * 查询店员日报区间
     *
     * @param startDate
     * @param endDate
     * @param stockId
     * @return
     */
    List<FinDyDaily> getDyDailyListAndDate(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("stockId") Long stockId);
}
