package com.vgit.yunqiang.pojo;

import com.vgit.yunqiang.pojo.base.BasePojo;

public class BisTradeStock extends BasePojo {
	
	private Long id;

    private String fileName;

    private String beforeResource;

    private String afterResource;

    private Long confirmTime;

    private Integer status;

    private Integer type;

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
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getBeforeResource() {
        return beforeResource;
    }

    public void setBeforeResource(String beforeResource) {
        this.beforeResource = beforeResource == null ? null : beforeResource.trim();
    }

    public String getAfterResource() {
        return afterResource;
    }

    public void setAfterResource(String afterResource) {
        this.afterResource = afterResource == null ? null : afterResource.trim();
    }

    public Long getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Long confirmTime) {
        this.confirmTime = confirmTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
	public String toString() {
		return "BisTradeStock [id=" + id + ", fileName=" + fileName + ", beforeResource=" + beforeResource
				+ ", afterResource=" + afterResource + ", confirmTime=" + confirmTime + ", status=" + status + "]";
	}

}
