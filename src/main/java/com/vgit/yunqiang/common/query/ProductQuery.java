package com.vgit.yunqiang.common.query;

import com.vgit.yunqiang.common.query.base.BaseQuery;

public class ProductQuery extends BaseQuery {

    private String code;

    private String name;

    private Long productType;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

}
