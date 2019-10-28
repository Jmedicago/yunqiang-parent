package com.vgit.yunqiang.pojo;

import com.vgit.yunqiang.pojo.base.BasePojo;

public class FinStockQuarterly extends BasePojo {

    private Long id;

    private Long stockId;

    private Long userId;

    private String year;

    private String quarterly;

    // 进：进货总值
    private Double purchTotal;

    // 进：上季度欠款
    private Double beforeArrears;

    // 进：上季度库存货值
    private Double beforeInventory;

    // 进：上季度零钱
    private Double beforeChange;

    // 进：上季度保险柜现金
    private Double beforeSafe;

    // 出：销售额
    private Double salesTotal;

    // 出：总支出
    private Double expendTotal;

    // 出：总存银行
    private Double depositTotal;

    // 存：保险柜现金
    private Double safe;

    // 存：欠款
    private Double arrears;

    // 存：零钱
    private Double changes;

    // 存：库存货值
    private Double inventory;

    // 盈亏
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

    public Double getBeforeSafe() {
        return beforeSafe;
    }

    public void setBeforeSafe(Double beforeSafe) {
        this.beforeSafe = beforeSafe;
    }

    public Double getExpendTotal() {
        return expendTotal;
    }

    public void setExpendTotal(Double expendTotal) {
        this.expendTotal = expendTotal;
    }

    public Double getDepositTotal() {
        return depositTotal;
    }

    public void setDepositTotal(Double depositTotal) {
        this.depositTotal = depositTotal;
    }

    public Double getSafe() {
        return safe;
    }

    public void setSafe(Double safe) {
        this.safe = safe;
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
