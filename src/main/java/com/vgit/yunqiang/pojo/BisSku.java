package com.vgit.yunqiang.pojo;

import com.vgit.yunqiang.pojo.base.BasePojo;

import java.util.ArrayList;
import java.util.List;

public class BisSku extends BasePojo {

    private Long id;

    private Long productId;

    // SKU编码
    private String skuCode;

    // SKU规格名称
    private String skuName;

    // 包装形态 （双/件）
    private Integer pack;

    // 体积 （m^3）
    private Double volume;

    // 成本
    private Double costPrice;

    // 建议批发价（仅供参考）
    private Double marketPrice;

    // 利润 （%）
    private Double profit;

    // 供应商
    private String supplier;

    // 货柜编号
    private String container;

    // 可用库存
    private Integer availableStock;

    // 锁定库存
    private Integer frozenStock;

    // 入库时间
    private Long pushStockTime;

    // SKU属性摘要
    private String skuProperties;

    // 预览图
    private String skuMainPic;

    // 关键字
    private String keyword;

    private Integer northStock;

    private Integer southStock;

    /**
     * Sku的属性值列表
     */
    private List<BisSkuProperty> skuPropertyList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public Integer getPack() {
        return pack;
    }

    public void setPack(Integer pack) {
        this.pack = pack;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    public Double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    public Integer getAvailableStock() {
        return availableStock;
    }

    public void setAvailableStock(Integer availableStock) {
        this.availableStock = availableStock;
    }

    public Integer getFrozenStock() {
        return frozenStock;
    }

    public void setFrozenStock(Integer frozenStock) {
        this.frozenStock = frozenStock;
    }

    public Long getPushStockTime() {
        return pushStockTime;
    }

    public void setPushStockTime(Long pushStockTime) {
        this.pushStockTime = pushStockTime;
    }

    public String getSkuProperties() {
        return skuProperties;
    }

    public void setSkuProperties(String skuProperties) {
        this.skuProperties = skuProperties;
    }

    public String getSkuMainPic() {
        return skuMainPic;
    }

    public void setSkuMainPic(String skuMainPic) {
        this.skuMainPic = skuMainPic;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<BisSkuProperty> getSkuPropertyList() {
        return skuPropertyList;
    }

    public void setSkuPropertyList(List<BisSkuProperty> skuPropertyList) {
        this.skuPropertyList = skuPropertyList;
    }

    public Integer getNorthStock() {
        return northStock;
    }

    public void setNorthStock(Integer northStock) {
        this.northStock = northStock;
    }

    public Integer getSouthStock() {
        return southStock;
    }

    public void setSouthStock(Integer southStock) {
        this.southStock = southStock;
    }

    @Override
    public String toString() {
        return "BisSku{" +
                "id=" + id +
                ", productId=" + productId +
                ", skuCode='" + skuCode + '\'' +
                ", skuName='" + skuName + '\'' +
                ", pack=" + pack +
                ", volume=" + volume +
                ", costPrice=" + costPrice +
                ", marketPrice=" + marketPrice +
                ", profit=" + profit +
                ", supplier='" + supplier + '\'' +
                ", container='" + container + '\'' +
                ", availableStock=" + availableStock +
                ", frozenStock=" + frozenStock +
                ", pushStockTime=" + pushStockTime +
                ", skuProperties='" + skuProperties + '\'' +
                ", skuMainPic='" + skuMainPic + '\'' +
                '}';
    }

}
