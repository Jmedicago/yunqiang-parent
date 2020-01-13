package com.vgit.yunqiang.pojo;

import com.vgit.yunqiang.pojo.base.BasePojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FinDzDaily extends BasePojo {

    private Long id;

    private String code;

    private Long stockId;

    private Long userId;

    private Date date;

    private String dateFormatter;

    private Double incomeSubTotal;

    private Double expendSubTotal;

    private Double deposit;

    private Double purch;

    private Double arrears;

    private Double sales;

    private BisStock bisStock;

    private List<FinDyDaily> finDyDailyList = new ArrayList<FinDyDaily>();

    private List<FinDailyExpend> finDailyExpendList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getIncomeSubTotal() {
        return incomeSubTotal;
    }

    public void setIncomeSubTotal(Double incomeSubTotal) {
        this.incomeSubTotal = incomeSubTotal;
    }

    public Double getExpendSubTotal() {
        return expendSubTotal;
    }

    public void setExpendSubTotal(Double expendSubTotal) {
        this.expendSubTotal = expendSubTotal;
    }

    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
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

    public Double getSales() {
        return sales;
    }

    public void setSales(Double sales) {
        this.sales = sales;
    }

    public BisStock getBisStock() {
        return bisStock;
    }

    public void setBisStock(BisStock bisStock) {
        this.bisStock = bisStock;
    }

    public List<FinDyDaily> getFinDyDailyList() {
        return finDyDailyList;
    }

    public void setFinDyDailyList(List<FinDyDaily> finDyDailyList) {
        this.finDyDailyList = finDyDailyList;
    }

    public String getDateFormatter() {
        return dateFormatter;
    }

    public void setDateFormatter(String dateFormatter) {
        this.dateFormatter = dateFormatter;
    }

    public List<FinDailyExpend> getFinDailyExpendList() {
        return finDailyExpendList;
    }

    public void setFinDailyExpendList(List<FinDailyExpend> finDailyExpendList) {
        this.finDailyExpendList = finDailyExpendList;
    }

    @Override
    public String toString() {
        return "{" +
                "  \"id\":" + id +
                ", \"code\":\"" + code + "\"" +
                ", \"stockId\":" + stockId +
                ", \"userId:\":" + userId +
                ", \"date\":\"" + date + "\"" +
                ", \"incomeSubTotal\":" + incomeSubTotal +
                ", \"expendSubTotal\":" + expendSubTotal +
                ", \"deposit\":" + deposit +
                ", \"purch\":" + purch +
                ", \"arrears\":" + arrears +
                ", \"sales\":" + sales +
                ", \"finDyDailyList\":" + finDyDailyList +
                '}';
    }

}
