package com.vgit.yunqiang.pojo;

import com.vgit.yunqiang.pojo.base.BasePojo;

public class FinRegionStockDaily extends BasePojo {

    private Long id;

    private Long stockId;

    private Long userId;

    private Double safe;

    private Double deposit;

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

    @Override
    public String toString() {
        return "FinRegionStockDaily{" +
                "id=" + id +
                ", stockId=" + stockId +
                ", userId=" + userId +
                ", safe=" + safe +
                ", deposit=" + deposit +
                '}';
    }

}
