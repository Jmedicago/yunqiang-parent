package com.vgit.yunqiang.service;

import com.vgit.yunqiang.common.exception.BisException;
import com.vgit.yunqiang.common.service.BaseService;
import com.vgit.yunqiang.common.utils.Page;
import com.vgit.yunqiang.pojo.FinStockDailyExpendItem;

import java.util.List;

public interface FinStockDailyExpendItemService extends BaseService<FinStockDailyExpendItem> {

    /**
     * 编辑支付项明细信息
     *
     * @param stockDailyExpendItem
     */
    FinStockDailyExpendItem saveOrUpdateStockDailyExpendItem(FinStockDailyExpendItem stockDailyExpendItem) throws BisException;

    /**
     * 区域支出明细
     *
     * @param stockId
     * @return
     */
    Page<FinStockDailyExpendItem> getExpendItemsByStockId(Long stockId, Long dateTime);

    /**
     * 支出明细
     *
     * @param id
     * @return
     */
    List<FinStockDailyExpendItem> getExpendByDailyId(Long id);

    /**
     * 查询每日表报
     *
     * @param stockId
     * @return
     */
    List<FinStockDailyExpendItem> getExpendReport(Long stockId);
}
