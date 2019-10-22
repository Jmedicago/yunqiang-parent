package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.consts.ICodes;
import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.impl.BaseServiceImpl;
import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.mapper.FinExpendItemMapper;
import com.vgit.yunqiang.pojo.FinExpendItem;
import com.vgit.yunqiang.service.FinExpendItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinExpendItemServiceImpl extends BaseServiceImpl<FinExpendItem> implements FinExpendItemService {

    @Autowired
    private FinExpendItemMapper mapper;

    @Override
    protected BaseMapper<FinExpendItem> getMapper() {
        return this.mapper;
    }


    @Override
    public FinExpendItem saveOrUpdateExpendItem(FinExpendItem expendItem) {
        if (expendItem.getId() == null) {
            expendItem.setCreateTime(System.currentTimeMillis());
            this.mapper.savePart(expendItem);
        } else {
            expendItem.setUpdateTime(System.currentTimeMillis());
            this.mapper.updatePart(expendItem);
        }
        return expendItem;
    }

    @Override
    public Ret deleteById(Long id) {
        FinExpendItem finExpendItem = this.mapper.get(id);
        if (finExpendItem != null) {
            return Ret.me().setSuccess(false).setCode(ICodes.NOT_AUTHORIZED);
        }
        return Ret.me().setSuccess(true).setCode(ICodes.SUCCESS);
    }

}
