package com.vgit.yunqiang.pojo;

import java.math.BigDecimal;

/**
 * 终端仓库季报
 */
public class GodownQuarterly {

    private Long id;

    private Long year;

    /*
    季度
     */
    private String quarterly;

    /*
    当前季度进货总值
     */
    private BigDecimal currentQuarterlyTotalPurchase;

    /*
    上季度欠款
     */
    private BigDecimal lastQuarterArrears;

    /*
    上季度零钱
     */
    private BigDecimal lastQuarterChange;

    /*
    上季度库存货值
     */
    private BigDecimal lastQuarterInventory;

    /*
    销售额
     */
    private BigDecimal salesVolume;

    /*
    当前季度欠款
     */
    private BigDecimal currentQuarterArrears;

    /*
    当前季度零钱
     */
    private BigDecimal currentQuarterChange;

    /*
    当前季度库存货值
     */
    private BigDecimal currentQuarterInventory;

    /*
    盈亏
     */
    private BigDecimal profitAndLoss;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public String getQuarterly() {
        return quarterly;
    }

    public void setQuarterly(String quarterly) {
        this.quarterly = quarterly;
    }

    public BigDecimal getCurrentQuarterlyTotalPurchase() {
        return currentQuarterlyTotalPurchase;
    }

    public void setCurrentQuarterlyTotalPurchase(BigDecimal currentQuarterlyTotalPurchase) {
        this.currentQuarterlyTotalPurchase = currentQuarterlyTotalPurchase;
    }

    public BigDecimal getLastQuarterArrears() {
        return lastQuarterArrears;
    }

    public void setLastQuarterArrears(BigDecimal lastQuarterArrears) {
        this.lastQuarterArrears = lastQuarterArrears;
    }

    public BigDecimal getLastQuarterChange() {
        return lastQuarterChange;
    }

    public void setLastQuarterChange(BigDecimal lastQuarterChange) {
        this.lastQuarterChange = lastQuarterChange;
    }

    public BigDecimal getLastQuarterInventory() {
        return lastQuarterInventory;
    }

    public void setLastQuarterInventory(BigDecimal lastQuarterInventory) {
        this.lastQuarterInventory = lastQuarterInventory;
    }

    public BigDecimal getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(BigDecimal salesVolume) {
        this.salesVolume = salesVolume;
    }

    public BigDecimal getCurrentQuarterArrears() {
        return currentQuarterArrears;
    }

    public void setCurrentQuarterArrears(BigDecimal currentQuarterArrears) {
        this.currentQuarterArrears = currentQuarterArrears;
    }

    public BigDecimal getCurrentQuarterChange() {
        return currentQuarterChange;
    }

    public void setCurrentQuarterChange(BigDecimal currentQuarterChange) {
        this.currentQuarterChange = currentQuarterChange;
    }

    public BigDecimal getCurrentQuarterInventory() {
        return currentQuarterInventory;
    }

    public void setCurrentQuarterInventory(BigDecimal currentQuarterInventory) {
        this.currentQuarterInventory = currentQuarterInventory;
    }

    public BigDecimal getProfitAndLoss() {
        return profitAndLoss;
    }

    public void setProfitAndLoss(BigDecimal profitAndLoss) {
        this.profitAndLoss = profitAndLoss;
    }

    @Override
    public String toString() {
        return "GodownQuarterly{" +
                "id=" + id +
                ", year=" + year +
                ", quarterly='" + quarterly + '\'' +
                ", currentQuarterlyTotalPurchase=" + currentQuarterlyTotalPurchase +
                ", lastQuarterArrears=" + lastQuarterArrears +
                ", lastQuarterChange=" + lastQuarterChange +
                ", lastQuarterInventory=" + lastQuarterInventory +
                ", salesVolume=" + salesVolume +
                ", currentQuarterArrears=" + currentQuarterArrears +
                ", currentQuarterChange=" + currentQuarterChange +
                ", currentQuarterInventory=" + currentQuarterInventory +
                ", profitAndLoss=" + profitAndLoss +
                '}';
    }
}
