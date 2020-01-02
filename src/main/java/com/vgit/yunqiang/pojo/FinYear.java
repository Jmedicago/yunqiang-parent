package com.vgit.yunqiang.pojo;

import java.util.List;

public class FinYear {

    private Long id;

    private String name;

    List<FinMonth> monthList;

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

    public List<FinMonth> getMonthList() {
        return monthList;
    }

    public void setMonthList(List<FinMonth> monthList) {
        this.monthList = monthList;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"name\":\"" + name + "\"" +
                ", \"monthList\":" + monthList +
                '}';
    }

}
