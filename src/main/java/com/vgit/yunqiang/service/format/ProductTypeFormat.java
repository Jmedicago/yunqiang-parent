package com.vgit.yunqiang.service.format;

import com.vgit.yunqiang.pojo.BisProductType;
import com.vgit.yunqiang.service.BisProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductTypeFormat {

    private static BisProductTypeService bisProductTypeService;

    @Autowired
    public void setBisProductTypeService(BisProductTypeService bisProductTypeService) {
        ProductTypeFormat.bisProductTypeService = bisProductTypeService;
    }

    public static String getProductTypeName(Long id) {
        BisProductType bisProductType = bisProductTypeService.get(id);
        if (bisProductType != null) {
            return bisProductType.getName();
        } else {
            return "unknown";
        }
    }
}
