package com.vgit.yunqiang.service;

import com.vgit.yunqiang.common.query.ProductQuery;
import com.vgit.yunqiang.common.service.BaseService;
import com.vgit.yunqiang.common.utils.Page;
import com.vgit.yunqiang.pojo.BisSku;

public interface BisSkuService extends BaseService<BisSku> {

    /**
     * 获取SKU商品列表
     *
     * @param query
     * @return
     */
    Page<BisSku> queryPage(ProductQuery query);

}
