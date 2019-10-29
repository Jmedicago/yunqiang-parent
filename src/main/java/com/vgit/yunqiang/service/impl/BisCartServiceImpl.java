package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.consts.bis.BooleanConsts;
import com.vgit.yunqiang.common.consts.bis.CartStatusConsts;
import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.impl.BaseServiceImpl;
import com.vgit.yunqiang.common.utils.StrUtils;
import com.vgit.yunqiang.mapper.BisCartMapper;
import com.vgit.yunqiang.pojo.BisCart;
import com.vgit.yunqiang.pojo.BisProduct;
import com.vgit.yunqiang.pojo.BisProductMedia;
import com.vgit.yunqiang.pojo.BisSku;
import com.vgit.yunqiang.service.BisCartService;
import com.vgit.yunqiang.service.BisProductService;
import com.vgit.yunqiang.service.QuartzService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BisCartServiceImpl extends BaseServiceImpl<BisCart> implements BisCartService {

    @Autowired
    private BisCartMapper mapper;

    @Autowired
    private BisProductService bisProductService;
    
    @Autowired
    private QuartzService quartzService;

    @Override
    protected BaseMapper<BisCart> getMapper() {
        return this.mapper;
    }

    @Override
    public void add(Long userId, Long skuId, Integer number) {
        // 查询购物车是否存在
        BisCart existBisCart = this.mapper.getByUserSku(userId, skuId);
        // 存在则增加数量
        if (null != existBisCart) {
            existBisCart.setAmount(existBisCart.getAmount() + number);
            existBisCart.setSelected(BooleanConsts.YES);
            this.mapper.updatePart(existBisCart);
            return;
        }

        // 获取数据库最新的SKU信息
        BisSku sku = bisProductService.getSku(skuId);
        // 获取商品的信息
        BisProduct product = this.bisProductService.get(sku.getProductId());
        // 获取展示主图
        String mainPic = sku.getSkuMainPic();
        if (StringUtils.isBlank(mainPic)) {
            List<BisProductMedia> productMedias = this.bisProductService.getProductMedias(product.getId());
            if (!productMedias.isEmpty()) {
                mainPic = productMedias.get(0).getResource();
            }
        }

        // 创建购物车条目并保存到数据库
        BisCart cart = new BisCart();
        cart.setSkuMainPic(mainPic);
        cart.setSkuProperties(sku.getSkuProperties());
        cart.setProductId(product.getId());
        cart.setName(product.getName());
        cart.setAmount(number);
        cart.setCreateTime(System.currentTimeMillis());
        cart.setSelected(BooleanConsts.YES);
        cart.setSkuId(skuId);
        cart.setUserId(userId);
        cart.setStatus(CartStatusConsts.UN_LOCKED);
        this.mapper.save(cart);
    }

    @Override
    public void add(Long userId, Long... skuIds) {
        if (skuIds == null || skuIds.length == 0) {
            return;
        }
        for (Long skuId : skuIds) {
            this.add(userId, skuId, 1);
        }
    }

    @Override
    public void del(Long userId, Long[] cartIdArr) {
        if (null != cartIdArr && cartIdArr.length > 0) {
            this.mapper.delCartByUser(userId, cartIdArr);
        }
    }

    @Override
    public BisSku changeNumber(Long userId, Long cartId, Integer number) {
        this.mapper.changeNumber(userId, cartId, number);
        BisCart cart = this.mapper.get(cartId);
        return this.bisProductService.getSku(cart.getSkuId());
    }

    @Override
    public void updateSelectCart(Long userId, Long[] cartIdArr) {
        if (null != cartIdArr && cartIdArr.length > 0) {
            this.mapper.updateSelectCart(userId, cartIdArr);
        } else {
            this.mapper.cancelSelectAll(userId);
        }
    }

    @Override
    public void quick(Long userId, Long skuId, Integer number) {
        // 取消所有选中的购物车
        this.mapper.cancelSelectAll(userId);
        // 查询是否有相同SKU的购物条目
        BisCart existCart = this.mapper.getByUserSku(userId, skuId);
        if (null == existCart) {
            // 不存在添加一项
            this.add(userId, skuId, number);
        } else {
            // 已存在，更新购物车数量为申购的数量，选中该条项目
            existCart.setAmount(number);
            existCart.setSelected(BooleanConsts.YES);
            this.mapper.updatePart(existCart);
        }
    }

    @Override
    public Map<String, Object> calculate(Long userId) {
        return this.statistic(this.getCarts(userId));
    }

    @Override
    public List<BisCart> getCarts(Long userId) {
        List<BisCart> carts = this.mapper.getCarts(userId);
        for (BisCart cart : carts) {
            BisSku sku = this.bisProductService.getSku(cart.getSkuId());
            cart.setSku(sku);
        }
        return carts;
    }

    @Override
    public Map<String, Object> statistic(List<BisCart> cartList) {
        HashMap<String, Object> result = new HashMap<>();
        int goodsNumber = 0;
        double goodsTotalVolume = 0;
        double goodsTotalPrice = 0;
        int selectedGoodsNumber = 0;
        double selectedGoodsTotalVolume = 0;
        double selectedGoodsTotalPrice = 0;
        if (null != cartList) {
            for (BisCart bisCart : cartList) {
                goodsNumber += bisCart.getAmount();
                goodsTotalVolume += bisCart.getAmount() * bisCart.getSku().getVolume();
                goodsTotalPrice += bisCart.getAmount() * bisCart.getSku().getCostPrice();
                if (bisCart.getSelected() == BooleanConsts.YES) {
                    selectedGoodsNumber += bisCart.getAmount();
                    selectedGoodsTotalVolume += bisCart.getAmount() * bisCart.getSku().getVolume();
                    selectedGoodsTotalPrice += bisCart.getAmount() * bisCart.getSku().getCostPrice();
                }
            }
        }
        result.put("goodsNumber", goodsNumber);
        result.put("goodsTotalVolume", goodsTotalVolume);
        result.put("goodsTotalPrice", goodsTotalPrice);
        result.put("selectedGoodsNumber", selectedGoodsNumber);
        result.put("selectedGoodsTotalVolume", selectedGoodsTotalVolume);
        result.put("selectedGoodsTotalPrice", selectedGoodsTotalPrice);
        return result;
    }

    @Override
    public Map<String, Object> info(Long userId) {
        Map<String, Object> result = new HashMap<String, Object>();

        int total = this.mapper.getCartsTotal(userId);
        result.put("total", total);

        List<BisCart> cartList = this.getCarts(userId);
        for (BisCart bisCart : cartList) {
            String skuProperties = bisCart.getSkuProperties();
            bisCart.setSkuProperties(StrUtils.convertPropertiesToHtml(skuProperties));
        }
        result.put("rows", cartList);

        result.put("footer", this.statistic(cartList));
        return result;
    }

    @Override
    public Map<String, Object> selectedInfo(Long userId) {
        List<BisCart> selectedList = new ArrayList<>();
        List<BisCart> cartList = this.getCarts(userId);
        for (BisCart bisCart : cartList) {
            if (bisCart.getSelected() == BooleanConsts.YES) {
                String skuProperties = bisCart.getSkuProperties();
                bisCart.setSkuProperties(StrUtils.convertPropertiesToHtml(skuProperties));
                selectedList.add(bisCart);
            }
        }

        Map<String, Object> infoMap = this.statistic(cartList);
        infoMap.put("data", selectedList);
        return infoMap;
    }

    @Override
    public void clearQuick(Long userId) {
        this.mapper.clearQuick(userId);
    }

    @Override
    public void changeSelected(Long userId, Long cartId, Integer selected) {
        this.mapper.changeSelected(userId, cartId, selected);
    }

}
