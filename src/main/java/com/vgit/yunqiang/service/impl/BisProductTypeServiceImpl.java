package com.vgit.yunqiang.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vgit.yunqiang.common.query.ProductTypeQuery;
import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.impl.BaseServiceImpl;
import com.vgit.yunqiang.mapper.BisProductTypeMapper;
import com.vgit.yunqiang.pojo.BisProductType;
import com.vgit.yunqiang.pojo.BisStock;
import com.vgit.yunqiang.service.BisProductTypeService;

@Service
public class BisProductTypeServiceImpl extends BaseServiceImpl<BisProductType> implements BisProductTypeService {

	@Autowired
	private BisProductTypeMapper mapper;

	@Override
	protected BaseMapper<BisProductType> getMapper() {
		// TODO Auto-generated method stub
		return this.mapper;
	}

	@Override
	public List<BisProductType> treegrid(Long root, ProductTypeQuery query) {
		List<BisProductType> models = new ArrayList<BisProductType>();
		// 查询商品类型列表
		List<BisProductType> productTypes = this.mapper.queryTree(root);
		// 遍历商品类型
		for (BisProductType productType : productTypes) {
			BisProductType model = new BisProductType();
			model.setId(productType.getId());
			model.setName(productType.getName());
			model.setParentId(productType.getParentId());
			model.setSort(productType.getSort());
			List<BisProductType> children = this.treegrid(productType.getId(), query);
			model.setChildren(children);
			models.add(model);
		}
		return models;
	}

	@Override
	public BisProductType saveOrUpdateProductType(BisProductType productType) {
		if (productType.getId() == null) {
			productType.setSort(100);
            this.mapper.savePart(productType);
        } else {
            this.mapper.updatePart(productType);
        }
        return productType;
	}
	
	@Override
    public void delete(Long id) {
        if (!this.mapper.isParent(id)) {
            this.mapper.delete(id);
        } else {
            this.mapper.deleteByParentId(id);
            this.mapper.delete(id);
        }
    }

}
