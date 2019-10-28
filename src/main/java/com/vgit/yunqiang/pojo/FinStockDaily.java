package com.vgit.yunqiang.pojo;

import com.vgit.yunqiang.pojo.base.BasePojo;

public class FinStockDaily extends BasePojo {

    private Long id;

    private Long stockId;

    private Long userId;

    private Double income;

    private Double expendTotal;

    private Double sales;

    private Double purch;

    private Double arrears;

    private Double safe;

    private Double deposit;

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

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Double getExpendTotal() {
        return expendTotal;
    }

    public void setExpendTotal(Double expendTotal) {
        this.expendTotal = expendTotal;
    }

    public Double getSales() {
        return sales;
    }

    public void setSales(Double sales) {
        this.sales = sales;
    }

    public Double getPurch() {
        return purch;
    }

    public void setPurch(Double purch) {
        this.purch = purch;
    }

    public Double getArrears() {
        return arrears;
    }

    public void setArrears(Double arrears) {
        this.arrears = arrears;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getSafe() {
        return safe;
    }

    public void setSafe(Double safe) {
        this.safe = safe;
    }

    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "FinStockDaily{" +
                "id=" + id +
                ", stockId=" + stockId +
                ", userId=" + userId +
                ", income=" + income +
                ", expendTotal=" + expendTotal +
                ", sales=" + sales +
                ", purch=" + purch +
                ", arrears=" + arrears +
                ", safe=" + safe +
                ", deposit=" + deposit +
                ", type=" + type +
                '}';
    }

}
