package com.vgit.yunqiang.controller;

import com.vgit.yunqiang.common.query.ExtendItemQuery;
import com.vgit.yunqiang.common.utils.Page;
import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.controller.consts.ControllerConsts;
import com.vgit.yunqiang.pojo.FinExpendItem;
import com.vgit.yunqiang.service.FinExpendItemService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("/" + FinExpendItemController.DOMAIN)
public class FinExpendItemController {

    public static final String DOMAIN = "expend-item";

    @Autowired
    private FinExpendItemService finExpendItemService;

    @RequestMapping(ControllerConsts.URL_INDEX)
    public String index() {
        return DOMAIN + ControllerConsts.VIEW_INDEX;
    }

    @RequestMapping(ControllerConsts.URL_JSON)
    @ResponseBody
    public Page<FinExpendItem> json(ExtendItemQuery query) {
        return this.finExpendItemService.queryPage(query);
    }

    @RequestMapping(ControllerConsts.URL_LIST)
    @ResponseBody
    public List<FinExpendItem> getAll() {
        ExtendItemQuery query = new ExtendItemQuery();
        query.setRows(1000);
        return this.finExpendItemService.query(query);
    }

    @RequestMapping(ControllerConsts.URL_EDIT)
    public String edit(Long id, Model model) {
        if (id != null) {
            // 支出项
            FinExpendItem finExpendItem = this.finExpendItemService.get(id);
            model.addAttribute("finExpendItem", finExpendItem);
        }
        return DOMAIN + ControllerConsts.VIEW_EDIT;
    }

    /**
     * 创建支付项
     *
     * @param expendItem
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequiresRoles(value = {"admin", "expend"}, logical = Logical.OR)
    @RequestMapping(ControllerConsts.URL_STORE)
    @ResponseBody
    public Ret store(FinExpendItem expendItem) throws UnsupportedEncodingException {
        // 更新支付项信息
        this.finExpendItemService.saveOrUpdateExpendItem(expendItem);
        return Ret.me();
    }

    @RequiresRoles(value = {"admin", "expend"}, logical = Logical.OR)
    @RequestMapping(ControllerConsts.URL_DELETE)
    @ResponseBody
    public Ret delete(Long id) {
        return this.finExpendItemService.deleteById(id);
    }

    @RequestMapping("/info")
    @ResponseBody
    public FinExpendItem get(Long id) {
        return this.finExpendItemService.get(id);
    }

}
