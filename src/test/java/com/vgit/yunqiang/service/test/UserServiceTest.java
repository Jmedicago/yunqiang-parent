package com.vgit.yunqiang.service.test;

import com.vgit.yunqiang.pojo.LogResources;
import com.vgit.yunqiang.service.LogResourceService;
import com.vgit.yunqiang.service.SysUserService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext-*.xml")
public class UserServiceTest {

    @Resource
    private SysUserService sysUserService;

    @Autowired
    private LogResourceService logResourceService;

    @Test
    public void testFindRoles() throws Exception {
        System.out.println(this.sysUserService.findRoles("wenjf"));
    }

    @Test
    public void testRegister() throws Exception {
        String algorithmName = "md5";
        String username = "admin";
        String password = "admin";
        String salt1 = username;
        String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
        int hashIterations = 2;
        SimpleHash hash = new SimpleHash(algorithmName, password, salt1 + salt2, hashIterations);
        String encodedPassword = hash.toHex();
        System.out.println("password:" + encodedPassword);
        System.out.println("salt:" + salt2);
    }

    @Test
    public void testSaveLogger() {
        LogResources resources = new LogResources();
        resources.setOldFileName("test");
        resources.setRename("test");
        resources.setState(1);
        resources.setCreateTime(System.currentTimeMillis());
        logResourceService.savePart(resources);
    }

}
