package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.consts.msg.BisOrderMsgConsts;
import com.vgit.yunqiang.common.exception.BisException;
import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.impl.BaseServiceImpl;
import com.vgit.yunqiang.common.utils.Page;
import com.vgit.yunqiang.common.utils.StrUtils;
import com.vgit.yunqiang.mapper.BisOrderDetailMapper;
import com.vgit.yunqiang.pojo.BisOrder;
import com.vgit.yunqiang.pojo.BisOrderDetail;
import com.vgit.yunqiang.service.BisOrderDetailService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vgit.yunqiang.service.BisOrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BisOrderDetailServiceImpl extends BaseServiceImpl<BisOrderDetail> implements BisOrderDetailService {

    @Autowired
    private BisOrderDetailMapper mapper;

    @Autowired
    private BisOrderService bisOrderService;

    @Override
    protected BaseMapper<BisOrderDetail> getMapper() {
        return this.mapper;
    }

    @Override
    public Page<BisOrderDetail> getOrderDetail(Long orderId) {
        Page<BisOrderDetail> page = new Page<BisOrderDetail>();
        double volumeTotal = 0;

        List<BisOrderDetail> orderDetails = this.mapper.getOrderDetail(orderId);
        for (BisOrderDetail orderDetail : orderDetails) {
            volumeTotal += orderDetail.getTotalVolume();
            orderDetail.setSkuProperties(StrUtils.convertPropertiesToHtml(orderDetail.getSkuProperties()));
        }
        page.setRows(orderDetails);

        Map<String, Object> footer = new HashMap<String, Object>();
        footer.put("volumeTotal", volumeTotal);
        page.setFooter(footer);
        return page;
    }

    @Override
    public BisOrder updateOrderDetail(BisOrderDetail orderDetail) throws BisException {
        if (this.isLocked(orderDetail.getOrderId())) {
            BisOrderDetail newOrderDetail = new BisOrderDetail();
            // 更新订单明细
            newOrderDetail.setId(orderDetail.getId());
            newOrderDetail.setTotalVolume(orderDetail.getVolume() * orderDetail.getAmount());
            newOrderDetail.setTotalMoney(orderDetail.getPrice() * orderDetail.getAmount());
            newOrderDetail.setAmount(orderDetail.getAmount());
            this.updatePart(newOrderDetail);
            BisOrder bisOrder = this.updateOrder(orderDetail.getOrderId());
            return bisOrder;
        } else {
            throw new BisException().setCode(BisOrderMsgConsts.ORDER_LOCKED);
        }
    }

    private boolean isLocked(Long orderId) {
        BisOrder order = this.bisOrderService.get(orderId);
        if (order != null && order.getStatus() <= 0) {
            return true;
        }
        return false;
    }

    private BisOrder updateOrder(Long orderId) {
        // 更新订单信息
        StringBuilder digest = new StringBuilder(); // 订单摘要
        Double totalMoney = 0.0d; // 订单所有商品的总价
        Double totalVolume = 0.0d; // 订单所有商品的体积
        List<BisOrderDetail> orderDetails = this.mapper.getOrderDetail(orderId);
        for (BisOrderDetail detail : orderDetails) {
            // 获取商品订单总价
            totalMoney += detail.getAmount() * detail.getPrice();
            // 获取商品的体积
            totalVolume += detail.getAmount() * detail.getVolume();
            // 获取摘要
            digest.append(detail.getName());
            String skuProperties = detail.getSkuProperties();
            if (StringUtils.isNotBlank(skuProperties)) {
                String[] propArr = skuProperties.split("_");
                for (String props : propArr) {
                    String[] propValueArr = props.split(":");
                    digest.append("-").append(propValueArr[3]);
                }
            }
            digest.append("×").append(detail.getAmount()).append(",");
        }
        BisOrder bisOrder = this.bisOrderService.get(orderId);
        bisOrder.setTotalMoney(totalMoney);
        bisOrder.setTotalVolume(totalVolume);
        bisOrder.setDigest(digest.toString());
        bisOrder.setDetailList(orderDetails);
        this.bisOrderService.updatePart(bisOrder);
        return bisOrder;
    }

}
