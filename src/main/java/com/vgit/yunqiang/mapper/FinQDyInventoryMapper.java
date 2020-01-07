package com.vgit.yunqiang.mapper;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.pojo.FinQDyInventory;
import org.apache.ibatis.annotations.Param;

public interface FinQDyInventoryMapper extends BaseMapper<FinQDyInventory> {

    /**
     * 店员季度盘点报表
     *
     * @param year
     * @param quarterly
     * @param stockId
     * @return
     */
    FinQDyInventory getQDyInventory(@Param("year") String year, @Param("quarterly") String quarterly, @Param("stockId") Long stockId);

}
