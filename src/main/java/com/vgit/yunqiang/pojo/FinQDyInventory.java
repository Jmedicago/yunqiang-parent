package com.vgit.yunqiang.pojo;

public class FinQDyInventory {

    private Long id;

    private Long stockId;

    private Long yearId;

    private String startDate;

    private String endDate;

    private Long quarterlyId;

    private Double beforeSafe; // 上季度保险柜现金

    private Double beforeArrears; // 上季度商客欠款

    private Double beforeChange; // 上季度柜台零钱

    private Double beforePurch; // 上季度库存商品总货值

    private Double expend; // 支出总额

    private Double dailyCash; // 累计交付日现金总额

    private Double safe; // 保险柜现金

    private Double arrears; // 商客欠款

    private Double change; // 柜台零钱

    private Double purch; // 商品总货值

    private Double income; // 累计进货值

    private Integer state;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

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

    public Long getYearId() {
        return yearId;
    }

    public void setYearId(Long yearId) {
        this.yearId = yearId;
    }

    public Long getQuarterlyId() {
        return quarterlyId;
    }

    public void setQuarterlyId(Long quarterlyId) {
        this.quarterlyId = quarterlyId;
    }

    public Double getBeforeSafe() {
        return beforeSafe;
    }

    public void setBeforeSafe(Double beforeSafe) {
        this.beforeSafe = beforeSafe;
    }

    public Double getBeforeArrears() {
        return beforeArrears;
    }

    public void setBeforeArrears(Double beforeArrears) {
        this.beforeArrears = beforeArrears;
    }

    public Double getBeforeChange() {
        return beforeChange;
    }

    public void setBeforeChange(Double beforeChange) {
        this.beforeChange = beforeChange;
    }

    public Double getBeforePurch() {
        return beforePurch;
    }

    public void setBeforePurch(Double beforePurch) {
        this.beforePurch = beforePurch;
    }

    public Double getExpend() {
        return expend;
    }

    public void setExpend(Double expend) {
        this.expend = expend;
    }

    public Double getDailyCash() {
        return dailyCash;
    }

    public void setDailyCash(Double dailyCash) {
        this.dailyCash = dailyCash;
    }

    public Double getSafe() {
        return safe;
    }

    public void setSafe(Double safe) {
        this.safe = safe;
    }

    public Double getArrears() {
        return arrears;
    }

    public void setArrears(Double arrears) {
        this.arrears = arrears;
    }

    public Double getChange() {
        return change;
    }

    public void setChange(Double change) {
        this.change = change;
    }

    public Double getPurch() {
        return purch;
    }

    public void setPurch(Double purch) {
        this.purch = purch;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "{" +
                "  \"id\":" + id +
                ", \"stockId\":" + stockId +
                ", \"yearId\":" + yearId +
                ", \"quarterlyId\":" + quarterlyId +
                ", \"beforeSafe\":" + beforeSafe +
                ", \"beforeArrears\":" + beforeArrears +
                ", \"beforeChange\":" + beforeChange +
                ", \"beforePurch\":" + beforePurch +
                ", \"expend\":" + expend +
                ", \"dailyCash\":" + dailyCash +
                ", \"safe\":" + safe +
                ", \"arrears\":" + arrears +
                ", \"change\":" + change +
                ", \"purch\":" + purch +
                ", \"income\":" + income +
                ", \"state\":" + state +
                '}';
    }
}
