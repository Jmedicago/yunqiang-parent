package com.vgit.yunqiang.controller;

import com.vgit.yunqiang.common.query.RoleQuery;
import com.vgit.yunqiang.common.utils.Page;
import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.controller.consts.ControllerConsts;
import com.vgit.yunqiang.pojo.SysPermission;
import com.vgit.yunqiang.pojo.SysRole;
import com.vgit.yunqiang.service.SysRoleService;
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
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/" + SysRoleController.DOMAIN)
public class SysRoleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysRoleController.class);

    public static final String DOMAIN = "role";

    @Autowired
    private SysRoleService sysRoleService;

    @RequestMapping(ControllerConsts.URL_INDEX)
    public String index() {
        return DOMAIN + ControllerConsts.VIEW_INDEX;
    }

    /**
     * 查询角色列表页面
     *
     * @param query
     * @return
     */
    @RequestMapping(ControllerConsts.URL_JSON)
    @ResponseBody
    public Page<SysRole> json(RoleQuery query) {
        return this.sysRoleService.queryPage(query);
    }

    @RequestMapping(ControllerConsts.URL_EDIT)
    public String edit(Long id, Model model) {
        if (id != null) {
            // 角色信息
            SysRole sysRole = this.sysRoleService.get(id);
            model.addAttribute("sysRole", sysRole);

            // 关联权限集合
            List<SysPermission> permissions = this.sysRoleService.findPermissionByRoleId(sysRole.getId());
            model.addAttribute("selectPermissions", this.getPermissionsArray(permissions));
        }
        return DOMAIN + ControllerConsts.VIEW_EDIT;
    }

    /**
     * 创建角色
     *
     * @param role
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequiresRoles(value = {"admin", "system"}, logical = Logical.OR)
    @RequestMapping(ControllerConsts.URL_STORE)
    @ResponseBody
    public Ret store(SysRole role, Long[] permissionIds) throws UnsupportedEncodingException {
        LOGGER.info("选择的权限列表={}", permissionIds);
        // 更新用户信息
        this.sysRoleService.saveOrUpdateRole(role);

        // 更新角色权限信息
        if (role != null) {
            this.sysRoleService.deleteAllRolePermissions(role.getId());
            this.sysRoleService.correlationPermissions(role.getId(), permissionIds);
        }
        return Ret.me();
    }

    @RequestMapping(ControllerConsts.URL_LIST)
    @ResponseBody
    public List<SysRole> list() {
        return this.sysRoleService.getAll();
    }

    private List<String> getPermissionsArray(List<SysPermission> permissions) {
        List<String> array = null;
        if (permissions != null && !permissions.isEmpty()) {
            array = new ArrayList<>();
            for (SysPermission permission : permissions) {
                array.add(String.valueOf(permission.getId()));
            }
        }
        return array;
    }


}
