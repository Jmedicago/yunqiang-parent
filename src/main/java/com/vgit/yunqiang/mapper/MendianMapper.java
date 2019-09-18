package com.vgit.yunqiang.mapper;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.pojo.Mendian;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MendianMapper extends BaseMapper<Mendian> {

    List<Mendian> getByPid(@Param("pid") Long pid);

}
