package com.vgit.yunqiang.common.query;

import com.vgit.yunqiang.common.query.base.BaseQuery;
import com.vgit.yunqiang.common.utils.TimeUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

public class LogStockShuntQuery extends BaseQuery {

    private Long stockId;

    private Long skuId;

    private String date;

    private Long beginDate;

    private Long endDate;

    private String remark;

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getBeginDate() {
        if (StringUtils.isNotBlank(date)) {
            Date curDate = TimeUtils.strToDate(date);
            return TimeUtils.getDayBegin(curDate).getTime();
        }
        return null;
    }

    public Long getEndDate() {
        if (StringUtils.isNotBlank(date)) {
            Date curDate = TimeUtils.strToDate(date);
            return TimeUtils.getDayEnd(curDate).getTime();
        }
        return null;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
