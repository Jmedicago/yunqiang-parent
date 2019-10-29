package com.vgit.yunqiang.controller;

import com.vgit.yunqiang.common.query.OrderQuery;
import com.vgit.yunqiang.common.utils.Page;
import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.controller.consts.ControllerConsts;
import com.vgit.yunqiang.pojo.BisOrder;
import com.vgit.yunqiang.service.BisOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/" + BisOrderVerifyController.DOMAIN)
public class BisOrderVerifyController {

    public static final String DOMAIN = "order-verify";

    @Autowired
    private BisOrderService bisOrderService;

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

}
