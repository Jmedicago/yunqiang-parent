package com.vgit.yunqiang.pojo;

public class FinDyQInvProduct {

    private Long yearId;

    private Long quarerlyId;

    private Long stockId;

    private Long skuId;

    private Long productId;

    private String productType;

    private String skuMainPic;

    private String productName;

    private String skuCode;

    private String skuProperties;

    private Integer pack;

    private Double costPrice;

    private Double marketPrice;

    private Integer purchCount;

    private Integer realCount;

    private Double inventoryAmount;

    private Integer state;

    public Long getYearId() {
        return yearId;
    }

    public void setYearId(Long yearId) {
        this.yearId = yearId;
    }

    public Long getQuarerlyId() {
        return quarerlyId;
    }

    public void setQuarerlyId(Long quarerlyId) {
        this.quarerlyId = quarerlyId;
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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getSkuMainPic() {
        return skuMainPic;
    }

    public void setSkuMainPic(String skuMainPic) {
        this.skuMainPic = skuMainPic;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getSkuProperties() {
        return skuProperties;
    }

    public void setSkuProperties(String skuProperties) {
        this.skuProperties = skuProperties;
    }

    public Integer getPack() {
        return pack;
    }

    public void setPack(Integer pack) {
        this.pack = pack;
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

    public Integer getPurchCount() {
        return purchCount;
    }

    public void setPurchCount(Integer purchCount) {
        this.purchCount = purchCount;
    }

    public Integer getRealCount() {
        return realCount;
    }

    public void setRealCount(Integer realCount) {
        this.realCount = realCount;
    }

    public Double getInventoryAmount() {
        return inventoryAmount;
    }

    public void setInventoryAmount(Double inventoryAmount) {
        this.inventoryAmount = inventoryAmount;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "FinDyQInvProduct{" +
                "yearId=" + yearId +
                ", quarerlyId=" + quarerlyId +
                ", stockId=" + stockId +
                ", skuId=" + skuId +
                ", productId=" + productId +
                ", productType='" + productType + '\'' +
                ", skuMainPic='" + skuMainPic + '\'' +
                ", productName='" + productName + '\'' +
                ", skuCode='" + skuCode + '\'' +
                ", skuProperties='" + skuProperties + '\'' +
                ", pack=" + pack +
                ", costPrice=" + costPrice +
                ", marketPrice=" + marketPrice +
                ", purchCount=" + purchCount +
                ", realCount=" + realCount +
                ", inventoryAmount=" + inventoryAmount +
                ", state=" + state +
                '}';
    }

}
