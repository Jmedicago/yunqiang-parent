package com.vgit.yunqiang.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.vgit.yunqiang.common.consts.ICodes;
import com.vgit.yunqiang.common.service.TreeGridService;
import com.vgit.yunqiang.common.service.impl.TreeGridServiceImpl;
import com.vgit.yunqiang.common.utils.Ret;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.mapper.BisProductTypeMapper;
import com.vgit.yunqiang.pojo.BisProductType;
import com.vgit.yunqiang.service.BisProductTypeService;

@Service
public class BisProductTypeServiceImpl extends TreeGridServiceImpl<BisProductType> implements BisProductTypeService {

    @Autowired
    private BisProductTypeMapper mapper;

    @Override
    protected BaseMapper<BisProductType> getMapper() {
        // TODO Auto-generated method stub
        return this.mapper;
    }

    @Override
    public BisProductType saveOrUpdateProductType(BisProductType productType) {
        if (productType.getId() == null) {
            productType.setSort(100);
            this.mapper.savePart(productType);
            this.handleSave(productType);
            this.mapper.updatePart(productType);
        } else {
            this.handleSave(productType);
            this.mapper.updatePart(productType);
        }
        return productType;
    }

    @Override
    public Ret deleteById(Long id) {
        Integer count = this.mapper.delBeforeCheck(id);
        if (count != null && count > 0) {
            return Ret.me().setSuccess(false).setInfo("该类目已关联商品，不能删除！");
        }

        BisProductType productType = this.mapper.get(id);
        if (productType != null && productType.getParentId() == TreeGridService.ROOT) {
            return Ret.me().setSuccess(false).setCode(ICodes.NOT_AUTHORIZED);
        }
        this.delByRoot(id); // 删除该节点及所有子节点
        return Ret.me().setSuccess(true).setCode(ICodes.SUCCESS);
    }

    @Override
    public BisProductType getProductTypeByNameAndParentId(String name, Long parentId) {
        return this.mapper.getProductTypeByNameAndParentId(name, parentId);
    }

    @Override
    public String getProductTypeByProductId(Long productId) {
        BisProductType bisProductType = this.mapper.getProductTypeByProductId(productId);
        if (bisProductType != null) {
            StringBuffer sb = new StringBuffer();

            String[] paths = bisProductType.getPath().split("\\.");
            List<String> pathList = Arrays.asList(paths);
            //pathList.remove(0);

            for (int i = 2; i < pathList.size(); i++) {
                BisProductType type = this.get(Long.valueOf(pathList.get(i)));
                sb.append(type.getName());
                sb.append("\\");
            }

            return sb.toString().substring(0, sb.length() - 1);
        } else {
            return "";
        }
    }

    /**
     * 存储修改分类前进行预处理数据
     *
     * @param o
     */
    private void handleSave(BisProductType o) {
        Long id = o.getId();
        Long pid = o.getParentId();
        String path = "";
        if (pid == null || 0 == pid) { // 一级分类
            path = "." + id + ".";
        } else { // 子类
            BisProductType parentType = this.get(pid);
            path = parentType.getPath() + id + ".";
        }
        o.setPath(path);
    }

}
