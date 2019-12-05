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

    /*public static  Map<String, Object> getShunts(Long id) {
        Map<String, Object> shunts = new HashMap<>();
        List<BisStockShunt> bisStockShunts = bisStockShuntService.getList(id);
        if (bisStockShunts != null && bisStockShunts.size() > 0) {
            for (BisStockShunt stockShunt : bisStockShunts) {
                if (BisStockShuntService.DEFAULT_STOCK.equals(String.valueOf(stockShunt.getStockId()))) {
                    shunts.put("总仓库存", stockShunt.getAmount());
                } else {
                    shunts.put("总仓库存", 0);
                }
                if (BisStockShuntService.NORTH_STOCK.equals(String.valueOf(stockShunt.getStockId()))) {
                    shunts.put("北仓库存", stockShunt.getAmount());
                } else {
                    shunts.put("北仓库存", 0);
                }
                if (BisStockShuntService.SOUTH_STOCK.equals(String.valueOf(stockShunt.getStockId()))) {
                    shunts.put("南仓库存", stockShunt.getAmount());
                } else {
                    shunts.put("南仓库存", 0);
                }
            }
        } else {
            shunts.put("总仓库存", 0);
            shunts.put("北仓库存", 0);
            shunts.put("南仓库存", 0);
        }
        return shunts;
    }*/

    public static List<BisStockShunt> getShunts(Long id) {
        return bisStockShuntService.getList(id);
    }

    public static Integer getSkuStock(Long skuId, Long orderId) {
        return bisStockShuntService.getSkuStockByOrderId(skuId, orderId);
    }

}
