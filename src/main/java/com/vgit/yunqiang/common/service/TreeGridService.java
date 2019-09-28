package com.vgit.yunqiang.common.service;

import com.vgit.yunqiang.common.query.base.BaseQuery;
import com.vgit.yunqiang.common.utils.Ret;

import java.util.List;

/**
 * 树形网格
 *
 * @param <T>
 */
public interface TreeGridService<T> extends BaseService<T> {

    Long ROOT = 0L;

    /**
     * 获取树深度ID集合
     */
    void depth(Long id, List<Long> ids);

    /**
     * 树形网格数据集合
     *
     * @param root
     * @param query
     * @return
     */
     List<T> treegrid(Long root, BaseQuery query);

    /**
     * 删除该节点的所有子节点
     *
     * @return
     */
     void delByRoot(Long root);

}
