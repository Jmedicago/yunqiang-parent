package com.vgit.yunqiang.controller;

import com.vgit.yunqiang.common.exception.BisException;
import com.vgit.yunqiang.common.query.StockDailyExpendItemQuery;
import com.vgit.yunqiang.common.query.base.BaseQuery;
import com.vgit.yunqiang.common.utils.Page;
import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.controller.consts.ControllerConsts;
import com.vgit.yunqiang.pojo.FinStockDailyExpendItem;
import com.vgit.yunqiang.service.FinStockDailyExpendItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/" + FinStockDailyExpendItemController.DOMAIN)
public class FinStockDailyExpendItemController {

    public static final String DOMAIN = "stock-daily-expend-item";

    @Autowired
    private FinStockDailyExpendItemService finStockDailyExpendItemService;

    @RequestMapping(ControllerConsts.URL_JSON)
    @ResponseBody
    public Page<FinStockDailyExpendItem> json(StockDailyExpendItemQuery query) {
        Page<FinStockDailyExpendItem> page = this.finStockDailyExpendItemService.queryPage(query);
        if (page != null && page.getRows().size() > 0) {
            Map<String, Object> footer = new HashMap<String, Object>();
            double expendTotal = 0.0;
            for (FinStockDailyExpendItem stockDailyExpendItem : page.getRows()) {
                expendTotal += stockDailyExpendItem.getAmount();
            }
            footer.put("expendTotal", expendTotal);
            page.setFooter(footer);
        }
        return page;
    }

    @RequestMapping(ControllerConsts.URL_EDIT)
    public String edit(Long id, Model model) {
        if (id != null) {
            // 支出项明细
            FinStockDailyExpendItem finStockDailyExpendItem = this.finStockDailyExpendItemService.get(id);
            model.addAttribute("finStockDailyExpendItem", finStockDailyExpendItem);
        }
        return DOMAIN + ControllerConsts.VIEW_EDIT;
    }

    /**
     * 创建支付项明细
     *
     * @param stockDailyExpendItem
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(ControllerConsts.URL_STORE)
    @ResponseBody
    public Ret store(FinStockDailyExpendItem stockDailyExpendItem) throws UnsupportedEncodingException {
        stockDailyExpendItem.setAmount(stockDailyExpendItem.getAmount() * 100);
        try {
            // 更新支付项明细信息
            this.finStockDailyExpendItemService.saveOrUpdateStockDailyExpendItem(stockDailyExpendItem);
        } catch (BisException e) {
            return Ret.me().setSuccess(false).setInfo(e.getInfo());
        }
        return Ret.me();
    }

    @RequestMapping(ControllerConsts.URL_DELETE)
    @ResponseBody
    public Ret delete(Long id) {
        this.finStockDailyExpendItemService.delete(id);
        return Ret.me();
    }

    @RequestMapping("/region")
    @ResponseBody
    public Page<FinStockDailyExpendItem> getRegionExpendItems(Long stockId, Long dateTime) {
        return this.finStockDailyExpendItemService.getExpendItemsByStockId(stockId, dateTime);
    }

}
