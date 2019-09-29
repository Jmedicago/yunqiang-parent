package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.query.PropertyOptionQuery;
import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.impl.BaseServiceImpl;
import com.vgit.yunqiang.mapper.BisPropertyOptionMapper;
import com.vgit.yunqiang.pojo.BisPropertyOption;
import com.vgit.yunqiang.service.BisPropertyOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BisPropertyOptionServiceImpl extends BaseServiceImpl<BisPropertyOption> implements BisPropertyOptionService {

    @Autowired
    private BisPropertyOptionMapper mapper;

    @Override
    protected BaseMapper<BisPropertyOption> getMapper() {
        return this.mapper;
    }

    @Override
    public List<BisPropertyOption> getPropertiesOptions(PropertyOptionQuery query) {
        return this.mapper.getPropertiesOptions(query);
    }

    @Override
    public BisPropertyOption saveOrUpdatePropertyOption(BisPropertyOption propertyOption) {
        if (propertyOption.getId() != null) {
            propertyOption.setUpdateTime(System.currentTimeMillis());
            this.mapper.updatePart(propertyOption);
        } else {
            propertyOption.setCreateTime(System.currentTimeMillis());
            this.mapper.savePart(propertyOption);
        }
        return propertyOption;
    }

}
