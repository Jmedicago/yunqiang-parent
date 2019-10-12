package com.vgit.yunqiang.controller;

import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.common.utils.StrUtils;
import com.vgit.yunqiang.controller.consts.ControllerConsts;
import com.vgit.yunqiang.controller.utils.UserContext;
import com.vgit.yunqiang.pojo.BisCart;
import com.vgit.yunqiang.pojo.BisSku;
import com.vgit.yunqiang.pojo.SysUser;
import com.vgit.yunqiang.service.BisCartService;
import com.vgit.yunqiang.service.BisSkuService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/" + BisCartController.DOMAIN)
public class BisCartController {

    public static final String DOMAIN = "cart";

    @Autowired
    private BisCartService bisCartService;

    @Autowired
    private BisSkuService bisSkuService;

    @RequestMapping(ControllerConsts.URL_INDEX)
    public String index() {
        return DOMAIN + ControllerConsts.VIEW_INDEX;
    }

    @RequestMapping(ControllerConsts.URL_JSON)
    @ResponseBody
    public Map<String, Object> json() {
        SysUser existUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        return this.bisCartService.info(existUser.getId());
    }

    /**
     * 添加SKU到购物车
     *
     * @param skuIds skuIds
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public Ret cartAdd(String skuIds) {
        this.bisCartService.add(UserContext.getUserId(), StrUtils.splitToLong(skuIds, ","));
        return Ret.me();
    }

    @RequestMapping("/addAmount")
    @ResponseBody
    public Ret cartAddAmount(Long cartId, Integer number) {
        SysUser existUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        BisCart cart = this.bisCartService.get(cartId);
        // 获取商品总库存
        BisSku sku = this.bisSkuService.get(cart.getSkuId());
        if (number > sku.getAvailableStock()) {
            return Ret.me().setSuccess(false).setInfo("商品库存不足！");
        }
        BisSku bisSku = this.bisCartService.changeNumber(existUser.getId(), cartId, number);
        Map<String, Object> calResultMap = this.bisCartService.calculate(existUser.getId());
        Double marketPrice = bisSku.getMarketPrice();
        Double costPrice = bisSku.getCostPrice();
        Double total = costPrice * number;
        calResultMap.put("marketPrice", marketPrice);
        calResultMap.put("price", costPrice);
        calResultMap.put("subTotal", total);
        return Ret.me().setData(calResultMap);
    }

    @RequestMapping("/removeAmount")
    @ResponseBody
    public Ret cartRemoveAmount(Long cartId, Integer number) {
        SysUser existUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        if (number < 1) {
            return Ret.me().setSuccess(false).setInfo("已减到最小数量！");
        }
        BisSku bisSku = this.bisCartService.changeNumber(existUser.getId(), cartId, number);
        Map<String, Object> calResultMap = this.bisCartService.calculate(existUser.getId());
        Double marketPrice = bisSku.getMarketPrice();
        Double costPrice = bisSku.getCostPrice();
        Double total = costPrice * number;
        calResultMap.put("marketPrice", marketPrice);
        calResultMap.put("price", costPrice);
        calResultMap.put("subTotal", total);
        return Ret.me().setData(calResultMap);
    }

    /**
     * 修改购物车中商品的数量
     *
     * @param cartId
     * @param number
     * @return
     */
    @RequestMapping("/changeNumber")
    @ResponseBody
    public Ret changeCartNumber(Long cartId, Integer number) {
        SysUser existUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        BisSku sku = this.bisCartService.changeNumber(existUser.getId(), cartId, number);
        Map<String, Object> calResultMap = this.bisCartService.calculate(existUser.getId());
        Double marketPrice = sku.getMarketPrice();
        Double costPrice = sku.getCostPrice();
        Double total = costPrice * number;
        calResultMap.put("marketPrice", marketPrice);
        calResultMap.put("price", costPrice);
        calResultMap.put("subTotal", total);
        return Ret.me().setData(calResultMap);
    }

    @RequestMapping("/changeSelected")
    @ResponseBody
    public Ret changeSelected(Long cartId, Integer selected) {
        this.bisCartService.changeSelected(UserContext.getUserId(), cartId, selected);
        return Ret.me();
    }

    @RequestMapping("/remove")
    @ResponseBody
    public Ret cartRemove(String cartIds) {
        this.bisCartService.del(UserContext.getUserId(), StrUtils.splitToLong(cartIds));
        return Ret.me();
    }

}
