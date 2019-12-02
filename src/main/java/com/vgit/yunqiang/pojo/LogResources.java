package com.vgit.yunqiang.pojo;

import com.vgit.yunqiang.pojo.base.BasePojo;

public class LogResources extends BasePojo {

    private Long id;

    private String oldFileName;

    private String fileName;

    private String resource;

    private String suffix;

    private Integer state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOldFileName() {
        return oldFileName;
    }

    public void setOldFileName(String oldFileName) {
        this.oldFileName = oldFileName;
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

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "LogResources{" +
                "id=" + id +
                ", oldFileName='" + oldFileName + '\'' +
                ", fileName='" + fileName + '\'' +
                ", resource='" + resource + '\'' +
                ", suffix='" + suffix + '\'' +
                ", state=" + state +
                '}';
    }
}
