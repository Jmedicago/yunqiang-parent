package com.vgit.yunqiang.pojo;

import com.vgit.yunqiang.service.format.ProductTypeFormat;
import com.vgit.yunqiang.service.format.SkuFormat;
import com.vgit.yunqiang.service.format.StockShuntFormat;

/**
 * 订单明细
 */
public class BisOrderDetail {

    private Long id;

    private Long orderId;

    private Long productId;

    private String name;

    private Long skuId;

    private String skuMainPic;

    private String skuProperties;

    private Double price;

    private Integer amount;

    private Double volume;

    private Double totalMoney;

    private Double totalVolume;

    private Integer isComment;

    private String inputUser;

    private Integer realAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getSkuMainPic() {
        return skuMainPic;
    }

    public void setSkuMainPic(String skuMainPic) {
        this.skuMainPic = skuMainPic;
    }

    public String getSkuProperties() {
        return skuProperties;
    }

    public void setSkuProperties(String skuProperties) {
        this.skuProperties = skuProperties;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Double getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(Double totalVolume) {
        this.totalVolume = totalVolume;
    }

    public Integer getIsComment() {
        return isComment;
    }

    public void setIsComment(Integer isComment) {
        this.isComment = isComment;
    }

    public String getInputUser() {
        return inputUser;
    }

    public void setInputUser(String inputUser) {
        this.inputUser = inputUser;
    }

    public Integer getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(Integer realAmount) {
        this.realAmount = realAmount;
    }

    public Integer getAvailableStock() {
        return StockShuntFormat.getSkuStock(skuId, orderId);
    }

    public String getProductType() {
        return ProductTypeFormat.getProductTypeByProductId(productId);
    }

    public BisSku getSku() {
        return SkuFormat.get(skuId);
    }

}
