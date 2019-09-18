package com.vgit.yunqiang.controller;

import com.vgit.yunqiang.common.consts.ConstUtils;
import com.vgit.yunqiang.common.utils.KV;
import com.vgit.yunqiang.model.MenuModel;
import com.vgit.yunqiang.pojo.SysRole;
import com.vgit.yunqiang.pojo.SysUser;
import com.vgit.yunqiang.service.SysPermissionService;
import com.vgit.yunqiang.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.List;

@Controller
public class IndexController {

    private final static Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysPermissionService sysPermissionService;

    @RequestMapping("/")
    public String toIndex(Model model) {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        if (StringUtils.isNotBlank(username)) {
            // 当前登录用户信息
            SysUser userInfo = this.sysUserService.findByUsername(username);
            LOGGER.info("当前登录系统用户信息={}", username);
            model.addAttribute("userInfo", userInfo);

            // 角色列表
            List<SysRole> roles = this.sysUserService.findRoles(username);
            model.addAttribute("roleInfo", roles);

            // 菜单列表
            if (roles != null && !roles.isEmpty()) {
                List<MenuModel> menus = this.sysPermissionService.menus(roles, SysPermissionService.ROOT);
                LOGGER.info("当前登录系统用户菜单={}", menus.toString());
                model.addAttribute("menus", menus);
            }
        }
        return "common/index";
    }

    /**
     * 后台主页视图
     *
     * @return
     */
    @RequestMapping("/main")
    public String index() {
        return "common/index";
    }

    /**
     * 个人工作台视图
     *
     * @return
     */
    @RequestMapping("/main/dashbord")
    public String dashbord() {
        return "common/dashbord";
    }

    /**
     * 注销当前登录用户
     *
     * @return
     */
    @RequestMapping("/login/logout")
    public String logout() {
        return "common/login";
    }


    /**
     * 获取后台常量KV列表
     * 如：请求属性类型KV列表
     * 前台请求地址： /const/propertyType
     *
     * @param constName
     * @return
     */
    @RequestMapping("/const/{constName}")
    @ResponseBody
    public List<KV<Integer, String>> getConstList(@PathVariable(value = "constName") String constName) {
        Class<?> clazz;
        try {
            String constPrefix = constName.substring(0, 1).toUpperCase() + constName.substring(1);
            String className = "com.vgit.yunqiang.common.consts.bis." + constPrefix + "Consts";
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return Collections.EMPTY_LIST;
        }
        List<KV<Integer, String>> kvList = ConstUtils.getKvList(clazz);
        return kvList;
    }

}
