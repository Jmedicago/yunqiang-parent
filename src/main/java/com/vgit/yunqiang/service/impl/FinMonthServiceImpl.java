package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.BaseService;
import com.vgit.yunqiang.common.service.impl.BaseServiceImpl;
import com.vgit.yunqiang.mapper.FinMonthMapper;
import com.vgit.yunqiang.pojo.FinMonth;
import com.vgit.yunqiang.service.FinMonthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinMonthServiceImpl extends BaseServiceImpl<FinMonth> implements FinMonthService {

    @Autowired
    private FinMonthMapper mapper;

    @Override
    protected BaseMapper<FinMonth> getMapper() {
        return this.mapper;
    }

    @Override
    public List<FinMonth> getAll() {
        return this.mapper.getAll();
    }

}
