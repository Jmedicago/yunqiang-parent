package com.vgit.yunqiang.pojo;

import java.io.Serializable;
import java.util.List;

public class BisProductType implements Serializable {

	private Long id;
	
	private String name;
	
	private Long parentId;
	
	private Integer sort;

	private String text;
	
	private List<BisProductType> children;

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

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getText() {
		return name;
	}

	public List<BisProductType> getChildren() {
		return children;
	}

	public void setChildren(List<BisProductType> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "BisProductType{" +
				"id=" + id +
				", name='" + name + '\'' +
				", parentId=" + parentId +
				", sort=" + sort +
				", children=" + children +
				'}';
	}

}
