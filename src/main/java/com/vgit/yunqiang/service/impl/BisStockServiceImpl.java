/**
 * 
 */
package com.vgit.yunqiang.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vgit.yunqiang.common.query.StockQuery;
import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.impl.BaseServiceImpl;
import com.vgit.yunqiang.common.utils.Page;
import com.vgit.yunqiang.mapper.BisStockMapper;
import com.vgit.yunqiang.pojo.BisStock;
import com.vgit.yunqiang.service.BisStockService;

/**
 * 业务 - 库存
 * 
 * @author Admin
 *
 */
@Service
public class BisStockServiceImpl extends BaseServiceImpl<BisStock> implements BisStockService {

	@Autowired
	private BisStockMapper mapper;

	@Override
	protected BaseMapper<BisStock> getMapper() {
		// TODO Auto-generated method stub
		return this.mapper;
	}

	@Override
	public void stockIn() {
		// TODO Auto-generated method stub
		BisStock stock = this.mapper.get(0L);
	}

	@Override
	public void stockOut() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<BisStock> treegrid(Long parentId) {
		List<BisStock> models = new ArrayList<BisStock>();

		// 查询仓库列表
		List<BisStock> stocks = this.mapper.queryTree(parentId);
		if (stocks.isEmpty()) {
			models.add(null);
		} else {
			// 遍历仓库
			for (BisStock stock : stocks) {
				BisStock model = new BisStock();
				model.setId(stock.getId());
				model.setName(stock.getName());
				model.setParentId(stock.getParentId());
				model.setCreateTime(stock.getCreateTime());
				model.setUpdateTime(stock.getUpdateTime());
				model.setChildern(this.treegrid(stock.getId()));
				models.add(model);
			}
		}
		return models;
	}

}
