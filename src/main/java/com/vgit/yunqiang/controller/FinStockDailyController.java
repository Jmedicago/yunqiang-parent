package com.vgit.yunqiang.controller;

import com.vgit.yunqiang.common.consts.bis.StockDailyTypeConsts;
import com.vgit.yunqiang.common.exception.BisException;
import com.vgit.yunqiang.common.query.StockDailyQuery;
import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.controller.consts.ControllerConsts;
import com.vgit.yunqiang.controller.utils.UserContext;
import com.vgit.yunqiang.pojo.FinStockDaily;
import com.vgit.yunqiang.pojo.SysUser;
import com.vgit.yunqiang.service.FinStockDailyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("/" + FinStockDailyController.DOMAIN)
public class FinStockDailyController {

    public static final String DOMAIN = "stock-daily";

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
        query.setType(StockDailyTypeConsts.CHILD_STOCK_DAILY);
        return this.finStockDailyService.query(query);
    }

    @RequestMapping(ControllerConsts.URL_EDIT)
    public String edit(Long id, Model model) {
        if (id != null) {
            // 日进出账明细
            FinStockDaily finStockDaily = this.finStockDailyService.get(id);
            model.addAttribute("finStockDaily", finStockDaily);
        }
        return DOMAIN + ControllerConsts.VIEW_EDIT;
    }

    /**
     * 创建日报
     *
     * @param finStockDaily
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(ControllerConsts.URL_STORE)
    @ResponseBody
    public Ret store(FinStockDaily finStockDaily) throws UnsupportedEncodingException {
        SysUser existUser = UserContext.getUser();
        if (StringUtils.isBlank(existUser.getStockIds())) {
            return Ret.me().setSuccess(false).setInfo("该用户没有门店管理权，无法新建门店日报！");
        }
        finStockDaily.setStockId(Long.valueOf(existUser.getStockIds()));
        finStockDaily.setCreateTime(System.currentTimeMillis());
        finStockDaily.setType(StockDailyTypeConsts.CHILD_STOCK_DAILY);
        // 格式化
        if (finStockDaily.getExpendTotal() != null) {
            finStockDaily.setExpendTotal(finStockDaily.getExpendTotal() * 100);
        }
        if (finStockDaily.getArrears() != null) {
            finStockDaily.setArrears(finStockDaily.getArrears() * 100);
        }
        if (finStockDaily.getIncome() != null) {
            finStockDaily.setIncome(finStockDaily.getIncome() * 100);
        }
        if (finStockDaily.getPurch() != null) {
            finStockDaily.setPurch(finStockDaily.getPurch() * 100);
        }
        if (finStockDaily.getSales() != null) {
            finStockDaily.setSales(finStockDaily.getSales() * 100);
        }
        try {
            // 更新日报信息
            this.finStockDailyService.saveOrUpdateDaily(finStockDaily);
            return Ret.me().setData(finStockDaily);
        } catch (BisException e) {
            return Ret.me().setSuccess(false).setInfo("今日日报已存在，无法新增日报！");
        }
    }

    @RequestMapping(ControllerConsts.URL_DELETE)
    @ResponseBody
    public Ret delete(Long id) {
        return this.finStockDailyService.deleteById(id);
    }
}
