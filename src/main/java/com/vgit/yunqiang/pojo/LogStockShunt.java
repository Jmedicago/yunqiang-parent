package com.vgit.yunqiang.pojo;

import com.vgit.yunqiang.service.format.ProductTypeFormat;
import com.vgit.yunqiang.service.format.SkuFormat;
import com.vgit.yunqiang.service.format.StockFormat;

public class LogStockShunt {

    private Long id;

    private Long date;

    private Long stockId;

    private Long skuId;

    private Integer amount;

    private Byte state;

    private Long inputUser;

    private BisSku sku;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Long getInputUser() {
        return inputUser;
    }

    public void setInputUser(Long inputUser) {
        this.inputUser = inputUser;
    }

    public BisSku getSku() {
        return sku;
    }

    public void setSku(BisSku sku) {
        this.sku = sku;
    }

    public String getStockFormatter() {
        return StockFormat.getStockName(stockId);
    }

    public String getProductTypeFormatter() {
        Long productTypeId = SkuFormat.getProductType(skuId);
        if (productTypeId != null) {
            return ProductTypeFormat.getProductTypePath(productTypeId);
        } else {
            return null;
        }

    }

}
