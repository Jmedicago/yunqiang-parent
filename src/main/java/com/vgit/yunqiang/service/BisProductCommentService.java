package com.vgit.yunqiang.service;

import com.vgit.yunqiang.common.service.BaseService;
import com.vgit.yunqiang.pojo.BisProductComment;

import java.util.List;

public interface BisProductCommentService extends BaseService<BisProductComment> {

    /**
     * 查看商品评价
     *
     * @param productId
     * @return
     */
    List<BisProductComment> getByProductId(Long productId);
}
