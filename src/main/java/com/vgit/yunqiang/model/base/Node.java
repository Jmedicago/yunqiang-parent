package com.vgit.yunqiang.model.base;

/**
 * 树状模型
 */
public class Node {

    /*
    节点的id值
     */
    private Long id; //

    /*
    节点对应的名称
     */
    private String text;

    /*
    是否是父节点
     */
    private Boolean isParent;

    /*
    当前节点对应的父节点的id值
     */
    private Long pid;

    public Node() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getParent() {
        return isParent;
    }

    public void setParent(Boolean parent) {
        isParent = parent;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

}
