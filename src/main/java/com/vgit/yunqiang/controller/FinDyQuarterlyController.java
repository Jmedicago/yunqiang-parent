package com.vgit.yunqiang.controller;

import com.vgit.yunqiang.controller.consts.ControllerConsts;
import com.vgit.yunqiang.pojo.FinQuarterly;
import com.vgit.yunqiang.pojo.SysUser;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/" + FinDyQuarterlyController.DOMAIN)
public class FinDyQuarterlyController {

    public static final String DOMAIN = "dy-quarterly";

    @RequestMapping(ControllerConsts.URL_INDEX)
    public String index(Model model) {
        SysUser existUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("stockId", existUser.getStockIds());
        return DOMAIN + ControllerConsts.VIEW_INDEX;
    }

}
