package com.vgit.yunqiang.pojo;

public class FinArrears {

    private Long id;

    private Long yearId;

    private Long monthId;

    private Long stockId;

    private Double amount;

    /*private BisStock stock;*/

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

    /*public BisStock getStock() {
        return stock;
    }

    public void setStock(BisStock stock) {
        this.stock = stock;
    }*/

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"yearId\":" + yearId +
                ", \"monthId\":" + monthId +
                ", \"stockId\":" + stockId +
                ", \"amount\":" + amount +
                /*", \"stock\":" + stock +*/
                '}';
    }

}
