package com.vgit.yunqiang.controller;

import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.controller.consts.ControllerConsts;
import com.vgit.yunqiang.pojo.BisSku;
import com.vgit.yunqiang.service.BisSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/" + BisStockShuntController.DOMAIN)
public class BisStockShuntController {
    public static final String DOMAIN = "stock-shunt";

    @Autowired
    private BisSkuService bisSkuService;

    @RequestMapping(ControllerConsts.URL_INDEX)
    public String index() {
        return DOMAIN + ControllerConsts.VIEW_INDEX;
    }

    @RequestMapping(ControllerConsts.URL_EDIT)
    public String form(Long id, Model model) {
        BisSku bisSku = this.bisSkuService.get(id);
        model.addAttribute("bisSku", bisSku);
        return DOMAIN + ControllerConsts.VIEW_EDIT;
    }

    @RequestMapping("/shunt")
    @ResponseBody
    public Ret shunt(BisSku sku) {
        if(sku.getFrozenStock() > sku.getAvailableStock()) {
            return Ret.me().setSuccess(false).setInfo("总仓库存不足");
        }
        sku.setAvailableStock(sku.getAvailableStock() - sku.getFrozenStock());
        this.bisSkuService.updatePart(sku);
        return Ret.me();
    }

}
