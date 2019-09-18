package com.vgit.yunqiang.pojo;

/**
 * 门店日支出集
 */
public class MendianDailyExpenditureItem {

    private Long id;

    /*
    支出项ID
     */
    private Long expenditureItemId;

    /*
    门店日报ID
     */
    private Long MendianDailyId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExpenditureItemId() {
        return expenditureItemId;
    }

    public void setExpenditureItemId(Long expenditureItemId) {
        this.expenditureItemId = expenditureItemId;
    }

    public Long getMendianDailyId() {
        return MendianDailyId;
    }

    public void setMendianDailyId(Long mendianDailyId) {
        MendianDailyId = mendianDailyId;
    }

    @Override
    public String toString() {
        return "MendianDailyExpenditureItem{" +
                "id=" + id +
                ", expenditureItemId=" + expenditureItemId +
                ", MendianDailyId=" + MendianDailyId +
                '}';
    }

}
