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
	
	private List<BisStock> childern;

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

	public List<BisStock> getChildern() {
		return childern;
	}

	public void setChildern(List<BisStock> childern) {
		this.childern = childern;
	}

}
