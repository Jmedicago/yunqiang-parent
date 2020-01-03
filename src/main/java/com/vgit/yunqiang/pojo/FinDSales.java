package com.vgit.yunqiang.pojo;

public class FinDSales {

    private Long id;

    private Long yearId;

    private Long monthId;

    private Long dayId;

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

    public Long getDayId() {
        return dayId;
    }

    public void setDayId(Long dayId) {
        this.dayId = dayId;
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
                " \"id\":" + id +
                ", \"yearId\":" + yearId +
                ", \"monthId\":" + monthId +
                ", \"dayId\":" + dayId +
                ", \"stockId\":" + stockId +
                ", \"amount\":" + amount +
                '}';
    }

}
