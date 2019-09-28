package com.vgit.yunqiang.common.service.impl;

import com.vgit.yunqiang.common.query.base.BaseQuery;
import com.vgit.yunqiang.common.service.TreeGridService;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public abstract class TreeGridServiceImpl<T> extends BaseServiceImpl<T> implements TreeGridService<T> {

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

    @Override
    public List<T> treegrid(Long root, BaseQuery query) {
        List<T> grid = new ArrayList<>();
        try {
            // 遍历树
            List<T> nodes = this.getMapper().queryTree(root);
            for (T node : nodes) {
                Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
                T entity = entityClass.newInstance();

                // 设置属性值
                Field[] fields = entity.getClass().getDeclaredFields();
                for (Field field : fields) {
                    Field[] nodeFields = node.getClass().getDeclaredFields();
                    for (Field nodeField : nodeFields) {
                        // 如果是叶子节点
                        if ("children".equals(field.getName())) {
                            if ("id".equals(nodeField.getName())) {
                                field.setAccessible(true);
                                nodeField.setAccessible(true);
                                field.set(entity, this.treegrid((Long) nodeField.get(node), query));
                            }
                        } else {
                            if (nodeField.getName().equals(field.getName())) {
                                field.setAccessible(true);
                                nodeField.setAccessible(true);
                                field.set(entity, nodeField.get(node));
                            }
                        }
                    }
                }
                // 返回树形数据集
                grid.add(entity);
            }
            return grid;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return grid;
    }

    @Override
    public void delByRoot(Long root) {
        List<Long> nodes = new ArrayList<Long>();
        this.depth(root, nodes);
        if (!nodes.isEmpty()) {
            // 删除所有子节点
            this.getMapper().delByIds(nodes);
        }
        // 删除当前节点
        this.getMapper().delete(root);
    }

}
