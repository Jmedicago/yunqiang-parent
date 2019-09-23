package com.vgit.yunqiang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.pojo.BisProductType;

public interface BisProductTypeMapper extends BaseMapper<BisProductType> {

	List<BisProductType> queryTree(@Param("parentId") Long parentId);

}
