package com.vgit.yunqiang.controller;

import com.vgit.yunqiang.common.query.ProductQuery;
import com.vgit.yunqiang.common.utils.Page;
import com.vgit.yunqiang.controller.consts.ControllerConsts;
import com.vgit.yunqiang.pojo.BisSku;
import com.vgit.yunqiang.service.BisSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/" + BisSkuController.DOMAIN)
public class BisSkuController {

    public static final String DOMAIN = "sku";

    @Autowired
    private BisSkuService bisSkuService;

    @RequestMapping(ControllerConsts.URL_JSON)
    @ResponseBody
    public Page<BisSku> json(ProductQuery query) {
        return this.bisSkuService.queryPage(query);
    }

}
