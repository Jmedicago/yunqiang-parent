package com.vgit.yunqiang.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vgit.yunqiang.service.format.RoleFormatter;
import com.vgit.yunqiang.service.format.StockFormat;

/**
 * 用户
 */
public class SysUser {

    private Long id;

    private String username;

    private String email;

    private String phone;

    private String manager;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private String salt;

    private String roleIds;

    private String stockIds;

    private Byte status;

    private Long createTime;

    private Long updateTime;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public String getStockIds() {
        return stockIds;
    }

    public void setStockIds(String stockIds) {
        this.stockIds = stockIds;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    @JsonIgnore
    public String getCredentialsSalt() {
        return this.username + this.salt;
    }

    @JsonIgnore
    public boolean isEmpty() {
        if (this == null) return true;
        return false;
    }

    @JsonIgnore
    public boolean isNotEmpty() {
        return !isEmpty();
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRoleList() {
        return RoleFormatter.getRoles(roleIds);
    }

    public String getStockPath() {
        return StockFormat.getStockPath(Long.valueOf(stockIds));
    }

}
