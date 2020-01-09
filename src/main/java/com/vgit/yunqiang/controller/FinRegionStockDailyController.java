package com.vgit.yunqiang.controller;

import com.vgit.yunqiang.common.consts.bis.StockDailyTypeConsts;
import com.vgit.yunqiang.common.exception.BisException;
import com.vgit.yunqiang.common.query.StockDailyQuery;
import com.vgit.yunqiang.common.utils.Page;
import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.controller.consts.ControllerConsts;
import com.vgit.yunqiang.controller.utils.UserContext;
import com.vgit.yunqiang.pojo.FinDyDaily;
import com.vgit.yunqiang.pojo.FinDzDaily;
import com.vgit.yunqiang.pojo.FinStockDaily;
import com.vgit.yunqiang.pojo.SysUser;
import com.vgit.yunqiang.service.FinDzDailyService;
import com.vgit.yunqiang.service.FinStockDailyService;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("/" + FinRegionStockDailyController.DOMAIN)
public class FinRegionStockDailyController {

    public static final String DOMAIN = "region-stock-daily";

    @Autowired
    private FinStockDailyService finStockDailyService;

    @Autowired
    private FinDzDailyService finDzDailyService;

   /* @RequestMapping(ControllerConsts.URL_INDEX)
    public String index() {
        return DOMAIN + ControllerConsts.VIEW_INDEX;
    }*/

    @RequestMapping(ControllerConsts.URL_INDEX)
    public String index(Model model) {
        Long stockId = Long.valueOf(UserContext.getUser().getStockIds());
        model.addAttribute("stockId", stockId);
        return DOMAIN + ControllerConsts.VIEW_INDEX;
    }

    /*@RequestMapping(ControllerConsts.URL_JSON)
    @ResponseBody
    public List<FinStockDaily> json(StockDailyQuery query) {
        Long stockId = Long.valueOf(UserContext.getUser().getStockIds());
        query.setStockId(stockId);
        query.setType(StockDailyTypeConsts.REGION_STOCK_DAILY);
        return this.finStockDailyService.query(query);
    }*/

    @RequestMapping(ControllerConsts.URL_JSON)
    @ResponseBody
    public List<FinDzDaily> json(StockDailyQuery query) {
        Long stockId = Long.valueOf(UserContext.getUser().getStockIds());
        query.setStockId(stockId);
        return this.finDzDailyService.query(query);
    }

    /*@RequestMapping(ControllerConsts.URL_EDIT)
    public String edit(Long id, Model model) {
        if (id != null) {
            // 日进出账明细
            FinStockDaily finRegionStockDaily = this.finStockDailyService.get(id);
            model.addAttribute("finRegionStockDaily", finRegionStockDaily);
        }
        return DOMAIN + ControllerConsts.VIEW_EDIT;
    }*/

    @RequestMapping(ControllerConsts.URL_EDIT)
    public String edit(Long id, Model model) {
        if (id != null) {
            // 日进出账明细
            FinDzDaily dzDaily = this.finDzDailyService.get(id);
            model.addAttribute("dzDaily", dzDaily);
        }
        return DOMAIN + ControllerConsts.VIEW_EDIT;
    }

    /**
     * 创建区域日报
     *
     *
     * @return
     * @throws UnsupportedEncodingException
     *//*
    @RequestMapping(ControllerConsts.URL_STORE)
    @ResponseBody
    public Ret store(FinStockDaily regionStockDaily) throws UnsupportedEncodingException {
        SysUser existUser = UserContext.getUser();
        if (StringUtils.isBlank(existUser.getStockIds())) {
            return Ret.me().setSuccess(false).setInfo("该用户没有区域门店管理权，无法新建门店日报！");
        }
        regionStockDaily.setStockId(Long.valueOf(existUser.getStockIds()));
        regionStockDaily.setUserId(existUser.getId());
        regionStockDaily.setType(StockDailyTypeConsts.REGION_STOCK_DAILY);
        // 格式化
        if (regionStockDaily.getSafe() != null) {
            regionStockDaily.setSafe(regionStockDaily.getSafe() * 100);
        }
        if (regionStockDaily.getDeposit() != null) {
            regionStockDaily.setDeposit(regionStockDaily.getDeposit() * 100);
        }
        if (regionStockDaily.getExpendTotal() != null) {
            regionStockDaily.setExpendTotal(regionStockDaily.getExpendTotal() * 100);
        }
        try {
            // 更新日报信息
            this.finStockDailyService.saveOrUpdateDaily(regionStockDaily);
            return Ret.me().setData(regionStockDaily);
        } catch (BisException e) {
            return Ret.me().setSuccess(false).setInfo(e.getInfo());
        }
    }*/

    @RequestMapping(ControllerConsts.URL_STORE)
    @ResponseBody
    public Ret store(FinDzDaily dzDaily) throws UnsupportedEncodingException {
        SysUser existUser = UserContext.getUser();
        try {
            dzDaily.setStockId(Long.valueOf(existUser.getStockIds()));
            dzDaily.setUserId(existUser.getId());
            dzDaily = this.finDzDailyService.saveOrUpdateDaily(dzDaily);
        } catch (BisException e) {
            return Ret.me().setSuccess(false).setInfo(e.getInfo());
        }
        return Ret.me().setData(dzDaily);
    }

    @RequestMapping(ControllerConsts.URL_DELETE)
    @ResponseBody
    public Ret delete(Long id) {
        return this.finStockDailyService.deleteById(id);
    }

    @RequestMapping("/children")
    @ResponseBody
    public Page<FinStockDaily> getChildren(Long stockId, Long dateTime) {
        Page<FinStockDaily> page = new Page<FinStockDaily>();
        // 进出账明细
        List<FinStockDaily> rows = this.finStockDailyService.getChildren(stockId, dateTime);
        page.setRows(rows);

        double incomeTotal = 0;
        for (FinStockDaily row : rows) {
            if (row.getIncome() != null) {
                incomeTotal += row.getIncome();
            }
        }
        Map<String, Object> footer = new HashMap<String, Object>();
        footer.put("incomeTotal", incomeTotal);
        page.setFooter(footer);
        return page;
    }

}
