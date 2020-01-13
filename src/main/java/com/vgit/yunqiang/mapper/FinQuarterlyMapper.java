package com.vgit.yunqiang.mapper;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.pojo.FinQuarterly;

import java.util.List;

public interface FinQuarterlyMapper extends BaseMapper<FinQuarterly> {

    List<FinQuarterly> getAll();
}
