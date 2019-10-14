package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.query.QuartzJobInfo;
import com.vgit.yunqiang.service.BisCartService;
import com.vgit.yunqiang.service.JobRunnerService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobRunnerServiceImpl implements JobRunnerService {
	
	@Autowired
	private BisCartService bisCartService;

    @Override
    public void cancelOrder(QuartzJobInfo info) {
    	Map<String, Object> params = info.getParams();
    	Long userId = (Long) params.get("userId");
    	this.bisCartService.deleleAll(userId);
    }

    @Override
    public void confirmOrder(QuartzJobInfo info) {
        System.out.println("-----------------------------------------------");
        System.out.println("|                                             |");
        System.out.println("|                  提交订单                   |");
        System.out.println("|                                             |");
        System.out.println("-----------------------------------------------");
    }

}
