package com.vgit.yunqiang.mapper;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.pojo.BisStock;
import org.apache.ibatis.annotations.Param;

public interface BisStockMapper extends BaseMapper<BisStock> {

    /**
     * 根据名字获取仓库信息
     *
     * @param name
     * @return
     */
    BisStock getStockByName(@Param("name") String name);
}
