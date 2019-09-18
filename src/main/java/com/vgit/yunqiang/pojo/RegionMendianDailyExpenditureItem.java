package com.vgit.yunqiang.pojo;

/**
 * 区域门店支出集
 */
public class RegionMendianDailyExpenditureItem {

    private Long id;

    private Long expenditureItemId;

    private Long regionMendianDailyId;

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

    public Long getRegionMendianDailyId() {
        return regionMendianDailyId;
    }

    public void setRegionMendianDailyId(Long regionMendianDailyId) {
        this.regionMendianDailyId = regionMendianDailyId;
    }

    @Override
    public String toString() {
        return "RegionMendianDailyExpenditureItem{" +
                "id=" + id +
                ", expenditureItemId=" + expenditureItemId +
                ", regionMendianDailyId=" + regionMendianDailyId +
                '}';
    }

}
