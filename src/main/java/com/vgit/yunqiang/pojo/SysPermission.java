package com.vgit.yunqiang.pojo;

/**
 * 权限
 */
public class SysPermission {

    private Long id;

    /*
    名称
     */
    private String name;

    /*
    图标
     */
    private String icon;

    /*
    唯一标识
     */
    private String identity;

    /*
    描述
     */
    private String description;

    /*
    资源类型
     */
    private Byte type;

    /*
    资源路径
     */
    private String url;

    /*
    权限字符串
     */
    private String permission;

    /*
    父编号
     */
    private Long parentId;

    /*
    路径
     */
    private String path;

    /*
    排序
     */
    private Integer sort;

    /*
    是否显示
     */
    private Boolean available;

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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "SysPermission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", identity='" + identity + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", url='" + url + '\'' +
                ", permission='" + permission + '\'' +
                ", parentId=" + parentId +
                ", path='" + path + '\'' +
                ", sort=" + sort +
                ", available=" + available +
                '}';
    }

}
