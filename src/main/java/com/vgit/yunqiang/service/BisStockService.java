package com.vgit.yunqiang.service;

import com.vgit.yunqiang.common.service.TreeGridService;
import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.pojo.BisStock;

/**
 * 业务 - 库存
 *
 * @author Admin
 */
public interface BisStockService extends TreeGridService<BisStock> {

    /**
     * 入库
     */
    void stockIn();

    /**
     * 出库
     */
    void stockOut();

    /**
     * 编辑仓库信息
     *
     * @param stock
     */
    BisStock saveOrUpdateStock(BisStock stock);

    /**
     * 根据ID删除仓库信息
     *
     * @param id
     * @return
     */
    Ret deleteById(Long id);

    /**
     *  根据name获取仓库信息
     *
     * @param name
     */
    BisStock getStockByName(String name);
}
