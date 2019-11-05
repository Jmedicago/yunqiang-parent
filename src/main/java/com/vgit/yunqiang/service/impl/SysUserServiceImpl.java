package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.consts.bis.SysUserStateConsts;
import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.impl.BaseServiceImpl;
import com.vgit.yunqiang.common.utils.PassUtils;
import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.mapper.SysUserMapper;
import com.vgit.yunqiang.pojo.SysPermission;
import com.vgit.yunqiang.pojo.SysRole;
import com.vgit.yunqiang.pojo.SysUser;
import com.vgit.yunqiang.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper mapper;

    @Override
    protected BaseMapper<SysUser> getMapper() {
        return this.mapper;
    }

    @Override
    public void correlationRoles(Long userId, Long... roleIds) {
        if (roleIds == null || roleIds.length == 0) {
            return;
        }
        for (Long roleId : roleIds) {
            if (!exists(userId, roleId)) {
                this.mapper.correlationRoles(userId, roleId);
            }
        }
    }

    @Override
    public void unCorrelationRoles(Long userId, Long... roleIds) {
        if (roleIds == null || roleIds.length == 0) {
            return;
        }
        for (Long roleId : roleIds) {
            if (exists(userId, roleId)) {
                this.mapper.unCorrelationRoles(userId, roleId);
            }
        }
    }

    @Override
    public List<SysRole> findRoles(String username) {
        return this.mapper.findRoles(username);
    }

    @Override
    public List<SysPermission> findPermissions(String username) {
        return this.mapper.findPermissions(username);
    }

    @Override
    public void changePassword(Long userId, String newPassword) {

    }

    @Override
    public SysUser findByUsername(String username) {
        return this.mapper.findByUsername(username);
    }

    @Override
    public void deleteAllUserRoles(Long id) {
        this.mapper.deleteAllUserRoles(id);
    }

    @Override
    public SysUser saveOrUpdateUser(SysUser user) {
        if (user.getId() == null) {
            //加密密码
            new PassUtils().encryptPassword(user);
            user.setStatus(SysUserStateConsts.NORMAL);
            user.setCreateTime(System.currentTimeMillis());
            this.mapper.savePart(user);
        } else {
            user.setUpdateTime(System.currentTimeMillis());
            this.mapper.updatePart(user);
        }
        return user;
    }

    /**
     * 判断当前的用户和角色是否存在
     *
     * @param userId
     * @param roleId
     * @return
     */
    public boolean exists(Long userId, Long roleId) {
        return this.mapper.exists(userId, roleId);
    }

	@Override
	public void deleteAllUserStocks(Long id) {
		// TODO Auto-generated method stub
		this.mapper.deleteAllUserStocks(id);
	}

	@Override
	public void correlationStocks(Long userId, Long... stockIds) {
		// TODO Auto-generated method stub
		if (stockIds == null || stockIds.length == 0) {
            return;
        }
        for (Long stockId : stockIds) {
            if (!exists(userId, stockId)) {
                this.mapper.correlationStocks(userId, stockId);
            }
        }
	}

	@Override
	public Ret modifyPsw(Long id, String newPsw, String onceNewPsw) {
		if (newPsw.equals(onceNewPsw)) {
			return Ret.me().setSuccess(false).setInfo("密码不一致，请重新输入");
		}
		SysUser sysUser = new SysUser();
		sysUser.setId(id);
		sysUser.setPassword(newPsw);
		new PassUtils().encryptPassword(sysUser);
		
		this.mapper.updatePart(sysUser);
		return Ret.me();
	}

}
