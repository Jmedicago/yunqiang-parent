package com.vgit.yunqiang.pojo;

import java.math.BigDecimal;

/**
 *
 */
public class RegionMendianDaily {

    private Long id;

    private Long mendianId;

    /*
    保险柜存款
     */
    private BigDecimal safeDeposit;

    /*
    存
     */
    private BigDecimal deposit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMendianId() {
        return mendianId;
    }

    public void setMendianId(Long mendianId) {
        this.mendianId = mendianId;
    }

    public BigDecimal getSafeDeposit() {
        return safeDeposit;
    }

    public void setSafeDeposit(BigDecimal safeDeposit) {
        this.safeDeposit = safeDeposit;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    @Override
    public String toString() {
        return "RegionMendianDaily{" +
                "id=" + id +
                ", mendianId=" + mendianId +
                ", safeDeposit=" + safeDeposit +
                ", deposit=" + deposit +
                '}';
    }

}
