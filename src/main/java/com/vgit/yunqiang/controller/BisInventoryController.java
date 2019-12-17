package com.vgit.yunqiang.controller;

import com.vgit.yunqiang.common.exception.BisException;
import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.controller.consts.ControllerConsts;
import com.vgit.yunqiang.pojo.BisSku;
import com.vgit.yunqiang.pojo.BisStockShunt;
import com.vgit.yunqiang.service.BisSkuService;
import com.vgit.yunqiang.service.BisStockShuntService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

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
    public String edit(String opt, Long skuId, Long stockId, Model model) {
        BisStockShunt stockShunt = new BisStockShunt();
        stockShunt.setSkuId(skuId);
        stockShunt.setStockId(stockId);
        model.addAttribute("stockShunt", stockShunt);
        model.addAttribute("opt", opt);
        return DOMAIN + ControllerConsts.VIEW_EDIT;
    }

    @RequestMapping(ControllerConsts.URL_STORE)
    @ResponseBody
    public Ret store(String opt, BisStockShunt bisStockShunt) {
        if (bisStockShunt.getAmount() < 0) {
            return Ret.me().setSuccess(false).setInfo("不能输入小于0之外的数字！");
        }
        try {
            this.stockShuntService.checkIn(opt, bisStockShunt.getSkuId(), bisStockShunt.getAmount());
        } catch (BisException e) {
            return Ret.me().setSuccess(false).setCode(e.getCode());
        }

        return Ret.me();
    }

    /**
     * Excel 批量导出
     *
     * @param fileName
     * @param request
     * @return
     */
    @RequestMapping("/export")
    public String export(String fileName, HttpServletRequest request) throws Exception {
        if (StringUtils.isNotBlank(fileName)) {
            fileName = new String(fileName.getBytes("ISO-8859-1"), "UTF-8");
        }

        return "redirect:" + this.stockShuntService.export(fileName, request);
    }

}
