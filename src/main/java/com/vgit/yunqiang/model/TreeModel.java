package com.vgit.yunqiang.model;

import com.vgit.yunqiang.model.base.Node;

import java.io.Serializable;
import java.util.List;

public class TreeModel extends Node implements Serializable {

    private List<TreeModel> children;

    public List<TreeModel> getChildren() {
        return children;
    }

    public void setChildren(List<TreeModel> children) {
        this.children = children;
    }

}
