package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.impl.BaseServiceImpl;
import com.vgit.yunqiang.mapper.FinDyQInvProductMapper;
import com.vgit.yunqiang.pojo.FinDyQInvProduct;
import com.vgit.yunqiang.service.FinDyInvProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinDyInvProductServiceImpl extends BaseServiceImpl<FinDyQInvProduct> implements FinDyInvProductService {

    @Autowired
    private FinDyQInvProductMapper mapper;

    @Override
    protected BaseMapper<FinDyQInvProduct> getMapper() {
        return this.mapper;
    }



}
