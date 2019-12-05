package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.consts.GlobalSettingNames;
import com.vgit.yunqiang.common.consts.ICodes;
import com.vgit.yunqiang.common.consts.bis.BooleanConsts;
import com.vgit.yunqiang.common.consts.bis.JobTypeConsts;
import com.vgit.yunqiang.common.consts.bis.OrderStateConsts;
import com.vgit.yunqiang.common.consts.msg.BisOrderMsgConsts;
import com.vgit.yunqiang.common.consts.msg.SysUserMsgConsts;
import com.vgit.yunqiang.common.exception.BisException;
import com.vgit.yunqiang.common.query.QuartzJobInfo;
import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.impl.BaseServiceImpl;
import com.vgit.yunqiang.common.utils.GlobalSetting;
import com.vgit.yunqiang.common.utils.IDUtils;
import com.vgit.yunqiang.common.utils.Page;
import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.mapper.BisOrderMapper;
import com.vgit.yunqiang.pojo.*;
import com.vgit.yunqiang.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BisOrderServiceImpl extends BaseServiceImpl<BisOrder> implements BisOrderService {

    @Autowired
    private BisOrderMapper mapper;

    @Autowired
    private BisCartService bisCartService;

    @Autowired
    private BisProductService bisProductService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private BisSkuService bisSkuService;

    @Autowired
    private BisOrderDetailService bisOrderDetailService;

    @Autowired
    private BisStockShuntService bisStockShuntService;

    @Autowired
    private QuartzService quartzService;

    @Override
    protected BaseMapper<BisOrder> getMapper() {
        return this.mapper;
    }

    @Override
    public Ret create(BisOrder formOrder, Long billId) {
        Double totalMoney = 0.0d; // 订单所有商品的总价
        Double totalVolume = 0.0d; // 总体积
        //StringBuilder digest = new StringBuilder(); // 订单摘要

        // 获取用户购物车情况:处理库存、销量、计算订单总价及摘要
        List<BisCart> carts = this.bisCartService.getCarts(formOrder.getUserId());
        for (BisCart bisCart : carts) {
            if (bisCart.getSelected() == BooleanConsts.YES) {
                // 当前SKU
                BisSku sku = bisCart.getSku();

                // 检查权属
                Long userId = formOrder.getUserId();
                SysUser sysUser = this.sysUserService.get(userId);
                if (sysUser != null) {
                    String stockIds = sysUser.getStockIds();
                    // 购买数量
                    Integer amount = bisCart.getAmount();
                    try {
                        this.bisSkuService.checkStock(stockIds, sku, amount);
                    } catch (BisException e) {
                        return Ret.me().setSuccess(false).setCode(e.getCode()).setInfo(e.getInfo());
                    }
                }

                /*// 当前可用库存
                Integer availableStock = sku.getAvailableStock();
                // 购买数量
                Integer amount = bisCart.getAmount();
                // 库存不足
                if (amount > availableStock) {

                }*/
                // 库存充足，扣除库存
                /*sku.setAvailableStock(sku.getAvailableStock() - amount);
                sku.setFrozenStock(sku.getFrozenStock() + amount);
                this.bisSkuService.updatePart(sku);*/
                // TODO .增加商品的销量
                // 获取商品订单总价
                totalMoney += bisCart.getAmount() * bisCart.getSku().getCostPrice();
                totalVolume += bisCart.getAmount() * bisCart.getSku().getVolume();
                // 获取摘要
                /*digest.append(bisCart.getName());
                String skuProperties = bisCart.getSkuProperties();
                if (StringUtils.isNotBlank(skuProperties)) {
                    String[] propArr = skuProperties.split("_");
                    for (String props : propArr) {
                        String[] propValueArr = props.split(":");
                        digest.append("-").append(propValueArr[3]);
                    }
                }
                digest.append("×").append(bisCart.getAmount()).append(",");*/
            }
        }

        String orderSn = IDUtils.generateOrderSn(formOrder.getUserId());// 订单号

        // 计算订单取消截止时间
        BigDecimal hours = new BigDecimal(GlobalSetting.get(GlobalSettingNames.SYSTEM_TIME_LIMIT_HOURS));
        BigDecimal millsExpires = hours.multiply(new BigDecimal(3600000));
        long lastCancelTime = (millsExpires.add(new BigDecimal(System.currentTimeMillis()))).longValue();

        // 设置订单信息
        formOrder.setOrderSn(orderSn);
        formOrder.setStatus((int) OrderStateConsts.WAIT_SHIP_AUDITING);
        formOrder.setTotalMoney(totalMoney);
        formOrder.setTotalVolume(totalVolume);
        formOrder.setCommentStatus(BooleanConsts.NO);
        //formOrder.setDigest(digest.toString());
        formOrder.setCreateTime(System.currentTimeMillis());
        formOrder.setUpdateTime(formOrder.getCreateTime());
        formOrder.setConfirmTime(System.currentTimeMillis());
        formOrder.setLastCancelTime(lastCancelTime);
        this.savePart(formOrder);

        // 创建订单关联的订单明细
        for (BisCart bisCart : carts) {
            if (bisCart.getSelected() == BooleanConsts.YES) {
                // 当前SKU
                BisSku sku = bisCart.getSku();
                BisOrderDetail orderDetail = new BisOrderDetail();
                orderDetail.setAmount(bisCart.getAmount());
                orderDetail.setName(bisCart.getName());
                orderDetail.setOrderId(formOrder.getId());
                orderDetail.setPrice(sku.getCostPrice());
                orderDetail.setProductId(bisCart.getProductId());
                orderDetail.setSkuId(bisCart.getSkuId());
                orderDetail.setVolume(sku.getVolume());
                orderDetail.setSkuMainPic(bisCart.getSkuMainPic());
                orderDetail.setSkuProperties(bisCart.getSkuProperties());
                orderDetail.setTotalMoney(bisCart.getAmount() * sku.getCostPrice());
                orderDetail.setTotalVolume(bisCart.getAmount() * sku.getVolume());
                orderDetail.setIsComment(0);
                orderDetail.setInputUser("终端店长");
                orderDetail.setRealAmount(bisCart.getAmount());
                this.bisOrderDetailService.savePart(orderDetail);
            }
        }

        // 删除购物车已购买部分的数据
        bisCartService.clearQuick(formOrder.getUserId());

        // 取消订单取消倒计时任务
        Map<String, Object> jobParams = new HashMap<>();
        jobParams.put("orderId", formOrder.getId());
        QuartzJobInfo info = new QuartzJobInfo();
        info.setFireDate(new Date(lastCancelTime));
        info.setParams(jobParams);
        info.setJobName("CANCEL_ORDER_TASK_" + formOrder.getId());
        info.setType(JobTypeConsts.WAIT_ORDER_CANCEL_JOB);
        this.quartzService.addJob(info);

        // 返回创建好的订单
        return Ret.me().setData(formOrder);
    }

    @Override
    public BisOrder getById(Long id) {
        return this.mapper.get(id);
    }

    @Override
    public void cancel(Long orderId) {
        BisOrder order = this.getById(orderId);

        // 检查订单状态（是否可被取消），当订单状态为待审核状态可取消
        if (OrderStateConsts.WAIT_SHIP_AUDITING != order.getStatus()) {
            throw BisException.me().setCode(SysUserMsgConsts.ORDER_CANCEL_FORBIDDEN);
        }

        //修改订单状态
        order.setStatus((int) OrderStateConsts.CLOSED);
        this.updatePart(order);
        //TODO .（通知库存系统）恢复商品库存
        /*List<BisOrderDetail> detailList = order.getDetailList();
        for (BisOrderDetail orderDetail : detailList) {
            Long skuId = orderDetail.getSkuId();
            Integer amount = orderDetail.getAmount();
            // 恢复库存
            this.bisSkuService.recoverStock(skuId, amount);
        }*/
    }

    @Override
    public void sendShip(Long[] orderIdArr) {
        if (orderIdArr == null || orderIdArr.length == 0) {
            return;
        }

        for (Long orderId : orderIdArr) {
            BisOrder order = this.getById(orderId);
            if (order.getStatus() != OrderStateConsts.WAIT_SHIP_SEND) {
                throw BisException.me().setCode(ICodes.ILLEGAL_ACCESS);
            }

            List<BisOrderDetail> detailList = order.getDetailList();
            for (BisOrderDetail orderDetail : detailList) {
                Long skuId = orderDetail.getSkuId();
                Integer amount = orderDetail.getAmount();
                this.bisSkuService.outbound(skuId, amount);
            }
            //修改订单状态和配送信息
            BigDecimal days = new BigDecimal(GlobalSetting.get(GlobalSettingNames.SYSTEM_CONFIRM_SHIP_DAYS));
            BigDecimal millsExpires = days.multiply(new BigDecimal(24 * 3600000));
            long lastConfirmTime = (millsExpires.add(new BigDecimal(System.currentTimeMillis()))).longValue();

            order.setConfirmTime(lastConfirmTime);
            order.setShipTime(System.currentTimeMillis());
            order.setStatus((int) OrderStateConsts.WAIT_SHIP_TAKE);
            order.setUpdateTime(System.currentTimeMillis());
            this.updatePart(order);

            // 添加自动确认收货任务
            /*Map<String, Object> jobParams = new HashMap<>();
            jobParams.put("orderId", order.getId());
            QuartzJobInfo info = new QuartzJobInfo();
            info.setFireDate(new Date(lastConfirmTime));
            info.setParams(jobParams);
            info.setJobName("CONFIRM_ORDER_TASK_" + order.getId());
            info.setType(JobTypeConsts.WAIT_ORDER_CONFIRM_SHIP_JOB);
            quartzService.addJob(info);*/
            //TODO .可选择在发货后短信通知用户发货信息
        }
    }

    @Override
    public void confirmFinish(Long orderId) {
        BisOrder order = mapper.get(orderId);
        this.confirmFinishOrder(order);
    }

    @Override
    public Ret sendShip(Long orderId) {
        BisOrder bisOrder = this.mapper.get(orderId);
        if (bisOrder.getStatus() == 0) {
           /* Page<BisOrderDetail> page = this.bisOrderDetailService.getOrderDetail(orderId);
            if (page != null) {
                for (BisOrderDetail orderDetail : page.getRows()) {
                    BisSku bisSku = this.bisSkuService.get(orderDetail.getSkuId());

                    String stockId = String.valueOf(bisOrder.getStockId());
                    try {
                        this.bisSkuService.reduceStock(stockId, bisSku, orderDetail.getRealAmount());
                    } catch (BisException e) {
                        return Ret.me().setSuccess(false).setCode(e.getCode()).setInfo(e.getInfo());
                    }

                    // 库存不足
                    //if (orderDetail.getAmount() > bisSku.getAvailableStock()) {
                        return Ret.me().setSuccess(false).setCode(SysUserMsgConsts.ORDER_STOCK_NOT_ENOUGH);
                    //}
                    // 库存充足，扣除库存
                    //bisSku.setAvailableStock(bisSku.getAvailableStock() - orderDetail.getAmount());
                    //bisSku.setFrozenStock(bisSku.getFrozenStock() + orderDetail.getAmount());
                    //this.bisSkuService.updatePart(bisSku);
                }
            }*/

            // 减库存
            this.bisStockShuntService.checkOut(orderId);

            bisOrder.setShipTime(System.currentTimeMillis());
            bisOrder.setStatus((int) OrderStateConsts.WAIT_SHIP_SEND);
            this.mapper.updatePart(bisOrder);

            //删除待支付订单自动取消定时任务
            this.quartzService.delJob("CANCEL_ORDER_TASK_" + orderId);
            return Ret.me().setCode(ICodes.SUCCESS);
        }
        return Ret.me();
    }

    @Override
    public BisOrder print(Long orderId) throws BisException {
        BisOrder bisOrder = this.mapper.get(orderId);
        Page<BisOrderDetail> page = this.bisOrderDetailService.getOrderDetail(orderId);
        if (page != null) {
            bisOrder.setDetailList(page.getRows());
        }
        return bisOrder;

    }

    @Override
    public Ret checkPrint(Long id) {
        BisOrder bisOrder = this.mapper.get(id);
        if (bisOrder.getStatus() == OrderStateConsts.WAIT_SHIP_SEND) {
            return Ret.me();
        } else {
            return Ret.me().setSuccess(false).setCode(BisOrderMsgConsts.ORDER_UN_PRINT);
        }
    }

    @Override
    public boolean hasNotComment(Long orderId) {
        int count = this.mapper.hasNotComment(orderId);
        if (count > 0) {
            return true;
        }
        // 修改订单评价状态
        BisOrder bisOrder = new BisOrder();
        bisOrder.setId(orderId);
        bisOrder.setCommentStatus((byte) 1);
        this.mapper.updatePart(bisOrder);
        return false;
    }

    private void addToOrder(Long orderId, Long skuId, Integer number) throws BisException {
        BisOrder bisOrder = this.mapper.get(orderId);
        // 查询订单是否存在
        BisOrderDetail existBisOrderDetail = this.bisOrderDetailService.getOrderDetail(orderId, skuId);
        // 检查商品库存数量
        BisSku curSku = this.bisSkuService.get(skuId);
        // 检查权属
        this.bisSkuService.checkStock(String.valueOf(bisOrder.getStockId()), curSku);
        // 存在则增加数量
        if (null != existBisOrderDetail) {
            existBisOrderDetail.setRealAmount(existBisOrderDetail.getAmount() + number);
            this.bisOrderDetailService.updatePart(existBisOrderDetail);
            return;
        }
        // 获取数据库最新的SKU信息
        BisSku sku = bisProductService.getSku(skuId);
        // 获取商品的信息
        BisProduct product = this.bisProductService.get(sku.getProductId());
        // 获取展示主图
        String mainPic = sku.getSkuMainPic();
        if (StringUtils.isBlank(mainPic)) {
            List<BisProductMedia> productMedias = this.bisProductService.getProductMedias(product.getId());
            if (!productMedias.isEmpty()) {
                mainPic = productMedias.get(0).getResource();
            }
        }

        BisOrderDetail orderDetail = new BisOrderDetail();
        orderDetail.setOrderId(orderId);
        orderDetail.setProductId(product.getId());
        orderDetail.setName(product.getName());
        orderDetail.setSkuId(skuId);
        orderDetail.setSkuMainPic(mainPic);
        orderDetail.setSkuProperties(sku.getSkuProperties());
        orderDetail.setPrice(sku.getCostPrice());
        //orderDetail.setAmount(number);
        orderDetail.setVolume(sku.getVolume());
        orderDetail.setTotalMoney(number * sku.getCostPrice());
        orderDetail.setTotalVolume(number * sku.getVolume());
        orderDetail.setIsComment(0);
        orderDetail.setInputUser("仓管员");
        orderDetail.setRealAmount(number);

        this.bisOrderDetailService.savePart(orderDetail);

        // 更新订单信息
        BisOrder order = new BisOrder();
        order.setId(orderId);
        order.setDigest(this.bisOrderDetailService.digest(orderId));
        List<BisOrderDetail> orderDetailList = this.bisOrderDetailService.getList(orderId);
        double totalVolume = 0d;
        double totalPrice = 0d;
        for (BisOrderDetail orderDetailItem : orderDetailList) {
            totalVolume += orderDetailItem.getRealAmount() * orderDetailItem.getVolume();
            totalPrice += orderDetailItem.getRealAmount() * orderDetailItem.getPrice();
        }
        order.setTotalVolume(totalVolume);
        order.setTotalMoney(totalPrice);
        this.mapper.updatePart(order);
    }


    @Override
    public void addToOrder(Long orderId, Long... skuIds) {
        if (skuIds == null || skuIds.length == 0) {
            return;
        }
        for (Long skuId : skuIds) {
            this.addToOrder(orderId, skuId, 1);
        }
    }

    /**
     * 统一确认订单收货逻辑
     *
     * @param order
     */
    private void confirmFinishOrder(BisOrder order) {
        if (order.getStatus() != OrderStateConsts.WAIT_SHIP_SEND) {
            throw BisException.me().setCode(ICodes.ILLEGAL_ACCESS);
        }

        order.setFinishedTime(System.currentTimeMillis());
        order.setStatus((int) OrderStateConsts.FINISHED);
        this.updatePart(order);
    }

}
