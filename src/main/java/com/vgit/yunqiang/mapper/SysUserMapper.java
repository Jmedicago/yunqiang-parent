package com.vgit.yunqiang.mapper;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.pojo.SysPermission;
import com.vgit.yunqiang.pojo.SysRole;
import com.vgit.yunqiang.pojo.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 创建用户 - 角色关系
     *
     * @param userId
     * @param roleId
     */
    void correlationRoles(@Param("userId") Long userId, @Param("roleId") Long roleId);

    /**
     * 移除用户 - 权限之间的关系
     *
     * @param userId
     * @param roleId
     */
    void unCorrelationRoles(@Param("userId") Long userId, @Param("roleId") Long roleId);

    /**
     * 根据用户名查询对应的角色
     *
     * @param username
     * @return
     */
    List<SysRole> findRoles(@Param("username") String username);

    /**
     * 根据用户名查找其权限
     *
     * @param username
     * @return
     */
    List<SysPermission> findPermissions(@Param("username") String username);

    /**
     * 判断指定的用户和角色是否存在
     *
     * @param userId
     * @param roleId
     * @return
     */
    boolean exists(@Param("userId") Long userId, @Param("roleId") Long roleId);

    /**
     * 根据用户名查询
     *
     * @param username
     * @return
     */
    SysUser findByUsername(@Param("username") String username);

    /**
     * 删除此用户关联的所有角色信息
     *
     * @param userId
     */
    void deleteAllUserRoles(@Param("userId") Long userId);

}
