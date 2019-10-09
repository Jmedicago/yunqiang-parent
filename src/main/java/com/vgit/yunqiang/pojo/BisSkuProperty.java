package com.vgit.yunqiang.pojo;

import com.vgit.yunqiang.pojo.base.BasePojo;

public class BisSkuProperty extends BasePojo {

    private Long id;

    // SKU ID
    private Long skuId;

    // 属性ID
    private Long propId;

    // 属性名
    private String propName;

    // 选项ID
    private Long optionId;

    // 值
    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Long getPropId() {
        return propId;
    }

    public void setPropId(Long propId) {
        this.propId = propId;
    }

    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName;
    }

    public Long getOptionId() {
        return optionId;
    }

    public void setOptionId(Long optionId) {
        this.optionId = optionId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "BisSkuProperty{" +
                "id=" + id +
                ", skuId=" + skuId +
                ", propId=" + propId +
                ", propName='" + propName + '\'' +
                ", optionId=" + optionId +
                ", value='" + value + '\'' +
                '}';
    }

}
