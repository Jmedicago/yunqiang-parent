package com.vgit.yunqiang.controller;

import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.controller.consts.ControllerConsts;
import com.vgit.yunqiang.pojo.FinQDyInventory;
import com.vgit.yunqiang.pojo.FinQuarterly;
import com.vgit.yunqiang.pojo.SysUser;
import com.vgit.yunqiang.service.FinQDyInventoryService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/" + FinDyQuarterlyController.DOMAIN)
public class FinDyQuarterlyController {

    public static final String DOMAIN = "dy-quarterly";

    @Autowired
    private FinQDyInventoryService finQDyInventoryService;

    @RequestMapping(ControllerConsts.URL_INDEX)
    public String index(Model model) {
        SysUser existUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("stockId", existUser.getStockIds());
        return DOMAIN + ControllerConsts.VIEW_INDEX;
    }

    @RequestMapping(ControllerConsts.URL_EDIT)
    public String edit(Long id) {

        return DOMAIN + ControllerConsts.VIEW_EDIT;
    }

    @RequestMapping(ControllerConsts.URL_STORE)
    @ResponseBody
    public Ret store(FinQDyInventory qDyInventory) {
        SysUser existUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        qDyInventory.setStockId(Long.valueOf(existUser.getStockIds()));

        this.finQDyInventoryService.saveOrUpdateQuarterly(qDyInventory);
        return Ret.me();
    }

}
