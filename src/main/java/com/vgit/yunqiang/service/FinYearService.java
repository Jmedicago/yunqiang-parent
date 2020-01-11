package com.vgit.yunqiang.service;

import com.vgit.yunqiang.common.service.BaseService;
import com.vgit.yunqiang.pojo.FinYear;

import java.util.List;

public interface FinYearService extends BaseService<FinYear> {

    List<FinYear> getAll();
}
