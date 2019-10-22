package com.vgit.yunqiang.service;

import com.vgit.yunqiang.common.service.BaseService;
import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.pojo.FinStockDaily;

import java.util.List;

public interface FinStockDailyService extends BaseService<FinStockDaily> {

    /**
     * 编辑日报信息
     *
     * @param finStockDaily
     * @return
     */
    FinStockDaily saveOrUpdateDaily(FinStockDaily finStockDaily);

    /**
     * 删除日报信息
     *
     * @param id
     * @return
     */
    Ret deleteById(Long id);

    /**
     * 查询日进出账明细
     *
     * @param stockId
     * @return
     */
    List<FinStockDaily> getChildren(Long stockId, Long dateTime);
}
