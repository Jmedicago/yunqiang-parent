package com.vgit.yunqiang.mapper;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.pojo.BisStock;
import com.vgit.yunqiang.pojo.FinArrears;
import com.vgit.yunqiang.pojo.FinYear;

import java.util.List;

public interface FinArrearsMapper extends BaseMapper<FinArrears> {

    /**
     * 各区域连锁店客商欠款
     *
     * @param year
     * @return
     */
    //FinYear queryArrearsList(String year);

    /**
     * 各区域连锁店客商欠款
     *
     * @param year
     * @return
     */
    List<BisStock> queryArrearsList(String year);

}
