package com.vgit.yunqiang.service;

import com.vgit.yunqiang.common.exception.BisException;
import com.vgit.yunqiang.common.service.BaseService;
import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.pojo.FinDailyExpend;

public interface FinDailyExpendService extends BaseService<FinDailyExpend> {

    /**
     * 编辑
     *
     * @param dailyExpend
     */
    void saveOrUpdateStock(FinDailyExpend dailyExpend) throws BisException;

    /**
     * 删除
     *
     * @param id
     * @return
     */
    Ret deleteById(Long id);
}
