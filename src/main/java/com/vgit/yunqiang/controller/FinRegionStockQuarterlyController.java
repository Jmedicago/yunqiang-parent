package com.vgit.yunqiang.controller;

import com.vgit.yunqiang.common.consts.bis.StockDailyTypeConsts;
import com.vgit.yunqiang.common.query.StockDailyQuery;
import com.vgit.yunqiang.controller.consts.ControllerConsts;
import com.vgit.yunqiang.controller.utils.UserContext;
import com.vgit.yunqiang.pojo.FinStockDaily;
import com.vgit.yunqiang.service.FinStockDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/" + FinRegionStockQuarterlyController.DOMAIN)
public class FinRegionStockQuarterlyController {

    public static final String DOMAIN = "region-stock-quarterly";

    @Autowired
    private FinStockDailyService finStockDailyService;

    @RequestMapping(ControllerConsts.URL_INDEX)
    public String index() {
        return DOMAIN + ControllerConsts.VIEW_INDEX;
    }

    @RequestMapping(ControllerConsts.URL_JSON)
    @ResponseBody
    public List<FinStockDaily> json(StockDailyQuery query) {
        Long stockId = Long.valueOf(UserContext.getUser().getStockIds());
        query.setStockId(stockId);
        query.setType(StockDailyTypeConsts.REGION_STOCK_DAILY);
        return this.finStockDailyService.query(query);
    }

}
