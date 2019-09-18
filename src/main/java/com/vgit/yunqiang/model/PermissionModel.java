package com.vgit.yunqiang.model;

import com.vgit.yunqiang.pojo.SysPermission;

import java.io.Serializable;
import java.util.List;

public class PermissionModel extends SysPermission implements Serializable {

    private List<PermissionModel> children;

    public List<PermissionModel> getChildren() {
        return children;
    }

    public void setChildren(List<PermissionModel> children) {
        this.children = children;
    }

}
