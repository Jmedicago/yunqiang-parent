package com.vgit.yunqiang.common.service.impl;

import com.vgit.yunqiang.common.service.TreeGrid;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public abstract class TreeGridImpl<T> extends BaseServiceImpl<T> implements TreeGrid<T> {

    @Override
    public void depth(Long id, List<Long> ids) {
        try {
            List<? extends T> nodes = this.getMapper().queryTree(id);
            for (T node : nodes) {
                Long nodeId = (Long) node.getClass().getMethod("getId").invoke(node);
                // 查询当前要删除的节点是否是父节点
                if (this.getMapper().isParent(id)) {
                    // 如果是父节点
                    ids.add((Long) node.getClass().getMethod("getId").invoke(node));
                    this.depth(nodeId, ids);
                } else {
                    ids.add(nodeId);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

}
