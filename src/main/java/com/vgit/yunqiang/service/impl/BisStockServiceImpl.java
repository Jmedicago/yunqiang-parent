/**
 *
 */
package com.vgit.yunqiang.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.impl.BaseServiceImpl;
import com.vgit.yunqiang.mapper.BisStockMapper;
import com.vgit.yunqiang.pojo.BisStock;
import com.vgit.yunqiang.service.BisStockService;

/**
 * 业务 - 库存
 *
 * @author Admin
 */
@Service
public class BisStockServiceImpl extends BaseServiceImpl<BisStock> implements BisStockService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BisStockServiceImpl.class);

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
        // 遍历仓库
        for (BisStock stock : stocks) {
            BisStock model = new BisStock();
            model.setId(stock.getId());
            model.setName(stock.getName());
            model.setParentId(stock.getParentId());
            model.setDescription(stock.getDescription());
            model.setStatus(stock.getStatus());
            model.setCreateTime(stock.getCreateTime());
            model.setUpdateTime(stock.getUpdateTime());
            model.setChildren(this.treegrid(stock.getId()));
            models.add(model);
        }
        return models;
    }

    @Override
    public BisStock saveOrUpdateStock(BisStock stock) {
        if (stock.getId() == null) {
            stock.setCreateTime(System.currentTimeMillis());
            this.mapper.savePart(stock);
        } else {
            stock.setUpdateTime(System.currentTimeMillis());
            this.mapper.updatePart(stock);
        }
        return stock;
    }

    public void delete(Long id) {
        if (!this.mapper.isParent(id)) {
            this.mapper.delete(id);
        } else {
            this.mapper.deleteByParentId(id);
            this.mapper.delete(id);
        }
    }

}
