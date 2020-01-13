package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.impl.BaseServiceImpl;
import com.vgit.yunqiang.mapper.FinQuarterlyMapper;
import com.vgit.yunqiang.pojo.FinQuarterly;
import com.vgit.yunqiang.service.FinQuarterlyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinQuarterlyServiceImpl extends BaseServiceImpl<FinQuarterly> implements FinQuarterlyService {

    @Autowired
    private FinQuarterlyMapper mapper;

    @Override
    protected BaseMapper<FinQuarterly> getMapper() {
        return this.mapper;
    }

    @Override
    public List<FinQuarterly> getAll() {
        return this.mapper.getAll();
    }

}
