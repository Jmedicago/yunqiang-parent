package com.vgit.yunqiang.mapper;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.pojo.BisProduct;
import com.vgit.yunqiang.pojo.BisProductMedia;
import com.vgit.yunqiang.pojo.BisProperty;
import com.vgit.yunqiang.pojo.BisSku;

import java.util.List;
import java.util.Map;

public interface BisProductMapper extends BaseMapper<BisProduct> {

    /**
     * 通过商品id获取属性集合
     *
     * @param id
     * @return
     */
    List<BisProperty> getProperties(Long id);

    /**
     * 通过商品id获取Sku属性集合
     *
     * @param id
     * @return
     */
    List<BisProperty> getSkuProperties(Long id);

    /**
     * 通过商品ID删除属性集合
     *
     * @param id
     */
    void deleteProperties(Long id);

    /**
     * 批量添加商品属性集
     *
     * @param maps
     */
    void saveProperties(List<Map<String, Long>> maps);
    
    /**
     * 获取商品的SKU列表
     *
     * @param id
     * @return
     */
    List<BisSku> getSkuList(Long id);

    /**
     * 通过商品ID删除其所有媒体资源
     *
     * @param id
     */
    void deleteMedias(Long id);

    /**
     * 批量添加商品的媒体资源列表
     *
     * @param productMediaList
     */
    void saveMedias(List<BisProductMedia> productMediaList);

    /**
     * 通过商品ID获取其媒体列表
     *
     * @param id
     * @return
     */
    List<BisProductMedia> getMedias(Long id);

    /**
     * 根据商品编码查询商品
     * 
     * @param code
     * @return
     */
	BisProduct getProducByCode(String code);
	
	/**
	 * 删除商品根据商品名称
	 * 
	 * @param name
	 */
	void delProductByName(String name);

    /**
     * 获取最后一个商品的ID
     *
     * @return
     */
    Long getLastProductId();
}
