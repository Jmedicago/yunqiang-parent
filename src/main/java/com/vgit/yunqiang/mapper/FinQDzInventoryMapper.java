package com.vgit.yunqiang.mapper;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.pojo.FinQDzInventory;
import org.apache.ibatis.annotations.Param;

public interface FinQDzInventoryMapper extends BaseMapper<FinQDzInventory> {

    /**
     * 店长季度盘点
     *
     * @param year
     * @param quarterly
     * @param stockId
     * @return
     */
    FinQDzInventory getQDzInventory(@Param("year") String year, @Param("quarterly") String quarterly, @Param("stockId") Long stockId);
}
