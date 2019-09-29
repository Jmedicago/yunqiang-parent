package com.vgit.yunqiang.service;

import com.vgit.yunqiang.common.query.PropertyOptionQuery;
import com.vgit.yunqiang.common.service.BaseService;
import com.vgit.yunqiang.pojo.BisPropertyOption;

import java.util.List;

public interface BisPropertyOptionService extends BaseService<BisPropertyOption> {

    /**
     * 通过查询对象查询选项值列表
     *
     * @param query
     * @return
     */
    List<BisPropertyOption> getPropertiesOptions(PropertyOptionQuery query);

    /**
     * 保存属性选项
     *
     * @param propertyOption
     * @return
     */
    BisPropertyOption saveOrUpdatePropertyOption(BisPropertyOption propertyOption);

    /**
     * 删除指定ID的属性选项
     *
     * @param ids
     */
    void deletePropertyOption(String ids);
}
