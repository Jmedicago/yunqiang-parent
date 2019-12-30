package com.vgit.yunqiang.service.test;

import com.vgit.yunqiang.pojo.FinDyDaily;
import com.vgit.yunqiang.pojo.FinDzDaily;
import com.vgit.yunqiang.service.FinDyDailyService;
import com.vgit.yunqiang.service.FinDzDailyService;
import com.vgit.yunqiang.service.SysRoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext-*.xml")
public class RoleServiceTest {

    @Resource
    private SysRoleService sysRoleService;

    @Autowired
    private FinDyDailyService finDyDailyService;

    @Autowired
    private FinDzDailyService finDzDailyService;

    @Test
    public void testFindRoles() throws Exception {
        //System.out.println(this.roleService.findRoles(1L, 2L));

        Long[] ids = new Long[]{1013L};
        this.sysRoleService.correlationPermissions(1003L, ids);
    }

    @Test
    public void testDyDailyList() {
        List<FinDyDaily> dyDailyList = this.finDyDailyService.queryDailyList(1004L);
        for (FinDyDaily finDyDaily : dyDailyList) {
            System.out.println(finDyDaily.toString());
        }
    }

    @Test
    public void testDzDailyList() {
        List<FinDzDaily> dzDailyList = this.finDzDailyService.queryDailyList(1001L);
        System.out.println(dzDailyList);
    }

}
