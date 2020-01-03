package com.vgit.yunqiang.mapper;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.pojo.FinQExpends;
import com.vgit.yunqiang.pojo.FinQuarterly;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FinQExpendsMapper extends BaseMapper<FinQExpends> {

    /**
     * 区域各季度支出统计列表
     *
     * @param year
     * @return
     */
    List<FinQuarterly> getQExpendsList(@Param("year") String year, @Param("stockId") Long stockId);
}
