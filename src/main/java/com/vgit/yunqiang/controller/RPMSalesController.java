package com.vgit.yunqiang.controller;

import com.vgit.yunqiang.controller.consts.ControllerConsts;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(RPMSalesController.DOMAIN)
public class RPMSalesController {

    public static final String DOMAIN = "rp-m-sales";

    @RequestMapping(ControllerConsts.URL_INDEX)
    public String index() {
        return DOMAIN + ControllerConsts.VIEW_INDEX;
    }

}
