package com.vgit.yunqiang.controller;

import java.util.List;
import java.util.Map;

import com.vgit.yunqiang.common.consts.ICodes;
import com.vgit.yunqiang.common.consts.bis.OrderStateConsts;
import com.vgit.yunqiang.common.consts.msg.BisProductMsgConsts;
import com.vgit.yunqiang.common.exception.BisException;
import com.vgit.yunqiang.common.utils.Page;
import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.controller.utils.UserContext;
import com.vgit.yunqiang.pojo.BisCart;
import com.vgit.yunqiang.pojo.BisOrder;
import com.vgit.yunqiang.pojo.BisSku;
import com.vgit.yunqiang.service.BisOrderService;
import com.vgit.yunqiang.service.BisSkuService;
import org.slf4j.LoggerFactory;
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

	@Autowired
	private BisSkuService bisSkuService;
	
	@RequestMapping(ControllerConsts.URL_JSON)
	@ResponseBody
	public Page<BisOrderDetail> json(Long orderId) {
		return this.bisOrderDetailService.getOrderDetail(orderId);
	}

	@RequestMapping("/addAmount")
	@ResponseBody
	public Ret addAmount(BisOrderDetail orderDetail) {
		try {
			// 获取商品总库存
			BisSku sku = this.bisSkuService.get(orderDetail.getSkuId());
			if (orderDetail.getAmount() > sku.getAvailableStock()) {
				return Ret.me().setSuccess(false).setCode(BisProductMsgConsts.IN_A_SHORT_INVENTORY);
			}
			BisOrder order = this.bisOrderDetailService.updateOrderDetail(orderDetail);
			return Ret.me().setCode(ICodes.SUCCESS).setData(order);
		} catch (BisException e) {
			return Ret.me().setSuccess(false).setCode(e.getCode());
		}
	}

	@RequestMapping("/removeAmount")
	@ResponseBody
	public Ret removeAmount(BisOrderDetail orderDetail) {
		try {
			if (orderDetail.getAmount() < 1) {
				return Ret.me().setSuccess(false).setCode(BisProductMsgConsts.AT_A_SHORT_INVENTORY);
			}
			BisOrder order = this.bisOrderDetailService.updateOrderDetail(orderDetail);
			return Ret.me().setCode(ICodes.SUCCESS).setData(order);
		} catch (BisException e) {
			return Ret.me().setSuccess(false).setCode(e.getCode());
		}
	}

	/*@RequestMapping("/changeAmount")
	@ResponseBody
	public Ret changeAmount(BisOrderDetail orderDetail) {
		try {
			if (orderDetail.getRealAmount() < 1) {
				return Ret.me().setSuccess(false).setInfo("数量不能是负数");
			}
			BisOrder order = this.bisOrderDetailService.updateOrderDetail(orderDetail);
			return Ret.me().setCode(ICodes.SUCCESS).setData(order);
		} catch (BisException e) {
			return Ret.me().setSuccess(false).setCode(e.getCode());
		}
	}*/

	@RequestMapping("/changeAmount")
	@ResponseBody
	public Ret changeAmount(Integer o, Long id, Long orderId, Integer amount) {
		try {
			if (amount < 1) {
				return Ret.me().setSuccess(false).setInfo("数量不能是负数");
			}
			BisOrder order = this.bisOrderDetailService.updateOrderDetail(o, id, orderId, amount);
			return Ret.me().setCode(ICodes.SUCCESS).setData(order);
		} catch (BisException e) {
			return Ret.me().setSuccess(false).setCode(e.getCode());
		}
	}

	@RequestMapping("/del")
	@ResponseBody
	public Ret del(Integer o, Long id, Long orderId) {
		try {
			BisOrder order = this.bisOrderDetailService.delOrderDetail(o, id, orderId);
			return Ret.me().setCode(ICodes.SUCCESS).setData(order);
		} catch (BisException e) {
			return Ret.me().setSuccess(false).setCode(e.getCode());
		}
	}

}
