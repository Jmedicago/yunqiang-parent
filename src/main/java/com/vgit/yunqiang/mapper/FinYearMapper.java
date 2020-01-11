package com.vgit.yunqiang.mapper;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.pojo.FinYear;

import java.util.List;

public interface FinYearMapper extends BaseMapper<FinYear> {

    List<FinYear> getAll();
}
