package cn.com.consts.template;

import cn.com.consts.Composite;
import cn.com.consts.Template;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * 表单
 */
public class Form extends Template {

    private String action;

    @Override
    public Document build() {
        Document document = DocumentHelper.createDocument();
        Element formElement = document.addElement("form");
        formElement.addAttribute("action", this.action);

        for (Object o : children) {
            Document childDocument = ((Composite) o).build();
            formElement.add(childDocument.getRootElement());
        }
        return document;
    }

    public void setAction(String action) {
        this.action = action;
    }

}
