package com.vgit.yunqiang.pojo;

import java.io.Serializable;
import java.util.List;

import com.vgit.yunqiang.pojo.base.BasePojo;
import com.vgit.yunqiang.service.BisProductService;
import com.vgit.yunqiang.service.format.ProductMediaFormat;
import com.vgit.yunqiang.service.format.ProductTypeFormat;
import com.vgit.yunqiang.service.format.StockFormat;
import org.springframework.beans.factory.annotation.Autowired;

public class BisProduct extends BasePojo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	// 权属
	private Long stock;

	// 类目
	private Long productType;

	// 货柜编号
	private String code;

	private Integer state;

	private Integer saleCount;

	private String viewProperties;

    /**
     * 商品属性集
     */
    private Long[] properties;

	/**
	 * 商品媒体列表
	 */
	private List<BisProductMedia> productMediaList;

	private String productMedia;

	private String typeName;

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

	public Long getStock() {
		return stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}

	public Long getProductType() {
		return productType;
	}

	public void setProductType(Long productType) {
		this.productType = productType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getSaleCount() {
		return saleCount;
	}

	public void setSaleCount(Integer saleCount) {
		this.saleCount = saleCount;
	}

	public String getViewProperties() {
		return viewProperties;
	}

	public void setViewProperties(String viewProperties) {
		this.viewProperties = viewProperties;
	}

    public Long[] getProperties() {
        return properties;
    }

    public void setProperties(Long[] properties) {
        this.properties = properties;
    }

	public List<BisProductMedia> getProductMediaList() {
		return productMediaList;
	}

	public void setProductMediaList(List<BisProductMedia> productMediaList) {
		this.productMediaList = productMediaList;
	}

	public String getTypeName() {
		return ProductTypeFormat.getProductTypePath(productType);
	}

	public String getStockName() {
		return StockFormat.getStockName(stock);
	}

	public String getProductMedia() {
		return ProductMediaFormat.getProductImage(id);
	}

	@Override
	public String toString() {
		return "BisProduct{" +
				"id=" + id +
				", name='" + name + '\'' +
				", stock=" + stock +
				", productType=" + productType +
				", code='" + code + '\'' +
				", state=" + state +
				", saleCount=" + saleCount +
				", viewProperties='" + viewProperties + '\'' +
				'}';
	}

}
