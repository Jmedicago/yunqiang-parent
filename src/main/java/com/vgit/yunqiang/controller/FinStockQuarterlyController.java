package com.vgit.yunqiang.controller;

import com.vgit.yunqiang.common.consts.bis.StockDailyTypeConsts;
import com.vgit.yunqiang.common.exception.BisException;
import com.vgit.yunqiang.common.query.StockDailyQuery;
import com.vgit.yunqiang.common.query.StockQuarterlyQuery;
import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.controller.consts.ControllerConsts;
import com.vgit.yunqiang.controller.utils.UserContext;
import com.vgit.yunqiang.pojo.FinStockDaily;
import com.vgit.yunqiang.pojo.FinStockQuarterly;
import com.vgit.yunqiang.pojo.SysUser;
import com.vgit.yunqiang.service.FinStockQuarterlyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("/" + FinStockQuarterlyController.DOMAIN)
public class FinStockQuarterlyController {

    public static final String DOMAIN = "stock-quarterly";

    @Autowired
    private FinStockQuarterlyService finStockQuarterlyService;

    @RequestMapping(ControllerConsts.URL_INDEX)
    public String index() {
        return DOMAIN + ControllerConsts.VIEW_INDEX;
    }

    @RequestMapping(ControllerConsts.URL_JSON)
    @ResponseBody
    public List<FinStockQuarterly> json(StockQuarterlyQuery query) {
        Long stockId = Long.valueOf(UserContext.getUser().getStockIds());
        query.setStockId(stockId);
        query.setType(StockDailyTypeConsts.CHILD_STOCK_DAILY);
        return this.finStockQuarterlyService.query(query);
    }

    @RequestMapping(ControllerConsts.URL_EDIT)
    public String edit(Long id, Model model) {
        if (id != null) {
            // 季度表报明细
            FinStockQuarterly finStockQuarterly = this.finStockQuarterlyService.get(id);
            model.addAttribute("finStockQuarterly", finStockQuarterly);
        }
        return DOMAIN + ControllerConsts.VIEW_EDIT;
    }

    /**
     * 创建当前季度报表
     *
     * @param stockQuarterly
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(ControllerConsts.URL_STORE)
    @ResponseBody
    public Ret store(FinStockQuarterly stockQuarterly) throws UnsupportedEncodingException {
        SysUser existUser = UserContext.getUser();
        if (StringUtils.isBlank(existUser.getStockIds())) {
            return Ret.me().setSuccess(false).setInfo("该用户没有门店管理权，无法新建门店季度表报！");
        }
        stockQuarterly.setStockId(Long.valueOf(existUser.getStockIds()));
        stockQuarterly.setUserId(existUser.getId());
        stockQuarterly.setType(StockDailyTypeConsts.CHILD_STOCK_DAILY);
        stockQuarterly.setCreateTime(System.currentTimeMillis());
        // 格式化
        if (stockQuarterly.getChanges() != null) {
            stockQuarterly.setChanges(stockQuarterly.getChanges() * 100);
        }
        if (stockQuarterly.getInventory() != null) {
            stockQuarterly.setInventory(stockQuarterly.getInventory() * 100);
        }
        if (stockQuarterly.getPl() != null) {
            stockQuarterly.setPl(stockQuarterly.getPl() * 100);
        }
        try {
            // 更新日报信息
            this.finStockQuarterlyService.saveOrUpdateQuarterly(stockQuarterly);
            return Ret.me().setData(stockQuarterly);
        } catch (BisException e) {
            return Ret.me().setSuccess(false).setInfo("当前季度报表已存在，无法创建新报表！");
        }
    }

}
