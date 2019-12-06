package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.consts.msg.BisProductMsgConsts;
import com.vgit.yunqiang.common.consts.msg.SysUserMsgConsts;
import com.vgit.yunqiang.common.exception.BisException;
import com.vgit.yunqiang.common.query.ProductQuery;
import com.vgit.yunqiang.common.query.base.BaseQuery;
import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.impl.BaseServiceImpl;
import com.vgit.yunqiang.common.utils.Page;
import com.vgit.yunqiang.common.utils.StrUtils;
import com.vgit.yunqiang.mapper.BisSkuMapper;
import com.vgit.yunqiang.mapper.BisStockMapper;
import com.vgit.yunqiang.model.ProductModel;
import com.vgit.yunqiang.pojo.*;
import com.vgit.yunqiang.service.BisProductService;
import com.vgit.yunqiang.service.BisProductTypeService;
import com.vgit.yunqiang.service.BisSkuService;
import com.vgit.yunqiang.service.BisStockService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BisSkuServiceImpl extends BaseServiceImpl<BisSku> implements BisSkuService {

    private static final String NORTH_STOCK = "1062";

    private static final String SOUTH_STOCK = "1050";

    @Autowired
    private BisSkuMapper mapper;

    @Autowired
    private BisStockService bisStockService;

    @Autowired
    private BisProductTypeService bisProductTypeService;

    @Autowired
    private BisProductService bisProductService;

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
            if (StringUtils.isNotBlank(sku.getSkuProperties())) {
                sku.setSkuProperties(StrUtils.convertPropertiesToHtml(sku.getSkuProperties()));
            }
        }
        return new Page<>(list, total, query);
    }

    @Override
    public void recoverStock(Long skuId, Integer amount) {
        this.mapper.recoverStock(skuId, amount);
    }

    @Override
    public void outbound(Long skuId, Integer amount) {
        this.mapper.outbound(skuId, amount);
    }

    @Override
    public void inbound(Long id, Integer amount) {
        this.mapper.changeNumber(id, amount);
    }

    @Override
    public void checkStock(String stockIds, BisSku sku) throws BisException {
        BisStock stock = this.bisStockService.get(Long.valueOf(stockIds));
        if (stock.getPath().contains(NORTH_STOCK)) { // 是否是北部仓库
            // 检查北仓库存
            if (sku.getFrozenStock() == 0) {
                throw new BisException().setCode(BisProductMsgConsts.IN_A_SHORT_INVENTORY).setInfo(sku.getSkuCode() + " " + sku.getSkuName());
            }
        } else {
            if (sku.getAvailableStock() == 0) { // 库存不足
                throw new BisException().setCode(BisProductMsgConsts.IN_A_SHORT_INVENTORY).setInfo(sku.getSkuCode() + " " + sku.getSkuName());
            }
        }
    }

    @Override
    public void checkStock(String stockIds, BisSku sku, Integer amount) throws BisException {
        BisStock stock = this.bisStockService.get(Long.valueOf(stockIds));
        if (stock.getPath().contains(NORTH_STOCK)) { // 是否是北部仓库
            // 检查北仓库存  amount > availableStock
            if (amount > sku.getFrozenStock()) {
                throw new BisException().setCode(SysUserMsgConsts.ORDER_STOCK_NOT_ENOUGH).setInfo(sku.getSkuCode() + " " + sku.getSkuName());
            }
        } else {
            if (amount > sku.getAvailableStock()) { // 库存不足
                throw new BisException().setCode(SysUserMsgConsts.ORDER_STOCK_NOT_ENOUGH).setInfo(sku.getSkuCode() + " " + sku.getSkuName());
            }
        }
    }

    @Override
    public void reduceStock(String stockIds, BisSku sku, Integer amount) throws BisException {
        this.checkStock(stockIds, sku, amount);

        BisStock stock = this.bisStockService.get(Long.valueOf(stockIds));
        if (stock.getPath().contains(NORTH_STOCK)) {
            sku.setFrozenStock(sku.getFrozenStock() - amount);
        } else {
            sku.setAvailableStock(sku.getAvailableStock() - amount);
            // sku.setFrozenStock(sku.getFrozenStock() + amount); 已卖出库存
        }
        this.mapper.updatePart(sku);
    }

    @Override
    public Page<ProductModel> es(BaseQuery query) {
        int total = this.mapper.esTotal(query);
        List<ProductModel> list = this.mapper.es(query);
        return new Page<>(list, total, query);
    }

    @Override
    public void delByProduct(Long productId) {
        this.mapper.delByProductIds(productId);
    }

    @Override
    public Long getProductType(Long skuId) {
        BisSku sku = this.mapper.get(skuId);
        if (sku != null) {
            BisProduct product = this.bisProductService.get(sku.getProductId());
            return product.getProductType();
        }
        return null;
    }

}
