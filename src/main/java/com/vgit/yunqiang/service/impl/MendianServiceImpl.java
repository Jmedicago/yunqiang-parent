package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.impl.BaseServiceImpl;
import com.vgit.yunqiang.mapper.MendianMapper;
import com.vgit.yunqiang.model.MendianModel;
import com.vgit.yunqiang.pojo.Mendian;
import com.vgit.yunqiang.service.MendianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MendianServiceImpl extends BaseServiceImpl<Mendian> implements MendianService {

    @Autowired
    private MendianMapper mapper;

    @Override
    protected BaseMapper<Mendian> getMapper() {
        return this.mapper;
    }

    @Override
    public List<MendianModel> treegrid(Long pid) {
        List<MendianModel> models = new ArrayList<MendianModel>();
        List<Mendian> mendians = this.mapper.getByPid(pid);
        if (mendians != null && !mendians.isEmpty()) {
            for (Mendian mendian : mendians) {
                MendianModel model = new MendianModel();
                model.setId(mendian.getId());
                model.setName(mendian.getName());
                model.setPid(mendian.getPid());
                model.setLevel(mendian.getLevel());
                model.setState(mendian.getState());
                model.setCreateTime(mendian.getCreateTime());
                model.setUpdateTime(mendian.getUpdateTime());
                model.setChildren(this.treegrid(mendian.getId()));
                models.add(model);
            }
        } 
        return models;
    }
}
