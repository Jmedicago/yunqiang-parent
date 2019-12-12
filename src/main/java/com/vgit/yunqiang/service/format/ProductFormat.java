package com.vgit.yunqiang.service.format;

import com.vgit.yunqiang.pojo.BisProduct;
import com.vgit.yunqiang.service.BisProductService;
import com.vgit.yunqiang.service.BisSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductFormat {

    private static BisProductService bisProductService;


    @Autowired
    public void setBisProductService(BisProductService bisProductService) {
        ProductFormat.bisProductService = bisProductService;
    }


}
