package com.vgit.yunqiang.pojo;

public class FinQDyInventory {

    private Long id;

    private Long stockId;

    private Long yearId;

    private Long quarterlyId;

    private Double beforeSafe;

    private Double beforeArrears;

    private Double beforeChange;

    private Double beforePurch;

    private Double expend;

    private Double dailyCash;

    private Double safe;

    private Double arrears;

    private Double change;

    private Double purch;

    private Double income;

    private Integer state;

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
