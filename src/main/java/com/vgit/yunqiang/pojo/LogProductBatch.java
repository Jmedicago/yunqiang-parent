package com.vgit.yunqiang.pojo;

import com.vgit.yunqiang.pojo.base.BasePojo;

public class LogProductBatch extends BasePojo {

    private Long id;

    private String fileName;

    private String resource;

    private Integer state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "LogProductBatch{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", resource='" + resource + '\'' +
                ", state=" + state +
                '}';
    }
}
