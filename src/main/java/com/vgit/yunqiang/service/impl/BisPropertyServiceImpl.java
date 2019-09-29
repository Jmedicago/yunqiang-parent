package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.query.PropertyQuery;
import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.impl.BaseServiceImpl;
import com.vgit.yunqiang.mapper.BisPropertyMapper;
import com.vgit.yunqiang.pojo.BisProperty;
import com.vgit.yunqiang.pojo.BisPropertyOption;
import com.vgit.yunqiang.service.BisPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BisPropertyServiceImpl extends BaseServiceImpl<BisProperty> implements BisPropertyService {

    @Autowired
    private BisPropertyMapper mapper;

    @Override
    protected BaseMapper<BisProperty> getMapper() {
        return this.mapper;
    }

    @Override
    public List<BisProperty> getProperties(PropertyQuery query) {
        return this.mapper.getProperties(query);
    }

    @Override
    public List<BisPropertyOption> getOptions(Long id) {
        return this.mapper.getOptions(id);
    }

    @Override
    public BisProperty saveOrUpdateProperty(BisProperty property) {
        if (property.getId() != null) {
            property.setUpdateTime(System.currentTimeMillis());
            this.mapper.updatePart(property);
        } else {
            property.setCreateTime(System.currentTimeMillis());
            this.mapper.savePart(property);
        }
        return property;
    }

}
