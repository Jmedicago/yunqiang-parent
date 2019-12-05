package com.vgit.yunqiang.controller;

import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.controller.consts.ControllerConsts;
import com.vgit.yunqiang.pojo.BisSku;
import com.vgit.yunqiang.pojo.BisStockShunt;
import com.vgit.yunqiang.service.BisSkuService;
import com.vgit.yunqiang.service.BisStockShuntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/" + BisInventoryController.DOMAIN)
public class BisInventoryController {
    public static final String DOMAIN = "inventory";

    @Autowired
    private BisSkuService bisSkuService;

    @Autowired
    private BisStockShuntService stockShuntService;

    @RequestMapping(ControllerConsts.URL_INDEX)
    public String index() {
        return DOMAIN + ControllerConsts.VIEW_INDEX;
    }

    @RequestMapping("/addAmount")
    @ResponseBody
    public Ret addAmount(BisSku sku) {
        this.bisSkuService.inbound(sku.getId(), sku.getAvailableStock());
        return Ret.me();
    }

    @RequestMapping("/removeAmount")
    @ResponseBody
    public Ret removeAmount(BisSku sku) {
        this.bisSkuService.inbound(sku.getId(), sku.getAvailableStock());
        return Ret.me();
    }

    @RequestMapping(ControllerConsts.URL_EDIT)
    public String edit(Long skuId, Long stockId, Model model) {
        BisStockShunt stockShunt = new BisStockShunt();
        stockShunt.setSkuId(skuId);
        stockShunt.setStockId(stockId);
        model.addAttribute("stockShunt", stockShunt);
        return DOMAIN + ControllerConsts.VIEW_EDIT;
    }

    @RequestMapping(ControllerConsts.URL_STORE)
    @ResponseBody
    public Ret store(BisStockShunt bisStockShunt) {
        this.stockShuntService.checkIn(bisStockShunt.getSkuId(), bisStockShunt.getAmount());
        return Ret.me();
    }

}
