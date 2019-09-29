package com.vgit.yunqiang.pojo;

import com.vgit.yunqiang.pojo.base.BasePojo;

/**
 * 属性选项
 */
public class BisPropertyOption extends BasePojo {

    private Long id;

    private Long property;

    private String optionValue;

    private String optionIcon;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProperty() {
        return property;
    }

    public void setProperty(Long property) {
        this.property = property;
    }

    public String getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
    }

    public String getOptionIcon() {
        return optionIcon;
    }

    public void setOptionIcon(String optionIcon) {
        this.optionIcon = optionIcon;
    }

    @Override
    public String toString() {
        return "BisPropertyOption{" +
                "id=" + id +
                ", property=" + property +
                ", optionValue='" + optionValue + '\'' +
                ", optionIcon='" + optionIcon + '\'' +
                '}';
    }

}
