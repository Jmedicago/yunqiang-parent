package com.vgit.yunqiang.controller;

import com.vgit.yunqiang.common.consts.ICodes;
import com.vgit.yunqiang.common.query.PropertyOptionQuery;
import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.controller.consts.ControllerConsts;
import com.vgit.yunqiang.pojo.BisPropertyOption;
import com.vgit.yunqiang.service.BisPropertyOptionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/" + BisPropertyOptionController.DOMAIN)
public class BisPropertyOptionController {

    public static final String DOMAIN = "property-option";

    @Autowired
    private BisPropertyOptionService bisPropertyOptionService;

    @RequestMapping(ControllerConsts.URL_INDEX)
    public String index() {
        return DOMAIN + ControllerConsts.VIEW_INDEX;
    }

    @RequestMapping(ControllerConsts.URL_JSON)
    @ResponseBody
    public List<BisPropertyOption> json(PropertyOptionQuery query) {
        if (null == query.getProperty()) {
            return Collections.EMPTY_LIST;
        }
        // 属性选项集合
        List<BisPropertyOption> propertyOptions = this.bisPropertyOptionService.getPropertiesOptions(query);
        return propertyOptions;
    }

    @RequestMapping(ControllerConsts.URL_EDIT)
    public String edit(Long id, Model model) {
        return DOMAIN + ControllerConsts.VIEW_EDIT;
    }

    @RequestMapping(ControllerConsts.URL_STORE)
    @ResponseBody
    public Ret store(BisPropertyOption propertyOption) throws UnsupportedEncodingException {
        this.bisPropertyOptionService.saveOrUpdatePropertyOption(propertyOption);
        return Ret.me();
    }

    @RequestMapping(ControllerConsts.URL_DELETE)
    @ResponseBody
    public Ret delete(String id) {
        if(StringUtils.isBlank(id)){
            return Ret.me().setSuccess(false).setCode(ICodes.FAILED);
        }
        this.bisPropertyOptionService.deletePropertyOption(id);
        return Ret.me().setSuccess(true);
    }

}
