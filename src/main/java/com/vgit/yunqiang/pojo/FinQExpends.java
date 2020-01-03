package com.vgit.yunqiang.pojo;

public class FinQExpends {

    private Long id;

    private Long yearId;

    private Long quarterlyId;

    private Long stockId;

    private Long expendItemId;

    private Double amount;

    private FinExpendItem expendItem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public Long getExpendItemId() {
        return expendItemId;
    }

    public void setExpendItemId(Long expendItemId) {
        this.expendItemId = expendItemId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public FinExpendItem getExpendItem() {
        return expendItem;
    }

    public void setExpendItem(FinExpendItem expendItem) {
        this.expendItem = expendItem;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"yearId\":" + yearId +
                ", \"quarterlyId\":" + quarterlyId +
                ", \"stockId\":" + stockId +
                ", \"expendItemId\":" + expendItemId +
                ", \"expendItem\":" + expendItem +
                ", \"amount\":" + amount +
                '}';
    }

}
