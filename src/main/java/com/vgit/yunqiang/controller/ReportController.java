package com.vgit.yunqiang.controller;

import com.vgit.yunqiang.common.query.ReportQuery;
import com.vgit.yunqiang.controller.consts.ControllerConsts;
import com.vgit.yunqiang.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Hashtable;

@Controller
@RequestMapping("/" + ReportController.DOMAIN)
public class ReportController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportController.class);

    public static final String DOMAIN = "report";

    @Autowired
    private ReportService reportService;

    @RequestMapping(ControllerConsts.URL_INDEX)
    public String index(ReportQuery query, Model model) {
        Hashtable<String, Object> report = this.reportService.report(query);
        model.addAttribute("report", report);
        LOGGER.info("报表：{}", report);
        return DOMAIN + "/" + query.getRn() + ControllerConsts.VIEW_INDEX;
    }

}
