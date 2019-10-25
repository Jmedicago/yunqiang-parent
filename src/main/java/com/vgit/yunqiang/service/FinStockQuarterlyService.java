package com.vgit.yunqiang.service;

import com.vgit.yunqiang.common.service.BaseService;
import com.vgit.yunqiang.pojo.FinStockQuarterly;

public interface FinStockQuarterlyService extends BaseService<FinStockQuarterly> {

    /**
     * 创建当前季度表报
     *
     * @param stockQuarterly
     * @return
     */
    FinStockQuarterly saveOrUpdateQuarterly(FinStockQuarterly stockQuarterly);
}
