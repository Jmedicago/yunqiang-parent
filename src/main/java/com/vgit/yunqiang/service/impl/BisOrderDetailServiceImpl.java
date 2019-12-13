package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.consts.msg.BisOrderMsgConsts;
import com.vgit.yunqiang.common.exception.BisException;
import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.impl.BaseServiceImpl;
import com.vgit.yunqiang.common.utils.Page;
import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.common.utils.StrUtils;
import com.vgit.yunqiang.mapper.BisOrderDetailMapper;
import com.vgit.yunqiang.pojo.BisOrder;
import com.vgit.yunqiang.pojo.BisOrderDetail;
import com.vgit.yunqiang.pojo.BisSku;
import com.vgit.yunqiang.service.BisOrderDetailService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vgit.yunqiang.service.BisOrderService;
import com.vgit.yunqiang.service.BisSkuService;
import com.vgit.yunqiang.service.BisStockShuntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.vgit.yunqiang.common.consts.msg.BisProductMsgConsts.IN_A_SHORT_INVENTORY;

@Service
public class BisOrderDetailServiceImpl extends BaseServiceImpl<BisOrderDetail> implements BisOrderDetailService {

    @Autowired
    private BisSkuService bisSkuService;

    @Autowired
    private BisOrderDetailMapper mapper;

    @Autowired
    private BisOrderService bisOrderService;

    @Autowired
    private BisStockShuntService bisStockShuntService;

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
            // 检查库存
            BisOrder bisOrder = this.bisOrderService.get(orderDetail.getOrderId());
            if (!this.bisStockShuntService.checkStock(orderDetail.getSkuId(), bisOrder.getStockId(), orderDetail.getRealAmount())) {
                throw new BisException().setCode(IN_A_SHORT_INVENTORY);
            }

            BisOrderDetail newOrderDetail = new BisOrderDetail();
            // 更新订单明细
            newOrderDetail.setId(orderDetail.getId());
            newOrderDetail.setTotalVolume(orderDetail.getVolume() * orderDetail.getRealAmount());
            newOrderDetail.setTotalMoney(orderDetail.getPrice() * orderDetail.getRealAmount());
            // newOrderDetail.setAmount(orderDetail.getAmount());
            newOrderDetail.setRealAmount(orderDetail.getRealAmount());
            this.updatePart(newOrderDetail);
            BisOrder newOrder = this.updateOrder(orderDetail.getOrderId());
            return newOrder;
        } else {
            throw new BisException().setCode(BisOrderMsgConsts.ORDER_LOCKED);
        }
    }

    @Override
    public BisOrder updateOrderDetail(Integer o, Long id, Long orderId, Integer amount) {
        if (this.isLocked(orderId) && o == 2) {
            throw new BisException().setCode(BisOrderMsgConsts.ORDER_LOCKED);
        }

        // 检查库存
        BisOrder bisOrder = this.bisOrderService.get(orderId);

        BisOrderDetail orderDetail = this.get(id);

        if (!this.bisStockShuntService.checkStock(orderDetail.getSkuId(), bisOrder.getStockId(), amount)) {
            throw new BisException().setCode(IN_A_SHORT_INVENTORY);
        }

        BisOrderDetail newOrderDetail = new BisOrderDetail();
        // 更新订单明细
        newOrderDetail.setId(id);
        newOrderDetail.setTotalVolume(orderDetail.getVolume() * amount);
        newOrderDetail.setTotalMoney(orderDetail.getPrice() * amount);
        // newOrderDetail.setAmount(orderDetail.getAmount());
        newOrderDetail.setRealAmount(amount);
        this.updatePart(newOrderDetail);
        BisOrder newOrder = this.updateOrder(orderDetail.getOrderId());
        return newOrder;
    }

    @Override
    public BisOrder delOrderDetail(Integer o, Long id, Long orderId) {
        if (this.isLocked(orderId) && o == 2) {
            throw new BisException().setCode(BisOrderMsgConsts.ORDER_LOCKED);
        }
        this.delete(id);
        BisOrder bisOrder = this.updateOrder(orderId);
        return bisOrder;
    }

    @Override
    public String digest(Long orderId) {
        StringBuffer digest = new StringBuffer();

        int selectedGoodsTotalCount = 0;
        int selectedFactoryShoesTotalCount = 0;
        int selectedTradeShoesTotalCount = 0;
        int selectedGMTotalCount = 0;

        if (orderId != null) {
            Integer cSelectedFactoryShoesTotalCount = this.mapper.getTotalByProductType(1035L, orderId);
            if (cSelectedFactoryShoesTotalCount != null) {
                selectedFactoryShoesTotalCount = cSelectedFactoryShoesTotalCount;
            }

            Integer cSelectedTradeShoesTotalCount = this.mapper.getTotalByProductType(1010L, orderId);
            if (cSelectedTradeShoesTotalCount != null) {
                selectedTradeShoesTotalCount = cSelectedTradeShoesTotalCount;
            }

            Integer cSelectedGMTotalCount = this.mapper.getTotalByProductType(1011L, orderId);
            if (cSelectedGMTotalCount != null) {
                selectedGMTotalCount = cSelectedGMTotalCount;
            }

        }

        // 新增
        digest.append("工厂鞋：" + selectedFactoryShoesTotalCount + "件<br>");
        digest.append("贸易鞋：" + selectedTradeShoesTotalCount + "件<br>");
        digest.append("百货类：" + selectedGMTotalCount + "件<br>");

        return digest.toString();
    }

    @Override
    public BisOrderDetail getOrderDetail(Long orderId, Long skuId) {
        return this.mapper.getByOrderDetailSku(orderId, skuId);
    }

    @Override
    public List<BisOrderDetail> getList(Long orderId) {
        return this.mapper.getList(orderId);
    }


    @Override
    public boolean isLocked(Long orderId) {
        BisOrder order = this.bisOrderService.get(orderId);
        // 1, 2, 3, 4, 5
        if (order != null && order.getStatus() >= 1) { // 已打印订单
            return true;
        }
        return false;
    }

    private BisOrder updateOrder(Long orderId) {
        // 更新订单信息
        // StringBuilder digest = new StringBuilder(); // 订单摘要
        Double totalMoney = 0.0d; // 订单所有商品的总价
        Double totalVolume = 0.0d; // 订单所有商品的体积
        List<BisOrderDetail> orderDetails = this.mapper.getOrderDetail(orderId);
        for (BisOrderDetail detail : orderDetails) {
            // 获取商品订单总价
            totalMoney += detail.getRealAmount() * detail.getPrice();
            // 获取商品的体积
            totalVolume += detail.getRealAmount() * detail.getVolume();
            // 获取摘要
            /*digest.append(detail.getName());
            String skuProperties = detail.getSkuProperties();
            if (StringUtils.isNotBlank(skuProperties)) {
                String[] propArr = skuProperties.split("_");
                for (String props : propArr) {
                    String[] propValueArr = props.split(":");
                    digest.append("-").append(propValueArr[3]);
                }
            }
            digest.append("×").append(detail.getAmount()).append(",");*/
        }
        BisOrder bisOrder = this.bisOrderService.get(orderId);
        bisOrder.setTotalMoney(totalMoney);
        bisOrder.setTotalVolume(totalVolume);
        //bisOrder.setDigest(digest.toString());
        bisOrder.setDigest(digest(orderId));
        bisOrder.setDetailList(orderDetails);
        this.bisOrderService.updatePart(bisOrder);
        return bisOrder;
    }

}
