package com.vgit.yunqiang.pojo;

import com.vgit.yunqiang.pojo.base.BasePojo;

public class FinStockQuarterly extends BasePojo {

    private Long id;

    private Long stockId;

    private Long userId;

    private String year;

    private String quarterly;

    private Double purchTotal;

    // 上季度欠款
    private Double beforeArrears;

    // 上季度库存货值
    private Double beforeInventory;

    // 上季度零钱
    private Double beforeChange;

    private Double salesTotal;

    private Double arrears;

    private Double changes;

    private Double inventory;

    private Double pl;

    private Integer state;

    private Integer type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getQuarterly() {
        return quarterly;
    }

    public void setQuarterly(String quarterly) {
        this.quarterly = quarterly;
    }

    public Double getPurchTotal() {
        return purchTotal;
    }

    public void setPurchTotal(Double purchTotal) {
        this.purchTotal = purchTotal;
    }

    public Double getBeforeArrears() {
        return beforeArrears;
    }

    public void setBeforeArrears(Double beforeArrears) {
        this.beforeArrears = beforeArrears;
    }

    public Double getBeforeInventory() {
        return beforeInventory;
    }

    public void setBeforeInventory(Double beforeInventory) {
        this.beforeInventory = beforeInventory;
    }

    public Double getBeforeChange() {
        return beforeChange;
    }

    public void setBeforeChange(Double beforeChange) {
        this.beforeChange = beforeChange;
    }

    public Double getSalesTotal() {
        return salesTotal;
    }

    public void setSalesTotal(Double salesTotal) {
        this.salesTotal = salesTotal;
    }

    public Double getArrears() {
        return arrears;
    }

    public void setArrears(Double arrears) {
        this.arrears = arrears;
    }

    public Double getChanges() {
        return changes;
    }

    public void setChanges(Double changes) {
        this.changes = changes;
    }

    public Double getInventory() {
        return inventory;
    }

    public void setInventory(Double inventory) {
        this.inventory = inventory;
    }

    public Double getPl() {
        return pl;
    }

    public void setPl(Double pl) {
        this.pl = pl;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public boolean isEmpty() {
        return this == null ? true : false;
    }

    public boolean isNotEmpty() {
        return !isEmpty();
    }

    @Override
    public String toString() {
        return "FinStockQuarterly{" +
                "id=" + id +
                ", year='" + year + '\'' +
                ", quarterly='" + quarterly + '\'' +
                ", purchTotal=" + purchTotal +
                ", beforeArrears=" + beforeArrears +
                ", beforeInventory=" + beforeInventory +
                ", beforeChange=" + beforeChange +
                ", salesTotal=" + salesTotal +
                ", arrears=" + arrears +
                ", changes=" + changes +
                ", inventory=" + inventory +
                ", pl=" + pl +
                ", state=" + state +
                '}';
    }

}
