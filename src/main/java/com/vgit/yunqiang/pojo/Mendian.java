package com.vgit.yunqiang.pojo;

import com.vgit.yunqiang.pojo.base.BasePojo;

/**
 * 门店
 */
public class Mendian extends BasePojo {

    private Long id;

    private String name;

    private Long pid;

    /*
    门店级别
     */
    private Integer level;

    private Integer state;

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

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Mendian{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pid=" + pid +
                ", level=" + level +
                ", state=" + state +
                '}';
    }

}
