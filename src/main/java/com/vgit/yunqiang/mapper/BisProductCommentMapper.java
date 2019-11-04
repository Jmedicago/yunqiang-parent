package com.vgit.yunqiang.mapper;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.pojo.BisProductComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BisProductCommentMapper extends BaseMapper<BisProductComment> {

    /**
     * 产看商品评论
     *
     * @param productId
     * @return
     */
    List<BisProductComment> getByProductId(@Param("productId") Long productId);
}
