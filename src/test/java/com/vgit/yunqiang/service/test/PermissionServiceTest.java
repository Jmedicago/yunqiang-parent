package com.vgit.yunqiang.service.test;

import com.vgit.yunqiang.common.query.base.BaseQuery;
import com.vgit.yunqiang.pojo.Mendian;
import com.vgit.yunqiang.pojo.SysRole;
import com.vgit.yunqiang.service.MendianService;
import com.vgit.yunqiang.service.SysPermissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext-*.xml")
public class PermissionServiceTest {

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private MendianService mendianService;

    @Test
    public void testGetMenus() {
        List<SysRole> roles = new ArrayList<>();
        SysRole role = new SysRole();
        role.setId(1000L);
        roles.add(role);

        SysRole role2 = new SysRole();
        role2.setId(1002L);
        roles.add(role2);

        System.out.println(this.permissionService.menus(roles, 0L));
    }

    @Test
    public void testDepth() throws Exception {
        /*List<Long> ids = new ArrayList<Long>();
        this.mendianService.depth(2L, ids);
        System.out.println("####" + ids.toString());*/

        /*Mendian mendian = new Mendian();
        mendian.setName("**总值**");
        Mendian mendian2 = this.mendianService.getTree(mendian);
        System.out.println(mendian2.getName());*/

        List<Mendian> mendians = this.mendianService.treegrid(0L, new BaseQuery());
        System.out.print(mendians.toString());
    }

}
