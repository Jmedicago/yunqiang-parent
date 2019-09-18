package com.vgit.yunqiang.pojo;

/**
 * 资源
 */
public class SysResource {

    private Long id;

    /*
     标题
     */
    private String name;

    /*
    资源标识符 用于权限匹配的 如sys:resource
     */
    private String identity;

    /*
    地址
     */
    private String url;

    /*
    资源类型
     */
    private Integer type;

    /*
     父路径
     */
    private Long parentId;

    private String parentIds;

    /*
    图标
     */
    private String icon;

    /*
    权重 用于排序 越小越排在前边
     */
    private Integer weight;

    /*
    是否显示
     */
    private Boolean isShow;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Boolean getShow() {
        return isShow;
    }

    public void setShow(Boolean show) {
        isShow = show;
    }

}
