package com.vgit.yunqiang.service.format;

import com.vgit.yunqiang.common.utils.StrUtils;
import org.apache.commons.lang3.StringUtils;

public class PropertyFormat {

    public static String formatterProperties(String properties){
        if (StringUtils.isBlank(properties)) {
            return "";
        }
        return StrUtils.convertPropertiesToHtml(properties);
    }

}
