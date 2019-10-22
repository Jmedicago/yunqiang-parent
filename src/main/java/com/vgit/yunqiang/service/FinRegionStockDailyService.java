package com.vgit.yunqiang.service;

import com.vgit.yunqiang.common.service.BaseService;
import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.pojo.FinRegionStockDaily;
import com.vgit.yunqiang.pojo.FinStockDaily;

import java.util.List;

public interface FinRegionStockDailyService extends BaseService<FinRegionStockDaily> {

    /**
     * 编辑区域日报
     *
     * @param regionStockDaily
     * @return
     */
    FinRegionStockDaily saveOrUpdateDaily(FinRegionStockDaily regionStockDaily);

    /**
     * 删除区域日报
     *
     * @param id
     * @return
     */
    Ret deleteById(Long id);

    /**
     * 查询每日明细
     *
     * @param stockId
     * @return
     */
    List<FinStockDaily> getChildren(Long stockId, Long dateTime);
}
