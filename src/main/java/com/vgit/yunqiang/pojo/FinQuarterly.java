package com.vgit.yunqiang.pojo;

import java.util.List;

public class FinQuarterly {

    private Long id;

    private String name;

    private List<BisStock> bisStockList;

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

    public List<BisStock> getBisStockList() {
        return bisStockList;
    }

    public void setBisStockList(List<BisStock> bisStockList) {
        this.bisStockList = bisStockList;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"name\":'" + name + '\'' +
                ", \"bisStockList\":'" + bisStockList + '\'' +
                '}';
    }

}
