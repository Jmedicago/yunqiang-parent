package com.vgit.yunqiang.model;

import java.io.Serializable;
import java.util.List;

public class MenuModel implements Serializable {

    private String text;

    private String url;

    private String icon;

    private List<MenuModel> children;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<MenuModel> getChildren() {
        return children;
    }

    public void setChildren(List<MenuModel> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "MenuModel{" +
                "text='" + text + '\'' +
                ", url='" + url + '\'' +
                ", icon='" + icon + '\'' +
                ", children=" + children +
                '}';
    }

}
