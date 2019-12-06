package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.impl.BaseServiceImpl;
import com.vgit.yunqiang.mapper.LogStockShuntMapper;
import com.vgit.yunqiang.pojo.LogStockShunt;
import com.vgit.yunqiang.service.LogStockShuntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogStockShuntServiceImpl extends BaseServiceImpl<LogStockShunt> implements LogStockShuntService {

    @Autowired
    private LogStockShuntMapper mapper;

    @Override
    protected BaseMapper<LogStockShunt> getMapper() {
        return this.mapper;
    }

}
