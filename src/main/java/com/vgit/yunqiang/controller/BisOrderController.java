package com.vgit.yunqiang.controller;

import com.vgit.yunqiang.common.exception.BisException;
import com.vgit.yunqiang.common.query.OrderQuery;
import com.vgit.yunqiang.common.utils.Page;
import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.controller.consts.ControllerConsts;
import com.vgit.yunqiang.controller.utils.UserContext;
import com.vgit.yunqiang.pojo.BisOrder;
import com.vgit.yunqiang.pojo.SysUser;
import com.vgit.yunqiang.service.BisOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/" + BisOrderController.DOMAIN)
public class BisOrderController {

    public static final String DOMAIN = "order";

    @Autowired
    private BisOrderService bisOrderService;

    @RequestMapping(ControllerConsts.URL_INDEX)
    public String index() {
        return DOMAIN + ControllerConsts.VIEW_INDEX;
    }

    @RequestMapping(ControllerConsts.URL_JSON)
    @ResponseBody
    public Page<BisOrder> json(OrderQuery query) {
        query.setStockId(Long.valueOf(UserContext.getUser().getStockIds()));
        return this.bisOrderService.queryPage(query);
    }

    @RequestMapping(ControllerConsts.URL_EDIT)
    public String edit(Long id, Model model) {
        if (id != null) {
            // 订单信息
            BisOrder bisOrder = this.bisOrderService.get(id);
            model.addAttribute("bisOrder", bisOrder);
        }
        return DOMAIN + ControllerConsts.VIEW_EDIT;
    }

    /**
     * 提交订单
     *
     * @param formOrder 地址id
     * @param billId    发票id
     * @return
     */
    @RequestMapping("/submit")
    @ResponseBody
    public Ret orderSubmit(BisOrder formOrder, Long billId) {
        SysUser user = UserContext.getUser();
        formOrder.setUserId(user.getId());
        formOrder.setStockId(Long.valueOf(user.getStockIds()));
        // TODO.订单发票
        // 创建订单
        return this.bisOrderService.create(formOrder, billId);
    }

    @RequestMapping("/confirm-finish")
    @ResponseBody
    public Ret confirmFinish(Long orderId) {
        try {
            this.bisOrderService.confirmFinish(orderId);
        } catch (BisException e) {
            return Ret.me().setSuccess(false).setCode(e.getCode());
        }
        return Ret.me();
    }

    @RequestMapping(ControllerConsts.URL_SHOW)
    public String show(Long id, Model model) {
        if (id != null) {
            // 订单信息
            BisOrder bisOrder = this.bisOrderService.get(id);
            model.addAttribute("bisOrder", bisOrder);
        }
        return DOMAIN + ControllerConsts.VIEW_SHOW;
    }

    /*@RequestMapping("/show")
    public String show(Integer o,  Long id, Model model) {
        if (id != null) {
            // 订单信息
            BisOrder bisOrder = this.bisOrderService.get(id);
            model.addAttribute("bisOrder", bisOrder);
        }
        model.addAttribute("o", o);
        return DOMAIN + "/show";
    }*/

    @RequestMapping(ControllerConsts.URL_DELETE)
    @ResponseBody
    public Ret delete(Long id) {
        BisOrder bisOrder = this.bisOrderService.get(id);
        if (bisOrder.getStatus() == 0) {
            this.bisOrderService.delete(id);
            return Ret.me();
        } else {
            return Ret.me().setSuccess(false).setInfo("订单不能删除");
        }
    }


}
