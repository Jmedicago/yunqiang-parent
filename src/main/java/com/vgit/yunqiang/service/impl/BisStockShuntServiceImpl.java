package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.exception.BisException;
import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.impl.BaseServiceImpl;
import com.vgit.yunqiang.common.utils.ExcelUtils;
import com.vgit.yunqiang.mapper.BisStockShuntMapper;
import com.vgit.yunqiang.model.ProductModel;
import com.vgit.yunqiang.pojo.*;
import com.vgit.yunqiang.service.*;
import com.vgit.yunqiang.service.format.StockShuntFormat;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

import static com.vgit.yunqiang.common.consts.msg.BisProductMsgConsts.IN_A_SHORT_INVENTORY;

@Service
public class BisStockShuntServiceImpl extends BaseServiceImpl<BisStockShunt> implements BisStockShuntService {

    @Autowired
    private BisStockShuntMapper mapper;

    @Autowired
    private BisOrderService bisOrderService;

    @Autowired
    private BisOrderDetailService bisOrderDetailService;

    @Autowired
    private BisStockService bisStockService;

    @Autowired
    private LogStockShuntService logStockShuntService;

    @Autowired
    private BisSkuService bisSkuService;

    @Override
    protected BaseMapper<BisStockShunt> getMapper() {
        return this.mapper;
    }

    @Override
    public void submitShunt(BisStockShunt stockShunt) {
        stockShunt.setSubmitTime(System.currentTimeMillis());
        stockShunt.setCreateTime(System.currentTimeMillis());
        stockShunt.setState((byte) 0);
        this.mapper.save(stockShunt);
    }

    @Override
    public void confirmShunt(BisStockShunt stockShunt) {
        stockShunt.setState((byte) 1);
        stockShunt.setConfirmTime(System.currentTimeMillis());
        stockShunt.setUpdateTime(System.currentTimeMillis());
        this.mapper.updatePart(stockShunt);
    }

    /**
     * 分流
     *
     * @param stockShunt
     * @throws BisException 库存不足
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void shunt(BisStockShunt stockShunt, String remark) throws BisException {
        // 查询总仓库存信息
        BisStockShunt defaultStockShunt = this.mapper.getAmountBy(Long.valueOf(DEFAULT_STOCK), stockShunt.getSkuId());
        if (defaultStockShunt != null && defaultStockShunt.getAmount() >= stockShunt.getAmount()) { // 检查库存
            BisStockShunt childStock = this.mapper.getAmountBy(stockShunt.getStockId(), stockShunt.getSkuId());
            // 分流
            if (childStock != null) {
                childStock.setAmount(childStock.getAmount() + stockShunt.getAmount()); // 分仓加库存
                childStock.setUpdateTime(System.currentTimeMillis());
                this.mapper.updatePart(childStock);
            } else {
                childStock = new BisStockShunt();
                childStock.setStockId(stockShunt.getStockId());
                childStock.setSkuId(stockShunt.getSkuId());
                childStock.setState((byte) 1);
                childStock.setAmount(stockShunt.getAmount()); // 分仓初始化库存
                childStock.setCreateTime(System.currentTimeMillis());
                this.mapper.savePart(childStock);
            }
            // 操作日志
            LogStockShunt shuntLog = new LogStockShunt();
            shuntLog.setAmount(stockShunt.getAmount());
            shuntLog.setDate(System.currentTimeMillis());
            shuntLog.setStockId(stockShunt.getStockId());
            shuntLog.setSkuId(stockShunt.getSkuId());
            shuntLog.setState((byte) 1);
            shuntLog.setInputUser(stockShunt.getStockId());
            shuntLog.setRemark(remark);
            this.logStockShuntService.save(shuntLog);

            defaultStockShunt.setAmount(defaultStockShunt.getAmount() - stockShunt.getAmount()); // 总仓减库存
            defaultStockShunt.setUpdateTime(System.currentTimeMillis());
            this.mapper.updatePart(defaultStockShunt);
        } else {
            throw new BisException().setCode(IN_A_SHORT_INVENTORY);
        }

    }

    @Override
    public List<BisStockShunt> getList(Long skuId) {
        return this.mapper.getList(skuId);
    }

    @Override
    public BisStockShunt getSkuStock(Long skuId, Long stockId) {
        return this.mapper.getAmountBy(stockId, skuId);
    }

    @Override
    public boolean checkStock(Long skuId, Long stockId, Integer amount) {
        // 查询出货位置信息
        String location = this.bisStockService.getShipmentLocation(stockId);
        // 查询出货位置库存量
        BisStockShunt stock = this.getSkuStock(skuId, Long.valueOf(location));
        if (stock != null && stock.getAmount() >= amount) { // 库存量大于需求量
            return true;
        }
        return false;
    }

    @Override
    public Integer getSkuStockByOrderId(Long skuId, Long orderId) {
        BisOrder order = this.bisOrderService.get(orderId);
        if (order != null) {
            // 查询出货位置信息
            String location = this.bisStockService.getShipmentLocation(order.getStockId());
            BisStockShunt stockShunt = this.getSkuStock(skuId, Long.valueOf(location));
            if (stockShunt != null) {
                return stockShunt.getAmount();
            }
        }
        return 0;
    }

    @Override
    public String export(String fileName, HttpServletRequest request) {
        String filePath = null;

        // 文件路径
        filePath = request.getSession().getServletContext().getRealPath("/");
        System.out.println("IO输出文件路径：" + filePath);

        // 检查文件名
        if (StringUtils.isBlank(fileName)) {
            fileName = UUID.randomUUID().toString();
        }

        filePath = filePath + "/upload/" + fileName + ".xlsx";

        String[] titleArray = {"类别", "图片", "品名", "货品编号", "属性", "包装形态", "体积", "成本价", "批发价", "利润", "总仓", "北部分仓", "南部分仓", "货柜编号", "供应商", "备注"};
        List<String> titles = Arrays.asList(titleArray);

        try {
            // 获得所有商品列表
            List<ProductModel> products = this.bisSkuService.getAll();
            System.out.println("商品列表：" + products.toString());
            ExcelUtils.writeExcel(filePath, fileName, titles, this.productToMap(products));
            // TODO.上传到FTP
            return "/upload/" + fileName + ".xlsx";
        } catch (IOException e) {
            throw new BisException().setInfo("批量导出商品发生异常");
        }
    }

    private List<Map<String, Object>> productToMap(List<ProductModel> products) {
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        for (ProductModel product : products) {
            Map<String, Object> map = new HashMap<>();
            map.put("类别", product.getPath());
            map.put("图片", product.getSkuMainPic());
            map.put("品名", product.getName());
            map.put("货品编号", product.getCode());
            map.put("属性", formatterSkuProperties(product.getSkuProperties()));
            map.put("包装形态", product.getPack());
            map.put("体积", product.getVolume());
            DecimalFormat df = new DecimalFormat("0.00");
            map.put("成本价", df.format(product.getCostPrice() * 0.01));
            map.put("批发价", df.format(product.getMarketPrice() * 0.01));
            map.put("利润", product.getProfit());
            map.put("总仓", product.getAllStock());
            List<BisStockShunt> stockShunts = product.getStockShunt();
            for (BisStockShunt stockShunt : stockShunts) {
                if (NORTH_STOCK.equals(String.valueOf(stockShunt.getStockId()))) {
                    map.put("北部分仓", stockShunt.getAmount());
                }
                if (SOUTH_STOCK.equals(String.valueOf(stockShunt.getStockId()))) {
                    map.put("南部分仓", stockShunt.getAmount());
                }
            }
            map.put("货柜编号", product.getContainer());
            map.put("供应商", product.getSupplier());
            map.put("备注", product.getRemark());
            mapList.add(map);
        }
        return mapList;
    }

    private String formatterSkuProperties(String skuProperties) {
        if (StringUtils.isNotBlank(skuProperties)) {
            return skuProperties.replaceAll("<br>", "\n");
        }
        return "";
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void checkOut(Long orderId) throws BisException {
        // 查询订单信息
        BisOrder bisOrder = this.bisOrderService.get(orderId);
        // 查询出货位置信息
        String location = this.bisStockService.getShipmentLocation(bisOrder.getStockId());
        // 查询订单明细
        List<BisOrderDetail> orderDetails = this.bisOrderDetailService.getList(orderId);
        for (BisOrderDetail detail : orderDetails) {
            switch (location) {
                case DEFAULT_STOCK: // 总仓
                    this.locationCheckOut(Long.valueOf(location), detail.getSkuId(), detail.getRealAmount());
                    break;
                case NORTH_STOCK: // 北仓
                    this.locationCheckOut(Long.valueOf(location), detail.getSkuId(), detail.getRealAmount());
                    break;
                case SOUTH_STOCK: // 南仓
                    this.locationCheckOut(Long.valueOf(location), detail.getSkuId(), detail.getRealAmount());
                    break;
            }
        }
    }

    /**
     * 所在位置出货
     *
     * @param location 出货位置
     * @param sukId    出货商品
     * @param amount   出货数量
     */
    private void locationCheckOut(Long location, Long sukId, Integer amount) {
        // 查询所在位置的商品库存
        BisStockShunt localStockShunt = this.mapper.getAmountBy(location, sukId);
        if (localStockShunt != null && localStockShunt.getAmount() > 0) { // 有库存
            // 出库
            localStockShunt.setAmount(localStockShunt.getAmount() - amount);
            localStockShunt.setUpdateTime(System.currentTimeMillis());
            this.mapper.updatePart(localStockShunt);
        } else { // 库存不足
            throw new BisException().setCode(IN_A_SHORT_INVENTORY);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void checkIn(BisStockShunt stockShunt) {
        // 检查入库
        BisStockShunt defaultStockShunt = this.mapper.getAmountBy(Long.valueOf(DEFAULT_STOCK), stockShunt.getSkuId());
        if (defaultStockShunt != null) {
            defaultStockShunt.setAmount(defaultStockShunt.getAmount() + stockShunt.getAmount());
            defaultStockShunt.setSubmitTime(System.currentTimeMillis());
            defaultStockShunt.setConfirmTime(System.currentTimeMillis());
            defaultStockShunt.setUpdateTime(System.currentTimeMillis());
            this.mapper.updatePart(defaultStockShunt);
        } else {
            stockShunt.setStockId(Long.valueOf(DEFAULT_STOCK));
            stockShunt.setState((byte) 1); // 默认不用审核
            stockShunt.setSubmitTime(System.currentTimeMillis());
            stockShunt.setConfirmTime(System.currentTimeMillis());
            stockShunt.setCreateTime(System.currentTimeMillis());
            this.mapper.savePart(stockShunt);
        }
    }

    @Override
    public void checkIn(Long skuId, Integer amount) {
        this.checkIn(new BisStockShunt(skuId, amount));
    }

}
