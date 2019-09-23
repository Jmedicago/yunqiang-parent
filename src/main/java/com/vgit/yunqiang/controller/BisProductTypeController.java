package com.vgit.yunqiang.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vgit.yunqiang.common.query.ProductTypeQuery;
import com.vgit.yunqiang.controller.consts.ControllerConsts;
import com.vgit.yunqiang.pojo.BisProductType;
import com.vgit.yunqiang.service.BisProductTypeService;
import com.vgit.yunqiang.service.BisStockService;

@Controller
@RequestMapping("/" + BisProductTypeController.DOMAIN)
public class BisProductTypeController {

	public static final String DOMAIN = "product-type";

	@Autowired
	private BisProductTypeService bisProductTypeService;

	@RequestMapping(ControllerConsts.URL_INDEX)
	public String index() {
		return DOMAIN + ControllerConsts.VIEW_INDEX;
	}

	@RequiresRoles(value = { "admin", "product" }, logical = Logical.OR)
	@RequestMapping(ControllerConsts.URL_JSON)
	@ResponseBody
	public List<BisProductType> json(ProductTypeQuery query) {
		return this.bisProductTypeService.treegrid(BisStockService.ROOT, query);
	}

}
