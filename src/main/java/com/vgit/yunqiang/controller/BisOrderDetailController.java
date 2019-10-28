package com.vgit.yunqiang.controller;

import java.util.List;

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
	public List<BisOrderDetail> json(String sn) {	
		return this.bisOrderDetailService.getOrderDetail(sn);
	}

}
