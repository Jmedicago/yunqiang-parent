package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.query.QuartzJobInfo;
import com.vgit.yunqiang.service.JobRunnerService;
import org.springframework.stereotype.Service;

@Service
public class JobRunnerServiceImpl implements JobRunnerService {

    @Override
    public void cancelOrder(QuartzJobInfo info) {
        System.out.println("-----------------------------------------------");
        System.out.println("|                                             |");
        System.out.println("|                  取消订单                   |");
        System.out.println("|                                             |");
        System.out.println("-----------------------------------------------");
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
