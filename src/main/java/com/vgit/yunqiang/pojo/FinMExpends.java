package com.vgit.yunqiang.pojo;

public class FinMExpends {

    private Long id;

    private Long yearId;

    private Long monthId;

    private Long expendItemId;

    private Long stockId;

    private Double amount;

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

    public Long getMonthId() {
        return monthId;
    }

    public void setMonthId(Long monthId) {
        this.monthId = monthId;
    }

    public Long getExpendItemId() {
        return expendItemId;
    }

    public void setExpendItemId(Long expendItemId) {
        this.expendItemId = expendItemId;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "{" +
                "  \"id\":" + id +
                ", \"yearId\":" + yearId +
                ", \"monthId\":" + monthId +
                ", \"expendItemId\":" + expendItemId +
                ", \"stockId\":" + stockId +
                ", \"amount\":" + amount +
                '}';
    }

}
