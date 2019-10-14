package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.impl.BaseServiceImpl;
import com.vgit.yunqiang.mapper.BisOrderBillMapper;
import com.vgit.yunqiang.pojo.BisOrderBill;
import com.vgit.yunqiang.service.BisOrderBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BisOrderBillServiceImpl extends BaseServiceImpl<BisOrderBill> implements BisOrderBillService {

    @Autowired
    private BisOrderBillMapper mapper;

    @Override
    protected BaseMapper<BisOrderBill> getMapper() {
        return this.mapper;
    }

}
