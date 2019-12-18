package com.vgit.yunqiang.service.format;

import com.vgit.yunqiang.pojo.BisStockShunt;
import com.vgit.yunqiang.service.BisStockShuntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockShuntFormat {

    private static BisStockShuntService bisStockShuntService;

    @Autowired
    public void setBisStockShuntService(BisStockShuntService bisStockShuntService) {
        StockShuntFormat.bisStockShuntService = bisStockShuntService;
    }

    public static Integer getDefaultStock(Long id) {
        BisStockShunt stockShunt = bisStockShuntService.getSkuStock(id, Long.valueOf(BisStockShuntService.DEFAULT_STOCK));
        if (stockShunt == null) {
            return 0;
        }
        return stockShunt.getAmount();
    }

    public static List<BisStockShunt> getShunts(Long id) {
        return bisStockShuntService.getList(id);
    }

    public static Integer getSkuStock(Long skuId, Long orderId) {
        return bisStockShuntService.getSkuStockByOrderId(skuId, orderId);
    }

    public static Integer getNorthStock(Long id) {
        BisStockShunt stockShunt = bisStockShuntService.getSkuStock(id, Long.valueOf(BisStockShuntService.NORTH_STOCK));
        if (stockShunt == null) {
            return 0;
        }
        return stockShunt.getAmount();
    }

    public static Integer getSouthStock(Long id) {
        BisStockShunt stockShunt = bisStockShuntService.getSkuStock(id, Long.valueOf(BisStockShuntService.SOUTH_STOCK));
        if (stockShunt == null) {
            return 0;
        }
        return stockShunt.getAmount();
    }

}
