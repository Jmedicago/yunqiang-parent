package com.vgit.yunqiang.common.service;

import com.vgit.yunqiang.common.query.base.BaseQuery;

import java.util.List;

public interface BaseMapper<T> {

    void save(T t);

    void savePart(T t);

    void delete(Long id);

    void update(T t);

    void updatePart(T t);

    T get(Long id);

    int queryTotal(BaseQuery query);

    List<T> query(BaseQuery query);

}
