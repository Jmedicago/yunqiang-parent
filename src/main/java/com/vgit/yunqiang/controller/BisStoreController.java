package com.vgit.yunqiang.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vgit.yunqiang.controller.consts.ControllerConsts;
import com.vgit.yunqiang.pojo.BisStock;
import com.vgit.yunqiang.service.BisStockService;

@Controller
@RequestMapping("/" + BisStoreController.DOMAIN)
public class BisStoreController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BisStoreController.class);

	public static final String DOMAIN = "stock";
	
	@Autowired
	private BisStockService bisStockService;

	@RequestMapping(ControllerConsts.URL_INDEX)
	public String index() {
		return DOMAIN + ControllerConsts.VIEW_INDEX;
	}
	
	
	@RequiresRoles(value = {"admin", "stock"}, logical = Logical.OR)
	@RequestMapping(ControllerConsts.URL_JSON)
	@ResponseBody
	public List<BisStock> json() {
		return this.bisStockService.treegrid(BisStockService.ROOT);
	}

}
