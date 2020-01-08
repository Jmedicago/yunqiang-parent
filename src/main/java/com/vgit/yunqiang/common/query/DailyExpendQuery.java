package com.vgit.yunqiang.common.query;

import com.vgit.yunqiang.common.query.base.BaseQuery;

public class DailyExpendQuery extends BaseQuery {

    private String stockName;

    private String dailyCode;

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getDailyCode() {
        return dailyCode;
    }

    public void setDailyCode(String dailyCode) {
        this.dailyCode = dailyCode;
    }

}
