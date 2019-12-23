package com.vgit.yunqiang.controller;

import com.vgit.yunqiang.common.query.ProductModelQuery;
import com.vgit.yunqiang.common.query.SkuQuery;
import com.vgit.yunqiang.common.utils.Page;
import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.common.utils.StrUtils;
import com.vgit.yunqiang.controller.consts.ControllerConsts;
import com.vgit.yunqiang.model.ProductModel;
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
    public Page<BisSku> json(SkuQuery query) {
        return this.bisSkuService.queryPage(query);
    }

    @RequestMapping("/es")
    @ResponseBody
    public Page<ProductModel> es(ProductModelQuery query) {
        return this.bisSkuService.es(query);
    }

    @RequestMapping(ControllerConsts.URL_DELETE)
    @ResponseBody
    public Ret delete(String id) {
        Long[] ids = StrUtils.splitToLong(id);
        for (Long cid : ids) {
            //this.bisSkuService.delete(cid);
            this.bisSkuService.falseDel(cid);
        }
        return Ret.me();
    }

}
