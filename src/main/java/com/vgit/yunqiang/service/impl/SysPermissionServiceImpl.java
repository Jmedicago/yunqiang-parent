package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.consts.ICodes;
import com.vgit.yunqiang.common.query.PermissionQuery;
import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.impl.TreeGridImpl;
import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.mapper.SysPermissionMapper;
import com.vgit.yunqiang.model.MenuModel;
import com.vgit.yunqiang.model.PermissionModel;
import com.vgit.yunqiang.model.TreeModel;
import com.vgit.yunqiang.pojo.BisStock;
import com.vgit.yunqiang.pojo.SysPermission;
import com.vgit.yunqiang.pojo.SysRole;
import com.vgit.yunqiang.service.BisStockService;
import com.vgit.yunqiang.service.SysPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SysPermissionServiceImpl extends TreeGridImpl<SysPermission> implements SysPermissionService {

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
    public List<TreeModel> getAll(Long root) {
        List<TreeModel> tree = new ArrayList<>();
        List<SysPermission> permissions = this.mapper.findByParentId(root);
        // 遍历权限
        for (SysPermission permission : permissions) {
            TreeModel node = new TreeModel();
            node.setId(permission.getId());
            node.setText(permission.getName());
            node.setPid(permission.getParentId());
            List<TreeModel> children = this.getAll(permission.getId());
            node.setParent(children.isEmpty() ? false : true);
            node.setChildren(children);
            tree.add(node);
        }
        return tree;
    }

    @Override
    public List<PermissionModel> treegrid(Long root, PermissionQuery query) {
        List<PermissionModel> tree = new ArrayList<PermissionModel>();
        List<SysPermission> permissions = this.mapper.findByParentId(root);
        for (SysPermission permission : permissions) {
            PermissionModel model = new PermissionModel();
            model.setId(permission.getId());
            model.setName(permission.getName());
            model.setIcon(permission.getIcon());
            model.setDescription(permission.getDescription());
            model.setParentId(permission.getParentId());
            model.setPermission(permission.getPermission());
            model.setPath(permission.getPath());
            model.setSort(permission.getSort());
            model.setType(permission.getType());
            model.setUrl(permission.getUrl());
            model.setAvailable(permission.getAvailable());
            model.setChildren(this.treegrid(permission.getId(), query));
            tree.add(model);
        }
        return tree;
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
        if (permission != null && permission.getParentId() == SysPermissionService.ROOT) {
            // 该节点是根节点
            return Ret.me().setSuccess(false).setCode(ICodes.NOT_AUTHORIZED);
        }
        List<Long> ids = new ArrayList<Long>();
        this.depth(id, ids);
        if (!ids.isEmpty()) {
            // 删除所有子节点
            this.mapper.delByIds(ids);
        }
        // 删除当前节点
        this.mapper.delete(id);
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
