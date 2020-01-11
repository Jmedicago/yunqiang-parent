package com.vgit.yunqiang.mapper;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.pojo.FinMonth;

import java.util.List;

public interface FinMonthMapper extends BaseMapper<FinMonth> {


    List<FinMonth> getAll();
}
