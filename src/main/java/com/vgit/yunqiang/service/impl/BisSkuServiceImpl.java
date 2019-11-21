package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.query.ProductQuery;
import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.impl.BaseServiceImpl;
import com.vgit.yunqiang.common.utils.Page;
import com.vgit.yunqiang.common.utils.StrUtils;
import com.vgit.yunqiang.mapper.BisSkuMapper;
import com.vgit.yunqiang.pojo.BisSku;
import com.vgit.yunqiang.service.BisSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BisSkuServiceImpl extends BaseServiceImpl<BisSku> implements BisSkuService {

    @Autowired
    private BisSkuMapper mapper;

    @Override
    protected BaseMapper<BisSku> getMapper() {
        return this.mapper;
    }

    @Override
    public Page<BisSku> queryPage(ProductQuery query) {
        int total = this.queryTotal(query);
        List<BisSku> list = this.query(query);
        // 格式化
        for (BisSku sku : list) {
            sku.setSkuProperties(StrUtils.convertPropertiesToHtml(sku.getSkuProperties()));
        }
        return new Page<>(list, total, query);
    }

    @Override
    public void recoverStock(Long skuId, Integer amount) {
        this.mapper.recoverStock(skuId, amount);
    }

    @Override
    public void outbound(Long skuId, Integer amount) {
        this.mapper.outbound(skuId,amount);
    }

    @Override
    public void inbound(Long id, Integer amount) {
        this.mapper.changeNumber(id, amount);
    }

}
