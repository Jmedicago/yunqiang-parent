package com.vgit.yunqiang.service.format;

import com.vgit.yunqiang.pojo.BisProductType;
import com.vgit.yunqiang.pojo.BisStock;
import com.vgit.yunqiang.service.BisStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class StockFormat {

    private static BisStockService bisStockService;

    @Autowired
    public void setBisStockService(BisStockService bisStockService) {
        StockFormat.bisStockService = bisStockService;
    }

    public static String getStockName(Long id) {
        if (id == null) {
            return "unknown";
        }

        BisStock bisStock = bisStockService.get(id);
        if (bisStock != null) {
            return bisStock.getName();
        }
        return "unknown";
    }

    public static String getStockPath(Long id) {
        if (id == null) {
            return "";
        }

        BisStock bisStock = bisStockService.get(id);
        if (bisStock != null) {
            StringBuffer sb = new StringBuffer();

            String[] paths = bisStock.getPath().split("\\.");
            List<String> pathList = Arrays.asList(paths);
            //pathList.remove(0);

            for (int i = 2; i < pathList.size(); i++) {
                BisStock type = bisStockService.get(Long.valueOf(pathList.get(i)));
                sb.append(type.getName());
                sb.append("\\");
            }

            if (sb.length() > 0) {
                return sb.toString().substring(0, sb.length() - 1);
            } else {
                return "总仓";
            }

            //return sb.toString();
        } else {
            return "";
        }
    }
}
