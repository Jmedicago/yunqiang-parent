package com.vgit.yunqiang.controller;

import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.pojo.SysUser;
import com.vgit.yunqiang.service.BisCartService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/" + BisCartController.DOMAIN)
public class BisCartController {

    public static final String DOMAIN = "cart";

    @Autowired
    private BisCartService bisCartService;

    /**
     * 添加SKU到购物车
     *
     * @param skuId  skuId
     * @param number 数量
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public Ret cartAdd(Long skuId, Integer number) {
        SysUser existUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        this.bisCartService.add(existUser.getId(), skuId, number);
        return Ret.me();
    }

}
