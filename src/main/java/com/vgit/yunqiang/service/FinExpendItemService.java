package com.vgit.yunqiang.service;

import com.vgit.yunqiang.common.service.BaseService;
import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.pojo.FinExpendItem;

public interface FinExpendItemService extends BaseService<FinExpendItem> {

    /**
     * 编辑支出项信息
     *
     * @param expendItem
     * @return
     */
    FinExpendItem saveOrUpdateExpendItem(FinExpendItem expendItem);

    /**
     * 删除只出项明细
     *
     * @param id
     * @return
     */
    Ret deleteById(Long id);
}
