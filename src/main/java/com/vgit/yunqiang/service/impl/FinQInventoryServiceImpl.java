package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.impl.BaseServiceImpl;
import com.vgit.yunqiang.mapper.FinQInventoryMapper;
import com.vgit.yunqiang.pojo.FinQInventory;
import com.vgit.yunqiang.service.FinQInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinQInventoryServiceImpl extends BaseServiceImpl<FinQInventory> implements FinQInventoryService {

    @Autowired
    private FinQInventoryMapper mapper;

    @Override
    protected BaseMapper<FinQInventory> getMapper() {
        return this.mapper;
    }

    @Override
    public void saveQInventory(FinQInventory finQInventory) {
        if (saveBefore(finQInventory.getYearId(), finQInventory.getQuarterlyId())) {
            //
        } else {
            this.mapper.savePart(finQInventory);
        }
    }

    private boolean saveBefore(Long year, Long quarterlyId) {
        int count = this.mapper.exits(year, quarterlyId);
        if (count > 0) {
            return true;
        }
        return false;
    }
}
