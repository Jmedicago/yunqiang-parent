package com.vgit.yunqiang.model;

import com.vgit.yunqiang.pojo.Mendian;

import java.util.List;

public class MendianModel extends Mendian {

    private List<MendianModel> children;

    public List<MendianModel> getChildren() {
        return children;
    }

    public void setChildren(List<MendianModel> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "MendianModel{" +
                "children=" + children +
                '}';
    }

}
