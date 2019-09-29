package com.vgit.yunqiang.service.test;

import com.vgit.yunqiang.service.SysRoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext-*.xml")
public class RoleServiceTest {

    @Resource
    private SysRoleService sysRoleService;

    @Test
    public void testFindRoles() throws Exception {
        //System.out.println(this.roleService.findRoles(1L, 2L));

        Long[] ids = new Long[]{1013L};
        this.sysRoleService.correlationPermissions(1003L, ids);
    }

}
