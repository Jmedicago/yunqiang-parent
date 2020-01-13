package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.impl.BaseServiceImpl;
import com.vgit.yunqiang.mapper.FinQInventoryMapper;
import com.vgit.yunqiang.pojo.FinQInventory;
import com.vgit.yunqiang.service.FinQInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinQInventoryImpl extends BaseServiceImpl<FinQInventory> implements FinQInventoryService {

    @Autowired
    private FinQInventoryMapper mapper;

    @Override
    protected BaseMapper<FinQInventory> getMapper() {
        return this.mapper;
    }

}
