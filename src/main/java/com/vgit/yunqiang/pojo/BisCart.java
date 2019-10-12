package com.vgit.yunqiang.pojo;

import com.vgit.yunqiang.pojo.base.BasePojo;

public class BisCart extends BasePojo {

    private Long id;

    private Long userId;

    private Long productId;

    private Long skuId;

    private String skuMainPic;

    private String name;

    private String skuProperties;

    private Integer amount;

    private Byte selected;

    private BisSku sku;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkuProperties() {
        return skuProperties;
    }

    public void setSkuProperties(String skuProperties) {
        this.skuProperties = skuProperties;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Byte getSelected() {
        return selected;
    }

    public void setSelected(Byte selected) {
        this.selected = selected;
    }

    public BisSku getSku() {
        return sku;
    }

    public void setSku(BisSku sku) {
        this.sku = sku;
    }

    @Override
    public String toString() {
        return "BisCart{" +
                "id=" + id +
                ", userId=" + userId +
                ", productId=" + productId +
                ", skuId=" + skuId +
                ", skuMainPic='" + skuMainPic + '\'' +
                ", name='" + name + '\'' +
                ", skuProperties='" + skuProperties + '\'' +
                ", amount=" + amount +
                ", selected=" + selected +
                '}';
    }

}
