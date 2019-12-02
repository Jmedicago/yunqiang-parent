package com.vgit.yunqiang.mapper;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.pojo.LogProductBatch;

import java.util.List;

public interface LogProductBatchMapper extends BaseMapper<LogProductBatch> {

    /**
     * 根据文件名查找
     *
     * @param fileName
     * @return
     */
    List<LogProductBatch> findByFileName(String fileName);
}
