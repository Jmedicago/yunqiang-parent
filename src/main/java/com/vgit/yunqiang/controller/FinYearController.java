package com.vgit.yunqiang.controller;

import com.vgit.yunqiang.controller.consts.ControllerConsts;
import com.vgit.yunqiang.pojo.FinYear;
import com.vgit.yunqiang.service.FinYearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/" + FinYearController.DOMAIN)
public class FinYearController {

    public static final String DOMAIN = "year";

    @Autowired
    private FinYearService finYearService;

    @RequestMapping(ControllerConsts.URL_LIST)
    @ResponseBody
    public List<FinYear> list() {
        return finYearService.getAll();
    }

}
