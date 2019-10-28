package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.impl.BaseServiceImpl;
import com.vgit.yunqiang.mapper.BisOrderDetailMapper;
import com.vgit.yunqiang.pojo.BisOrderDetail;
import com.vgit.yunqiang.service.BisOrderDetailService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BisOrderDetailServiceImpl extends BaseServiceImpl<BisOrderDetail> implements BisOrderDetailService {

    @Autowired
    private BisOrderDetailMapper mapper;

    @Override
    protected BaseMapper<BisOrderDetail> getMapper() {
        return this.mapper;
    }

	@Override
	public List<BisOrderDetail> getOrderDetail(String sn) {
		return this.mapper.getOrderDetail(sn);
	}

}
