package cn.com.consts.template;

import cn.com.consts.Composite;
import cn.com.consts.Template;

/**
 * 面板
 */
public class Panel extends Template {

    @Override
    public String build() {
        StringBuffer sb = new StringBuffer();

        for (Object o : children) {
            sb.append(((Composite) o).build());
        }
        return sb.toString();
    }

}
