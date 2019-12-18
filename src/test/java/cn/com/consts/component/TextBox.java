package cn.com.consts.component;

import cn.com.consts.Component;

/**
 * 文本框
 */
public class TextBox extends Component {

    private String name;

    private String label;

    private String placeholder;

    public TextBox(String name) {
        this.name = name;
    }

    @Override
    public String build() {
        StringBuffer sb = new StringBuffer();
        sb.append("<div class=\"form-group\">");
        sb.append("<label for=\"" + this.name + "\">" + this.label + "</label>");
        sb.append("<input type=\"text\" class=\"form-control\" id=\"" + this.name + "\" placeholder=\"" + this.placeholder + "\">");
        sb.append("</div>");
        return sb.toString();
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

}
