package com.vgit.yunqiang.service;

import com.vgit.yunqiang.common.service.BaseService;
import com.vgit.yunqiang.pojo.SysPermission;
import com.vgit.yunqiang.pojo.SysRole;
import com.vgit.yunqiang.pojo.SysUser;

import java.util.List;

public interface SysUserService extends BaseService<SysUser> {

    /**
     * 添加用户 - 角色关系
     *
     * @param userId
     * @param roleIds
     */
    void correlationRoles(Long userId, Long... roleIds);

    /**
     * 移除用户 - 角色关系
     *
     * @param userId
     * @param roleIds
     */
    void unCorrelationRoles(Long userId, Long... roleIds);

    /**
     * 根据用户名查找其他角色
     *
     * @param username
     * @return
     */
    List<SysRole> findRoles(String username);

    /**
     * 根据用户名查找其他权限
     *
     * @param username
     * @return
     */
    List<SysPermission> findPermissions(String username);

    /**
     * 修改密码
     *
     * @param userId
     * @param newPassword
     */
    void changePassword(Long userId, String newPassword);

    /**
     * 根据用户名查询
     *
     * @param username
     * @return
     */
    SysUser findByUsername(String username);

    /**
     * 删除此用户关联的所有角色信息
     *
     * @param id
     */
    void deleteAllUserRoles(Long id);

    /**
     * 新增用户
     *
     * @param user
     */
    SysUser saveOrUpdateUser(SysUser user);

    /**
     * 删除此用户关联的所有归属信息
     *
     * @param id
     */
	void deleteAllUserStocks(Long id);

	 /**
     * 添加用户 - 归属关系
     *
     * @param userId
     * @param stockIds
     */
	void correlationStocks(Long userId, Long... stockIds);
}
