package com.vgit.yunqiang.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vgit.yunqiang.common.query.ProductTypeQuery;
import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.controller.consts.ControllerConsts;
import com.vgit.yunqiang.pojo.BisProductType;
import com.vgit.yunqiang.service.BisProductTypeService;
import com.vgit.yunqiang.service.BisStockService;

@Controller
@RequestMapping("/" + BisProductTypeController.DOMAIN)
public class BisProductTypeController {

	public static final String DOMAIN = "product-type";

	@Autowired
	private BisProductTypeService bisProductTypeService;

	@RequestMapping(ControllerConsts.URL_INDEX)
	public String index() {
		return DOMAIN + ControllerConsts.VIEW_INDEX;
	}

	@RequiresRoles(value = { "admin", "product" }, logical = Logical.OR)
	@RequestMapping(ControllerConsts.URL_JSON)
	@ResponseBody
	public List<BisProductType> json(ProductTypeQuery query) {
		return this.bisProductTypeService.treegrid(BisStockService.ROOT, query);
	}
	
	@RequestMapping(ControllerConsts.URL_EDIT)
    public String edit(Long id, Model model) {
        if (id != null) {
            // 商品类型信息
            BisProductType bisProductType = this.bisProductTypeService.get(id);
            model.addAttribute("bisProductType", bisProductType);
        }
        return DOMAIN + ControllerConsts.VIEW_EDIT;
    }

    /**
     * 创建商品类别
     *
     * @param productType
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequiresRoles(value = {"admin", "product"}, logical = Logical.OR)
    @RequestMapping(ControllerConsts.URL_STORE)
    @ResponseBody
    public Ret store(BisProductType productType) throws UnsupportedEncodingException {
        // 更新商品类别信息
        this.bisProductTypeService.saveOrUpdateProductType(productType);
        return Ret.me();
    }

    @RequiresRoles(value = {"admin", "product"}, logical = Logical.OR)
    @RequestMapping(ControllerConsts.URL_DELETE)
    @ResponseBody
    public Ret delete(Long id) {
        return this.bisProductTypeService.deleteById(id);
    }

}
