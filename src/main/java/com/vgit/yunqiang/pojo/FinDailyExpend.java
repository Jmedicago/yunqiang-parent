package com.vgit.yunqiang.pojo;

public class FinDailyExpend {

    private Long id;

    private Long expendItemId;

    private String stockName;

    private String dailyCode;

    private String detail;

    private Double amount;

    private FinExpendItem finExpendItem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExpendItemId() {
        return expendItemId;
    }

    public void setExpendItemId(Long expendItemId) {
        this.expendItemId = expendItemId;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getDailyCode() {
        return dailyCode;
    }

    public void setDailyCode(String dailyCode) {
        this.dailyCode = dailyCode;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public FinExpendItem getFinExpendItem() {
        return finExpendItem;
    }

    public void setFinExpendItem(FinExpendItem finExpendItem) {
        this.finExpendItem = finExpendItem;
    }

    @Override
    public String toString() {
        return "{" +
                "  \"id\":" + id +
                ", \"expendItemId\":" + expendItemId +
                ", \"stockName\":\"" + stockName + "\"" +
                ", \"dailyCode\":\"" + dailyCode + "\"" +
                ", \"detail\":\"" + detail + "\"" +
                ", \"amount\":" + amount +
                ", \"finExpendItem\":" + finExpendItem +
                '}';
    }

}
