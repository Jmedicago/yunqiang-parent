package com.vgit.yunqiang.service;

import com.vgit.yunqiang.common.service.TreeGrid;
import com.vgit.yunqiang.model.MendianModel;
import com.vgit.yunqiang.pojo.Mendian;

import java.util.List;

public interface MendianService extends TreeGrid<Mendian> {

    Long ROOT = 0L;

    /**
     * 门店树形表格
     *
     * @param pid
     * @return
     */
    List<MendianModel> treegrid (Long pid);
}
