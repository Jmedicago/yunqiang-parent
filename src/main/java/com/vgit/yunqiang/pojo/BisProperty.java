package com.vgit.yunqiang.pojo;

import com.vgit.yunqiang.pojo.base.BasePojo;

/**
 * 属性
 *
 */
public class BisProperty extends BasePojo {

    private Long id;

    private String name;

    private Long productType;

    private Integer type;

    private Integer inputMode;

    private Integer inputType;

    private String validatePattern;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getInputMode() {
        return inputMode;
    }

    public void setInputMode(Integer inputMode) {
        this.inputMode = inputMode;
    }

    public Integer getInputType() {
        return inputType;
    }

    public void setInputType(Integer inputType) {
        this.inputType = inputType;
    }

    public String getValidatePattern() {
        return validatePattern;
    }

    public void setValidatePattern(String validatePattern) {
        this.validatePattern = validatePattern;
    }

    @Override
    public String toString() {
        return "BisProperty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", productType=" + productType +
                ", type=" + type +
                ", inputMode=" + inputMode +
                ", inputType=" + inputType +
                ", validatePattern='" + validatePattern + '\'' +
                '}';
    }

}
