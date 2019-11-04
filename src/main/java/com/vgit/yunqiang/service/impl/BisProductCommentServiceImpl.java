package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.impl.BaseServiceImpl;
import com.vgit.yunqiang.mapper.BisProductCommentMapper;
import com.vgit.yunqiang.pojo.BisProductComment;
import com.vgit.yunqiang.service.BisProductCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BisProductCommentServiceImpl extends BaseServiceImpl<BisProductComment> implements BisProductCommentService {
    @Autowired
    private BisProductCommentMapper mapper;

    @Override
    protected BaseMapper<BisProductComment> getMapper() {
        return this.mapper;
    }

    @Override
    public List<BisProductComment> getByProductId(Long productId) {
        return this.mapper.getByProductId(productId);
    }
}
