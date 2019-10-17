package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.consts.ICodes;
import com.vgit.yunqiang.common.service.TreeGridService;
import com.vgit.yunqiang.common.service.impl.TreeGridServiceImpl;
import com.vgit.yunqiang.common.utils.Ret;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.mapper.BisStockMapper;
import com.vgit.yunqiang.pojo.BisStock;
import com.vgit.yunqiang.service.BisStockService;

/**
 * 业务 - 库存
 *
 * @author Admin
 */
@Service
public class BisStockServiceImpl extends TreeGridServiceImpl<BisStock> implements BisStockService {

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

    @Override
    public Ret deleteById(Long id) {
        BisStock stock = this.mapper.get(id);
        if (stock != null && stock.getParentId() == TreeGridService.ROOT) {
            return Ret.me().setSuccess(false).setCode(ICodes.NOT_AUTHORIZED);
        }
        this.delByRoot(id); // 删除该节点及所有子节点
        return Ret.me().setSuccess(true).setCode(ICodes.SUCCESS);
    }

    @Override
    public BisStock getStockByName(String name) {
        return this.mapper.getStockByName(name);
    }

}
