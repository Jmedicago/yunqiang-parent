package com.vgit.yunqiang.job;

import com.vgit.yunqiang.common.consts.bis.JobTypeConsts;
import com.vgit.yunqiang.common.query.QuartzJobInfo;
import com.vgit.yunqiang.service.impl.JobService;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 处理定时调度任务的统一入口
 */
public class MainJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        QuartzJobInfo info = (QuartzJobInfo) jobDataMap.get("params");

        switch (info.getType()) {
            case JobTypeConsts.WAIT_ORDER_CANCEL_JOB:
                JobService.cancelOrder(info);
                break;
            case JobTypeConsts.WAIT_ORDER_CONFIRM_JOB:
                JobService.confirmOrder(info);
                break;
            default:
                break;
        }
    }

}
