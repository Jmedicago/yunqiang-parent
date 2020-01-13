package com.vgit.yunqiang.service;

import com.vgit.yunqiang.common.service.BaseService;
import com.vgit.yunqiang.pojo.FinQuarterly;

import java.util.List;

public interface FinQuarterlyService extends BaseService<FinQuarterly> {

    List<FinQuarterly> getAll();
}
