package com.vgit.yunqiang.service;

import com.vgit.yunqiang.common.service.TreeGridService;
import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.model.MenuModel;
import com.vgit.yunqiang.pojo.SysPermission;
import com.vgit.yunqiang.pojo.SysRole;

import java.util.List;

public interface SysPermissionService extends TreeGridService<SysPermission> {

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
     * 编辑权信息
     *
     * @param permission
     */
    SysPermission saveOrUpdatePermission(SysPermission permission);

    /**
     * 根据权限获取菜单
     *
     * @param roles
     * @param root
     * @return
     */
    List<MenuModel> menus(List<SysRole> roles, Long root);

    /**
     * 删除权限
     *
     * @param id
     * @return
     */
    Ret deleteById(Long id);
}
