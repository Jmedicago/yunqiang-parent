package com.vgit.yunqiang.service;

import com.vgit.yunqiang.common.exception.BisException;
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
    FinStockDaily saveOrUpdateDaily(FinStockDaily finStockDaily) throws BisException;

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

    /**
     * 是否存在
     *
     * @param dateTime
     * @param type
     * @return
     */
    boolean exist(Long dateTime, Integer type, Long stockId);

    /**
     * 校验是否在当天填报时间内
     *
     * @param dailyId
     * @return
     */
    boolean validateTime(Long dailyId);

}
