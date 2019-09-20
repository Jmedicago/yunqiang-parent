package com.vgit.yunqiang.service;

import java.util.List;

import com.vgit.yunqiang.common.service.BaseService;
import com.vgit.yunqiang.pojo.BisStock;

/**
 * 业务 - 库存
 *
 * @author Admin
 */
public interface BisStockService extends BaseService<BisStock> {

    Long ROOT = 0L;

    /**
     * 入库
     */
    void stockIn();

    /**
     * 出库
     */
    void stockOut();

    /**
     * 仓库列表
     *
     * @param parentId
     * @return
     */
    List<BisStock> treegrid(Long parentId);

    /**
     * 编辑仓库信息
     *
     * @param stock
     */
    BisStock saveOrUpdateStock(BisStock stock);

}
