package com.vgit.yunqiang.service.format;

import com.vgit.yunqiang.pojo.BisProductMedia;
import com.vgit.yunqiang.service.BisProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductMediaFormat {

    private static BisProductService bisProductService;

    @Autowired
    public void setBisProductService(BisProductService bisProductService) {
        ProductMediaFormat.bisProductService = bisProductService;
    }

    public static String getProductImage(Long id) {
        if (id == null) {
            return "unknown";
        }

        List<BisProductMedia> productMediaList = bisProductService.getProductMedias(id);
        if (productMediaList != null && productMediaList.size() > 0) {
            return productMediaList.get(0).getResource();
        }
        return "unknown";
    }
}
