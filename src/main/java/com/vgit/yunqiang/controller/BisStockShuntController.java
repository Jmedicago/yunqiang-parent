package com.vgit.yunqiang.controller;

import com.vgit.yunqiang.common.exception.BisException;
import com.vgit.yunqiang.common.utils.IDUtils;
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
@RequestMapping("/" + BisStockShuntController.DOMAIN)
public class BisStockShuntController {
    public static final String DOMAIN = "stock-shunt";

    @Autowired
    private BisSkuService bisSkuService;

    @Autowired
    private BisStockShuntService bisStockShuntService;

    @RequestMapping(ControllerConsts.URL_INDEX)
    public String index() {
        return DOMAIN + ControllerConsts.VIEW_INDEX;
    }

    @RequestMapping(ControllerConsts.URL_EDIT)
    public String form(String opt, Long skuId, Long stockId, Model model) {
        BisStockShunt bisStockShunt = this.bisStockShuntService.getSkuStock(skuId, stockId);
        if (bisStockShunt == null) {
            bisStockShunt = new BisStockShunt();
            bisStockShunt.setSkuId(skuId);
            bisStockShunt.setStockId(stockId);
            bisStockShunt.setAmount(0);
        }

        bisStockShunt.setRemark(IDUtils.generateShuntSn(stockId));
        model.addAttribute("bisStockShunt", bisStockShunt);
        model.addAttribute("opt", opt);
        return DOMAIN + ControllerConsts.VIEW_EDIT;
    }

    @RequestMapping("/shunt")
    @ResponseBody
    public Ret shunt(String opt, BisStockShunt bisStockShunt, String remark) {
        if (bisStockShunt.getAmount() <= 0) {
            return Ret.me().setSuccess(false).setInfo("不能输入小于0之外的数字！");
        }
        try {
            this.bisStockShuntService.shunt(opt, bisStockShunt, remark);
        } catch (BisException e) {
            return Ret.me().setSuccess(false).setCode(e.getCode());
        }
        return Ret.me();
    }

}
