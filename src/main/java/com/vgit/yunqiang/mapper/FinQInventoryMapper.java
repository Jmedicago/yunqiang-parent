package com.vgit.yunqiang.mapper;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.pojo.FinQInventory;
import org.apache.ibatis.annotations.Param;

public interface FinQInventoryMapper extends BaseMapper<FinQInventory> {

    Integer exits(@Param("yearId") Long yearId, @Param("quarterlyId")Long quarterlyId);
}
