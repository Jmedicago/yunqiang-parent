package com.vgit.yunqiang.controller;

import com.vgit.yunqiang.common.consts.bis.SysUserStateConsts;
import com.vgit.yunqiang.common.query.UserQuery;
import com.vgit.yunqiang.common.utils.Page;
import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.common.utils.StrUtils;
import com.vgit.yunqiang.controller.consts.ControllerConsts;
import com.vgit.yunqiang.pojo.SysRole;
import com.vgit.yunqiang.pojo.SysUser;
import com.vgit.yunqiang.service.SysUserService;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/" + SysUserController.DOMAIN)
public class SysUserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysUserController.class);

    public static final String DOMAIN = "user";

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping(ControllerConsts.URL_INDEX)
    public String index(Model model) {
        return DOMAIN + ControllerConsts.VIEW_INDEX;
    }

    @RequestMapping(ControllerConsts.URL_EDIT)
    public String edit(Long id, Model model) {
        if (id != null) {
            SysUser sysUser = this.sysUserService.get(id);
            // 获取用户角色集合ID字符串集合
            String[] roleArr = sysUser.getRoleIds().split(",");
            List<String> roles =  Arrays.asList(roleArr);
            LOGGER.info("用户拥有的角色{}", roles);
            model.addAttribute("selectRoles", roles);
            if (StringUtils.isNotBlank(sysUser.getStockIds())) {
            	 // 获取用户所属区域集合ID字符串集合
                String[] stockArr = sysUser.getStockIds().split(",");
                List<String> stocks =  Arrays.asList(stockArr);
                LOGGER.info("用户归属区域{}", stocks);
                model.addAttribute("selectStocks", stocks);
            }
            // 用户信息
            LOGGER.info("用户信息{}", sysUser);
            model.addAttribute("sysUser", sysUser);
        }
        return DOMAIN + ControllerConsts.VIEW_EDIT;
    }

    /**
     * 查询用户列表页面
     *
     * @param query
     * @return
     */
    @RequestMapping(ControllerConsts.URL_JSON)
    @ResponseBody
    public Page<SysUser> json(UserQuery query) {
        return this.sysUserService.queryPage(query);
    }

    /**
     * 创建用户
     *
     * @param user
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequiresRoles(value = {"admin", "system"}, logical = Logical.OR)
    @RequestMapping(ControllerConsts.URL_STORE)
    @ResponseBody
    public Ret store(SysUser user) throws UnsupportedEncodingException {
        // 更新用户信息
        this.sysUserService.saveOrUpdateUser(user);
        LOGGER.info("用户选择的角色列表={}", user.getRoleIds());

        // 更新用户角色信息
        if (user != null) {
            this.sysUserService.deleteAllUserRoles(user.getId());
            this.sysUserService.correlationRoles(user.getId(), StrUtils.splitToLong(user.getRoleIds(), ","));
        }
        
        // 更新用户所属区域信息
        if (user != null) {
        	this.sysUserService.deleteAllUserStocks(user.getId());
        	this.sysUserService.correlationStocks(user.getId(), StrUtils.splitToLong(user.getStockIds(), ","));
        }

        return Ret.me();
    }

    /**
     * 停用
     *
     * @param user
     * @return
     */
    @RequiresRoles(value = {"admin", "system"}, logical = Logical.OR)
    @RequestMapping(ControllerConsts.URL_DELETE)
    @ResponseBody
    public Ret delete(SysUser user) {
        if (user.getId() == null) {
            return Ret.me().setSuccess(false).setInfo("无效的ID");
        }
        user.setStatus(SysUserStateConsts.DISABLED);
        user.setUpdateTime(System.currentTimeMillis());
        this.sysUserService.updatePart(user);
        return Ret.me().setSuccess(true);
    }

    /**
     * 根据用户名查找其角色
     *
     * @param username
     * @return
     */
    @RequiresRoles(value = {"admin"}, logical = Logical.OR)
    @RequiresPermissions(value = {"role:view", "role:*"}, logical = Logical.OR)
    @RequestMapping("/findRoles")
    @ResponseBody
    public Ret findRoles(String username) {
        List<SysRole> roles = this.sysUserService.findRoles(username);
        return Ret.me().setData(roles);
    }
    
    @RequestMapping("/modify-psw")
    public String modifyPsw(Long id, Model model) {
    	SysUser sysUser = this.sysUserService.get(id);
    	model.addAttribute("sysUser", sysUser);
    	return DOMAIN + "/modify-psw";
    }
    
    
    @RequestMapping("/store-psw")
    @ResponseBody
    public Ret storePsw(Long id, String newPsw, String onceNewPsw) {
    	return this.sysUserService.modifyPsw(id, newPsw, onceNewPsw);
    }

}
