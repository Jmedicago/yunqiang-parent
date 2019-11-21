package com.vgit.yunqiang.service.format;

import com.vgit.yunqiang.pojo.BisStock;
import com.vgit.yunqiang.service.BisStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockFormat {

    private static BisStockService bisStockService;

    @Autowired
    public void setBisStockService(BisStockService bisStockService) {
        StockFormat.bisStockService = bisStockService;
    }

    public static String getStockName(Long id) {
        BisStock bisStock = bisStockService.get(id);
        if (bisStock != null) {
            return bisStock.getName();
        }
        return "unknown";
    }
}
