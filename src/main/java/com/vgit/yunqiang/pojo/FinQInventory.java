package com.vgit.yunqiang.pojo;

import java.util.Date;

public class FinQInventory {

    private Long id;

    private Long yearId;

    private Long quarterlyId;

    private Date startDate;

    private Date endDate;

    private Double inventory;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getYearId() {
        return yearId;
    }

    public void setYearId(Long yearId) {
        this.yearId = yearId;
    }

    public Long getQuarterlyId() {
        return quarterlyId;
    }

    public void setQuarterlyId(Long quarterlyId) {
        this.quarterlyId = quarterlyId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Double getInventory() {
        return inventory;
    }

    public void setInventory(Double inventory) {
        this.inventory = inventory;
    }
    
}
