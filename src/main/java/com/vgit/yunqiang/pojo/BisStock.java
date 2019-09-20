package com.vgit.yunqiang.pojo;

import java.io.Serializable;
import java.util.List;

import com.vgit.yunqiang.pojo.base.BasePojo;

/**
 * 库存
 * 
 * @author Admin
 *
 */
public class BisStock extends BasePojo implements Serializable {
	
	private Long id;
	
	private String name;
	
	private Long parentId;

	private String description;

	private Integer status;
	
	private List<BisStock> children;

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

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<BisStock> getChildren() {
		return children;
	}

	public void setChildren(List<BisStock> children) {
		this.children = children;
	}

}
