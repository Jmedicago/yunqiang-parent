package com.vgit.yunqiang.pojo;

import com.vgit.yunqiang.pojo.base.BasePojo;

import java.math.BigDecimal;

/**
 * 支出项
 */
public class ExpenditureItem extends BasePojo {

    private Long id;

    private Character categoryEnum;

    private BigDecimal amount;

    private Integer state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Character getCategoryEnum() {
        return categoryEnum;
    }

    public void setCategoryEnum(Character categoryEnum) {
        this.categoryEnum = categoryEnum;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "ExpenditureItem{" +
                "id=" + id +
                ", categoryEnum=" + categoryEnum +
                ", amount=" + amount +
                ", state=" + state +
                '}';
    }

}
