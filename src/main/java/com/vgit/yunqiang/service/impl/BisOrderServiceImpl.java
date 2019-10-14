package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.impl.BaseServiceImpl;
import com.vgit.yunqiang.mapper.BisOrderMapper;
import com.vgit.yunqiang.pojo.BisOrder;
import com.vgit.yunqiang.service.BisOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BisOrderServiceImpl extends BaseServiceImpl<BisOrder> implements BisOrderService {

    @Autowired
    private BisOrderMapper mapper;

    @Override
    protected BaseMapper<BisOrder> getMapper() {
        return this.mapper;
    }

}
