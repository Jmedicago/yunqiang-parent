package com.vgit.yunqiang.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.vgit.yunqiang.common.consts.ICodes;
import com.vgit.yunqiang.common.utils.Ret;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vgit.yunqiang.controller.consts.ControllerConsts;
import com.vgit.yunqiang.pojo.BisStock;
import com.vgit.yunqiang.service.BisStockService;

@Controller
@RequestMapping("/" + BisStoreController.DOMAIN)
public class BisStoreController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BisStoreController.class);

    public static final String DOMAIN = "stock";

    @Autowired
    private BisStockService bisStockService;

    @RequestMapping(ControllerConsts.URL_INDEX)
    public String index() {
        return DOMAIN + ControllerConsts.VIEW_INDEX;
    }

    @RequiresRoles(value = {"admin", "stock"}, logical = Logical.OR)
    @RequestMapping(ControllerConsts.URL_JSON)
    @ResponseBody
    public List<BisStock> json() {
        return this.bisStockService.treegrid(BisStockService.ROOT);
    }

    @RequestMapping(ControllerConsts.URL_EDIT)
    public String edit(Long id, Model model) {
        if (id != null) {
            // 仓库信息
            BisStock bisStock = this.bisStockService.get(id);
            model.addAttribute("bisStock", bisStock);
        }
        return DOMAIN + ControllerConsts.VIEW_EDIT;
    }

    /**
     * 创建仓库
     *
     * @param stock
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequiresRoles(value = {"admin", "stock"}, logical = Logical.OR)
    @RequestMapping(ControllerConsts.URL_STORE)
    @ResponseBody
    public Ret store(BisStock stock) throws UnsupportedEncodingException {
        // 更新用户信息
        this.bisStockService.saveOrUpdateStock(stock);
        return Ret.me();
    }

    @RequiresRoles(value = {"admin", "stock"}, logical = Logical.OR)
    @RequestMapping(ControllerConsts.URL_DELETE)
    @ResponseBody
    public Ret delete(Long id) {
        this.bisStockService.delete(id);
        return Ret.me().setCode(ICodes.SUCCESS);
    }

}
