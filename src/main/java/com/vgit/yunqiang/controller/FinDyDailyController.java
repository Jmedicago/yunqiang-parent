package com.vgit.yunqiang.controller;

import com.vgit.yunqiang.common.exception.BisException;
import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.common.utils.TimeUtils;
import com.vgit.yunqiang.controller.consts.ControllerConsts;
import com.vgit.yunqiang.pojo.FinDyDaily;
import com.vgit.yunqiang.pojo.SysUser;
import com.vgit.yunqiang.service.FinDyDailyService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/" + FinDyDailyController.DOMAIN)
public class FinDyDailyController {

    public static final String DOMAIN = "dy-daily";

    @Autowired
    private FinDyDailyService finDyDailyService;

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
    public Ret store(FinDyDaily finDyDaily) {
        SysUser existUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        finDyDaily.setStockId(Long.valueOf(existUser.getStockIds()));
        finDyDaily.setUserId(existUser.getId());
        finDyDaily.setDate(TimeUtils.strToDate(finDyDaily.getDateFormatter()));

        try {
            this.finDyDailyService.saveOrUpdateDaily(finDyDaily);
        } catch (BisException e) {
            return Ret.me().setSuccess(false).setInfo(e.getInfo());
        }
        return Ret.me();
    }

}
