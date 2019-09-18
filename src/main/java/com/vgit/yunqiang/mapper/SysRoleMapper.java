package com.vgit.yunqiang.mapper;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.pojo.SysPermission;
import com.vgit.yunqiang.pojo.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 创建角色 - 权限
     *
     * @param roleId
     * @param permissionId
     */
    void correlationPermissions(@Param("roleId") Long roleId, @Param("permissionId") Long permissionId);

    /**
     * 删除角色 - 权限
     *
     * @param roleId
     * @param permissionId
     */
    void unCorrelationPermissions(@Param("roleId") Long roleId, @Param("permissionId") Long permissionId);

    /**
     * 查询表中是否存在新添加的数据
     *
     * @param roleId
     * @param permissionId
     * @return
     */
    boolean exists(@Param("roleId") Long roleId, @Param("permissionId") Long permissionId);

    /**
     * 根据角色Id查询当前Role Permission中关联的数据
     *
     * @param roleId
     * @return
     */
    List<SysPermission> findRolePermissionByRoleId(@Param("roleId") Long roleId);

    /**
     * 根据角色ID查询Permission表中属于此角色节点的子节点权限
     *
     * @param roleId
     * @return
     */
    List<SysPermission> findPermissionByRoleId(@Param("roleId") Long roleId);

    /**
     * 删除当前角色关联的所有权限ID
     *
     * @param id
     */
    void deleteAllRolePermissions(Long id);

    /**
     * 根据当前角色的更新数据，更新用户表中展示关联角色
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
}
