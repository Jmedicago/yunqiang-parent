package com.vgit.yunqiang.controller;

import com.vgit.yunqiang.controller.consts.ControllerConsts;
import com.vgit.yunqiang.pojo.FinQuarterly;
import com.vgit.yunqiang.service.FinQuarterlyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/" + FinQuarterlyController.DOMAIN)
public class FinQuarterlyController {

    public static final String DOMAIN = "quarterly";

    @Autowired
    public FinQuarterlyService finQuarterlyService;

    @RequestMapping(ControllerConsts.URL_LIST)
    @ResponseBody
    public List<FinQuarterly> getList() {
        return this.finQuarterlyService.getAll();
    }

}
