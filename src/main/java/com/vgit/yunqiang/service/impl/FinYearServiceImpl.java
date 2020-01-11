package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.impl.BaseServiceImpl;
import com.vgit.yunqiang.mapper.FinYearMapper;
import com.vgit.yunqiang.pojo.FinYear;
import com.vgit.yunqiang.service.FinYearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinYearServiceImpl extends BaseServiceImpl<FinYear> implements FinYearService {

    @Autowired
    private FinYearMapper mapper;

    @Override
    protected BaseMapper<FinYear> getMapper() {
        return this.mapper;
    }

    @Override
    public List<FinYear> getAll() {
        return this.mapper.getAll();
    }

}
