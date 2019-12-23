package com.vgit.yunqiang.service.format;

import com.vgit.yunqiang.pojo.SysRole;
import com.vgit.yunqiang.service.SysRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleFormatter {

    private static SysRoleService sysRoleService;

    @Autowired
    public void setSysRoleService(SysRoleService sysRoleService) {
        RoleFormatter.sysRoleService = sysRoleService;
    }

    public static String getRoles(String roleIds) {
        String roles = "";
        String[] rolesStrs = roleIds.split(",");
        for (String rolesStr : rolesStrs) {
            if (StringUtils.isNotBlank(rolesStr)) {
                SysRole sysRole = sysRoleService.get(Long.valueOf(rolesStr));
                if (sysRole != null) {
                    roles += sysRole.getName() + " ";
                }
            }
        }
        return roles;
    }
}
