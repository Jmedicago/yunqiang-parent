package com.vgit.yunqiang.controller;

import java.util.List;
import java.util.Map;

import com.vgit.yunqiang.common.consts.bis.OrderStateConsts;
import com.vgit.yunqiang.common.utils.Page;
import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.controller.utils.UserContext;
import com.vgit.yunqiang.pojo.BisCart;
import com.vgit.yunqiang.pojo.BisOrder;
import com.vgit.yunqiang.pojo.BisSku;
import com.vgit.yunqiang.service.BisOrderService;
import com.vgit.yunqiang.service.BisSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vgit.yunqiang.controller.consts.ControllerConsts;
import com.vgit.yunqiang.pojo.BisOrderDetail;
import com.vgit.yunqiang.service.BisOrderDetailService;

@Controller
@RequestMapping("/" + BisOrderDetailController.DOMAIN)
public class BisOrderDetailController {
	
	public static final String DOMAIN = "order-detail";
	
	@Autowired
	private BisOrderDetailService bisOrderDetailService;
	
	@RequestMapping(ControllerConsts.URL_JSON)
	@ResponseBody
	public Page<BisOrderDetail> json(Long orderId) {
		return this.bisOrderDetailService.getOrderDetail(orderId);
	}

}
