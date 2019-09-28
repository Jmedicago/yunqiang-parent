package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.consts.ICodes;
import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.TreeGridService;
import com.vgit.yunqiang.common.service.impl.TreeGridServiceImpl;
import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.mapper.SysPermissionMapper;
import com.vgit.yunqiang.model.MenuModel;
import com.vgit.yunqiang.pojo.SysPermission;
import com.vgit.yunqiang.pojo.SysRole;
import com.vgit.yunqiang.service.SysPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SysPermissionServiceImpl extends TreeGridServiceImpl<SysPermission> implements SysPermissionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysPermissionServiceImpl.class);

    @Autowired
    private SysPermissionMapper mapper;

    @Override
    protected BaseMapper<SysPermission> getMapper() {
        return this.mapper;
    }

    @Override
    public List<SysRole> findRoleByPermissionId(Long id) {
        return this.mapper.findRoleByPermissionId(id);
    }

    @Override
    public void deleteAllPermissionsRoles(Long id) {
        this.mapper.deleteAllPermissionsRoles(id);
    }

    @Override
    public void correlationRoles(Long permissionId, Long roleId) {
        this.mapper.correlationRoles(permissionId, roleId);
    }

    @Override
    public SysPermission saveOrUpdatePermission(SysPermission permission) {
        if (permission.getId() == null) {
            permission.setSort(100);
            permission.setAvailable(true);
            this.mapper.savePart(permission);
            this.handleSave(permission);
            this.mapper.updatePart(permission);
        } else {
            this.handleSave(permission);
            this.mapper.updatePart(permission);
        }
        return permission;
    }

    @Override
    public List<MenuModel> menus(List<SysRole> roles, Long root) {
        List<MenuModel> menus = new ArrayList<MenuModel>();

        List<Long> roleIds = new ArrayList<>();
        for (SysRole role : roles) {
            roleIds.add(role.getId());
        }

        List<SysPermission> permissions = this.mapper.findByRoleIdAndParentId(root, roleIds);
        for (SysPermission permission : permissions) {
            MenuModel menu = new MenuModel();
            menu.setText(permission.getIdentity());
            menu.setIcon(permission.getIcon());
            menu.setUrl(permission.getUrl());
            menu.setChildren(this.menus(roles, permission.getId()));
            menus.add(menu);
        }
        return menus;
    }

    @Override
    public Ret deleteById(Long id) {
        SysPermission permission = this.mapper.get(id);
        // 查询是否是根节点
        if (permission != null && permission.getParentId() == TreeGridService.ROOT) {
            // 该节点是根节点
            return Ret.me().setSuccess(false).setCode(ICodes.NOT_AUTHORIZED);
        }
        this.delByRoot(id); // 删除该节点及所有子节点
        return Ret.me().setSuccess(true).setCode(ICodes.SUCCESS);
    }

    /**
     * 存储修改分类前进行预处理数据
     *
     * @param o
     */
    private void handleSave(SysPermission o) {
        Long id = o.getId();
        Long pid = o.getParentId();
        String path = "";
        if (pid == null || 0 == pid) { // 一级分类
            path = "." + id + ".";
        } else {// 子类
            SysPermission permission = this.mapper.get(pid);
            path = permission.getPath() + id + ".";
        }
        o.setPath(path);
    }

}
