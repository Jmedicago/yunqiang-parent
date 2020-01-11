package com.vgit.yunqiang.pojo;

import com.vgit.yunqiang.pojo.base.BasePojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FinDyDaily extends BasePojo {

    private Long id;

    private String code;

    private Long stockId;

    private Long userId;

    private String dateFormatter;

    private Date date;

    private Double income;

    private Double expendSubTotal;

    private Double purch;

    private Double arrears;

    private Double sales;

    private BisStock bisStock;

    private List<FinDailyExpend> finDailyExpendList = new ArrayList<FinDailyExpend>();

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

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Double getExpendSubTotal() {
        return expendSubTotal;
    }

    public void setExpendSubTotal(Double expendSubTotal) {
        this.expendSubTotal = expendSubTotal;
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

    public List<FinDailyExpend> getFinDailyExpendList() {
        return finDailyExpendList;
    }

    public void setFinDailyExpendList(List<FinDailyExpend> finDailyExpendList) {
        this.finDailyExpendList = finDailyExpendList;
    }

    public String getDateFormatter() {
        return dateFormatter;
    }

    public void setDateFormatter(String dateFormatter) {
        this.dateFormatter = dateFormatter;
    }

    public BisStock getBisStock() {
        return bisStock;
    }

    public void setBisStock(BisStock bisStock) {
        this.bisStock = bisStock;
    }

    @Override
    public String toString() {
        return "{" +
                "  \"id\":" + id +
                ", \"code\":\"" + code + "\"" +
                ", \"stockId\":" + stockId +
                ", \"userId\":" + userId +
                ", \"date\":\"" + date + "\""+
                ", \"income\":" + income +
                ", \"expendSubTotal\":" + expendSubTotal +
                ", \"purch\":" + purch +
                ", \"arrears\":" + arrears +
                ", \"sales\":" + sales +
                ", \"bisStock\":" + bisStock +
                ", \"finDailyExpendList\":" + finDailyExpendList +
                '}';
    }

}
