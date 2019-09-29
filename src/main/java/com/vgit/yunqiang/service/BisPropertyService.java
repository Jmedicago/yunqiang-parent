package com.vgit.yunqiang.service;

import com.vgit.yunqiang.common.query.PropertyQuery;
import com.vgit.yunqiang.common.service.BaseService;
import com.vgit.yunqiang.pojo.BisProperty;
import com.vgit.yunqiang.pojo.BisPropertyOption;

import java.util.List;

public interface BisPropertyService extends BaseService<BisProperty> {

    /**
     * 通过查询对象获取属性集合
     *
     * @param query
     * @return
     */
    List<BisProperty> getProperties(PropertyQuery query);

    /**
     * 通过属性ID获取选项集合
     *
     * @param id
     * @return
     */
    List<BisPropertyOption> getOptions(Long id);

    /**
     * 编辑属性
     *
     * @param property
     */
    BisProperty saveOrUpdateProperty(BisProperty property);

}
