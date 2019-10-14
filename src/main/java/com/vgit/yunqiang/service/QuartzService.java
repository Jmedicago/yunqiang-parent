package com.vgit.yunqiang.service;

import com.vgit.yunqiang.common.exception.BisException;
import com.vgit.yunqiang.common.query.QuartzJobInfo;

/**
 * 统一定时任务服务
 */
public interface QuartzService {

    /**
     * 添加定时任务
     *
     * @param info 任务信息
     */
    void addJob(QuartzJobInfo info) throws BisException;

    /**
     * 删除定时任务
     *
     * @param string
     */
    void delJob(String string);

}
