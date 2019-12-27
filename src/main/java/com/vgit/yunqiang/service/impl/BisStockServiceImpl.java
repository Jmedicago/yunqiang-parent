package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.consts.ICodes;
import com.vgit.yunqiang.common.service.TreeGridService;
import com.vgit.yunqiang.common.service.impl.TreeGridServiceImpl;
import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.pojo.BisProductType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.mapper.BisStockMapper;
import com.vgit.yunqiang.pojo.BisStock;
import com.vgit.yunqiang.service.BisStockService;

import java.util.Arrays;
import java.util.List;

import static com.vgit.yunqiang.service.BisStockShuntService.DEFAULT_STOCK;
import static com.vgit.yunqiang.service.BisStockShuntService.NORTH_STOCK;
import static com.vgit.yunqiang.service.BisStockShuntService.SOUTH_STOCK;

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
            this.handleSave(stock);
            this.mapper.updatePart(stock);
        } else {
            stock.setUpdateTime(System.currentTimeMillis());
            this.handleSave(stock);
            this.mapper.updatePart(stock);
        }
        return stock;
    }

    /**
     * 存储修改分类前进行预处理数据
     *
     * @param o
     */
    private void handleSave(BisStock o) {
        Long id = o.getId();
        Long pid = o.getParentId();
        String path = "";
        if (pid == null || 0 == pid) { // 一级分类
            path = "." + id + ".";
        } else { // 子类
            BisStock stock = this.get(pid);
            path = stock.getPath() + id + ".";
        }
        o.setPath(path);
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

    @Override
    public String getShipmentLocation(Long stockId) {
        BisStock stock = this.mapper.get(stockId);
        if (stock.getPath().contains(NORTH_STOCK)) { // 是否是北部仓库
            return NORTH_STOCK;
        } else if (stock.getPath().contains(SOUTH_STOCK)) {
            return SOUTH_STOCK;
        } else {
            return DEFAULT_STOCK;
        }
    }

    @Override
    public String getPath(Long id) {
        if (id == null) {
            return "";
        }

        BisStock bisStock = this.get(id);
        if (bisStock != null) {
            StringBuffer sb = new StringBuffer();

            String[] paths = bisStock.getPath().split("\\.");
            List<String> pathList = Arrays.asList(paths);
            //pathList.remove(0);

            for (int i = 2; i < pathList.size(); i++) {
                BisStock type = this.get(Long.valueOf(pathList.get(i)));
                sb.append(type.getName());
                //sb.append("\\");
                sb.append(" ");
            }

            if (sb.length() > 0) {
                return sb.toString().substring(0, sb.length() - 1);
            } else {
                return "总仓";
            }

            //return sb.toString();
        } else {
            return "";
        }
    }

}
