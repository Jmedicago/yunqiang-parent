package com.vgit.yunqiang.controller;

import com.vgit.yunqiang.common.exception.BisException;
import com.vgit.yunqiang.common.query.OrderQuery;
import com.vgit.yunqiang.common.utils.Page;
import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.common.utils.TimeUtils;
import com.vgit.yunqiang.controller.consts.ControllerConsts;
import com.vgit.yunqiang.pojo.BisOrder;
import com.vgit.yunqiang.service.BisOrderService;
import com.vgit.yunqiang.service.BisStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/" + BisOrderVerifyController.DOMAIN)
public class BisOrderVerifyController {

    public static final String DOMAIN = "order-verify";

    @Autowired
    private BisOrderService bisOrderService;

    @Autowired
    private BisStockService bisStockService;

    @RequestMapping(ControllerConsts.URL_INDEX)
    public String index() {
        return DOMAIN + ControllerConsts.VIEW_INDEX;
    }

    @RequestMapping(ControllerConsts.URL_JSON)
    @ResponseBody
    public Page<BisOrder> json(OrderQuery query) {
        return this.bisOrderService.queryPage(query);
    }

    @RequestMapping("/sendShip")
    @ResponseBody
    public Ret sendShip(Long orderId){
        return this.bisOrderService.sendShip(orderId);
    }

    @RequestMapping("/check-print")
    @ResponseBody
    public Ret checkPrint(Long id) {
        return this.bisOrderService.checkPrint(id);
    }

    @RequestMapping("/print")
    public String print(Long id, Model model) throws BisException {
        BisOrder printOrder = this.bisOrderService.print(id);
        model.addAttribute("printOrder", printOrder);
        model.addAttribute("confirmTimeFormatter", TimeUtils.dateFormat(new Date(printOrder.getConfirmTime())));
        model.addAttribute("stockFormatter", this.bisStockService.get(printOrder.getStockId()).getName());
        return DOMAIN + "/print";
    }

}
