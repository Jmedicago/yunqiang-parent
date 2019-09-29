package com.vgit.yunqiang.service;

import com.vgit.yunqiang.common.service.BaseService;
import com.vgit.yunqiang.pojo.SysPermission;
import com.vgit.yunqiang.pojo.SysRole;

import java.util.List;

public interface SysRoleService extends BaseService<SysRole> {

    /**
     * 添加角色 - 权限之间关系
     *
     * @param roleId
     * @param permissionIds
     */
    void correlationPermissions(Long roleId, Long... permissionIds);

    /**
     * 移除角色 - 权限之间关系
     *
     * @param roleId
     * @param permissionIds
     */
    void unCorrelationPermissions(Long roleId, Long... permissionIds);

    /**
     * 根据角色ID查询当前role permission表中关联的数据
     *
     * @return
     */
    List<SysPermission> findRolePermissionByRoleId(Long id);

    /**
     * 根据角色ID查询permission表中属于此角色节点的子节点权限
     *
     * @param id
     * @return
     */
    List<SysPermission> findPermissionByRoleId(Long id);

    /**
     * 删除此角色所有关联的权限id
     *
     * @param id
     */
    void deleteAllRolePermissions(Long id);

    /**
     * 通过当前角色的更新数据，更新用户表中显示关联角色的字段
     *
     * @param role
     */
    void updateUserRoleId(SysRole role);

    /**
     * 角色列表
     *
     * @return
     */
    List<SysRole> getAll();

    /**
     * 更新角色信息
     *
     * @param role
     */
    SysRole saveOrUpdateRole(SysRole role);

}
