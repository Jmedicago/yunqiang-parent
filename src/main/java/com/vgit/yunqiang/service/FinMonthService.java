package com.vgit.yunqiang.service;

import com.vgit.yunqiang.common.service.BaseService;
import com.vgit.yunqiang.pojo.FinMonth;

import java.util.List;

public interface FinMonthService extends BaseService<FinMonth> {

    List<FinMonth> getAll();
}
