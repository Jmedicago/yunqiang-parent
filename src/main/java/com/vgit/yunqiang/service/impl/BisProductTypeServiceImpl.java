package com.vgit.yunqiang.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.vgit.yunqiang.common.consts.ICodes;
import com.vgit.yunqiang.common.service.TreeGridService;
import com.vgit.yunqiang.common.service.impl.TreeGridServiceImpl;
import com.vgit.yunqiang.common.utils.Ret;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.mapper.BisProductTypeMapper;
import com.vgit.yunqiang.pojo.BisProductType;
import com.vgit.yunqiang.service.BisProductTypeService;

@Service
public class BisProductTypeServiceImpl extends TreeGridServiceImpl<BisProductType> implements BisProductTypeService {

	@Autowired
	private BisProductTypeMapper mapper;

	@Override
	protected BaseMapper<BisProductType> getMapper() {
		// TODO Auto-generated method stub
		return this.mapper;
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
	public Ret deleteById(Long id) {
		BisProductType productType = this.mapper.get(id);
		if (productType != null && productType.getParentId() == TreeGridService.ROOT) {
			return Ret.me().setSuccess(false).setCode(ICodes.NOT_AUTHORIZED);
		}
		this.delByRoot(id); // 删除该节点及所有子节点
		return Ret.me().setSuccess(true).setCode(ICodes.SUCCESS);
	}

}
