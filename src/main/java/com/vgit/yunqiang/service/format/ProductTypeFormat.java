package com.vgit.yunqiang.service.format;

import com.vgit.yunqiang.pojo.BisProductType;
import com.vgit.yunqiang.service.BisProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductTypeFormat {

    private static BisProductTypeService bisProductTypeService;

    @Autowired
    public void setBisProductTypeService(BisProductTypeService bisProductTypeService) {
        ProductTypeFormat.bisProductTypeService = bisProductTypeService;
    }

    public static String getProductTypeName(Long id) {
        if (id == null) {
            return "unknown";
        }

        BisProductType bisProductType = bisProductTypeService.get(id);
        if (bisProductType != null) {
            return bisProductType.getName();
        } else {
            return "unknown";
        }
    }

    public static String getProductTypePath(Long id) {
        if (id == null) {
            return "unknown";
        }

        BisProductType bisProductType = bisProductTypeService.get(id);
        if (bisProductType != null) {
            StringBuffer sb = new StringBuffer();

            String[] paths = bisProductType.getPath().split("\\.");
            List<String> pathList = Arrays.asList(paths);
            //pathList.remove(0);

            for (int i = 2; i < pathList.size(); i++) {
                BisProductType type = bisProductTypeService.get(Long.valueOf(pathList.get(i)));
                sb.append(type.getName());
                sb.append("\\");
            }

            return sb.toString().substring(0, sb.length() - 1);
        } else {
            return "unknown";
        }
    }
}
