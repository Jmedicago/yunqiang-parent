package com.vgit.yunqiang.job.test;

import com.vgit.yunqiang.common.consts.GlobalSettingNames;
import com.vgit.yunqiang.common.consts.bis.JobTypeConsts;
import com.vgit.yunqiang.common.query.QuartzJobInfo;
import com.vgit.yunqiang.common.utils.GlobalSetting;
import com.vgit.yunqiang.service.QuartzService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext-*.xml")
public class JobTest {

    @Autowired
    private QuartzService quartzService;

    @Test
    public void testCancelOrder() throws IOException {
        // 计算支付截止时间
        BigDecimal hours = new BigDecimal(GlobalSetting.get(GlobalSettingNames.SYSTEM_TIME_LIMIT_HOURS));
        BigDecimal millsExpires = hours.multiply(new BigDecimal(3600000));
        long lastPayTime = (millsExpires.add(new BigDecimal(System.currentTimeMillis()))).longValue();
        System.out.println("最后取消时间：" + lastPayTime);

        // 取消订单支付倒计时任务
        Map<String, Object> jobParams = new HashMap<>();
        jobParams.put("orderId", "2019101401");
        QuartzJobInfo info = new QuartzJobInfo();
        info.setFireDate(new Date(lastPayTime));
        info.setParams(jobParams);
        info.setJobName("CANCEL_ORDER_TASK_" + 2019101401);
        info.setType(JobTypeConsts.WAIT_ORDER_CANCEL_JOB);
        this.quartzService.addJob(info);

        System.in.read();
    }

}
