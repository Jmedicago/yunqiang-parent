package cn.com.consts.component;

import cn.com.consts.Component;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

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
    public Document build() {
        Document document = DocumentHelper.createDocument();
        Element textBoxElement = document.addElement("div");
        textBoxElement.addAttribute("class", "form-group");

        Element labelElement = textBoxElement.addElement("label");
        labelElement.addAttribute("for", this.name);
        labelElement.setText(this.label);

        Element inputElement = textBoxElement.addElement("input");
        inputElement.addAttribute("type", "text");
        inputElement.addAttribute("class", "form-control");
        inputElement.addAttribute("id", this.name);
        inputElement.addAttribute("placeholder", this.placeholder);

        return document;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

}
