package com.vgit.yunqiang.controller;

import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.controller.utils.UserContext;
import com.vgit.yunqiang.pojo.BisOrder;
import com.vgit.yunqiang.pojo.SysUser;
import com.vgit.yunqiang.service.BisOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/" + BisOrderController.DOMAIN)
public class BisOrderController {

    public static final String DOMAIN = "order";

    @Autowired
    private BisOrderService bisOrderService;

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

}
