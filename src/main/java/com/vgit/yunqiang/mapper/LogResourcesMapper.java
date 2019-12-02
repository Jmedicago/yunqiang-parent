package com.vgit.yunqiang.mapper;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.pojo.LogResources;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogResourcesMapper extends BaseMapper<LogResources> {

    /**
     * 根据文件名查找
     *
     * @param oldFileName
     * @return
     */
    List<LogResources> findByFileName(@Param("oldFileName") String oldFileName);


}
