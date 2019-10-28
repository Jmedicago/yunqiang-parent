package com.vgit.yunqiang.realm;

import com.vgit.yunqiang.pojo.SysPermission;
import com.vgit.yunqiang.pojo.SysRole;
import com.vgit.yunqiang.pojo.SysUser;
import com.vgit.yunqiang.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SysUserRealm extends AuthorizingRealm {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysUserRealm.class);

    @Autowired
    private SysUserService sysUserService;

    /**
     * 权限校验
     *
     * @param principals
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        LOGGER.info("权限校验--执行了doGetAuthorizationInfo...");
        SysUser existUser = (SysUser) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> roles = new HashSet<String>();
        List<SysRole> roleList = this.sysUserService.findRoles(existUser.getUsername());
        for (SysRole role : roleList) {
            roles.add(role.getRole());
        }
        authorizationInfo.setRoles(roles);
        Set<String> permissions = new HashSet<String>();
        List<SysPermission> permissionList = this.sysUserService.findPermissions(existUser.getUsername());
        for (SysPermission permission : permissionList) {
            if (StringUtils.isNotBlank(permission.getPermission())) {
                permissions.add(permission.getPermission());
            }
        }
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }

    /**
     * 身份校验
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        LOGGER.info("身份校验--执行了goGetAuthenticationInfo...");
        String username = (String) token.getPrincipal();
        SysUser user = this.sysUserService.findByUsername(username);
        if (user == null) {
            throw new UnknownAccountException(); //没有找到账号
        }
        if ("locked".equals(user.getStatus())) {
            throw new LockedAccountException();  //账号已锁定
        }
        // 密码匹配
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo( 
                user, // 用户登录信息
                user.getPassword(), //密码
                ByteSource.Util.bytes(user.getSalt()), //salt=username+salt
                getName() //realm name
        );
        return authenticationInfo;
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }

}
