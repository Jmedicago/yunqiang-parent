package com.vgit.yunqiang.controller;

import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.controller.consts.ControllerConsts;
import com.vgit.yunqiang.pojo.BisSku;
import com.vgit.yunqiang.service.BisSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/" + BisInventoryController.DOMAIN)
public class BisInventoryController {
    public static final String DOMAIN = "inventory";

    @Autowired
    private BisSkuService bisSkuService;

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

}
