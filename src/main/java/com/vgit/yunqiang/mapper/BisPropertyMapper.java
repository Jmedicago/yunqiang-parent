package com.vgit.yunqiang.mapper;

import com.vgit.yunqiang.common.query.base.BaseQuery;
import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.pojo.BisProperty;
import com.vgit.yunqiang.pojo.BisPropertyOption;

import java.util.List;

public interface BisPropertyMapper extends BaseMapper<BisProperty> {

    List<BisProperty> getProperties(BaseQuery query);

    /**
     * 通过商品ID获取属性集合
     *
     * @param productId
     * @return
     */
    List<BisProperty> getProductProperties(Long productId);

    /**
     * 通过属性ID获取选项集合
     *
     * @param id
     * @return
     */
    List<BisPropertyOption> getOptions(Long id);

}
