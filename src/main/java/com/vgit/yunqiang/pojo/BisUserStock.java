package com.vgit.yunqiang.pojo;

public class BisUserStock {
	
	private Long id;
	
	private Long userId;
	
	private Long stockId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	@Override
	public String toString() {
		return "BisUserStock [id=" + id + ", userId=" + userId + ", stockId=" + stockId + "]";
	}

}
