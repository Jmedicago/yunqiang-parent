package com.vgit.yunqiang.service;

import com.vgit.yunqiang.common.query.PermissionQuery;
import com.vgit.yunqiang.common.service.BaseService;
import com.vgit.yunqiang.model.MenuModel;
import com.vgit.yunqiang.model.PermissionModel;
import com.vgit.yunqiang.model.TreeModel;
import com.vgit.yunqiang.pojo.SysPermission;
import com.vgit.yunqiang.pojo.SysRole;

import java.util.List;

public interface SysPermissionService extends BaseService<SysPermission> {

    Long ROOT = 0L;

    /**
     * 根据权限ID查询其所关联的角色数据
     *
     * @param id
     * @return
     */
    List<SysRole> findRoleByPermissionId(Long id);

    /**
     * 删除此权限关联的所有角色ID
     *
     * @param id
     */
    void deleteAllPermissionsRoles(Long id);

    /**
     * 更新此角色的权限依赖关系
     *
     * @param permissionId
     * @param roleId
     */
    void correlationRoles(Long permissionId, Long roleId);

    /**
     * 查询所有权限
     *
     * @param root
     * @return
     */
    List<TreeModel> getAll(Long root);

    /**
     * 树形数据表格
     *
     * @param root
     * @param query
     * @return
     */
    List<PermissionModel> treegrid(Long root, PermissionQuery query);

    /**
     * 编辑权信息
     *
     * @param permission
     */
    void saveOrUpdatePermission(SysPermission permission);

    /**
     * 根据权限获取菜单
     *
     * @param roles
     * @param root
     * @return
     */
    List<MenuModel> menus(List<SysRole> roles, Long root);
}
