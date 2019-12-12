package com.vgit.yunqiang.service;

import com.vgit.yunqiang.common.service.TreeGridService;
import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.pojo.BisProductType;

public interface BisProductTypeService extends TreeGridService<BisProductType> {

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

    /**
     * 根据类目名称查询类目
     *
     * @param name
     * @param parentId
     * @return
     */
    BisProductType getProductTypeByNameAndParentId(String name, Long parentId);

    String getProductTypeByProductId(Long productId);
}
