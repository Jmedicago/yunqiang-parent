package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.impl.BaseServiceImpl;
import com.vgit.yunqiang.mapper.LogProductBatchMapper;
import com.vgit.yunqiang.pojo.LogProductBatch;
import com.vgit.yunqiang.service.LogProductBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogProductBatchServiceImpl extends BaseServiceImpl<LogProductBatch> implements LogProductBatchService {

    @Autowired
    private LogProductBatchMapper mapper;

    @Override
    protected BaseMapper<LogProductBatch> getMapper() {
        return this.mapper;
    }



}
