package com.vgit.yunqiang.controller;

import com.vgit.yunqiang.common.consts.bis.TradeStockStateConsts;
import com.vgit.yunqiang.common.consts.msg.BisTradeStockMsgConsts;
import com.vgit.yunqiang.common.utils.Ret;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vgit.yunqiang.common.query.TradeStockQuery;
import com.vgit.yunqiang.common.utils.Page;
import com.vgit.yunqiang.controller.consts.ControllerConsts;
import com.vgit.yunqiang.pojo.BisTradeStock;
import com.vgit.yunqiang.service.BisTradeStockService;
import org.springframework.web.multipart.MultipartFile;

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

    @RequestMapping("/pr-trade")
    public String prTrade() {
        return DOMAIN + "/pr";
    }

    @RequestMapping("/upload-pr-trade")
    @ResponseBody
    public Ret uploadPrTrade(Integer type, MultipartFile uploadFile) {
	    return this.bisTradeStockService.uploadPrTrade(type, uploadFile);
    }

    @RequestMapping("/po-trade")
    public String poTrade(Long id, Model model) {
        BisTradeStock bisTradeStock = this.bisTradeStockService.get(id);
        model.addAttribute("bisTradeStock", bisTradeStock);
	    return DOMAIN + "/po";
    }

    @RequestMapping("/upload-po-trade")
    @ResponseBody
    public Ret uploadPoTrade(Long id, MultipartFile uploadFile) {
	    // 检查采购状态
        BisTradeStock tradeStock = this.bisTradeStockService.get(id);
        if (tradeStock.getStatus() == TradeStockStateConsts.SHIP_FINISH_TAKE) {
            return Ret.me().setSuccess(false).setCode(BisTradeStockMsgConsts.TRADE_FINISHED);
        }

	    if (id == null) {
	        return Ret.me().setSuccess(false).setCode(BisTradeStockMsgConsts.TRADE_NULL);
        }
        return this.bisTradeStockService.uploadPoTrade(id, uploadFile);
    }

    @RequestMapping("/finish-trade")
    @ResponseBody
    public Ret finishTrade(Long id) {
        return this.bisTradeStockService.finishTrade(id);
    }

}
