package com.vgit.yunqiang.mapper;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.pojo.FinExpendItem;
import com.vgit.yunqiang.pojo.FinMExpends;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FinMExpendsMapper extends BaseMapper<FinMExpends> {

    /**
     * 每月连锁店支出明细列表
     *
     * @param year
     * @return
     */
    List<FinExpendItem> getMExpendsList(@Param("year") String year);
}
