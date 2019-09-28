package com.vgit.yunqiang.common.service;

import java.util.List;

public interface TreeGrid<T> extends BaseService<T> {

    /**
     * 获取树深度ID集合
     *
     */
    void depth(Long id, List<Long> ids);

}
