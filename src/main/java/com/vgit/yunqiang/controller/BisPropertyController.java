package com.vgit.yunqiang.controller;

import com.vgit.yunqiang.common.consts.ICodes;
import com.vgit.yunqiang.common.query.PropertyQuery;
import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.controller.consts.ControllerConsts;
import com.vgit.yunqiang.pojo.BisProperty;
import com.vgit.yunqiang.pojo.BisPropertyOption;
import com.vgit.yunqiang.service.BisPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/" + BisPropertyController.DOMAIN)
public class BisPropertyController {

    public static final String DOMAIN = "property";

    @Autowired
    private BisPropertyService bisPropertyService;

    @RequestMapping(ControllerConsts.URL_INDEX)
    public String index() {
        return DOMAIN + ControllerConsts.VIEW_INDEX;
    }

    @RequestMapping(ControllerConsts.URL_JSON)
    @ResponseBody
    public List<BisProperty> json(PropertyQuery query) {
        if (null == query.getProductType()) {
            return Collections.EMPTY_LIST;
        }
        // 属性集合
        List<BisProperty> properties = this.bisPropertyService.getProperties(query);
        return properties;
    }

    @RequestMapping(ControllerConsts.URL_EDIT)
    public String edit(Long id, Model model) {
        return DOMAIN + ControllerConsts.VIEW_EDIT;
    }

    @RequestMapping(ControllerConsts.URL_STORE)
    @ResponseBody
    public Ret store(BisProperty property) {
        this.bisPropertyService.saveOrUpdateProperty(property);
        return Ret.me();
    }

    @RequestMapping("/getOptions")
    @ResponseBody
    public List<BisPropertyOption> getOptions(Long id){
        return this.bisPropertyService.getOptions(id);
    }

    @RequestMapping(ControllerConsts.URL_DELETE)
    @ResponseBody
    public Ret delete(Long id) {
        this.bisPropertyService.delete(id);
        return Ret.me().setSuccess(true).setCode(ICodes.SUCCESS);
    }

}
