package cn.com.consts.template;

import cn.com.consts.Composite;
import cn.com.consts.Template;

/**
 * 表单
 */
public class Form extends Template {

    private String action;

    @Override
    public String build() {
        StringBuffer sb = new StringBuffer();
        sb.append("<form role=\"form\" action=\"" + this.action + "\">");
        for (Object o : children) {
            sb.append(((Composite) o).build());
        }
        sb.append("</form>");
        return sb.toString();
    }

    public void setAction(String action) {
        this.action = action;
    }

}
