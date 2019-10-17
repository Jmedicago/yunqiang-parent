package com.vgit.yunqiang.mapper;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.pojo.BisProductType;
import org.apache.ibatis.annotations.Param;

public interface BisProductTypeMapper extends BaseMapper<BisProductType> {

    /**
     * 根据类目名称查询类目
     *
     * @param name
     * @param parentId
     * @return
     */
    BisProductType getProductTypeByNameAndParentId(@Param("name") String name, @Param("parentId") Long parentId);
}
