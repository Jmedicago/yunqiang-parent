package com.vgit.yunqiang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vgit.yunqiang.common.query.TradeStockQuery;
import com.vgit.yunqiang.common.utils.Page;
import com.vgit.yunqiang.controller.consts.ControllerConsts;
import com.vgit.yunqiang.pojo.BisTradeStock;
import com.vgit.yunqiang.service.BisTradeStockService;

@Controller
@RequestMapping("/" + BisTradeStockController.DOMAIN)
public class BisTradeStockController {
	
	public static final String DOMAIN = "trade-stock";
	
	@Autowired
	public BisTradeStockService bisTradeStockService;
	
	@RequestMapping(ControllerConsts.URL_INDEX)
    public String index() {
        return DOMAIN + ControllerConsts.VIEW_INDEX;
    }
	
	@RequestMapping(ControllerConsts.URL_JSON)
    @ResponseBody
    public Page<BisTradeStock> json(TradeStockQuery query) {
        return this.bisTradeStockService.queryPage(query);
    }

}
