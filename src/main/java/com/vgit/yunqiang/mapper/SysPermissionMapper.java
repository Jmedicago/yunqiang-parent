package com.vgit.yunqiang.mapper;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.pojo.SysPermission;
import com.vgit.yunqiang.pojo.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    /**
     * 根据权限ID查询其所关联的角色数据
     *
     * @param id
     * @return
     */
    List<SysRole> findRoleByPermissionId(Long id);

    /**
     * 删除此权限关联的所有角色
     *
     * @param id
     */
    void deleteAllPermissionsRoles(Long id);

    /**
     * 创建此权限与角色的依赖关系
     *
     * @param permissionId
     * @param roleId
     */
    void correlationRoles(Long permissionId, Long roleId);

    /**
     * 根据父ID查找
     *
     * @param parentId
     * @return
     */
    List<SysPermission> findByParentId(@Param("parentId") Long parentId);

    /**
     * 根据角色ID查询拥有权限
     *
     * @param roleIds
     * @param parentId
     * @return
     */
    List<SysPermission> findByRoleIdAndParentId(@Param("parentId") Long parentId, @Param("roleIds") List<Long> roleIds);

    /**
     * 是否是叶子节点
     *
     * @param id
     * @return
     */
    boolean isParent(Long id);

    /**
     * 根据父节点删除
     *
     * @param id
     */
    void deleteByParentId(Long id);
}
