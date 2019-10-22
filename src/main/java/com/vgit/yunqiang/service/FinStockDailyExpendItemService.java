package com.vgit.yunqiang.service;

import com.vgit.yunqiang.common.service.BaseService;
import com.vgit.yunqiang.common.utils.Page;
import com.vgit.yunqiang.pojo.FinStockDailyExpendItem;

public interface FinStockDailyExpendItemService extends BaseService<FinStockDailyExpendItem> {

    /**
     * 编辑支付项明细信息
     *
     * @param stockDailyExpendItem
     */
    FinStockDailyExpendItem saveOrUpdateStockDailyExpendItem(FinStockDailyExpendItem stockDailyExpendItem);

    /**
     * 区域支出明细
     *
     * @param stockId
     * @return
     */
    Page<FinStockDailyExpendItem> getExpendItemsByStockId(Long stockId, Long dateTime);

}
