package com.vgit.yunqiang.service;

import com.vgit.yunqiang.common.service.BaseService;
import com.vgit.yunqiang.pojo.LogResources;

public interface LogResourceService extends BaseService<LogResources> {

    /**
     * 检查是否上传过
     *
     * @param fileName
     * @return
     */
    boolean exist(String fileName);

    /**
     * 删除上传文件
     *
     * @param excelUrl
     */
    void deleteByUrl(String excelUrl);
}
