package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.impl.BaseServiceImpl;
import com.vgit.yunqiang.common.utils.Page;
import com.vgit.yunqiang.common.utils.StrUtils;
import com.vgit.yunqiang.mapper.BisOrderDetailMapper;
import com.vgit.yunqiang.pojo.BisOrderDetail;
import com.vgit.yunqiang.service.BisOrderDetailService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vgit.yunqiang.service.BisOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BisOrderDetailServiceImpl extends BaseServiceImpl<BisOrderDetail> implements BisOrderDetailService {

    @Autowired
    private BisOrderDetailMapper mapper;

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

}
