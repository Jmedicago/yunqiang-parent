package com.vgit.yunqiang.common.service.impl;

import com.vgit.yunqiang.common.query.base.BaseQuery;
import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.BaseService;
import com.vgit.yunqiang.common.utils.Page;
import com.vgit.yunqiang.pojo.FinStockQuarterly;

import java.util.List;

public abstract class BaseServiceImpl<T> implements BaseService<T> {

    protected abstract BaseMapper<T> getMapper();

    @Override
    public void save(T t) {
        this.getMapper().save(t);
    }

    @Override
    public void savePart(T t) {
        this.getMapper().savePart(t);
    }

    @Override
    public void delete(Long id) {
        this.getMapper().delete(id);
    }

    @Override
    public void update(T t) {
        this.getMapper().update(t);
    }

    @Override
    public void updatePart(T t) {
        this.getMapper().updatePart(t);
    }

    @Override
    public T get(long id) {
        return this.getMapper().get(id);
    }

    @Override
    public int queryTotal(BaseQuery query) {
        return this.getMapper().queryTotal(query);
    }

    @Override
    public List<T> query(BaseQuery query) {
        return this.getMapper().query(query);
    }

    @Override
    public Page<T> queryPage(BaseQuery query) {
        int total = this.queryTotal(query);
        List<T> rows = this.query(query);
        return new Page<>(rows, total, query);
    }

}

