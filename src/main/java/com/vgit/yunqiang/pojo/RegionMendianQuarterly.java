package com.vgit.yunqiang.pojo;

import java.math.BigDecimal;

/**
 * 区域门店季报
 */
public class RegionMendianQuarterly {

    private Long id;

    private Long year;

    /*
    季度
     */
    private String quarterly;

    /*
    上季度保险柜现金
     */
    private BigDecimal lastQuarterSafeCash;

    /*
   上季度库存货值
   */
    private BigDecimal lastQuarterInventory;

    /*
    上季度欠款
     */
    private BigDecimal lastQuarterArrears;

    /*
    上季度零钱
     */
    private BigDecimal lastQuarterChange;

    /*
    当前季度进货总值
     */
    private BigDecimal currentQuarterTotalPurchase;

    /*
    当前季度保险柜现金
     */
    private BigDecimal currentQuarterSafeCash;

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
    总支出
     */
    private BigDecimal totalExpenditure;

    /*
    总存银行
     */
    private BigDecimal depositBank;

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

    public BigDecimal getLastQuarterSafeCash() {
        return lastQuarterSafeCash;
    }

    public void setLastQuarterSafeCash(BigDecimal lastQuarterSafeCash) {
        this.lastQuarterSafeCash = lastQuarterSafeCash;
    }

    public BigDecimal getLastQuarterInventory() {
        return lastQuarterInventory;
    }

    public void setLastQuarterInventory(BigDecimal lastQuarterInventory) {
        this.lastQuarterInventory = lastQuarterInventory;
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

    public BigDecimal getCurrentQuarterTotalPurchase() {
        return currentQuarterTotalPurchase;
    }

    public void setCurrentQuarterTotalPurchase(BigDecimal currentQuarterTotalPurchase) {
        this.currentQuarterTotalPurchase = currentQuarterTotalPurchase;
    }

    public BigDecimal getCurrentQuarterSafeCash() {
        return currentQuarterSafeCash;
    }

    public void setCurrentQuarterSafeCash(BigDecimal currentQuarterSafeCash) {
        this.currentQuarterSafeCash = currentQuarterSafeCash;
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

    public BigDecimal getTotalExpenditure() {
        return totalExpenditure;
    }

    public void setTotalExpenditure(BigDecimal totalExpenditure) {
        this.totalExpenditure = totalExpenditure;
    }

    public BigDecimal getDepositBank() {
        return depositBank;
    }

    public void setDepositBank(BigDecimal depositBank) {
        this.depositBank = depositBank;
    }

    public BigDecimal getProfitAndLoss() {
        return profitAndLoss;
    }

    public void setProfitAndLoss(BigDecimal profitAndLoss) {
        this.profitAndLoss = profitAndLoss;
    }

    @Override
    public String toString() {
        return "RegionMendianQuarterly{" +
                "id=" + id +
                ", year=" + year +
                ", quarterly='" + quarterly + '\'' +
                ", lastQuarterSafeCash=" + lastQuarterSafeCash +
                ", lastQuarterInventory=" + lastQuarterInventory +
                ", lastQuarterArrears=" + lastQuarterArrears +
                ", lastQuarterChange=" + lastQuarterChange +
                ", currentQuarterTotalPurchase=" + currentQuarterTotalPurchase +
                ", currentQuarterSafeCash=" + currentQuarterSafeCash +
                ", currentQuarterArrears=" + currentQuarterArrears +
                ", currentQuarterChange=" + currentQuarterChange +
                ", currentQuarterInventory=" + currentQuarterInventory +
                ", totalExpenditure=" + totalExpenditure +
                ", depositBank=" + depositBank +
                ", profitAndLoss=" + profitAndLoss +
                '}';
    }

}
