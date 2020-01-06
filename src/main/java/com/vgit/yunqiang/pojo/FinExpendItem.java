package com.vgit.yunqiang.pojo;

import com.vgit.yunqiang.pojo.base.BasePojo;

import java.util.List;

/**
 * 支出项
 */
public class FinExpendItem extends BasePojo {

    private Long id;

    private String category;

    private String article;

    private Integer state;

    private List<FinMExpends> mExpendsList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public List<FinMExpends> getmExpendsList() {
        return mExpendsList;
    }

    public void setmExpendsList(List<FinMExpends> mExpendsList) {
        this.mExpendsList = mExpendsList;
    }

    @Override
    public String toString() {
        return "{" +
                "  \"id\":" + id +
                ", \"category\":\"" + category + "\"" +
                ", \"article\":\"" + article + "\"" +
                ", \"state\":" + state +
                '}';
    }

}
