package cn.com.consts.template;

import cn.com.consts.Composite;
import cn.com.consts.Tags;
import cn.com.consts.Template;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * 面板
 */
public class Body extends Template {

    @Override
    public Document build() {
        Document document = DocumentHelper.createDocument();
        Element bodyElement = document.addElement(Tags.BODY);

        for (Object o : children) {
            Document childDocument = ((Composite) o).build();
            bodyElement.add(childDocument.getRootElement());
        }
        return document;
    }

}
