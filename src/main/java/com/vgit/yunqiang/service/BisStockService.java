package com.vgit.yunqiang.service;

import com.vgit.yunqiang.common.service.TreeGrid;
import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.pojo.BisStock;

import java.util.List;

/**
 * 业务 - 库存
 *
 * @author Admin
 */
public interface BisStockService extends TreeGrid<BisStock> {

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

    /**
     * 根据ID删除仓库信息
     *
     * @param id
     * @return
     */
    Ret deleteById(Long id);

}
