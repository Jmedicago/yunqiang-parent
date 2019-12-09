package com.vgit.yunqiang.controller;

import com.vgit.yunqiang.common.consts.bis.TradeStockStateConsts;
import com.vgit.yunqiang.common.consts.msg.BisTradeStockMsgConsts;
import com.vgit.yunqiang.common.query.TradeStockQuery;
import com.vgit.yunqiang.common.utils.Page;
import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.controller.consts.ControllerConsts;
import com.vgit.yunqiang.pojo.BisTradeStock;
import com.vgit.yunqiang.service.BisTradeStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 生产性采购
 */
@Controller
@RequestMapping(ProductionBuyerController.DOMAIN)
public class ProductionBuyerController {

    public final static String DOMAIN = "production-buyer";

    @Autowired
    public BisTradeStockService bisTradeStockService;

    private static final Integer TRADE_TYPE = 1;

    @RequestMapping(ControllerConsts.URL_INDEX)
    public String index() {
        return DOMAIN + ControllerConsts.VIEW_INDEX;
    }

    @RequestMapping(ControllerConsts.URL_JSON)
    @ResponseBody
    public Page<BisTradeStock> json(TradeStockQuery query) {
        query.setType(TRADE_TYPE); // 生产性采购
        return this.bisTradeStockService.queryPage(query);
    }


    @RequestMapping("/pr")
    public String pr() {
        return DOMAIN + "/pr";
    }

    @RequestMapping("/pr-upload")
    @ResponseBody
    public Ret uploadPrTrade(MultipartFile uploadFile) {
        return this.bisTradeStockService.uploadPrTrade(TRADE_TYPE, uploadFile);
    }


    @RequestMapping("/po")
    public String poTrade(Long id, Model model) {
        BisTradeStock bisTradeStock = this.bisTradeStockService.get(id);
        model.addAttribute("bisTradeStock", bisTradeStock);
        return DOMAIN + "/po";
    }

    @RequestMapping("/po-upload")
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


    @RequestMapping("/finish")
    @ResponseBody
    public Ret finish(Long id) {
        return this.bisTradeStockService.finishTrade(id);
    }


}
