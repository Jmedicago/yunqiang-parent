package com.vgit.yunqiang.controller;

import com.vgit.yunqiang.controller.consts.ControllerConsts;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(RpDSalesController.DOMAIN)
public class RpDSalesController {

    public static final String DOMAIN = "rp-d-sales";

    @RequestMapping(ControllerConsts.URL_INDEX)
    public String index() {
        return DOMAIN + ControllerConsts.VIEW_INDEX;
    }

}
