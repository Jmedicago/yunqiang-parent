package com.vgit.yunqiang.controller;

import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.controller.consts.ControllerConsts;
import com.vgit.yunqiang.pojo.BisOrderDetail;
import com.vgit.yunqiang.pojo.BisProductComment;
import com.vgit.yunqiang.service.BisOrderDetailService;
import com.vgit.yunqiang.service.BisOrderService;
import com.vgit.yunqiang.service.BisProductCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("/" + BisProductCommentController.DOMAIN)
public class BisProductCommentController {
    public static final String DOMAIN = "product-comment";

    @Autowired
    private BisProductCommentService bisProductCommentService;

    @Autowired
    private BisOrderDetailService bisOrderDetailService;

    @Autowired
    private BisOrderService bisOrderService;

    @RequestMapping("/index")
    public String index(Long orderId, Model model) {
        model.addAttribute("orderId", orderId);
        return DOMAIN + ControllerConsts.VIEW_INDEX;
    }

    @RequestMapping(ControllerConsts.URL_EDIT)
    public String edit(Long orderId, Long productId, Long detailId, Model model) {
        BisProductComment productComment = new BisProductComment();
        productComment.setOrderId(orderId);
        productComment.setProductId(productId);
        model.addAttribute("productComment", productComment);
        model.addAttribute("detailId", detailId);
        return DOMAIN + ControllerConsts.VIEW_EDIT;
    }

    /**
     * 保存评价
     *
     * @param productComment
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(ControllerConsts.URL_STORE)
    @ResponseBody
    public Ret store(BisProductComment productComment, Long detailId) throws UnsupportedEncodingException {
        productComment.setCreateTime(System.currentTimeMillis());
        this.bisProductCommentService.savePart(productComment);
        // TODO.修改订单评价状态
        BisOrderDetail bisOrderDetail = new BisOrderDetail();
        bisOrderDetail.setId(detailId);
        bisOrderDetail.setIsComment(1);
        this.bisOrderDetailService.updatePart(bisOrderDetail);
        // 检查订单评价状态
        this.bisOrderService.hasNotComment(productComment.getOrderId());
        return Ret.me();
    }

    @RequestMapping(ControllerConsts.URL_SHOW)
    public String view(Long productId, Model model) {
        List<BisProductComment> productComments = this.bisProductCommentService.getByProductId(productId);
        model.addAttribute("productComments", productComments);
        return DOMAIN + ControllerConsts.VIEW_SHOW;
    }

}
