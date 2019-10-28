package com.vgit.yunqiang.pojo;

import com.vgit.yunqiang.pojo.base.BasePojo;

public class FinStockDailyExpendItem extends BasePojo {

    private Long id;

    private Long dailyId;

    private Long expendItemId;

    private String detail;

    private Double amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDailyId() {
        return dailyId;
    }

    public void setDailyId(Long dailyId) {
        this.dailyId = dailyId;
    }

    public Long getExpendItemId() {
        return expendItemId;
    }

    public void setExpendItemId(Long expendItemId) {
        this.expendItemId = expendItemId;
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

}
