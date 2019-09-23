package com.vgit.yunqiang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.pojo.BisProductType;

public interface BisProductTypeMapper extends BaseMapper<BisProductType> {

	List<BisProductType> queryTree(@Param("parentId") Long parentId);

	/**
     * 是否是叶子节点
     *
     * @param id
     * @return
     */
    boolean isParent(Long id);

    /**
     * 根据父节点删除
     *
     * @param id
     */
    void deleteByParentId(Long id);

}
