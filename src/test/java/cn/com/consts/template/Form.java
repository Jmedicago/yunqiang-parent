package cn.com.consts.template;

import cn.com.consts.Composite;
import cn.com.consts.Tags;
import cn.com.consts.Template;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * 表单
 */
public class Form extends Template {

    private String action;

    private String method;

    public Form(String action, String method) {
        this.action = action;
        this.method = method;
    }

    @Override
    public Document build() {
        Document document = DocumentHelper.createDocument();
        Element formElement = document.addElement("form");
        formElement.addAttribute("action", this.action);
        formElement.addAttribute("method", this.method);

        for (Object o : children) {
            Document childDocument = ((Composite) o).build();
            formElement.add(childDocument.getRootElement());
        }

        Element buttonGroups = formElement.addElement(Tags.DIV);
        buttonGroups.addAttribute("class", "form-group");
        Element submitButton = buttonGroups.addElement(Tags.BUTTON);
        submitButton.addAttribute("type", "submit");
        submitButton.setText("提交");
        return document;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setMethod(String method) {
        this.method = method;
    }

}
