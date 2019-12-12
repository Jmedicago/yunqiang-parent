package com.vgit.yunqiang.service.format;

import com.vgit.yunqiang.pojo.BisSku;
import com.vgit.yunqiang.service.BisSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkuFormat {

    private static BisSkuService bisSkuService;

    @Autowired
    public void setBisSkuService(BisSkuService bisSkuService) {
        SkuFormat.bisSkuService = bisSkuService;
    }

    public static String getAvailableStock(Long skuId) {
        BisSku sku = bisSkuService.get(skuId);
        if (sku != null) {
            return String.valueOf(sku.getAvailableStock());
        }
        return "0";
    }

    public static Long getProductType(Long skuId) {
        if (skuId != null) {
            return bisSkuService.getProductType(skuId);
        }
        return null;
    }

    public static BisSku get(Long skuId) {
        return bisSkuService.get(skuId);
    }
}
