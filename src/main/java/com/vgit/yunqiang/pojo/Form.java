package com.vgit.yunqiang.pojo;

import java.util.List;

public class Form {

    private Long id;

    private Double income;

    private Double purch;

    private Double arrears;

    private Double sales;

    private List<BaseForm> baseFormList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
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

    public List<BaseForm> getBaseFormList() {
        return baseFormList;
    }

    public void setBaseFormList(List<BaseForm> baseFormList) {
        this.baseFormList = baseFormList;
    }
    
}
