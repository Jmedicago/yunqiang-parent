package com.vgit.yunqiang.mapper;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.pojo.FinDzDaily;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FinDzDailyMapper extends BaseMapper<FinDzDaily> {

    /**
     * 查询店长每日资金进出账明细
     *
     * @param stockId
     * @return
     */
    List<FinDzDaily> queryDailyList(Long stockId);

    /**
     * 是否存在
     *
     * @param stockId
     * @return
     */
    int exits(@Param("stockId") Long stockId, @Param("date") String date);
}
