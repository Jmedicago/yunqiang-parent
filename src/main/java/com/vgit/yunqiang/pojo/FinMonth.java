package com.vgit.yunqiang.pojo;

import java.util.List;

public class FinMonth {

    private Long id;

    private Long quarterlyId;

    private String name;

    List<FinArrears> arrearsList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuarterlyId() {
        return quarterlyId;
    }

    public void setQuarterlyId(Long quarterlyId) {
        this.quarterlyId = quarterlyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FinArrears> getArrearsList() {
        return arrearsList;
    }

    public void setArrearsList(List<FinArrears> arrearsList) {
        this.arrearsList = arrearsList;
    }

    @Override
    public String toString() {
        return "{" +
                "  \"id\":" + id +
                ", \"name\":\"" + name + "\"" +
                ", \"arrearsList\":" + arrearsList +
                '}';
    }

}
