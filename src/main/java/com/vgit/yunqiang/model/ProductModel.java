package com.vgit.yunqiang.model;

import com.vgit.yunqiang.pojo.BisStockShunt;
import com.vgit.yunqiang.service.format.ProductTypeFormat;
import com.vgit.yunqiang.service.format.PropertyFormat;
import com.vgit.yunqiang.service.format.StockShuntFormat;

import java.util.List;

public class ProductModel {

    private Long id;

    private String name;

    private Long productType;

    private String path;

    private String code;

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

    private String remark;

    // 总仓库存
    private Integer allStock;

    // 库存
    private List<BisStockShunt> stockShunt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getProductType() {
        return productType;
    }

    public void setProductType(Long productType) {
        this.productType = productType;
    }

    public String getPath() {
        return ProductTypeFormat.getProductTypePath(productType);
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
        return PropertyFormat.formatterProperties(skuProperties);
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<BisStockShunt> getStockShunt() {
        return StockShuntFormat.getShunts(id);
    }

    public Integer getAllStock() {
        return allStock;
    }

    public void setAllStock(Integer allStock) {
        this.allStock = allStock;
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", productType=" + productType +
                ", path='" + path + '\'' +
                ", code='" + code + '\'' +
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
                ", remark='" + remark + '\'' +
                ", allStock=" + allStock +
                ", stockShunt=" + stockShunt +
                '}';
    }
}
