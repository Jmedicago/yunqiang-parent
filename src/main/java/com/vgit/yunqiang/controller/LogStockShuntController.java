package com.vgit.yunqiang.controller;

import com.vgit.yunqiang.common.query.LogStockShuntQuery;
import com.vgit.yunqiang.common.utils.Page;
import com.vgit.yunqiang.controller.consts.ControllerConsts;
import com.vgit.yunqiang.pojo.LogStockShunt;
import com.vgit.yunqiang.service.LogStockShuntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/" + LogStockShuntController.DOMAIN)
public class LogStockShuntController {

    public static final String DOMAIN = "stock-shunt-log";

    @Autowired
    private LogStockShuntService logStockShuntService;

    @RequestMapping(ControllerConsts.URL_INDEX)
    public String index() {
        return DOMAIN + ControllerConsts.VIEW_INDEX;
    }

    @RequestMapping(ControllerConsts.URL_JSON)
    @ResponseBody
    public Page<LogStockShunt> json(LogStockShuntQuery query) {
        return this.logStockShuntService.queryPage(query);
    }

}
