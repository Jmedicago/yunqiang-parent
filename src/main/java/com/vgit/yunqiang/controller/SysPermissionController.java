package com.vgit.yunqiang.controller;

import com.vgit.yunqiang.common.consts.ICodes;
import com.vgit.yunqiang.common.query.PermissionQuery;
import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.controller.consts.ControllerConsts;
import com.vgit.yunqiang.model.PermissionModel;
import com.vgit.yunqiang.model.TreeModel;
import com.vgit.yunqiang.pojo.SysPermission;
import com.vgit.yunqiang.pojo.SysUser;
import com.vgit.yunqiang.service.SysPermissionService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("/" + SysPermissionController.DOMAIN)
public class SysPermissionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysPermissionController.class);

    public static final String DOMAIN = "permission";

    @Autowired
    private SysPermissionService sysPermissionService;

    @RequestMapping(ControllerConsts.URL_INDEX)
    public String index() {
        return DOMAIN + ControllerConsts.VIEW_INDEX;
    }

    @RequestMapping(ControllerConsts.URL_TREE_JSON)
    @ResponseBody
    public List<TreeModel> tree() {
        // 查询所有权限
        return this.sysPermissionService.getAll(SysPermissionService.ROOT);
    }

    @RequestMapping(ControllerConsts.URL_JSON)
    @ResponseBody
    public List<PermissionModel> json(PermissionQuery query) {
        return this.sysPermissionService.treegrid(SysPermissionService.ROOT, query);
    }

    @RequestMapping(ControllerConsts.URL_EDIT)
    public String edit(Long id, Model model) {
        if (id != null) {
            // 权限信息
            SysPermission sysPermission = this.sysPermissionService.get(id);
            model.addAttribute("sysPermission", sysPermission);
        }
        return DOMAIN + ControllerConsts.VIEW_EDIT;
    }

    /**
     * 创建权限
     *
     * @param permission
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequiresRoles(value = {"admin", "system"}, logical = Logical.OR)
    @RequestMapping(ControllerConsts.URL_STORE)
    @ResponseBody
    public Ret store(SysPermission permission) throws UnsupportedEncodingException {
        LOGGER.info("权限信息={}", permission);
        // 更新权限信息
        this.sysPermissionService.saveOrUpdatePermission(permission);
        return Ret.me();
    }

    @RequiresRoles(value = {"admin", "system"}, logical = Logical.OR)
    @RequestMapping(ControllerConsts.URL_DELETE)
    @ResponseBody
    public Ret delete(Long id) {
        return this.sysPermissionService.deleteById(id);
    }


}
