package com.vgit.yunqiang.controller;

import com.vgit.yunqiang.common.exception.BisException;
import com.vgit.yunqiang.common.query.DailyExpendQuery;
import com.vgit.yunqiang.common.utils.Page;
import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.controller.consts.ControllerConsts;
import com.vgit.yunqiang.controller.utils.UserContext;
import com.vgit.yunqiang.pojo.BisStock;
import com.vgit.yunqiang.pojo.FinDailyExpend;
import com.vgit.yunqiang.pojo.FinDyDaily;
import com.vgit.yunqiang.service.BisStockService;
import com.vgit.yunqiang.service.FinDailyExpendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/" + FinDailyExpendController.DOMAIN)
public class FinDailyExpendController {

    public final static String DOMAIN = "daily-expend";

    @Autowired
    private FinDailyExpendService finDailyExpendService;

    @Autowired
    private BisStockService bisStockService;

    @RequestMapping(ControllerConsts.URL_INDEX)
    public String index(String dailyCode, Model model) {
        model.addAttribute("dailyCode", dailyCode);
        return DOMAIN + ControllerConsts.VIEW_INDEX;
    }

    @RequestMapping(ControllerConsts.URL_JSON)
    @ResponseBody
    public Page<FinDailyExpend> json(DailyExpendQuery query) {
        return this.finDailyExpendService.queryPage(query);
    }

    @RequestMapping(ControllerConsts.URL_EDIT)
    public String edit(Long id, String dailyCode, Model model) {
        if (id != null) {
            FinDailyExpend dailyExpend = this.finDailyExpendService.get(id);
            model.addAttribute("dailyExpend", dailyExpend);
        } else {
            FinDailyExpend dailyExpend = new FinDailyExpend();
            dailyExpend.setDailyCode(dailyCode);
            model.addAttribute("dailyExpend", dailyExpend);
        }
        return DOMAIN + ControllerConsts.VIEW_EDIT;
    }

    @RequestMapping(ControllerConsts.URL_STORE)
    @ResponseBody
    public Ret store(FinDailyExpend dailyExpend) throws UnsupportedEncodingException {
        Long stockId = Long.valueOf(UserContext.getUser().getStockIds());
        BisStock stock = this.bisStockService.get(stockId);
        dailyExpend.setStockName(stock.getName());
        try {
            this.finDailyExpendService.saveOrUpdateStock(dailyExpend);
        } catch (BisException e) {
            return Ret.me().setSuccess(false).setInfo(e.getInfo());
        }
        return Ret.me();
    }

    @RequestMapping(ControllerConsts.URL_DELETE)
    @ResponseBody
    public Ret delete(Long id) {
        return this.finDailyExpendService.deleteById(id);
    }

}
