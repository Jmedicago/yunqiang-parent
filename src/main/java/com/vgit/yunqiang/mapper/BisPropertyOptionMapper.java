package com.vgit.yunqiang.mapper;

import com.vgit.yunqiang.common.query.base.BaseQuery;
import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.pojo.BisPropertyOption;

import java.util.List;

public interface BisPropertyOptionMapper extends BaseMapper<BisPropertyOption> {

    /**
     * 通过查询对象获取属性选项值列表
     *
     * @param query
     * @return
     */
    List<BisPropertyOption> getPropertiesOptions(BaseQuery query);

}
