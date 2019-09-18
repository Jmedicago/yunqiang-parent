package com.vgit.yunqiang.service;

import com.vgit.yunqiang.common.service.BaseService;
import com.vgit.yunqiang.model.MendianModel;
import com.vgit.yunqiang.pojo.Mendian;

import java.util.List;

public interface MendianService extends BaseService<Mendian> {

    Long ROOT = 0L;

    /**
     * 门店树形表格
     *
     * @param pid
     * @return
     */
    List<MendianModel> treegrid (Long pid);
}
