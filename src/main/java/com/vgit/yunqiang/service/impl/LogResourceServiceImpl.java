package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.impl.BaseServiceImpl;
import com.vgit.yunqiang.mapper.LogResourcesMapper;
import com.vgit.yunqiang.pojo.LogResources;
import com.vgit.yunqiang.service.LogResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogResourceServiceImpl extends BaseServiceImpl<LogResources> implements LogResourceService {

    @Autowired
    private LogResourcesMapper mapper;

    @Override
    protected BaseMapper<LogResources> getMapper() {
        return this.mapper;
    }

    @Override
    public boolean exist(String fileName) {
        List<LogResources> resources = this.mapper.findByFileName(fileName);
        if (resources != null && resources.size() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void deleteByUrl(String excelUrl) {
        this.mapper.deleteByUrl(excelUrl);
    }

}
