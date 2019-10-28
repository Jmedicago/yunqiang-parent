package com.vgit.yunqiang.common.query;

import com.vgit.yunqiang.common.query.base.BaseQuery;

import java.util.Date;

public class StockDailyQuery extends BaseQuery {

    private String key;

    private Date dateTime;

    private Long stockId;

    private Integer type;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
