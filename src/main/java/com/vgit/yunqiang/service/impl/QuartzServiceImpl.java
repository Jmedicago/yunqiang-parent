package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.exception.BisException;
import com.vgit.yunqiang.common.query.QuartzJobInfo;
import com.vgit.yunqiang.job.QuartzUtils;
import com.vgit.yunqiang.service.QuartzService;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

@Service
public class QuartzServiceImpl implements QuartzService {

    @Autowired
    private SchedulerFactoryBean schedulerFactory;

    @Override
    public void addJob(QuartzJobInfo info) throws BisException {
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();
            QuartzUtils.addJob(scheduler, info.getJobName(), com.vgit.yunqiang.job.MainJob.class, info, info.getCronj());
        } catch (Exception e) {
            throw BisException.me().setInfo(e.getLocalizedMessage());
        }
    }

    @Override
    public void delJob(String jobName) {
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();
            QuartzUtils.removeJob(scheduler, jobName);
        } catch (Exception e) {
            throw BisException.me().setInfo(e.getLocalizedMessage());
        }
    }

}
