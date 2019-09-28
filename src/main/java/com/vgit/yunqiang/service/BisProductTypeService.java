package com.vgit.yunqiang.service;

import java.util.List;

import com.vgit.yunqiang.common.query.ProductTypeQuery;
import com.vgit.yunqiang.common.service.TreeGrid;
import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.pojo.BisProductType;

public interface BisProductTypeService extends TreeGrid<BisProductType> {

    Long ROOT = 0L;

    /**
     * 树形数据表格
     *
     * @param root
     * @param query
     * @return
     */
    List<BisProductType> treegrid(Long root, ProductTypeQuery query);

    /**
     * 编辑商品类别
     *
     * @param productType
     * @return
     */
    BisProductType saveOrUpdateProductType(BisProductType productType);

    /**
     * 根据ID删除
     *
     * @param id
     * @return
     */
    Ret deleteById(Long id);

}
