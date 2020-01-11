package com.vgit.yunqiang.controller;

import com.vgit.yunqiang.controller.consts.ControllerConsts;
import com.vgit.yunqiang.pojo.FinMonth;
import com.vgit.yunqiang.service.FinMonthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(FinMonthController.DOMAIN)
public class FinMonthController {

    public static final String DOMAIN = "/month";

    @Autowired
    private FinMonthService finMonthService;

    @RequestMapping(ControllerConsts.URL_INDEX)
    public String index() {
        return DOMAIN + ControllerConsts.VIEW_INDEX;
    }

    @RequestMapping(ControllerConsts.URL_LIST)
    @ResponseBody
    public List<FinMonth> list() {
        return this.finMonthService.getAll();
    }

}
