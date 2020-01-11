package com.vgit.yunqiang.controller;

import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.controller.consts.ControllerConsts;
import com.vgit.yunqiang.pojo.Form;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(TsFormController.DOMAIN)
public class TsFormController {

    public static final String DOMAIN = "ts-from";

    @RequestMapping(ControllerConsts.URL_INDEX)
    public String index() {
        return DOMAIN + ControllerConsts.VIEW_INDEX;
    }

    @RequestMapping(ControllerConsts.URL_STORE)
    @ResponseBody
    public Ret store(Form form) {
        return Ret.me().setData(form);
    }

}
