package com.vgit.yunqiang.controller;

import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.controller.consts.ControllerConsts;
import com.vgit.yunqiang.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping(TestController.DOMAIN)
public class TestController {

    public static final String DOMAIN = "test";

    @Autowired
    private TestService testService;

    @RequestMapping(ControllerConsts.URL_INDEX)
    public String index() {
        return DOMAIN + ControllerConsts.VIEW_INDEX;
    }

    @RequestMapping(ControllerConsts.URL_EDIT)
    public String edit(HttpServletRequest request, Model model) {
        String content = "";
        try {
            content = this.testService.reader(request);
            model.addAttribute("content", content);
            return DOMAIN + ControllerConsts.VIEW_EDIT;
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", e.getMessage());
            return "/test/error";
        }

    }

    @RequestMapping("/store")
    @ResponseBody
    public Ret store(String content, HttpServletRequest request) {
        try {
            this.testService.writer(content, request);
            return Ret.me();
        } catch (IOException e) {
            e.printStackTrace();
            return Ret.me().setSuccess(false);
        }
    }

}
