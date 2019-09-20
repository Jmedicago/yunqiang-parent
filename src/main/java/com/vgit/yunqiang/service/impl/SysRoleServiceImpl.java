package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.consts.bis.SysRoleConsts;
import com.vgit.yunqiang.common.consts.bis.SysUserStateConsts;
import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.impl.BaseServiceImpl;
import com.vgit.yunqiang.common.utils.PassUtils;
import com.vgit.yunqiang.mapper.SysRoleMapper;
import com.vgit.yunqiang.pojo.SysPermission;
import com.vgit.yunqiang.pojo.SysRole;
import com.vgit.yunqiang.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole> implements SysRoleService {

    @Autowired
    private SysRoleMapper mapper;

    @Override
    protected BaseMapper<SysRole> getMapper() {
        return this.mapper;
    }

    @Override
    public void correlationPermissions(Long roleId, Long... permissionIds) {
        if (permissionIds == null || permissionIds.length == 0) {
            return;
        }
        for (Long permissionId : permissionIds) {
            if (!exists(roleId, permissionId)) {
                this.mapper.correlationPermissions(roleId, permissionId);
            }
        }
    }

    @Override
    public void unCorrelationPermissions(Long roleId, Long... permissionIds) {
        if (roleId == null || permissionIds.length == 0) {
            return;
        }
        for (Long permissionId : permissionIds) {
            if (exists(roleId, permissionId)) {
                this.mapper.unCorrelationPermissions(roleId, permissionId);
            }
        }
    }

    @Override
    public List<SysPermission> findRolePermissionByRoleId(Long id) {
        return this.mapper.findRolePermissionByRoleId(id);
    }

    @Override
    public List<SysPermission> findPermissionByRoleId(Long id) {
        return this.mapper.findPermissionByRoleId(id);
    }

    @Override
    public void deleteAllRolePermissions(Long id) {
        this.mapper.deleteAllRolePermissions(id);
    }

    @Override
    public void updateUserRoleId(SysRole role) {
        this.mapper.updateUserRoleId(role);
    }

    @Override
    public List<SysRole> getAll() {
        return this.mapper.getAll();
    }

    @Override
    public SysRole saveOrUpdateRole(SysRole role) {
        if (role.getId() == null) {
            role.setAvailable(true);
            this.mapper.savePart(role);
        } else {
            this.mapper.updatePart(role);
        }
        return role;
    }

    /**
     * 查询表中是否存在此数据
     *
     * @param roleId
     * @param permissionId
     * @return
     */
    private boolean exists(Long roleId, Long permissionId) {
        return this.mapper.exists(roleId, permissionId);
    }

}
