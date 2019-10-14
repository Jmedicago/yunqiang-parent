package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.query.QuartzJobInfo;
import com.vgit.yunqiang.service.JobRunnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService {

    private static JobRunnerService runService;

    @Autowired
    public void setRunService(JobRunnerService runService) {
        JobService.runService = runService;
    }

    /**
     * 取消订单
     *
     * @param info
     */
    public static void cancelOrder(QuartzJobInfo info) {
        runService.cancelOrder(info);
    }

    /**
     * 确认订单
     *
     * @param info
     */
    public static void confirmOrder(QuartzJobInfo info) {
        runService.confirmOrder(info);
    }

}
