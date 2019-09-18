package com.vgit.yunqiang.pojo;

import java.math.BigDecimal;

/**
 * 门店日报
 */
public class MendianDaily {

    private Long id;

    private Long mendianId;

    private Long dateTime;

    /*
    日收入
     */
    private BigDecimal dailyIncome;

    /*
    日支出总计
     */
    private BigDecimal totalDailyExpenditure;

    /*
    销售额
     */
    private BigDecimal salesVolume;

    /*
    进货值
     */
    private BigDecimal purchaseValue;

    /*
    欠款
     */
    private BigDecimal arrears;

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

    public Long getDateTime() {
        return dateTime;
    }

    public void setDateTime(Long dateTime) {
        this.dateTime = dateTime;
    }

    public BigDecimal getDailyIncome() {
        return dailyIncome;
    }

    public void setDailyIncome(BigDecimal dailyIncome) {
        this.dailyIncome = dailyIncome;
    }

    public BigDecimal getTotalDailyExpenditure() {
        return totalDailyExpenditure;
    }

    public void setTotalDailyExpenditure(BigDecimal totalDailyExpenditure) {
        this.totalDailyExpenditure = totalDailyExpenditure;
    }

    public BigDecimal getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(BigDecimal salesVolume) {
        this.salesVolume = salesVolume;
    }

    public BigDecimal getPurchaseValue() {
        return purchaseValue;
    }

    public void setPurchaseValue(BigDecimal purchaseValue) {
        this.purchaseValue = purchaseValue;
    }

    public BigDecimal getArrears() {
        return arrears;
    }

    public void setArrears(BigDecimal arrears) {
        this.arrears = arrears;
    }

    @Override
    public String toString() {
        return "MendianDaily{" +
                "id=" + id +
                ", mendianId=" + mendianId +
                ", dateTime=" + dateTime +
                ", dailyIncome=" + dailyIncome +
                ", totalDailyExpenditure=" + totalDailyExpenditure +
                ", salesVolume=" + salesVolume +
                ", purchaseValue=" + purchaseValue +
                ", arrears=" + arrears +
                '}';
    }

}
