package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.impl.BaseServiceImpl;
import com.vgit.yunqiang.mapper.SysRoleMapper;
import com.vgit.yunqiang.pojo.SysPermission;
import com.vgit.yunqiang.pojo.SysRole;
import com.vgit.yunqiang.pojo.SysRolePermission;
import com.vgit.yunqiang.service.SysPermissionService;
import com.vgit.yunqiang.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole> implements SysRoleService {

    @Autowired
    private SysRoleMapper mapper;

    @Autowired
    private SysPermissionService sysPermissionService;

    @Override
    protected BaseMapper<SysRole> getMapper() {
        return this.mapper;
    }

    @Override
    public void correlationPermissions(Long roleId, Long... permissionIds) {
        if (permissionIds == null || permissionIds.length == 0) {
            return;
        }

        List<Long> ids = new ArrayList<Long>();
        // 遍历
        for (Long permissionId : permissionIds) {
            this.correlation(permissionId, ids);
        }
        // 去重
        for (int i = 0; i < ids.size() - 1; i++) {
            for (int j = i + 1; j < ids.size(); j++) {
                if (ids.get(i).equals(ids.get(j))) {
                    ids.remove(j);
                }
            }
        }

        // 保存角色权限关联关系
        List<SysRolePermission> rolePermissions = new ArrayList<SysRolePermission>();
        for (Long id : ids) {
            SysRolePermission rolePermission = new SysRolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(id);
            rolePermissions.add(rolePermission);
        }
        this.mapper.correlationPermissions(rolePermissions);
    }

    private void correlation(Long leaf, List<Long> ids) {
        SysPermission permission = this.sysPermissionService.get(leaf);
        if (permission != null) {
            ids.add(leaf);
            this.correlation(permission.getParentId(), ids);
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
