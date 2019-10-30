package com.vgit.yunqiang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.impl.BaseServiceImpl;
import com.vgit.yunqiang.mapper.BisTradeStockMapper;
import com.vgit.yunqiang.pojo.BisTradeStock;
import com.vgit.yunqiang.service.BisTradeStockService;

@Service
public class BisTradeStockServiceImpl extends BaseServiceImpl<BisTradeStock> implements BisTradeStockService {
	
	@Autowired
	private BisTradeStockMapper mapper;

	@Override
	protected BaseMapper<BisTradeStock> getMapper() {
		// TODO Auto-generated method stub
		return this.mapper;
	}
	

}
