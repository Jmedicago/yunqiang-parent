package cn.com.consts.component;

import cn.com.consts.Component;
import cn.com.consts.Tags;
import cn.com.consts.template.Body;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * 窗体
 */
public class Html extends Component {

    private Head head;

    private Body body;

    @Override
    public Document build() {
        Document document = DocumentHelper.createDocument();

        Element htmlElement = document.addElement(Tags.HTML);
        htmlElement.add(this.head.build().getRootElement());
        htmlElement.add(this.body.build().getRootElement());
        return document;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public void setBody(Body body) {
        this.body = body;
    }

}
